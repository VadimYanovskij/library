package com.senla.training.library.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Slf4j
@Component
public class JwtTokenService {
    public static final String ROLES = "ROLES";

    @Value("${library.app.jwt.token.expired}")
    private long expired;

    @Value("${library.app.jwt.token.secret}")
    private String secret;

    public String getUsernameFromToken(String token) {
        log.info("Getting username from jwt token");
        String result = getClaimFromToken(token, Claims::getSubject);
        log.info("Username has got from jwt token");
        return result;
    }

    public Date getExpirationDateFromToken(String token) {
        log.info("Getting expiration date from jwt token");
        Date result = getClaimFromToken(token, Claims::getExpiration);
        log.info("Expiration date has got from jwt token");
        return result;
    }

    public List<String> getRoles(String token) {
        log.info("Getting roles from jwt token");
        List<String> result = getClaimFromToken(token, claims -> (List) claims.get(ROLES));
        log.info("Roles has got from jwt token");
        return result;
    }

    public <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
        log.info("Getting claims from jwt token");
        final Claims claims = getAllClaimsFromToken(token);
        return claimsResolver.apply(claims);
    }

    private Claims getAllClaimsFromToken(String token) {
        log.info("Getting any information from jwt token with secret key");
        Claims result = Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
        log.info("Any information has got from jwt token");
        return result;
    }

    private Boolean isTokenExpired(String token) {
        log.info("Checking if the token has expired");
        final Date expiration = getExpirationDateFromToken(token);
        Boolean result = expiration.before(new Date());
        log.info("Token expire has checked successful");
        return result;
    }

    public String generateToken(Authentication authentication) {
        log.info("Start generating token for user");
        final Map<String, Object> claims = new HashMap<>();
        final UserDetails user = (UserDetails) authentication.getPrincipal();

        final List<String> roles = authentication.getAuthorities()
                .stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());

        claims.put(ROLES, roles);
        String result = generateToken(claims, user.getUsername());
        log.info("Token for user has generated successfully");
        return result;
    }

    private String generateToken(Map<String, Object> claims, String subject) {
        final long now = System.currentTimeMillis();
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(subject)
                .setIssuedAt(new Date(now))
                .setExpiration(new Date(now + expired))
                .signWith(SignatureAlgorithm.HS512, secret).compact();
    }

    public Boolean validateToken(String token) {
        log.info("Start validating token");
        final String username = getUsernameFromToken(token);
        Boolean result = username != null && !isTokenExpired(token);
        log.info("Token has validated successfully");
        return result;
    }
}
