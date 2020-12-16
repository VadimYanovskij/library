package com.senla.training.library.filter;

import com.senla.training.library.service.BlockedTokenService;
import com.senla.training.library.service.JwtTokenService;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Component
public class JwtRequestFilter extends OncePerRequestFilter {

    public static final String AUTHORIZATION = "Authorization";
    public static final String BEARER = "Bearer ";

    private final JwtTokenService jwtTokenService;
    private final BlockedTokenService blockedTokenService;

    public JwtRequestFilter(JwtTokenService jwtTokenService,
                            BlockedTokenService blockedTokenService) {
        this.jwtTokenService = jwtTokenService;
        this.blockedTokenService = blockedTokenService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest,
                                    HttpServletResponse httpServletResponse,
                                    FilterChain filterChain)
            throws ServletException, IOException {

        final Optional<String> jwt = getJwtFromRequest(httpServletRequest);
        jwt.ifPresent(token -> {
            log.info("Start validating the access token {}", token);
            try {
                if (blockedTokenService.findById(token).isBlank()
                        && jwtTokenService.validateToken(token)) {
                    setSecurityContext(new WebAuthenticationDetailsSource()
                            .buildDetails(httpServletRequest), token);
                }

            } catch (IllegalArgumentException | MalformedJwtException | ExpiredJwtException e) {
                log.error("Unable to get JWT Token or JWT Token has expired");
            }
        });
        log.info("End of token validating");
        filterChain.doFilter(httpServletRequest, httpServletResponse);
    }

    private void setSecurityContext(WebAuthenticationDetails authDetails, String token) {
        log.info("Start setting the Authentication in the context");
        final String username = jwtTokenService.getUsernameFromToken(token);
        final List<String> roles = jwtTokenService.getRoles(token);
        final UserDetails userDetails = new User(
                username, "", roles.stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList()));
        final UsernamePasswordAuthenticationToken authentication =
                new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
        authentication.setDetails(authDetails);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        log.info("Current user is authenticated");
    }

    private static Optional<String> getJwtFromRequest(HttpServletRequest request) {
        log.info("Getting token from request");
        String bearerToken = request.getHeader(AUTHORIZATION);
        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith(BEARER)) {
            log.info("Token has got from request successfully");
            return Optional.of(bearerToken.substring(7));
        }
        log.info("Request hasn't token");
        return Optional.empty();
    }
}
