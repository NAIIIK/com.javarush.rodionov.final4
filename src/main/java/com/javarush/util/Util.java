package com.javarush.util;

public final class Util {

    private Util() {}

    public static final String DB_USER = System.getenv().getOrDefault("DB_USER", "postgres");
    public static final String DB_PASSWORD = System.getenv().getOrDefault("DB_PASSWORD", "postgres");

    public static final String DB_HOST = System.getenv().getOrDefault("DB_HOST", "localhost");
    public static final String DB_PORT = System.getenv().getOrDefault("DB_PORT", "5432");
    public static final String REDIS_HOST = System.getenv().getOrDefault("REDIS_HOST", "localhost");
    public static final String REDIS_PORT = System.getenv().getOrDefault("REDIS_PORT", "6379");

    public static final String DB_NAME = System.getenv().getOrDefault("DB_NAME", "");
    public static final String DEFAULT_SCHEMA_NAME = "world";

    public static final String DB_URL = buildDbUrl("jdbc:postgresql");

    public static final String P6SPY_URL = buildDbUrl("jdbc:p6spy:postgresql");

    public static final String DB_DIALECT = "org.hibernate.dialect.PostgreSQLDialect";

    public static final String P6SPY_DRIVER = "com.p6spy.engine.spy.P6SpyDriver";

    public static final String CHANGE_LOG_PATH = "db/changelog/master.yaml";

    private static String buildDbUrl(String dbPrefix) {
        return String.format("%s://%s:%s/%s", dbPrefix, DB_HOST, DB_PORT, DB_NAME);
    }
}
