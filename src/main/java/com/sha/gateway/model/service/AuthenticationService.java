package com.sha.gateway.model.service;

import com.sha.gateway.model.entity.User;
import com.sha.gateway.security.model.UserPrinciple;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService extends AbstractAuthenticationService
{

    @Override
    public String signInAndReturnJWT(User signInUser) {
        UsernamePasswordAuthenticationToken token=
                new UsernamePasswordAuthenticationToken(signInUser.getUsername(),signInUser.getPassword());

        Authentication authentication = authenticationManager.authenticate(token);

        UserPrinciple userPrinciple= (UserPrinciple) authentication.getPrincipal();

        return providable.generateToken(userPrinciple);
    }
}
