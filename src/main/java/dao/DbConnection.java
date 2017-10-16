package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DbConnection {

    public static Connection connection() {
        Connection connection = null;

        try {
            connection = DriverManager.getConnection("jdbc:sqlite:src/main/resources/db.db");
        } catch (SQLException e) {
            System.out.println("Connection to database failed.");
            System.out.println(e.getMessage());
        }
        return connection;
    }

    public static void createTables() {
        try {
            Connection connection = connection();
            Statement statement = connection.createStatement();
            statement.execute("CREATE TABLE IF NOT EXISTS \"events\" (\n" +
                    "\t`id`\tINTEGER PRIMARY KEY AUTOINCREMENT,\n" +
                    "\t`name`\tTEXT NOT NULL,\n" +
                    "\t`date`\tTEXT NOT NULL,\n" +
                    "\t`description`\tTEXT NOT NULL,\n" +
                    "\t`category`\tTEXT,\n" +
                    "\t`link`\tTEXT\n" +
                    ")");

        } catch (SQLException e) {
            System.out.println("Creating tables failed.");
            System.out.println(e.getMessage());
        }
    }
}
