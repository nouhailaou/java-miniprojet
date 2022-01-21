package com.mini.projet.dao;

import java.sql.SQLException;
import java.util.List;

public interface Idao<T> {
    boolean update(T entity) throws SQLException;

    boolean delete(Long id) throws SQLException;

    boolean create(T entity) throws SQLException;

    List<T> findAll() throws SQLException;

    T findOne(Long id) throws SQLException;
}