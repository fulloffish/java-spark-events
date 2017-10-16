package dao;

import java.sql.Connection;

/**
 * Created by apolak on 09.05.17.
 */
abstract class BaseDao {

    private Connection connection;

    public BaseDao() {
        Connection connection = DbConnection.connection();
        this.setConnection(connection);
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    public Connection getConnection() {
        return connection;
    }
}

