package com.epf.rentmanager.persistence;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import org.h2.tools.DeleteDbFiles;

public class DestroyDatabase {
    public static void main(String[] args) throws Exception {
        try {
            DeleteDbFiles.execute("~", "RentManagerDatabase", true);
            insertWithPreparedStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void insertWithPreparedStatement() throws SQLException {
        Connection connection = ConnectionManager.getConnection();

        try {
            connection.setAutoCommit(false);

            // Vidage de la base
            Statement statement = connection.createStatement();
            statement.execute("DROP TABLE Vehicle");
            statement.execute("DROP TABLE Client");
            statement.execute("DROP TABLE Reservation");

            connection.commit();
            System.out.println("Success!");
        } catch (SQLException e) {
            System.out.println("Exception Message " + e.getLocalizedMessage());
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            connection.close();
        }
    }
}
