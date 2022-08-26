package com.revature.yolp.utils;

import io.jsonwebtoken.SignatureAlgorithm;

import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.security.Key;

public class JwtConfig {
    private final String salt = "laskdjflasdkfsdkfjasdklfjhasdkfikjehfksjdhfklsadjhflskdf";

    private int expiration = 60 * 60 * 1000;

    private final SignatureAlgorithm sigAlg = SignatureAlgorithm.HS256;

    private final Key signingKey;

    public JwtConfig() {
        byte[] saltyBytes = DatatypeConverter.parseBase64Binary(salt);
        signingKey = new SecretKeySpec(saltyBytes, sigAlg.getJcaName());
    }

    public int getExpiration() {
        return expiration;
    }

    public SignatureAlgorithm getSigAlg() {
        return sigAlg;
    }

    public Key getSigningKey() {
        return signingKey;
    }
}