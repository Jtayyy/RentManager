package com.epf.rentmanager.servlet;

import com.epf.rentmanager.exception.ServiceException;
import com.epf.rentmanager.model.Client;
import com.epf.rentmanager.service.ClientService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@WebServlet("/users/create")
public class CreateUserServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        this.getServletContext().getRequestDispatcher("/WEB-INF/views/users/create.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String prenom = request.getParameter("firstname");
        String nom = request.getParameter("lastname");
        String email = request.getParameter("email");
        LocalDate bdate = LocalDate.parse(request.getParameter("bdate"), DateTimeFormatter.ofPattern("dd/MM/yyyy"));

        try {
            ClientService.getInstance().create(new Client(prenom, nom, email, bdate));
        }
        catch (ServiceException e) {
            throw new ServletException(e);
        }

        this.getServletContext().getRequestDispatcher("/WEB-INF/views/users/create.jsp").forward(request, response);
    }

}
