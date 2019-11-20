package io.agileinteligence.ppmtool.security;

public class SecurityConstants {

    public static final String SIGN_UP_URL = "/api/users/**";
    // public static final String H2_URL = "h2-console/**";
    public static final String SECRET = "SecretKeyToGenJWTs";
    public static final String TOKEN_PREFIX = "Bearer "; // Should end with space
    public static final String HEADER_STRING = "Authorization";
    public static final long  TOKEN_EXPIRATION_TIME = 30_000; // 30 seconds

}
