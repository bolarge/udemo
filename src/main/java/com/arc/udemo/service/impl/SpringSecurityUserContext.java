package com.arc.udemo.service.impl;

import com.arc.udemo.service.UDemoService;
import com.arc.udemo.service.UserContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

@Component
public class SpringSecurityUserContext implements UserContext {

    private static final Logger logger = LoggerFactory.getLogger(SpringSecurityUserContext.class);

    private final UDemoService uDemoService;

    //@Qualifier("uDemoUserDetailService")
    private final UserDetailsService userDetailsService;

    @Autowired
    public SpringSecurityUserContext(UDemoService uDemoService, @Qualifier("uDemoUserDetailService") UserDetailsService userDetailsService) {
        if (uDemoService == null) {
            throw new IllegalArgumentException("uDemoService cannot be null");
        }
        if (userDetailsService == null) {
            throw new IllegalArgumentException("userDetailsService cannot be null");
        }
        this.uDemoService = uDemoService;
        this.userDetailsService = userDetailsService;
    }

    @Override
    public com.arc.udemo.domain.users.User getCurrentUser() {
        SecurityContext context = SecurityContextHolder.getContext();
        Authentication authentication = context.getAuthentication();
        if (authentication == null) {
            return null;
        }

        //User user = (User)authentication.getPrincipal();
        //String email = user.getEmail(); //user.getUsername();

       User user = (User)authentication.getPrincipal();
       String email = user.getUsername();

        if (email == null) {
            return null;
        }
        com.arc.udemo.domain.users.User result = uDemoService.findUserByEmail(email);
        if (result == null) {
            throw new IllegalStateException(
                    "Spring Security is not in synch with uDemo users. Could not find user with email " + email);
        }

        logger.info("uDemo User: {}", result);
        return result;
    }

    @Override
    public void setCurrentUser(com.arc.udemo.domain.users.User user) {
        if (user == null) {
            throw new IllegalArgumentException("user cannot be null");
        }
        UserDetails userDetails = userDetailsService.loadUserByUsername(user.getEmail());
        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails,
                user.getPassword(), userDetails.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }

}
