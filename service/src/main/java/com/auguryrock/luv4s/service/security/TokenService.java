package com.auguryrock.luv4s.service.security;

import com.auguryrock.luv4s.domain.scouting.User;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.impl.crypto.MacProvider;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.crypto.spec.SecretKeySpec;
import java.io.*;
import java.security.Key;
import java.sql.Date;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

/**
 * Created by MonCherWatson on 19/06/2015.
 */
@Component
public class TokenService {
    private Key key;

    @PostConstruct
    public void init() throws IOException, ClassNotFoundException {
        InputStream stream = this.getClass().getResourceAsStream("/key.ser");
        ObjectInputStream objectInputStream = new ObjectInputStream(stream);
        key = (Key) objectInputStream.readObject();
    }

    public String generateToken(User user) {
        Instant instant = LocalDateTime.now().plusDays(7).atZone(ZoneId.systemDefault()).toInstant();

        String compact = Jwts.builder().setSubject(user.getName()).setExpiration(Date.from(instant))
                .signWith(SignatureAlgorithm.HS512, key).compact();

        return compact;
    }

    public String getUserFromToken(String token) {
        return Jwts.parser().setSigningKey(key).parseClaimsJws(token).getBody().getSubject();
    }



}
