package com.mini.projet.controller;

import com.google.gson.Gson;
import com.mini.projet.dao.Idao;
import com.mini.projet.model.Salle;
import com.mini.projet.service.*;
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
@WebServlet("/salle/*")
public class SalleServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public SalleServlet() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getServletPath() + request.getPathInfo();

        try {
            switch (action) {
                case "/":
                    listSalle(request, response);
                    break;
                case "/salle/new":
                    newSalle(request, response);
                    break;
                case "/salle/edit":
                    editSalle(request, response);
                    break;
                default:
                    listSalle(request, response);
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
                default:
                    listSalle(request, response);
                    break;
            }
        } catch (SQLException e) {
            throw new ServletException(e);
        }
    }


    private void listSalle(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("/views/salle/list.jsp");
        dispatcher.forward(request, response);
    }

    private void newSalle(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("/views/salle/create.jsp");
        dispatcher.forward(request, response);
    }

    private void editSalle(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("/views/salle/edit.jsp");
        dispatcher.forward(request, response);
    }
}
