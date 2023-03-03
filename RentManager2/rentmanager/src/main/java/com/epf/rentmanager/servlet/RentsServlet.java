package com.epf.rentmanager.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/rents")
public class RentsServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

//        try{
//            request.setAttribute("nbClients", ClientService.getInstance().getCount());
//            request.setAttribute("nbVehicles", VehicleService.getInstance().getCount());
//            request.setAttribute("nbReservations", ReservationService.getInstance().getCount());
//        }
//        catch (ServiceException e){
//            throw new ServletException();
//        }

        this.getServletContext().getRequestDispatcher("/WEB-INF/views/rents/list.jsp").forward(request, response);
    }

}
