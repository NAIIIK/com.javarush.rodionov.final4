package com.javarush.liquibase;

import com.javarush.util.Util;
import liquibase.command.CommandScope;
import liquibase.database.Database;
import liquibase.database.DatabaseFactory;
import liquibase.database.jvm.JdbcConnection;
import liquibase.resource.ClassLoaderResourceAccessor;

import java.sql.Connection;
import java.sql.DriverManager;

public final class LiquibaseInitializer {

    private LiquibaseInitializer() {}

    public static void runMigrations() {
        try (Connection connection = DriverManager.getConnection(Util.DB_URL, Util.DB_USER, Util.DB_PASSWORD)) {

            Database database = DatabaseFactory.getInstance()
                    .findCorrectDatabaseImplementation(new JdbcConnection(connection));

            database.setDefaultSchemaName(Util.DEFAULT_SCHEMA_NAME);

            CommandScope updateCommand = new CommandScope("update");

            updateCommand.addArgumentValue("changeLogFile", Util.CHANGE_LOG_PATH);
            updateCommand.addArgumentValue("database", database);
            updateCommand.addArgumentValue("resourceAccessor", new ClassLoaderResourceAccessor());
            updateCommand.addArgumentValue("defaultSchemaName", Util.DEFAULT_SCHEMA_NAME);

            updateCommand.execute();
        } catch (Exception e) {
            throw new RuntimeException("Ошибка при выполнении миграций Liquibase.", e);
        }
    }
}
