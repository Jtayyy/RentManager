package com.epf.rentmanager.main;
import com.epf.rentmanager.dao.ClientDao;
import com.epf.rentmanager.exception.ServiceException;
import com.epf.rentmanager.model.Client;
import com.epf.rentmanager.service.ClientService;

import java.time.LocalDate;

public class main {

    public static void main(String args[]) throws ServiceException {

        Client premier = new Client();
        System.out.println(premier);
        ClientService clients = ClientService.getInstance();
        clients.findAll();

    }
}
