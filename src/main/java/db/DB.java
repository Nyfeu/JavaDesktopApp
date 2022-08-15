package db;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;

public class DB {

    private static Connection conn = null;

    /* Static methods to manage the DB connection */

    private static Properties loadProperties() {

        // This method loads the db properties by db.properties file

        try(FileInputStream fis = new FileInputStream("db.properties")) {

            Properties props = new Properties();
            props.load(fis);
            return props;

        } catch (IOException e) {

            /* The personalized RuntimeException is throw to overcome the
               extension of Exception Class. */

            throw new DBException(e.getMessage());

        }

    }

    public static Connection getConnection() {

        try {

            if (conn == null) {

                Properties props = loadProperties();
                String url = props.getProperty("db-url");
                conn = DriverManager.getConnection(url, props);

            }
            return conn;

        } catch (SQLException e) {

            throw new DBException(e.getMessage());

        }

    }

    public static void closeConnection() {

        try {

            if (conn != null) conn.close();

        } catch (SQLException e) {

            throw new DBException(e.getMessage());

        }

    }

    public static void closeStatement(Statement st) {

        if (st != null) {

            try {

                st.close();

            } catch (SQLException e) {

                throw new DBException(e.getMessage());

            }

        }

    }

    public static void closeResultSet(ResultSet rs) {

        if (rs != null) {

            try {

                rs.close();

            } catch (SQLException e) {

                throw new DBException(e.getMessage());

            }

        }

    }

}
