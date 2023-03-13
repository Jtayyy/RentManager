package com.epf.rentmanager.servlet;

import com.epf.rentmanager.exception.ServiceException;
import com.epf.rentmanager.model.Client;
import com.epf.rentmanager.service.ClientService;
import com.epf.rentmanager.service.ReservationService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/users/details")
public class UserDetailsServlet extends HttpServlet {


    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try{
            long id = Long.parseLong(request.getParameter("id"));
            ReservationService reservations = ReservationService.getInstance();
            Client client = ClientService.getInstance().findById(id);
            request.setAttribute("client", client);
            request.setAttribute("nbReservations", reservations.getCount(client));
            request.setAttribute("allReservations", reservations.findResaByClientId(client.getId()));
            request.setAttribute("nbVehicles", reservations.getActualVehiclesCount(client.getId()));
            request.setAttribute("allVehicles", reservations.findActualVehiclesByClientId(client.getId()));

        }
        catch (ServiceException e){
            throw new ServletException();
        }

        this.getServletContext().getRequestDispatcher("/WEB-INF/views/users/details.jsp").forward(request, response);
    }

}
