package com.couchtalks.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.log4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by NShkarupa on 23.08.2017.
 */
public class LoginFailureHandler extends SimpleUrlAuthenticationFailureHandler {
    private static final Logger log = (Logger) LoggerFactory.getLogger(LoginFailureHandler.class);

    @Autowired
    private ObjectMapper customObjectMapper;

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        super.onAuthenticationFailure(request, response, exception);
        if(exception.getClass().isAssignableFrom(UsernameNotFoundException.class)) {
            response.sendError(1,"BAD_CREDENTIAL");
        } else if (exception.getClass().isAssignableFrom(DisabledException.class)) {
            response.sendError(1,"USER_DISABLED");
        }
    }

}