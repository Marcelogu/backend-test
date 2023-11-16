package com.nisum.backendtest.service.impl;

import com.nisum.backendtest.service.JwtService;
import io.jsonwebtoken.Claims;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class JwtServiceImplTest {

    @Mock
    JwtService jwtService;

    @BeforeEach
    void init(){
        jwtService = new JwtServiceImpl("564E635266556A586E3234842899782F413F4428472B4B6250645367566B3278");
    }

    @Test
    void verify() {
        Claims claims = jwtService.verify("eyJhbGciOiJIUzI1NiJ9.eyJ1c2VybmFtZSI6Ikp1YW4gUGVyZXoiLCJzdWIiOiJKdWFuIFBlcmV6IiwiaWF0IjoxNzAwMDc5OTg3fQ.GHxZgEL8wC-UtN6yOFdrNWmmEgf_sqXLU2SiN7LYanE");

        assertEquals("Juan Perez", claims.get("username").toString());
    }

    @Test
    void generate() {
        Map<String, Object> claims = new HashMap<>();
        claims.put("username", "Juan Perez");

        String token = jwtService.generate(claims);

        assertTrue(token.length() > 0);
    }
}