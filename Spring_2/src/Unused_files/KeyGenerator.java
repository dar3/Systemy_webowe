package org.example.spring_2;

import io.jsonwebtoken.security.Keys;

import java.util.Base64;

public class KeyGenerator {
    public static void main(String[] args) {
        // Generate a 256-bit key
        byte[] keyBytes = Keys.secretKeyFor(io.jsonwebtoken.SignatureAlgorithm.HS256).getEncoded();
        String encodedKey = Base64.getEncoder().encodeToString(keyBytes);
        System.out.println("Your 256-bit secret key: " + encodedKey);
    }
}

