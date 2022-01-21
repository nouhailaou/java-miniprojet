package com.mini.projet.controller;

import com.google.gson.Gson;
import com.mini.projet.dao.Idao;
import com.mini.projet.model.Machine;
import com.mini.projet.service.MachineService;
import com.mini.projet.vo.ChartData;

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
@WebServlet("/dashboard/*")
public class DashboardServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private MachineService machineService;
    private Gson gson = new Gson();
    public DashboardServlet() {
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
        String action = request.getServletPath() + request.getPathInfo();

        try {
            switch (action) {
                case "/dashboard/getData":
                    getMachineData(request,response);
                    break;
                default:
                    getChart(request, response);
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

    private void getMachineData(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
        List<ChartData> machines = machineService.getDashboardData();
        sendJson(request,response,machines);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getServletPath() + request.getPathInfo();

        try {
            switch (action) {
                default:
                    getChart(request, response);
                    break;
            }
        } catch (SQLException e) {
            throw new ServletException(e);
        }
    }


    private void getChart(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("/views/dashboard/chart.jsp");
        dispatcher.forward(request, response);
    }
}
