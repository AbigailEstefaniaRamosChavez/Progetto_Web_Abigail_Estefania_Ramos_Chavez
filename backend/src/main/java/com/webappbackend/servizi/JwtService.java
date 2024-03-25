package com.webappbackend.servizi;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.time.LocalDate;
import java.util.Date;
import java.util.function.Function;

@Service
public class JwtService {
    @Value("${application.security.jwt.secret}")
    private String secret;
    @Value("${application.security.jwt.expiration}")
    private int expiration;




    public boolean Valido(String jwt){
        return this.DaiClaim(jwt, Claims::getExpiration).after(new Date());
    }


    public <T> T DaiClaim(String token, Function<Claims, T> estrattore) {
        final Claims claims = DaiClamis(token);
        return estrattore.apply(claims);
    }
    public String DaiToken(int id){
        return Jwts.builder()
                .claim("id", id)
                .setIssuer("https://www.Abi.com")
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + expiration))
                .signWith(GetKey())
                .compact();
    }

    public int DaiId(String jwt){
        return DaiClaim(jwt, "id", Integer.class);
    }

    public <T> T DaiClaim(String jwt, String claim, Class<T> type){
        return DaiClamis(jwt).get(claim, type);
    }
    private Claims DaiClamis(String jwt){
        return Jwts
                .parserBuilder()
                .setSigningKey(GetKey())
                .build()
                .parseClaimsJws(jwt)
                .getBody();
    }

    public Key GetKey(){
        byte[] secretBytes = Decoders.BASE64.decode(secret);
        return Keys.hmacShaKeyFor(secretBytes);
    }


}
