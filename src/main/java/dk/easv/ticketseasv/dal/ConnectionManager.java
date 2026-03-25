package dk.easv.ticketseasv.dal;

import com.microsoft.sqlserver.jdbc.SQLServerDataSource;
import com.microsoft.sqlserver.jdbc.SQLServerException;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.util.Properties;


/**
 * ConnectionManager is responsible for creating and managing
 * database connections to the SQL Server database.
 */

public class ConnectionManager {

    private static final String Connection = "config/config.settings"; // Path to the configuration file containing database credentials
    private SQLServerDataSource dataSource; // DataSource object used to create database connections, Microsoft’s JDBC class for SQL Server connections

    public ConnectionManager() {
        try {
            Properties databaseProperties = new Properties(); // create a properties object to store a database settings
            databaseProperties.load(new FileInputStream(new File(Connection))); //load database settings from the file config
            dataSource = new SQLServerDataSource();
            dataSource.setServerName(databaseProperties.getProperty("Server")); // reads values from the config file and assigns them to the data source
            dataSource.setDatabaseName(databaseProperties.getProperty("Database"));
            dataSource.setUser(databaseProperties.getProperty("User"));
            dataSource.setPassword(databaseProperties.getProperty("Password"));
            dataSource.setPortNumber(1433);
            dataSource.setTrustServerCertificate(true);
        } catch (IOException e) {
            System.err.println("Error connecting to database: " + e.getMessage());
        }
    }

    // Use dataSource to create and return a Connection
    public Connection getConnection() {
        try {
            return dataSource.getConnection();
        } catch (SQLServerException e) {
            System.err.println("Error retrieving data from the database: " + e.getMessage());
            return null;
        }
    }

}