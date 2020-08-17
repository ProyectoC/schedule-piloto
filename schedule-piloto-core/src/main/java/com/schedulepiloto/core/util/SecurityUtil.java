package com.schedulepiloto.core.util;

import org.apache.commons.lang3.RandomStringUtils;
import org.passay.CharacterData;
import org.passay.CharacterRule;
import org.passay.EnglishCharacterData;
import org.passay.PasswordGenerator;
import org.springframework.util.MultiValueMap;

import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

public class SecurityUtil {

    // Default rol
    public static final String ROL_SUPER_ADMIN_DEFAULT = "Super Administrador";
    public static final String ROL_ADMINISTRATION_DEFAULT = "Administrador";
    public static final String ROL_COMMON_DEFAULT = "Usuario";
    public static final String ROL_SECURITY_DEFAULT = "USER";

    // Default keys
    public static final String USER_NAME_KEY = "username";
    public static final String COMPANY_NAME_KEY = "company";

    // Times tokens
    public static final Integer SECONDS_EXPIRED_TOKEN_COMMON_USER = 5184000;
    public static final Integer SECONDS_EXPIRED_TOKEN_HOST_USER = 31536000;
    public static final Integer SECONDS_EXPIRED_TOKEN_EMAIL_VALIDATION = 86400;

    // Default configuration password
    public static final int SIZE_GENERATE_PASSWORD = 35;
    public static final String USER_SECURITY = "user";
    public static final String PASSWORD_SECURITY = "password";
    public static final String PASSWORD_TYPE_DEFAULT = "SHA-256";

    // Configuration tokens
    public static final String AUTHORIZATION_TYPE = "Authorization";
    public static final String TOKEN_TYPE = "Bearer ";

    // Default headers
    public static final String HEADER_OAUTH = "oauth2";

    // Default type users
    public static final String OAUTH_HOST_USER = "host-user-oauth";
    public static final String OAUTH_COMMON_USER = "common-user-oauth";
    public static final String OAUTH_EXTERNAL_USER = "external-user";

    // Default url
    public final static String ALL_RESOURCES = "*";
    public final static String ALL_RESOURCES_DEFAULT = "/**";
    public static final String SING_HOST_REST_DEFAULT = "/public/security-service/auth/host";
    public static final String SING_USER_REST_DEFAULT = "/public/security-service/auth/user";
    public static final String VERIFICATION_EMAIL_REST_DEFAULT = "/public/security-service/users/verify-account"; // /common/oauth2/authorize/user
    public static final String CREATE_SECURITY_USER_REST_DEFAULT = "/public/security-service/users/create";
    public static final String FORGOT_PASSWORD_REST_DEFAULT = "/public/security-service/users/forgot-password";
    public static final String CHANGE_PASSWORD_REST_DEFAULT = "/public/security-service/users/change-password";

    // Structure Token Host
    public static final String PARAMETER_CUSTOMER_ID = "customer_nit";
    public static final String PARAMETER_CUSTOMER_KEY = "customer_key";
    public static final String PARAMETER_CUSTOMER_HOST = "customer_host";
    public static final String PARAMETER_CUSTOMER_WEB_SITE = "customer_web_site";
    public static final String PARAMETER_SCOPES = "scopes";

    // Default domain
//    public static final String END_POINT_REST_SERVICES = "http://localhost:8082/api/v1";

    // White list
    public static String[] AUTH_WHITELIST_DEFAULT() {
        return new String[]{"/api/swagger-ui.html**", "/v2/api-docs", "/swagger-resources",
                "/swagger-resources/configuration/ui", "/swagger-resources/configuration/security", "/**", "/favicon.ico",
                "/**/*.png", "/**/*.gif", "/**/*.svg", "/**/*.jpg", "/**/*.html", "/**/*.css", "/**/*.js",
                "/actuator/*"};
    }

    public static String[] AUTH_WHITELIST_APIS_DEFAULT() {
        return new String[]{"/api/swagger-ui.html**", "/v2/api-docs", "/swagger-resources",
                "/swagger-resources/configuration/ui", "/swagger-resources/configuration/security", "/favicon.ico",
                "/**/*.png", "/**/*.gif", "/**/*.svg", "/**/*.jpg", "/**/*.html", "/**/*.css", "/**/*.js",
                "/actuator/*"};
    }

    // Messages
    public static final String ANSWER_ERROR_NOT_ALLOW_AUTHORIZATION = "ANSWER ERROR NO ALLOW, NO AUTHORIZATION. MESSAGE - {}";
    public static final String USER_AUTHENTICATION_ERROR_MESSAGE = "THE USER AUTHENTICATION CAN NOT BE ESTABLISHED IN THE SECURITY CONTEXT";

    // Methods
    public static String hashPassword(String clearTextPassword) {
        try {
            MessageDigest md = MessageDigest.getInstance(PASSWORD_TYPE_DEFAULT);
            byte[] byteFromStr = clearTextPassword.getBytes(Charset.forName(CommonUtil.ENCODING_DEFAULT));
            md.update(byteFromStr);
            Base64.Encoder base64Encoder = Base64.getEncoder().withoutPadding();
            return base64Encoder.encodeToString(md.digest());
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    public static String generatePassword() {
        return RandomStringUtils.random(SIZE_GENERATE_PASSWORD, true, true);
    }

    public static String getValueMultiValueMap(MultiValueMap<String, String> parameters, String key) throws Exception {
        if (parameters == null)
            throw new Exception("Parameters can not be null.");
        if (!parameters.containsKey(key))
            throw new Exception("Parameter: " + key + ", is not valid.");
        return parameters.getFirst(key);
    }

    public static String generateStrongPassword(int length) {
        PasswordGenerator gen = new PasswordGenerator();
        CharacterData lowerCaseChars = EnglishCharacterData.LowerCase;
        CharacterRule lowerCaseRule = new CharacterRule(lowerCaseChars);
        lowerCaseRule.setNumberOfCharacters(2);

        CharacterData upperCaseChars = EnglishCharacterData.UpperCase;
        CharacterRule upperCaseRule = new CharacterRule(upperCaseChars);
        upperCaseRule.setNumberOfCharacters(2);

        CharacterData digitChars = EnglishCharacterData.Digit;
        CharacterRule digitRule = new CharacterRule(digitChars);
        digitRule.setNumberOfCharacters(2);

        CharacterData specialChars = new CharacterData() {
            public String getErrorCode() {
                return "error";
            }

            public String getCharacters() {
                return "!@#$%^&*()_+";
            }
        };
        CharacterRule splCharRule = new CharacterRule(specialChars);
        splCharRule.setNumberOfCharacters(2);

        return gen.generatePassword(length, splCharRule, lowerCaseRule,
                upperCaseRule, digitRule);
    }

    /* public static void main(String[] args) {
        System.out.println("Security Key: " + generateStrongPassword(40));
        System.out.println("Nit: " + generateStrongPassword(20));
    }*/
}
