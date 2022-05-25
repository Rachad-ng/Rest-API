package com.negra.projetapirest.filter;

import com.negra.projetapirest.exception.AuthorizationException;
import com.negra.projetapirest.service.interfaces.IAuthorizationService;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@AllArgsConstructor
@Component
public class AuthorizationFilter implements Filter {

    private IAuthorizationService authorizationService;
    private static final Logger LOGGER = LoggerFactory.getLogger(AuthorizationFilter.class);

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse res = (HttpServletResponse) servletResponse;

        String authorization = req.getHeader("Authorization");

        try{
            authorizationService.verifyAuthorization(authorization);

            filterChain.doFilter(req, res);

        }catch (AuthorizationException e){
            LOGGER.error(e.getMessage());

            res.setStatus(401);

        }

    }

}
