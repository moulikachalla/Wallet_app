package com.wallet.app.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class WalletDaoUtility {

    public static Connection getConnectionToMySQL() {
        Connection connection = null;
        try {
            connection = DriverManager
                    .getConnection("jdbc:mysql://localhost:3306/fordschema?" + "user=root&password=1234");
            System.out.println("Connection to MYSQL successful!");
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return connection;
    }


}