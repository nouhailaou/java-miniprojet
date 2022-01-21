package com.mini.projet.service;

import com.mini.projet.dao.Idao;
import com.mini.projet.model.Salle;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SalleService implements Idao<Salle> {

    private String jdbcURL;
    private String jdbcUsername;
    private String jdbcPassword;
    private Connection jdbcConntection;

    public SalleService(String jdbcURL, String jdbcUsername, String jdbcPassword) {
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
            jdbcConntection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
        }
    }

    protected void disconnect() throws SQLException {
        if (jdbcConntection != null && !jdbcConntection.isClosed()) {
            jdbcConntection.close();
        }
    }


    @Override
    public boolean update(Salle entity) throws SQLException {
        String sql = "UPDATE salle SET  code = ?, type = ? WHERE id = ?";
        connect();

        PreparedStatement ps = jdbcConntection.prepareStatement(sql);
        ps.setString(1, entity.getCode());
        ps.setString(2, entity.getType());
        ps.setLong(3, entity.getId());
        boolean rowDeleted = ps.executeUpdate() > 0;

        ps.close();
        disconnect();

        return rowDeleted;
    }

    @Override
    public boolean delete(Long id) throws SQLException {
        String sql = "DELETE FROM salle WHERE id = ?";
        connect();
        PreparedStatement ps = jdbcConntection.prepareStatement(sql);
        ps.setLong(1, id);

        boolean rowUpdated = ps.executeUpdate() > 0;
        ps.close();
        disconnect();

        return rowUpdated;
    }

    @Override
    public boolean create(Salle entity) throws SQLException {
        String sql = "INSERT INTO salle (code, type) VALUES (?, ?)";
        connect();

        PreparedStatement ps = jdbcConntection.prepareStatement(sql);
        ps.setString(1, entity.getCode());
        ps.setString(2, entity.getType());

        boolean rowInserted = ps.executeUpdate() > 0;
        ps.close();
        disconnect();

        return rowInserted;
    }

    @Override
    public List<Salle> findAll() throws SQLException {
        List<Salle> listArticle = new ArrayList<>();
        String sql = "SELECT * FROM salle";
        connect();

        PreparedStatement ps = jdbcConntection.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            Long id = rs.getLong("id");
            String code = rs.getString("code");
            String type = rs.getString("type");

            Salle salle = new Salle(id, code, type, null);
            listArticle.add(salle);
        }
        return listArticle;
    }

    @Override
    public Salle findOne(Long ids) throws SQLException {
        String sql = "SELECT * FROM salle where id = ?";
        connect();

        PreparedStatement ps = jdbcConntection.prepareStatement(sql);
        ps.setLong(1, ids);
        ResultSet rs = ps.executeQuery();

        if (rs.first()) {
            Long id = rs.getLong("id");
            String code = rs.getString("code");
            String type = rs.getString("type");
            Salle article = new Salle(id, code, type, null);

            ps.close();
            disconnect();
            return article;
        }

        ps.close();
        disconnect();
        return null;
    }
}
