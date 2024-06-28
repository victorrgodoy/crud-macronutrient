package factory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ConnectionFactory {
    private static final Logger LOGGER = Logger.getLogger(ConnectionFactory.class.getName());
    private static final ConnectionFactory INSTANCE = new ConnectionFactory();
    private String url;
    private String user;
    private String password;
    private Connection connection;

    private ConnectionFactory() {
      getProperties();
    }

    public static ConnectionFactory getInstance(){
        return INSTANCE;
    }

    private void getProperties(){
        PropertiesLoaderFactory propertiesLoaderFactory = new PropertiesLoaderFactory("src/main/resources/config.properties");
        this.url = propertiesLoaderFactory.getProperty("url");
        this.user= propertiesLoaderFactory.getProperty("user");
        this.password = propertiesLoaderFactory.getProperty("password");
    }

    public Connection getConnection() {
        try {
            if (connection == null || connection.isClosed()) {
                connection = DriverManager.getConnection(url, user, password);
            }
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error getting database connection", e);
            throw new RuntimeException(e);
        }
        return connection;
    }
}



