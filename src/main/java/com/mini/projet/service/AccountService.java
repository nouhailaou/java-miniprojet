package com.mini.projet.service;

import com.mini.projet.model.Machine;
import com.mini.projet.model.User;

import java.sql.*;

public class AccountService {

    private String jdbcURL;
    private String jdbcUsername;
    private String jdbcPassword;
    private Connection jdbcConntection;

    public AccountService(String jdbcURL, String jdbcUsername, String jdbcPassword) {
        this.jdbcURL = jdbcURL;
        this.jdbcUsername = jdbcUsername;
        this.jdbcPassword = jdbcPassword;
    }

    protected void connect() throws SQLException {
        if (jdbcConntection == null || jdbcConntection.isClosed()) {
            try {
                Class.forName("com.mysql.jdbc.Driver");
            } catch (ClassNotFoundException e) {
                throw new SQLException(e);
            }
            System.out.println("account "+ jdbcURL);
            jdbcConntection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
        }
    }

    protected void disconnect() throws SQLException {
        if (jdbcConntection != null && !jdbcConntection.isClosed()) {
            jdbcConntection.close();
        }
    }
    
    public User findOne(Long ids) throws SQLException {
        String sql = "SELECT * FROM users where id = ?";
        connect();

        PreparedStatement ps = jdbcConntection.prepareStatement(sql);
        ps.setLong(1, ids);
        ResultSet rs = ps.executeQuery();

        if (rs.first()) {
            Long id = rs.getLong("id");
            String username = rs.getString("username");
            String password = rs.getString("password");
            String fname = rs.getString("firstname");
            String lname = rs.getString("lastname");
            String phone = rs.getString("phone");

            User account = new User(id, username, password,fname,lname,phone);

            ps.close();
            disconnect();
            return account;
        }

        ps.close();
        disconnect();
        return null;
    }

    public User checkLogin(User user) throws SQLException {
        String sql = "SELECT * FROM users WHERE username = ? AND password = ?";
        connect();

        PreparedStatement ps = jdbcConntection.prepareStatement(sql);
        ps.setString(1, user.getUsername());
        ps.setString(2, user.getPassword());
        ResultSet rs = ps.executeQuery();

        if (rs.first()) {
            String id = String.valueOf(rs.getInt("id"));
            String username = rs.getString("username");
            String password = rs.getString("password");
            String fname = rs.getString("firstname");
            String lname = rs.getString("lastname");
            String phone = rs.getString("phone");
            User account = new User(Long.parseLong(id), username, password,fname,lname,phone);

            ps.close();
            disconnect();
            return account;
        }
        ps.close();
        disconnect();
        return null;
    }
    
    public boolean update(User entity) throws SQLException {
        String sql = "UPDATE users SET  username= ?, password = ?, firstname=?,lastname=?,phone=? WHERE id = ?";
        connect();

        PreparedStatement ps = jdbcConntection.prepareStatement(sql);
        ps.setString(1, entity.getUsername());
        ps.setString(2, entity.getPassword());
        ps.setString(3, entity.getFirstname());
        ps.setString(4, entity.getLastname());
        ps.setString(5, entity.getPhone());
        ps.setLong(6, entity.getId());
        boolean rowDeleted = ps.executeUpdate() > 0;

        ps.close();
        disconnect();

        return rowDeleted;
    }
}
