//package ru.clickgroup.vtbmockapi.services.jwt;
//
//import io.jsonwebtoken.*;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.stereotype.Component;
//
//import java.time.Instant;
//import java.time.LocalDate;
//import java.time.ZoneId;
//import java.util.Date;
//
//@Component
//public class JwtProvider {
//    @Value("${jwt.secret}")
//    private String jwtSecret;
//    public String generateToken(UserAuthenticatable user) {
//        Date date = Date.from(LocalDate.now().plusDays(365).atStartOfDay(ZoneId.systemDefault()).toInstant());//TODO: should reduce the lifetime of token
//        return Jwts.builder()
//                .setSubject(user.getLogin())
//                .setIssuedAt(Date.from(Instant.now()))
//                .setExpiration(date)
//                .claim("currentUser", user.getLogin())
//                /*.claim("userId", user.getId())*/
//                .claim("expirationDate", date)
//                .signWith(SignatureAlgorithm.HS512, jwtSecret)
//                .compact();
//    }
//
//
//    public boolean validateToken(String token) {
//        try {
//            Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token);
//            return true;
//        } catch (ExpiredJwtException expEx) {
//            System.out.println("Token expired");
//        } catch (UnsupportedJwtException unsEx) {
//            System.out.println("Unsupported jwt");
//        } catch (MalformedJwtException mjEx) {
//            System.out.println("Malformed jwt");
//        } catch (SignatureException sEx) {
//            System.out.println("Invalid signature");
//        } catch (Exception e) {
//            System.out.println("invalid token");
//        }
//        return false;
//    }
//
//    public String getLoginFromToken(String token) {
//        Claims claims = Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody();
//        return claims.getSubject();
//    }
//
//}
