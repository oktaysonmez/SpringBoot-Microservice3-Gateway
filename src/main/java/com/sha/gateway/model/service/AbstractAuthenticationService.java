package com.sha.gateway.model.service;

import com.sha.gateway.model.entity.User;
import com.sha.gateway.security.jwt.JWTProvidable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;

public abstract class AbstractAuthenticationService
{
    @Autowired
    protected JWTProvidable providable;

    @Autowired
    protected AuthenticationManager authenticationManager;

    public abstract String signInAndReturnJWT(User signInUser);
}
