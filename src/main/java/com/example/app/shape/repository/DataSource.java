package com.example.app.shape.repository;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import java.sql.Connection;
import java.sql.SQLException;

public class DataSource {


    private static HikariConfig config = new HikariConfig();
    private static HikariDataSource ds;

    private static final String CREATE_SQL = "CREATE TABLE SHAPE (ID varchar(255), TYPE varchar(255), COORDINATES varchar(255))";

    static {
        config.setJdbcUrl("jdbc:h2:mem:");
        config.setConnectionInitSql(CREATE_SQL);
        config.addDataSourceProperty("cachePrepStmts", "true");
        config.addDataSourceProperty("prepStmtCacheSize", "250");
        config.addDataSourceProperty("prepStmtCacheSqlLimit", "2048");
        ds = new HikariDataSource(config);
    }

    private DataSource() {
    }

    public static Connection getConnection() throws SQLException {
        return ds.getConnection();
    }
}

