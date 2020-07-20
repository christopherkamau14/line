package com.high.school.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.context.HttpRequestResponseHolder;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.stereotype.Component;

@Component
public class ReloadUser extends HttpSessionSecurityContextRepository {

    @Autowired
    private SystemUserDetails userDetailsImpl;



    @Override
    public SecurityContext loadContext(HttpRequestResponseHolder requestResponseHolder) {
        final SecurityContext context = super.loadContext(requestResponseHolder);
        final Authentication authentication = context.getAuthentication();
        if(authentication==null) return context;
        if(authentication.getPrincipal()!=null) {
            final UserDetails userDetails = this.createNewUserDetailsFromPrincipal(authentication.getPrincipal());
            final UsernamePasswordAuthenticationToken newAuthentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
            context.setAuthentication(newAuthentication);
        }
        return context;
    }

    private UserDetails createNewUserDetailsFromPrincipal(Object principal) {
        final UserDetails userDetails = (UserDetails) principal;
        final UserDetails user = this.userDetailsImpl.loadUserByUsername(userDetails.getUsername());
        return user;
    }
}
