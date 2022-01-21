package com.mini.projet.controller;

import com.google.gson.Gson;
import com.mini.projet.dao.Idao;
import com.mini.projet.model.Salle;
import com.mini.projet.service.SalleService;
import com.mini.projet.vo.ClientResponse;
import org.codehaus.jackson.map.ObjectMapper;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;

/**
 * Servlet implementation class SalleServlet
 */
@WebServlet("/salle/api/*")
public class SalleServletApi extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private Idao<Salle> salleService;
    private Gson gson = new Gson();

    public SalleServletApi() {
        super();
    }

    public void init() {
        String jdbcURL = getServletContext().getInitParameter("jdbcURL");
        String jdbcUsername = getServletContext().getInitParameter("jdbcUsername");
        String jdbcPassword = getServletContext().getInitParameter("jdbcPassword");
        System.out.println("========+++> CONNECTION "+jdbcURL);
        salleService = new SalleService(jdbcURL, jdbcUsername, jdbcPassword);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action =  request.getPathInfo();

        try {
            switch (action) {
                case "/":
                    listSalle(request, response);
                    break;
                case "/get":
                    viewSalle(request, response);
                    break;
                default:
                    sendJson(request, response,new ClientResponse("Endpoint Not Found",false));
                    break;
            }
        } catch (SQLException e) {
            throw new ServletException(e);
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getPathInfo();

        try {
            switch (action) {
                case "/add":
                    insertSalle(request, response);
                    break;
                case "/update":
                    updateSalle(request, response);
                    break;
                case "/delete":
                    deleteSalle(request, response);
                    break;
                default:
                    sendJson(request, response,new ClientResponse("Endpoint Not Found",false));
                    break;
            }
        } catch (SQLException e) {
            throw new ServletException(e);
        }
    }

    private <T> void sendJson(HttpServletRequest request, HttpServletResponse response, T data) throws IOException {
        String jsonData = this.gson.toJson(data);

        PrintWriter out = response.getWriter();
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        out.print(jsonData);
        out.flush();
    }

    private void listSalle(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException {
        List<Salle> salles = salleService.findAll();
        sendJson(request,response,salles);
    }

    private void viewSalle(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException {
        Long id = Long.parseLong(request.getParameter("id"));
        Salle salle = salleService.findOne(id);
        sendJson(request,response,salle);
    }

    private void insertSalle(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException {
        System.out.println("DATA SUBMITTED");
        String code = request.getParameter("code");
        String type = request.getParameter("type");

        Salle salle = new Salle(null,code, type,null);
        System.out.println(salle);
        boolean result = salleService.create(salle);
        String message = result ? "Success": "ERROR";
        sendJson(request,response,new ClientResponse(message,result));
    }

    private void updateSalle(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException {
        Long id = Long.parseLong(request.getParameter("id"));
        String code = request.getParameter("code");
        String type = request.getParameter("type");

        Salle salle = new Salle(id, code, type, null);
        boolean result = salleService.update(salle);
        String message = result ? "Success": "ERROR";
        sendJson(request,response,new ClientResponse(message,result));
    }

    private void deleteSalle(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException {
        Long id = Long.parseLong(request.getParameter("id"));
        salleService.delete(id);
        boolean result = salleService.delete(id);
        String message = result ? "Success": "ERROR";
        sendJson(request,response,new ClientResponse(message,result));
    }
}
