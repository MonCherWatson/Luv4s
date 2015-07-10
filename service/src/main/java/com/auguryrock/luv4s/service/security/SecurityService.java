package com.auguryrock.luv4s.service.security;

import com.auguryrock.luv4s.domain.scouting.Player;
import com.auguryrock.luv4s.domain.scouting.Role;
import com.auguryrock.luv4s.domain.scouting.RoleRepository;
import com.auguryrock.luv4s.domain.scouting.RoleType;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
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
//TODO: key in memory not hard coded
@Component
public class SecurityService {
    private Key key;
    @Autowired
    private RoleRepository roleRepository;
//    @Autowired
//    private PlayerService playerService;

    @PostConstruct
    public void init() throws IOException, ClassNotFoundException {
        InputStream stream = this.getClass().getResourceAsStream("/key.ser");
        ObjectInputStream objectInputStream = new ObjectInputStream(stream);
        key = (Key) objectInputStream.readObject();
    }

    public String checkCredentialsAndGenerateJwt(Player player) {
        checkPlayer(player);
        return generateJwt(player);
    }


    protected Player checkPlayer(Player player) {
        if (player == null) {
            throw new BadCredentialsException("Player doesn't exist.");
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

    public Set<GrantedAuthority> getAuthorities(Player player, String scoutingKey) {
        checkPlayer(player);

        Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
        grantedAuthorities.add(new SimpleGrantedAuthority(RoleType.basic.toString()));

        Role role = roleRepository.findByPlayerAndScoutingKey(player, scoutingKey);
        if (role != null) {
            grantedAuthorities.add(new SimpleGrantedAuthority(role.getRoleType().toString()));
        }
        return grantedAuthorities;
    }

    public Player getCurrentPlayer() {
        return (Player) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }

}
