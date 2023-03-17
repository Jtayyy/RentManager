package com.epf.rentmanager.servlet;

import com.epf.rentmanager.exception.ServiceException;
import com.epf.rentmanager.model.Client;
import com.epf.rentmanager.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;

@WebServlet("/users/modify")
public class ModifyUserServlet extends HttpServlet {

    @Autowired
    private ClientService clientService;
    @Override
    public void init() throws ServletException {
        super.init();
        SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try{
            long id = Long.parseLong(request.getParameter("id"));
            Client client = clientService.findById(id);
            request.setAttribute("client", client);
        }
        catch (ServiceException e){
            throw new ServletException();
        }

        this.getServletContext().getRequestDispatcher("/WEB-INF/views/users/modify.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {
            long id = Long.parseLong(request.getParameter("id"));
            String prenom = request.getParameter("firstname");
            String nom = request.getParameter("lastname");
            String email = request.getParameter("email");
            LocalDate bdate = LocalDate.parse(request.getParameter("bdate"));
            clientService.modify(new Client(id, prenom, nom, email, bdate));
            request.setAttribute("allClients", clientService.findAll());
        }
        catch (ServiceException e) {
            throw new ServletException(e);
        }

        this.getServletContext().getRequestDispatcher("/WEB-INF/views/users/list.jsp").forward(request, response);
    }

}