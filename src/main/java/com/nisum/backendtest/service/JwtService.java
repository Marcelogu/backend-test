package com.nisum.backendtest.service;

import io.jsonwebtoken.Claims;

import java.util.Map;

public interface JwtService {

    Claims verify(String token);
    String generate(Map<String, Object> claims);
}
