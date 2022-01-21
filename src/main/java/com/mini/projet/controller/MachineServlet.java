package com.mini.projet.controller;

import com.google.gson.Gson;
import com.mini.projet.dao.Idao;
import com.mini.projet.model.Machine;
import com.mini.projet.model.Salle;
import com.mini.projet.service.MachineService;
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
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Servlet implementation class MachineServlet
 */
@WebServlet("/machine/*")
public class MachineServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private MachineService machineService;
    private SalleService salleService;

    public MachineServlet() {
        super();
    }

    public void init() {
        String jdbcURL = getServletContext().getInitParameter("jdbcURL");
        String jdbcUsername = getServletContext().getInitParameter("jdbcUsername");
        String jdbcPassword = getServletContext().getInitParameter("jdbcPassword");
        System.out.println("========+++> CONNECTION "+jdbcURL);
        machineService = new MachineService(jdbcURL, jdbcUsername, jdbcPassword);
        salleService = new SalleService(jdbcURL, jdbcUsername, jdbcPassword);

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getServletPath() + request.getPathInfo();

        try {
            switch (action) {
                case "/":
                    listMachine(request, response);
                    break;
                case "/machine/new":
                    newMachine(request, response);
                    break;
                case "/machine/edit":
                    editMachine(request, response);
                    break;
                case "/machine/salle":
                    listMachineBySalle(request, response);
                    break;
                case "/machine/date":
                    listMachineByDateGet(request, response);
                    break;
                default:
                    listMachine(request, response);
                    break;
            }
        } catch (SQLException e) {
            throw new ServletException(e);
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getServletPath() + request.getPathInfo();

        try {
            switch (action) {
                case "/machine/salle":
                    listMachineBySalle(request, response);
                    break;
                case "/machine/date":
                    listMachineByDate(request, response);
                    break;
                default:
                    listMachine(request, response);
                    break;
            }
        } catch (SQLException e) {
            throw new ServletException(e);
        }
    }

    private void listMachineBySalle(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
        String id = request.getParameter("salleId");
        List<Machine> machines;
        List<Salle> salles = salleService.findAll();
        if(id==null || id.equals("0")){
            machines = machineService.findAll();
        }else{
            Long salleId = Long.parseLong(id);
           machines = machineService.getBySalleId(salleId);
        }
        RequestDispatcher dispatcher = request.getRequestDispatcher("/views/machine/search.jsp");
        request.setAttribute("listMachine", machines);
        request.setAttribute("listSalle", salles);
        request.setAttribute("salleId", id);

        dispatcher.forward(request, response);
    }
    private void listMachineByDateGet (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
        List<Machine> machines;
        machines = machineService.findAll();
        RequestDispatcher dispatcher = request.getRequestDispatcher("/views/machine/search-date.jsp");
        request.setAttribute("listMachine", machines);
        dispatcher.forward(request, response);
}
        private void listMachineByDate(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
        System.out.println("DATA SUBMITTED");
        String strDateF = request.getParameter("dateF");
        String strDateD = request.getParameter("dateD");
        System.out.println("DATE =>" + strDateD);
        List<Machine> machines;
        if(strDateD==null || strDateF==null){
            machines = machineService.findAll();
        }else{
            Date dateD = Date.valueOf(strDateD);
            Date dateF = Date.valueOf(strDateF);
            machines = machineService.getByDate(dateD,dateF);
        }
        RequestDispatcher dispatcher = request.getRequestDispatcher("/views/machine/search-date.jsp");
        request.setAttribute("listMachine", machines);
        request.setAttribute("dateF", strDateF);
        request.setAttribute("dateD", strDateD);

        dispatcher.forward(request, response);
    }



    private void listMachine(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("/views/machine/list.jsp");
        dispatcher.forward(request, response);
    }

    private void newMachine(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("/views/machine/create.jsp");
        List<Salle> salles = salleService.findAll();
        request.setAttribute("listSalle", salles);
        dispatcher.forward(request, response);
    }

    private void editMachine(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("/views/machine/edit.jsp");
        List<Salle> salles = salleService.findAll();
        request.setAttribute("listSalle", salles);
        dispatcher.forward(request, response);
    }
}
