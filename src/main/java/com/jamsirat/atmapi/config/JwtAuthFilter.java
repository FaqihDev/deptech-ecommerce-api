package com.jamsirat.atmapi.config;

import com.jamsirat.atmapi.repository.ITokenRepository;
import com.jamsirat.atmapi.service.JwtService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;


import java.io.IOException;
import java.util.Objects;

@Component
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class JwtAuthFilter extends OncePerRequestFilter {

    private final JwtService jwtService;

    private final UserDetailsService userDetailsService;

    private final ITokenRepository tokenRepository;


    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest httpServletRequest,
                                    @NonNull HttpServletResponse httpServletResponse,
                                    @NonNull FilterChain filterChain) throws ServletException, IOException {

        //check if the url is authentication
        if (httpServletRequest.getServletPath().contains("/api/v1/auth")) {
            filterChain.doFilter(httpServletRequest,httpServletResponse);
            return;
        }

        //check if the header contain Authorization
        String AUTHORIZATION_HEADER = "Authorization";
        final String authHeader = httpServletRequest.getHeader(AUTHORIZATION_HEADER);
        final String jwt;
        final String userEmail;

        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            filterChain.doFilter(httpServletRequest,httpServletResponse);
        }

        if (Objects.nonNull(authHeader)) {
            jwt = authHeader.substring(7);
            userEmail = jwtService.extractUsername(jwt);
            //check for new user
            if (userEmail != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                //get the user from userDetails
                UserDetails userDetails = this.userDetailsService.loadUserByUsername(userEmail);
                var isTokenValid = tokenRepository.findByToken(jwt)
                        .map(token -> !token.isTokenExpired() && !token.isRevoked())
                        .orElse(false);
                //recheck if token is valid belongs to user
                if (jwtService.isTokenValid(jwt,userDetails) && Boolean.TRUE.equals(isTokenValid)) {
                    UsernamePasswordAuthenticationToken authToken =
                            new UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities());
                    authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(httpServletRequest));
                    SecurityContextHolder.getContext().setAuthentication(authToken);
                }
            }
        }
        filterChain.doFilter(httpServletRequest,httpServletResponse);
    }
}