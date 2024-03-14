package source_files.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import source_files.data.models.baseEntities.UserEntity;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
public class JwtService {
    @Value("${application.security.jwt.secret-key}")
    private String secretKey;
    @Value("${application.security.jwt.expiration}")
    private long expiration;

    public String generateToken(UserEntity user) {
        Map<String, Object> customClaims = new HashMap<>(Map.of(
                "id", user.getId(),
                "emailAddress", user.getEmailAddress(),
                "firstname", user.getName(),
                "lastname", user.getSurname(),
                "phoneNumber", user.getPhoneNumber(),
                "role", user.getAuthorities()
        ));
        return generateToken(customClaims, user);
    }

    public String generateToken(Map<String, Object> customClaims, UserDetails userDetails) {
        return Jwts
                .builder()
                .setClaims(customClaims)
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + expiration))
                .signWith(this.getSigninKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    public String generateRefreshToken(UserEntity user) {
        Map<String, Object> refreshTokenClaims = new HashMap<>();
        refreshTokenClaims.put("userId", user.getId());

        return Jwts.builder()
                .setClaims(refreshTokenClaims)
                .setExpiration(new Date(System.currentTimeMillis() + expiration))
                .signWith(getSigninKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    public boolean isTokenValid(String token, UserDetails user) {
        final String usernameFromToken = extractUsername(token);
        return (user.getUsername().equals(usernameFromToken)) && !isTokenExpired(token);
    }

    public boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    public Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    public Claims extractAllClaims(String token) {
        return Jwts.parserBuilder().setSigningKey(getSigninKey()).build().parseClaimsJws(token).getBody(); // Jwt içerisindeki datayı parse eder.
    }

    public Key getSigninKey() {
        byte[] keyBytes = Decoders.BASE64.decode(secretKey);
        return Keys.hmacShaKeyFor(keyBytes);
    }

}