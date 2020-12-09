//package org.example.springtrain.security;
//
//import io.jsonwebtoken.Claims;
//import io.jsonwebtoken.ExpiredJwtException;
//import io.jsonwebtoken.Jwts;
//import io.jsonwebtoken.MalformedJwtException;
//import io.jsonwebtoken.SignatureAlgorithm;
//import io.jsonwebtoken.SignatureException;
//import io.jsonwebtoken.UnsupportedJwtException;
//import java.nio.file.attribute.UserPrincipal;
//import java.security.Principal;
//import java.time.LocalDateTime;
//import java.util.Collection;
//import java.util.Collections;
//import java.util.Date;
//import java.util.List;
//import java.util.stream.Collectors;
//import lombok.RequiredArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.stereotype.Component;
//
//@Slf4j
//@Component
//@RequiredArgsConstructor
//public class JwtTokenProvider {
//
//    private static final String ROLES_JWT_CLAIM = "roles";
//
//    @Value("${jwt.expirationInMs}")
//    private long jwtExpirationInMs;
//
//    @Value("${jwt.secret}")
//    private String jwtSecret;
//
//    public String generateToken(Authentication authentication) {
//        Principal principal = (Principal) authentication.getPrincipal();
//        String login = principal.getName();
//        Collection<GrantedAuthority> authorities = (Collection<GrantedAuthority>) authentication.getAuthorities();
//        List<String> roles = authorities.stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList());
//
//        Date now = new Date();
//        Date expiryDate = new Date(now.getTime() + jwtExpirationInMs);
//
//        String token = Jwts.builder()
//                .setSubject(login)
//                .addClaims(Collections.singletonMap(ROLES_JWT_CLAIM, roles))
//                .setIssuedAt(now)
//                .setExpiration(expiryDate)
//                .signWith(SignatureAlgorithm.HS512, jwtSecret)
//                .compact();
//        return token;
//    }
//
//    public String getUsernameFromJwt(String token) {
//        Claims claims = parseClaims(token);
//        return claims.getSubject();
//    }
//
//    public Collection<SimpleGrantedAuthority> getRolesFromJwt(String token) {
//        Claims claims = parseClaims(token);
//        List<String> roles = (List<String>) claims.get(ROLES_JWT_CLAIM);
//        List<SimpleGrantedAuthority> authorities = roles.stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList());
//        return authorities;
//    }
//
//    private Claims parseClaims(String token) {
//        return Jwts.parser()
//                .setSigningKey(jwtSecret)
//                .parseClaimsJws(token)
//                .getBody();
//    }
//
//    public boolean validateToken(String token) {
//        try {
//            Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token);
//            return true;
//        } catch (SignatureException ex) {
//            log.error("Invalid JWT signature");
//        } catch (MalformedJwtException ex) {
//            log.error("Invalid JWT token");
//        } catch (ExpiredJwtException ex) {
//            log.error("Expired JWT token");
//        } catch (UnsupportedJwtException ex) {
//            log.error("Unsupported JWT token");
//        } catch (IllegalArgumentException ex) {
//            log.error("JWT claims string is empty.");
//        }
//        return false;
//    }
//}
