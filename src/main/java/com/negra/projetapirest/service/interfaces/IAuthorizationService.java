package com.negra.projetapirest.service.interfaces;

import com.negra.projetapirest.exception.AuthorizationException;

public interface IAuthorizationService {

    void verifyAuthorization(String authorization) throws AuthorizationException;

}
