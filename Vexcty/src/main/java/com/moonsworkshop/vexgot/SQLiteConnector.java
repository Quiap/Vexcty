package com.moonsworkshop.vexgot;

import org.bukkit.plugin.Plugin;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SQLiteConnector implements DatabaseConnector {
    private final Plugin plugin;
    private final String connectionString;
    private Connection connection;

    public SQLiteConnector(Plugin plugin) {
        this.plugin = plugin;
        this.connectionString = "jdbc:sqlite:" + plugin.getDataFolder() + File.separator + plugin.getDescription().getName().toLowerCase() + ".db";

        try {
            Class.forName("org.sqlite.JDBC"); // This is required to put here for Spigot 1.10 and below to force class load
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public boolean isInitialized() {
        return true; // Always available
    }

    @Override
    public void closeConnection() {
        try {
            if (this.connection != null) {
                this.connection.close();
            }
        } catch (SQLException ex) {
            this.plugin.getLogger().severe("An error occurred closing the SQLite database connection: " + ex.getMessage());
        }
    }

    @Deprecated
    @Override
    public void connect(ConnectionCallback callback) {
        try {
            callback.accept(getConnection());
        } catch (Exception ex) {
            this.plugin.getLogger().severe("An error occurred executing an SQLite query: " + ex.getMessage());
            ex.printStackTrace();
        }
    }

    @Override
    public Connection getConnection() {
        try {
            if (this.connection == null || this.connection.isClosed()) {
                try {
                    this.connection = DriverManager.getConnection(this.connectionString);
                } catch (SQLException ex) {
                    this.plugin.getLogger().severe("An error occurred retrieving the SQLite database connection: " + ex.getMessage());
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return this.connection;
    }

    @Override
    public DatabaseType getType() {
        return DatabaseType.SQLITE;
    }
}
