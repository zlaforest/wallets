package io.pax.cryptos.dao;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * Created by AELION on 09/02/2018.
 */
public class JdbcConnector {

    DataSource dataSource= this.createDataSource();

    DataSource createDataSource() {
        DataSource dataSource;

        try { // cas du serveur
            Context context = new InitialContext();
            dataSource = (DataSource) context.lookup("java:/cryptos");

        } catch (NamingException e) { // cas du public main void
            MysqlDataSource mysqldataSource = new MysqlDataSource();
            mysqldataSource.setUser("root");
            //dataSource.setPassword("");
            mysqldataSource.setServerName("localhost");
            mysqldataSource.setDatabaseName("cryptos");
            mysqldataSource.setPort(3306); //3306
            dataSource = mysqldataSource;
        }

        return dataSource;
    }

    public Connection getConnection() throws SQLException {
        return dataSource.getConnection();
    }

}
