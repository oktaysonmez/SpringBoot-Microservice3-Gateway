package com.sha.gateway.security.utility;

import com.sha.gateway.security.jwt.JWTAuthorizationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration //iceride bean tanimlanacak
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter
{
    @Autowired
    private UserDetailsService userDetailsService;

    @Bean
    JWTAuthorizationFilter createJWTAuthorizationFilter()
    {
        return new JWTAuthorizationFilter();
    }


    /*
        kaynaklar arasi paylasim icin
        CORS (cross origin resource sharing) politikasi

        CORS konfigurasyonu ile
            izin verilen kaynaklar,
            izin verilen metotlar,
            izin verilen yollari belirleyecegiz.

        ornegin: Burasi sayesinde, uygulamaya yalnizca get istegi izni evrilebilir.
                 Belirli bir alan adi varsa, postlar yalnizca ona gore kisitlanabilir.

        CORS tarafindan bir istege izin verilmiyorsa istek engellenir.

        Bu bi Java bean(bkz. @Bean) oldugu icin,
        bundan yeni nesneler olusturulup tum uygulama bazinda kullanilabilir.


     */
    @Bean
    public WebMvcConfigurer corsConfigurer()
    {
        return new WebMvcConfigurer()
        {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")
                        .allowedOrigins("*") //ornek: localhost
                        .allowedMethods("*"); //ornek: POST, GET etc.

            }
        };
    }

    @Override
    @Bean(BeanIds.AUTHENTICATION_MANAGER)
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }



    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception
    {
        httpSecurity.csrf().disable();

        /*
            SessionCreationPolicy.ALWAYS      -> session yoksaa mutlaka olustur
            SessionCreationPolicy.NEWER       -> framework, yeni bir session hic bir zaman olusturmaz.
                                                 ne var ki, eger hali hazirda varsa onu kullanir
            SessionCreationPolicy.IF_REQUIRED -> gerekliysee oturum olusturur. (varsayilan bu)
            SessionCreationPolicy.STATELESS   -> framework, yeni bir session hic bir zaman olusturmaz ve kullanmaz
         */
        httpSecurity.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        /*
            istemciler, web sunucusu tarafindan desteklenen istekleri ogrenmek icin
            OPTION istegi yollar.
         */
        httpSecurity.authorizeRequests()
                .antMatchers(HttpMethod.OPTIONS).permitAll()
                .antMatchers("/api/authentication/**").permitAll()
                .anyRequest().authenticated();

        httpSecurity.addFilterBefore(createJWTAuthorizationFilter(), UsernamePasswordAuthenticationFilter.class);
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception
    {
        auth.userDetailsService(userDetailsService).passwordEncoder(createPasswordEncoder());
    }

    @Bean
    public PasswordEncoder createPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
