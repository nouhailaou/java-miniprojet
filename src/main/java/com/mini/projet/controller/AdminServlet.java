package com.mini.projet.controller;

import com.mini.projet.model.User;
import com.mini.projet.service.AccountService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;

/**
 * Servlet implementation class HelloServlet
 */
@WebServlet("/admin/*")
public class AdminServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private AccountService accountService;

    public AdminServlet() {
        super();
    }

    public void init() {
        String jdbcURL = getServletContext().getInitParameter("jdbcURL");
        String jdbcUsername = getServletContext().getInitParameter("jdbcUsername");
        String jdbcPassword = getServletContext().getInitParameter("jdbcPassword");
        System.out.println("========+++> CONNECTION "+jdbcURL);
        accountService = new AccountService(jdbcURL, jdbcUsername, jdbcPassword);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getServletPath() + request.getPathInfo();

        try {
            switch (action) {
                case "/admin/profile":
                    viewAdmin(request, response);
                    break;
                default:
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
                case "/admin/profile":
                    updateAdmin(request, response);
                    break;
                default:
                    break;
            }
        } catch (SQLException e) {
            throw new ServletException(e);
        }
    }

    private void viewAdmin(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException {
        HttpSession session = request.getSession();
        Object uid = session.getAttribute("uid");
        if(uid==null){
            response.sendRedirect("/auth/login");
            return;
        }
        Long id = Long.parseLong(String.valueOf(uid));
        User account = accountService.findOne(id);

        if (account != null) {
            RequestDispatcher dispatcher = request.getRequestDispatcher("/views/admin/profile.jsp");
            session.setAttribute("username", account.getUsername());
            session.setAttribute("fullname", account.getFullname());
            request.setAttribute("account", account);
            request.setAttribute("success", "Update Done");

            dispatcher.forward(request, response);
        }else{
            RequestDispatcher dispatcher = request.getRequestDispatcher("/views/account/login.jsp");
            request.setAttribute("error", "User Not Found");
            dispatcher.forward(request, response);
        }
    }
    private void updateAdmin(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException {
        HttpSession session = request.getSession();
        Object uid = session.getAttribute("uid");
        if(uid==null){
            response.sendRedirect("/auth/login");
            return;
        }
        Long id = Long.parseLong(String.valueOf(uid));
        String username = request.getParameter("username");
        String fname = request.getParameter("fname");
        String lname = request.getParameter("lname");
        String phone = request.getParameter("phone");
        String pass = request.getParameter("pass");
        User account = new User(id, username, pass,fname,lname,phone);

        boolean result = accountService.update(account);

        if (result) {
            RequestDispatcher dispatcher = request.getRequestDispatcher("/views/admin/profile.jsp");
            request.setAttribute("account", account);
            session.setAttribute("username", account.getUsername());
            session.setAttribute("fullname", account.getFullname());
            dispatcher.forward(request, response);
        }else{
            RequestDispatcher dispatcher = request.getRequestDispatcher("/views/admin/profile.jsp");
            request.setAttribute("error", "Error While Updating!");
            dispatcher.forward(request, response);
        }
    }

}
