package com.epf.rentmanager.main;
import com.epf.rentmanager.config.AppConfiguration;
import com.epf.rentmanager.exception.DaoException;
import com.epf.rentmanager.exception.ServiceException;
import com.epf.rentmanager.model.Client;
import com.epf.rentmanager.persistence.ConnectionManager;
import com.epf.rentmanager.service.ClientService;
import com.epf.rentmanager.service.ReservationService;
import com.epf.rentmanager.service.VehicleService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;


public class SQLtests {

    public static void main(String args[]) throws ServiceException {

        // Test de la requete de modification

        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfiguration.class);
        ClientService clientService = context.getBean(ClientService.class);
        VehicleService vehicleService = context.getBean(VehicleService.class);
        ReservationService reservationService = context.getBean(ReservationService.class);

        final String MODIFY_CLIENT_QUERY = "UPDATE Client SET nom=?, prenom=?, email=?, naissance=? WHERE id=?;";

        Client client = clientService.findById(1);
        System.out.println(client);

        long clientId = 1;
        String newFirstName = "Pierre";
        String newLastName = "Andre";
        String newEmail = "pierre.andre@gmail.com";
        LocalDate newDate = LocalDate.now();

        try(Connection connection = ConnectionManager.getConnection();){

            PreparedStatement statement = connection.prepareStatement(MODIFY_CLIENT_QUERY);

            statement.setString(1, newFirstName);
            statement.setString(2, newLastName);
            statement.setString(3, newEmail);
            statement.setDate(4, Date.valueOf(newDate));
            statement.setLong(5, clientId);

            statement.execute();

            client = clientService.findById(1);
            System.out.println(client);
        }
        catch (SQLException e){
            System.out.println("Fail");
        }
    }
}