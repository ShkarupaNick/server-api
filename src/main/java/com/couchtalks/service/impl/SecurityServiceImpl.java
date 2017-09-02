package com.couchtalks.service.impl;

import com.couchtalks.service.SecurityService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.AuditorAware;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

/**
 * Implementation of {@link SecurityService} interface.
 *
 * @author Eugene Suleimanov
 * @version 1.0
 */

@Service
public class SecurityServiceImpl implements SecurityService {

    final static Logger logger = Logger.getLogger(SecurityServiceImpl.class);

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserDetailsService userDetailsService;

    @Override
    public String findLoggedInUsername() {
        Object userDetails = SecurityContextHolder.getContext().getAuthentication().getDetails();
        if (userDetails instanceof UserDetails) {
            return ((UserDetails) userDetails).getUsername();
        }
        return null;
    }

    @Override
    public void autoLogin(String username, String password) {
        logger.info(username + ":" + password);
        UserDetails userDetails = userDetailsService.loadUserByUsername(username);
        if (userDetails==null){
            throw new BadCredentialsException("The username you entered are not correct. Please try again.");
        }
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userDetails, password, userDetails.getAuthorities());
       try {
           authenticationManager.authenticate(authenticationToken);
       } catch (InternalAuthenticationServiceException e){
           throw new BadCredentialsException("The username or password you entered are not correct. Please try again.");
       }
        if (authenticationToken.isAuthenticated()) {
            SecurityContextHolder.getContext().setAuthentication(authenticationToken);
            logger.debug(String.format("Successfully %s auto logged in", username));
        }
    }

    @Override
    public void logout() {
        if (SecurityContextHolder.getContext().getAuthentication().isAuthenticated()) {
            SecurityContextHolder.getContext().getAuthentication().setAuthenticated(false);
            logger.debug("Successfully logged out");
        }
    }


}
