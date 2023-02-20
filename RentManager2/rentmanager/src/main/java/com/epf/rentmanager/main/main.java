package com.epf.rentmanager.main;
import com.epf.rentmanager.dao.ClientDao;
import com.epf.rentmanager.exception.ServiceException;
import com.epf.rentmanager.model.Client;
import com.epf.rentmanager.model.Vehicle;
import com.epf.rentmanager.service.ClientService;
import com.epf.rentmanager.service.VehicleService;

import java.time.LocalDate;

public class main {

    public static void main(String args[]) throws ServiceException {

        ClientService clients = ClientService.getInstance();
        clients.findAll();
        Client test_client = clients.findById(3);
        System.out.println(test_client);
        
        System.out.println(clients.getCount());

        VehicleService vehicules = VehicleService.getInstance();
        vehicules.findAll();
        Vehicle test_vehicule = vehicules.findById(2);
        System.out.println(test_vehicule);

    }
}