package com.mini.projet.controller;

import com.google.gson.Gson;
import com.mini.projet.dao.Idao;
import com.mini.projet.model.Machine;
import com.mini.projet.service.MachineService;
import com.mini.projet.vo.ClientResponse;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

/**
 * Servlet implementation class MachineServlet
 */
@WebServlet("/machine/api/*")
public class MachineServletApi extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private Idao<Machine> machineService;
    private Gson gson = new Gson();

    public MachineServletApi() {
        super();
    }

    public void init() {
        String jdbcURL = getServletContext().getInitParameter("jdbcURL");
        String jdbcUsername = getServletContext().getInitParameter("jdbcUsername");
        String jdbcPassword = getServletContext().getInitParameter("jdbcPassword");
        System.out.println("========+++> CONNECTION "+jdbcURL);
        machineService = new MachineService(jdbcURL, jdbcUsername, jdbcPassword);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action =  request.getPathInfo();

        try {
            switch (action) {
                case "/":
                    listMachine(request, response);
                    break;
                case "/get":
                    viewMachine(request, response);
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
                    insertMachine(request, response);
                    break;
                case "/update":
                    updateMachine(request, response);
                    break;
                case "/delete":
                    deleteMachine(request, response);
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

    private void listMachine(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException {
        List<Machine> machines = machineService.findAll();
        sendJson(request,response,machines);
    }

    private void viewMachine(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException {
        Long id = Long.parseLong(request.getParameter("id"));
        Machine machine = machineService.findOne(id);
        sendJson(request,response,machine);
    }

    private void insertMachine(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException {
        System.out.println("DATA SUBMITTED");
        String reference = request.getParameter("reference");
        Date dateAchat = Date.valueOf(request.getParameter("dateAchat"));
        Long salleId = Long.parseLong(request.getParameter("salle_id"));
        String type = request.getParameter("type");
        Machine machine = new Machine(null,salleId,reference,type,dateAchat,null);
        System.out.println(machine);
        boolean result = machineService.create(machine);
        String message = result ? "Success": "ERROR";
        sendJson(request,response,new ClientResponse(message,result));
    }

    private void updateMachine(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException {
        Long id = Long.parseLong(request.getParameter("id"));
        String reference = request.getParameter("reference");
        Date dateAchat = Date.valueOf(request.getParameter("dateAchat"));
        Long salleId = Long.parseLong(request.getParameter("salle_id"));
        String type = request.getParameter("type");

        Machine machine = new Machine(id,salleId,reference,type,dateAchat,null);
        boolean result = machineService.update(machine);
        String message = result ? "Success": "ERROR";
        sendJson(request,response,new ClientResponse(message,result));
    }

    private void deleteMachine(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException {
        Long id = Long.parseLong(request.getParameter("id"));
        machineService.delete(id);
        boolean result = machineService.delete(id);
        String message = result ? "Success": "ERROR";
        sendJson(request,response,new ClientResponse(message,result));
    }
}
