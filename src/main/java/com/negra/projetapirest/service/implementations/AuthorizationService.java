package com.negra.projetapirest.service.implementations;

import com.negra.projetapirest.exception.AuthorizationException;
import com.negra.projetapirest.service.interfaces.IAuthorizationService;
import org.springframework.stereotype.Service;

@Service
public class AuthorizationService implements IAuthorizationService {

    private static final String ErrorMessage = "Le login et/ou le mot de passe n'est pas correcte";

    @Override
    public void verifyAuthorization(String authorization) throws AuthorizationException {

        if(!authorization.equals("rachad:hello"))
            throw new AuthorizationException(ErrorMessage);

    }

}
