package com.alura_challenge.foro.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class JwtService {

    @Autowired
    private Environment environment;

    public String generateToken(String username){
        Algorithm algorithm = Algorithm.HMAC256(Objects.requireNonNull(environment.getProperty("SECRET_KEY_TOKEN")));
        return JWT.create()
                .withIssuer("API do Fórum da Alura")
                .withSubject(username)
                .sign(algorithm);
    }

    public String validateAndGetSubject(String token){
        Algorithm algorithm = Algorithm.HMAC256(Objects.requireNonNull(environment.getProperty("SECRET_KEY_TOKEN")));
        return JWT.require(algorithm)
                .withIssuer("API do Fórum da Alura")
                .build()
                .verify(token)
                .getSubject();
    }

}
