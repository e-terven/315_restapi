//package com.katia.spring.security.model;
//
//import com.katia.spring.security.services.CustomUserDetailsService;
//import io.jsonwebtoken.Claims;
//import io.jsonwebtoken.Jwts;
//import io.jsonwebtoken.SignatureAlgorithm;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.stereotype.Component;
//import org.springframework.util.StringUtils;
//
//import javax.servlet.http.HttpServletRequest;
//import java.util.Date;
//import java.util.Map;
//import java.util.function.Function;
//
//@Component
//public class JwtTokenUtil {
//    private static final long EXPIRATION_TIME = 86400000; // 24 hours in milliseconds
//    public static final String AUTHORIZATION_HEADER = "Authorization";
//    public static final String TOKEN_PREFIX = "Bearer ";
//    private final CustomUserDetailsService customUserDetailsService;
//
//    public JwtTokenUtil(CustomUserDetailsService customUserDetailsService) {
//        this.customUserDetailsService = customUserDetailsService;
//    }
//
//    public String generateToken(UserDTO user) {
//        Claims claims = Jwts.claims().setSubject(user.getEmail());
//        claims.put("userId", user.getId());
//
//        Date now = new Date();
//
//        claims.put("expiration", new Date(now.getTime() + EXPIRATION_TIME).toString());
//
//        return Jwts.builder()
//                .setClaims(claims)
//                .signWith(SignatureAlgorithm.HS512, getSecretKey())
//                .compact();
//    }
//
//    private String doGenerateToken(Map<String, Object> claims, String subject) {
//        Date now = new Date();
//        Date validity = new Date(now.getTime() + EXPIRATION_TIME);
//
//        return Jwts.builder()
//                .setClaims(claims)
//                .setSubject(subject)
//                .setIssuedAt(now)
//                .setExpiration(validity)
//                .signWith(SignatureAlgorithm.HS512, getSecretKey())
//                .compact();
//    }
//
//    public Boolean validateToken(String token) {
//        final String username = getUsernameFromToken(token);
//        return (username.equals(getUsernameFromToken(token)) && !isTokenExpired(token));
//    }
//
//    public String getUsernameFromToken(String token) {
//        return getClaimFromToken(token, Claims::getSubject);
//    }
//
//    public <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
//        final Claims claims = getAllClaimsFromToken(token);
//        return claimsResolver.apply(claims);
//    }
//
//    private Claims getAllClaimsFromToken(String token) {
//        return Jwts.parser().setSigningKey(getSecretKey()).parseClaimsJws(token).getBody();
//    }
//
//    private Boolean isTokenExpired(String token) {
//        final Date expiration = getExpirationDateFromToken(token);
//        return expiration.before(new Date());
//    }
//
//    public Date getExpirationDateFromToken(String token) {
//        return getClaimFromToken(token, Claims::getExpiration);
//    }
//
//    public String resolveToken(HttpServletRequest request) {
//        String bearerToken = request.getHeader(AUTHORIZATION_HEADER);
//        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith(TOKEN_PREFIX)) {
//            return bearerToken.substring(TOKEN_PREFIX.length());
//        }
//        return null;
//    }
//
//    public Authentication getAuthentication(String token) {
//        UserDetails userDetails = customUserDetailsService.loadUserByUsername(getUsernameFromToken(token));
//        return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
//    }
//
//    private String getSecretKey() {
//        // ключ из безопасного хранилища / конфигурационного файла
//        return "mysecretkey";
//    }
//}