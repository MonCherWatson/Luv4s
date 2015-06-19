package com.auguryrock.luv4s.service.security;

import com.auguryrock.luv4s.domain.scouting.*;
import com.auguryrock.luv4s.service.player.PlayerService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.security.Key;
import java.sql.Date;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by MonCherWatson on 19/06/2015.
 */
@Component
public class SecurityService {
    private Key key;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private PlayerService playerService;

    @PostConstruct
    public void init() throws IOException, ClassNotFoundException {
        InputStream stream = this.getClass().getResourceAsStream("/key.ser");
        ObjectInputStream objectInputStream = new ObjectInputStream(stream);
        key = (Key) objectInputStream.readObject();
    }

    public String checkCredentialsAndGenerateJwt(String playerName, String password) {
        Player player = checkPlayer(playerName);
        if (!player.getPassword().equals(password)) {
            throw new BadCredentialsException("Invalid password for user: " + playerName + "!");
        }
        return generateJwt(player);
    }


    protected Player checkPlayer(String playerName) {
        Player player = playerService.findByName(playerName);
        if (player == null) {
            throw new BadCredentialsException("Player doesnt exist: " + playerName + "!");
        }
        return player;
    }

    protected String generateJwt(Player player) {
        Instant instant = LocalDateTime.now().plusDays(7).atZone(ZoneId.systemDefault()).toInstant();

        String compact = Jwts.builder().setSubject(player.getName()).setExpiration(Date.from(instant))
                .signWith(SignatureAlgorithm.HS512, key).compact();

        return compact;
    }

    public String getPlayerNameFromToken(String token) {
        return Jwts.parser().setSigningKey(key).parseClaimsJws(token).getBody().getSubject();
    }

    public Set<GrantedAuthority> getAuthorities(String username, String scoutingKey) {
        checkPlayer(username);

        Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
        grantedAuthorities.add(new SimpleGrantedAuthority(RoleType.basic.toString()));


        Role role = roleRepository.findByUserAndScoutingKey(username, scoutingKey);
        if (role != null) {
            grantedAuthorities.add(new SimpleGrantedAuthority(role.getRoleType().toString()));
        }
        return grantedAuthorities;
    }

}
