package com.example.BankSampah.model.kecamatan;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class JdbcKecamatanRepository implements KecamatanRepository{
    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public List<Kecamatan> findAll() {
        String sql = "SELECT * FROM kecamatan";
        return jdbcTemplate.query(sql,this::mapRowToKecamatan);
    }

    @Override
    public Kecamatan mapRowToKecamatan(ResultSet resultSet, int rowNum) throws SQLException {
        return new Kecamatan(
            resultSet.getInt("idKec"),
            resultSet.getString("namaKec")
        );
    }

    
}
