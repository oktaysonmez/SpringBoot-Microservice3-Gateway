package com.sha.gateway.security.jwt;

import com.sha.gateway.security.model.UserPrinciple;
import com.sha.gateway.utility.Util;
import org.springframework.security.core.Authentication;

import javax.servlet.http.HttpServletRequest;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;

public interface JWTProvidable
{
    // JWT Temel Mantik seklindeki 3. asama Bearer
    String HEADER_STRING = "Authorization";
    String TOKEN_PREFIX = "Bearer";
    int TOKEN_START_DIGIT = 7;

    default KeyFactory getKeyFactory() throws NoSuchAlgorithmException
    {
        return KeyFactory.getInstance("RSA");
    }
    /**
     *
     * @param request
     *      cozumlenip token uretilecek istek
     *
     * @return
     *      son kullanma tarihi gecmisse false
     *      aksi halde true
     *
     * Detay:
     * Uretilen token'dan kullanici detaylari alinabilir (Claims nesnesi)
     * (Claims nesnesi key-value seklinde bilgi icerir.)
     */
    boolean isValidToken(HttpServletRequest request);

    /**
     *
     * @param request
     *          cozumlenip token uretilecek istek
     *
     * @return
     *      kullanici ad, id ve rol bilgilerini iceren Authentication nesnesi
     *
     * Detay:
     *      Uretilen token'dan kullanici detaylari alinabilir. (key-value seklinde - Claims)
     *      Claims uzerinden kullanici ad, id ve roller bilgisi cekilebilir.
     *      Bu bilgilerle, UsernamePasswordAuthenticationToken nesnesi uretilebilir.
     */
    Authentication getAuthentication(HttpServletRequest request);


    /**
     @param authentication
        oturum acma isleminden sonra olusturulan UserPrinciple nesnesi

     @return
        Kimligi dogrulanmis kullanicilarin kimlik bilgilerini (credentials)
        ve rollerini iceren String
     */
    String generateToken(UserPrinciple authentication);
}
