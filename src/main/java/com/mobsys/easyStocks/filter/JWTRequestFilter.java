package com.mobsys.easyStocks.filter;

import com.mobsys.easyStocks.config.JWTTokenUtil;
import com.mobsys.easyStocks.service.JWTUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class JWTRequestFilter extends OncePerRequestFilter {

    @Autowired
    private JWTUserDetailsService jwtUserDetailsService;

    @Autowired
    private JWTTokenUtil jwtTokenUtil;

    @Override
    protected void doFilterInternal(final HttpServletRequest request, final HttpServletResponse response, final FilterChain chain)
            throws ServletException, IOException {
        if (!request.getServletPath().startsWith("/watchlist") && !request.getServletPath().startsWith("/notifications")) {
            chain.doFilter(request, response);
            return;
        }
        final String requestTokenHeader = request.getHeader("Authorization");
        String username = null;
        String jwtToken = null;
        // JWT Token is in the form "Bearer token". Remove Bearer word and get
        // only the Token
        if (requestTokenHeader == null || requestTokenHeader.length() == 0) {
            sendMissingJwtError(response);
            return;
        }
        if (requestTokenHeader.startsWith("Bearer ")) {
            jwtToken = requestTokenHeader.substring(7);
        } else {
            jwtToken = requestTokenHeader;
        }
        if (jwtToken == null || jwtToken.length() == 0) {
            sendMissingJwtError(response);
            return;
        }
        try {
            username = jwtTokenUtil.getUsernameFromToken(jwtToken);
        } catch (final IllegalArgumentException e) {
            logger.error("Unable to get JWT Token");
        }

        // Once we get the token validate it.
        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            final UserDetails userDetails = this.jwtUserDetailsService.loadUserByUsername(username);

            // if token is valid configure Spring Security to manually set
            // authentication
            if (Boolean.TRUE == jwtTokenUtil.validateToken(jwtToken, userDetails)) {

                final UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
                        userDetails, null, userDetails.getAuthorities());
                usernamePasswordAuthenticationToken
                        .setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                // After setting the Authentication in the context, we specify
                // that the current user is authenticated. So it passes the
                // Spring Security Configurations successfully.
                SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
            }
        }
        chain.doFilter(request, response);
    }

    private void sendMissingJwtError(final HttpServletResponse response) throws IOException {
        response.setStatus(HttpStatus.UNAUTHORIZED.value());
        response.getWriter().write("{ \"error\": \"JWT Token Missing\", \"status\": 401 }");
    }

}
