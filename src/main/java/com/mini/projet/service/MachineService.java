package com.mini.projet.service;

import com.mini.projet.dao.Idao;
import com.mini.projet.model.Machine;
import com.mini.projet.model.Salle;
import com.mini.projet.vo.ChartData;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class MachineService implements Idao<Machine> {

    private String jdbcURL;
    private String jdbcUsername;
    private String jdbcPassword;
    private Connection jdbcConntection;

    public MachineService(String jdbcURL, String jdbcUsername, String jdbcPassword) {
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
    public boolean update(Machine entity) throws SQLException {
        String sql = "UPDATE machine SET  reference= ?, type = ?, dateAchat=?,salle_id=? WHERE id = ?";
        connect();

        PreparedStatement ps = jdbcConntection.prepareStatement(sql);
        ps.setString(1, entity.getReference());
        ps.setString(2, entity.getType());
        ps.setDate(3, entity.getDateAchat());
        ps.setLong(4, entity.getSalleId());
        ps.setLong(5, entity.getId());
        boolean rowDeleted = ps.executeUpdate() > 0;

        ps.close();
        disconnect();

        return rowDeleted;
    }

    @Override
    public boolean delete(Long id) throws SQLException {
        String sql = "DELETE FROM machine WHERE id = ?";
        connect();
        PreparedStatement ps = jdbcConntection.prepareStatement(sql);
        ps.setLong(1, id);

        boolean rowUpdated = ps.executeUpdate() > 0;
        ps.close();
        disconnect();

        return rowUpdated;
    }

    @Override
    public boolean create(Machine entity) throws SQLException {
        String sql = "INSERT INTO machine (reference, type, dateAchat,salle_id) VALUES (?, ?,?,?)";
        connect();

        PreparedStatement ps = jdbcConntection.prepareStatement(sql);
        ps.setString(1, entity.getReference());
        ps.setString(2, entity.getType());
        ps.setDate(3, entity.getDateAchat());
        ps.setLong(4, entity.getSalleId());

        boolean rowInserted = ps.executeUpdate() > 0;
        ps.close();
        disconnect();

        return rowInserted;
    }

    @Override
    public List<Machine> findAll() throws SQLException {
        List<Machine> list = new ArrayList<>();
        String sql = "SELECT * FROM machine";
        connect();

        PreparedStatement ps = jdbcConntection.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            Long id = rs.getLong("id");
            String reference = rs.getString("reference");
            String type = rs.getString("type");
            Date dateAchat = rs.getDate("dateAchat");
            Long salleId = rs.getLong("salle_id");

            Machine machine = new Machine(id, salleId,reference,type,dateAchat,null);
            list.add(machine);
        }
        return list;
    }

    @Override
    public Machine findOne(Long ids) throws SQLException {
        String sql = "SELECT * FROM machine where id = ?";
        connect();

        PreparedStatement ps = jdbcConntection.prepareStatement(sql);
        ps.setLong(1, ids);
        ResultSet rs = ps.executeQuery();

        if (rs.first()) {
            Long id = rs.getLong("id");
            String reference = rs.getString("reference");
            String type = rs.getString("type");
            Date dateAchat = rs.getDate("dateAchat");
            Long salleId = rs.getLong("salle_id");
            String pattern = "yyyy-MM-dd";
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);

            String date = simpleDateFormat.format(dateAchat);
            Machine machine = new Machine(id, salleId,reference,type,dateAchat,date);

            ps.close();
            disconnect();
            return machine;
        }

        ps.close();
        disconnect();
        return null;
    }

    public List<Machine> getBySalleId(Long salle_Id) throws SQLException {
        List<Machine> list = new ArrayList<>();
        String sql = "SELECT * FROM machine where `salle_id`="+salle_Id+"";
        connect();
        PreparedStatement ps = jdbcConntection.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            Long id = rs.getLong("id");
            String reference = rs.getString("reference");
            String type = rs.getString("type");
            Date dateAchat = rs.getDate("dateAchat");
            Long salleId = rs.getLong("salle_id");
            Machine machine = new Machine(id, salleId,reference,type,dateAchat,null);
            list.add(machine);
        }
        return list;
    }

    public List<Machine> getByDate(Date dateD, Date dateF) throws SQLException {
        List<Machine> list = new ArrayList<>();
        String sql = "select * from machine where `dateAchat` between '"+dateD.toString()+"' and '"+dateF.toString()+"'";
        connect();
        PreparedStatement ps = jdbcConntection.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            Long id = rs.getLong("id");
            String reference = rs.getString("reference");
            String type = rs.getString("type");
            Date dateAchat = rs.getDate("dateAchat");
            Long salleId = rs.getLong("salle_id");
            Machine machine = new Machine(id, salleId,reference,type,dateAchat,null);
            list.add(machine);
        }
        return list;
    }

    public List<ChartData> getDashboardData() throws SQLException {
        List<ChartData> list = new ArrayList<>();
        String sql = "select s.code as 'salle',count(m.id) as 'machine' from machine m inner join salle s on m.salle_id=s.id  group by s.code";
        connect();
        PreparedStatement ps = jdbcConntection.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            int machineCount = rs.getInt("machine");
            String salleCode = rs.getString("salle");

            ChartData data = new ChartData(salleCode,machineCount);
            list.add(data);
        }
        return list;
    }
}
