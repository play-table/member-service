package com.playtable.member.config;

import com.playtable.member.domain.entity.Member;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Service
public class JwtService {

    @Value("${jwt.secret}")
    private String secret;

    public MemberTokenInfo extractUser(String token){
        Claims claims = extractAllClaims(token);
        return MemberTokenInfo.builder()
                .id(UUID.fromString(claims.get("id", String.class)))
                .username(claims.get("username", String.class))
                .email(claims.get("email", String.class))
                .realName(claims.get("realName", String.class))
                .contact(claims.get("contact", String.class))
                .role(claims.get("role", String.class))
                .nickName(claims.get("nickName", String.class))
                .profileImage(claims.get("profileImage", String.class))
                .build();
    }
    public String generateToken(
            Map<String, Object> extraClaims,
            Member member
    ) {
        return Jwts.builder()
                .setClaims(extraClaims)
                .setSubject(member.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000L * 60 * 60 * 24 * 30))
                .signWith(SignatureAlgorithm.HS256, secret.getBytes())
                .compact();
    }

    public String generateToken(Member member) {
        HashMap<String, Object> extraClaims = new HashMap<>();
        extraClaims.put("id", member.getId());
        extraClaims.put("username", member.getUsername());
        extraClaims.put("email", member.getEmail());

        extraClaims.put("realName", member.getRealName());
        extraClaims.put("contact", member.getContact());
        extraClaims.put("nickName", member.getNickName());
        extraClaims.put("role", member.getRole().name());
        extraClaims.put("profileImage", member.getProfileImage());

        return generateToken(extraClaims, member);
    }


    private Claims extractAllClaims(String token) {
        return (Claims) Jwts.parserBuilder()
                .setSigningKey(secret.getBytes())
                .build()
                .parse(token)
                .getBody();

    }
}