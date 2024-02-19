package com.javarush.jira.common.internal.config;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.SignatureException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
@Slf4j
public class JwtRequestFilter extends OncePerRequestFilter {
        private final JwtTokenUtils jwtTokenUtils;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String authHeader = request.getHeader("Authorization");
        String username = null;
        String jwt = null;
        System.out.println("Auth header: "+authHeader);
        if ((authHeader != null && authHeader.startsWith("Bearer ")) || authHeader == null) {
            try {
                jwt = authHeader.substring(7);
                username = jwtTokenUtils.getUsername(jwt);
            } catch (ExpiredJwtException e) {
                log.debug("Token lifetime is over");
            } catch (SignatureException e) {
                log.debug("Incorrect signature");
            } catch (Exception e){
                SecurityContextHolder.clearContext();
                log.debug("Logout by filter");
            }
        }
        System.out.println("username: " + username + "; authentication: " + SecurityContextHolder.getContext().getAuthentication());
        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(
                    username,
                    null,
                    jwtTokenUtils.getRoles(jwt).stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList())
            );
            SecurityContextHolder.getContext().setAuthentication(token);
        }
        System.out.println("JWT OK");
        filterChain.doFilter(request, response);
    }
}
