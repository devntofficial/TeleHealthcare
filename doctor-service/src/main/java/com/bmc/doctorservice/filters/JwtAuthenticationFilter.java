package com.bmc.doctorservice.filters;

import com.bmc.doctorservice.dto.UserPrincipal;
import com.bmc.doctorservice.services.AuthUserService;
import com.bmc.doctorservice.services.SecurityService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class JwtAuthenticationFilter extends OncePerRequestFilter {

    @Autowired
    private SecurityService securityService;

    @Autowired
    private AuthUserService authUserService;

    @Override
    protected void doFilterInternal(HttpServletRequest req, HttpServletResponse res, FilterChain filterChain) throws ServletException, IOException {
        try{
            String jwt = req.getHeader("Authorization");
            if(!StringUtils.isEmpty(jwt) && securityService.validateToken(jwt)) {
                String userNameInToken = securityService.getUserNameFromToken(jwt);
                UserPrincipal userPrincipal = authUserService.loadUserByUsername(userNameInToken);

                UsernamePasswordAuthenticationToken authenticationToken =
                        new UsernamePasswordAuthenticationToken(userPrincipal, null, userPrincipal.getAuthorities());
                SecurityContextHolder.getContext().setAuthentication(authenticationToken);
            }
        } catch(Exception ex) {
            System.out.println("Failed to validate the token and/or set authentication token in security context");
        }

        filterChain.doFilter(req, res);
    }
}
