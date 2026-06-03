package com.javarush.config;

import com.javarush.domain.City;
import com.javarush.domain.Country;
import com.javarush.domain.CountryLanguage;
import com.javarush.util.Util;
import io.lettuce.core.RedisClient;
import io.lettuce.core.RedisURI;
import io.lettuce.core.api.StatefulRedisConnection;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AvailableSettings;
import org.hibernate.cfg.Configuration;

import java.util.Properties;

public final class AppConfig {

    private AppConfig() {}

    public static SessionFactory prepareRelationalDb() {
        Properties properties = new Properties();
        properties.put(AvailableSettings.DIALECT, Util.DB_DIALECT);
        properties.put(AvailableSettings.DRIVER, Util.P6SPY_DRIVER);
        properties.put(AvailableSettings.URL, Util.P6SPY_URL);
        properties.put(AvailableSettings.USER, Util.DB_USER);
        properties.put(AvailableSettings.PASS, Util.DB_PASSWORD);
        properties.put(AvailableSettings.CURRENT_SESSION_CONTEXT_CLASS, "thread");
        properties.put(AvailableSettings.HBM2DDL_AUTO, "validate");
        properties.put(AvailableSettings.STATEMENT_BATCH_SIZE, "100");
        properties.put(AvailableSettings.DEFAULT_SCHEMA, Util.DEFAULT_SCHEMA_NAME);

        return new Configuration()
                .addAnnotatedClass(City.class)
                .addAnnotatedClass(Country.class)
                .addAnnotatedClass(CountryLanguage.class)
                .addProperties(properties)
                .buildSessionFactory();
    }

    public static RedisClient prepareRedisClient() {
        RedisClient client = RedisClient.create(RedisURI.create(Util.REDIS_HOST, Util.REDIS_PORT));

        try (StatefulRedisConnection<String, String> connection = client.connect()) {
            System.out.println("Connected to Redis\n");
        }

        return client;
    }
}
