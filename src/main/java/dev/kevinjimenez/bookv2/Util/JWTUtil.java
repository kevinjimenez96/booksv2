package dev.kevinjimenez.bookv2.Util;

import dev.kevinjimenez.bookv2.exception.InvalidTokenException;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import java.security.Key;

public class JWTUtil {
    private final static Key KEY = Keys.secretKeyFor(SignatureAlgorithm.HS256);

    public static JsonWebToken generateToken(String email){
        JsonWebToken jsonWebToken = new JsonWebToken(Jwts.builder().setSubject(email).signWith(KEY).compact());
        return jsonWebToken;
    }

    public static boolean verifyToken(String jws) throws InvalidTokenException {
        try{
            Jwts.parser().setSigningKey(KEY).parseClaimsJws(jws);
            return true;
        }catch (JwtException e){
            throw new InvalidTokenException();
        }
    }
}
