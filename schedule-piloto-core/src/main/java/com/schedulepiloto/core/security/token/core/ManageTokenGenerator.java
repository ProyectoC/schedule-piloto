package com.schedulepiloto.core.security.token.core;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.schedulepiloto.core.security.token.util.UUIDGenerator;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.Calendar;
import java.util.Map;
import java.util.UUID;

public interface ManageTokenGenerator {

    String TOKEN_PARAMETER_AUTHORITIES = "authorities";
    String TOKEN_PARAMETER_SUBJECT = "schedulepiloto_api_2020";
    // TODO: This can change in the future.
    String[] TOKEN_PARAMETER_AUDIENCE_DEFAULT = {"http://schedule-piloto.ml/", "https://schedule-piloto.ml/"};

    static String generateCommonToken(String keySecret, Map<String, String> parameters, Integer secondsExpired, String[] authorities) throws UnsupportedEncodingException {
        Algorithm algorithm = Algorithm.HMAC512(keySecret);
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.SECOND, secondsExpired);
        JWTCreator.Builder t = JWT.create();
        for (Map.Entry<String, String> pair : parameters.entrySet()) {
            t.withClaim(pair.getKey(), pair.getValue());
        }
        if (authorities != null)
            t.withArrayClaim(TOKEN_PARAMETER_AUTHORITIES, authorities);
        t.withSubject(TOKEN_PARAMETER_SUBJECT);
        t.withAudience(TOKEN_PARAMETER_AUDIENCE_DEFAULT);
        t.withJWTId(UUID.randomUUID().toString());
        t.withIssuedAt(Calendar.getInstance().getTime());
        t.withNotBefore(Calendar.getInstance().getTime());
        t.withExpiresAt(calendar.getTime());
        t.withIssuer("Schedule Piloto");
        t.withArrayClaim("scopes", authorities);
        return t.sign(algorithm);
    }

    static String generateEmailToken() throws NoSuchAlgorithmException, UnsupportedEncodingException {
        return UUIDGenerator.generateUniqueKeysWithUUIDAndMessageDigest();
    }

    static String validateCommonToken(String token, String keySecret, String claim) throws UnsupportedEncodingException {
        Algorithm algorithm = Algorithm.HMAC512(keySecret);
        JWTVerifier verifier = JWT.require(algorithm).build();
        DecodedJWT jwt = verifier.verify(token);
        return jwt.getClaim(claim).asString();
    }
}
