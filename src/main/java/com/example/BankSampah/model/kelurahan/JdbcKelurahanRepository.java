package com.example.BankSampah.model.kelurahan;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class JdbcKelurahanRepository implements KelurahanRepository{
    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public List<Kelurahan> findAll() {
        String sql = "SELECT * FROM kelurahan";
        return jdbcTemplate.query(sql,this::mapRowToKelurahan);
    }

    @Override
    public Kelurahan mapRowToKelurahan(ResultSet resultSet, int rowNum) throws SQLException {
        return new Kelurahan(
            resultSet.getInt("idKel"),
            resultSet.getString("namaKel"),
            resultSet.getInt("idKec")
        );
    }

    @Override
    public List<Kelurahan> findByIdKec(int idKec) {
        String sql = "SELECT * FROM kelurahan WHERE idKec = ?";
        return jdbcTemplate.query(sql,this::mapRowToKelurahan, idKec);
    }

    @Override
    public List<Kelurahan> findByIdKel(int idKel) {
        String sql = "SELECT * FROM kelurahan WHERE idKel = ?";
        return jdbcTemplate.query(sql,this::mapRowToKelurahan, idKel);
    }
}
