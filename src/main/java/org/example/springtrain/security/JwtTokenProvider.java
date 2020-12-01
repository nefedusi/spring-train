package org.example.springtrain.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.nio.file.attribute.UserPrincipal;
import java.security.Principal;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class JwtTokenProvider {

    private static final String ROLES_JWT_CLAIM = "roles";

    @Value("${jwt.expirationSeconds}")
    private long jwtExpirationSeconds;

    @Value("${jwt.secret}")
    private String jwtSecret;

    private final CustomUserDetailsService userDetailsService;

    public String generateToken(Authentication authentication) {
        Principal principal = (Principal) authentication.getPrincipal();
        String login = principal.getName();
        List<GrantedAuthority> authorities = authentication.getAuthorities();

        //UserPrincipal userPrincipal = userDetailsService.loadUserByUsername();

        LocalDateTime now = LocalDateTime.now();
        LocalDateTime expiredAt = now.plusSeconds(jwtExpirationSeconds);

        String token = Jwts.builder()
                .setSubject(login)
                .addClaims(Collections.singletonMap(ROLES_JWT_CLAIM, roles))
                .setIssuedAt(now)
                .setExpiration(expiredAt)
                .signWith(SignatureAlgorithm.HS512, jwtSecret)
                .compact();
        return token;
    }
}
