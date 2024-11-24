package com.example.BankSampah.model.sampah;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class JdbcSampahRepository implements SampahRepository {
    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public Iterable<Sampah> findAll() {
        String sql =  "SELECT * FROM get_gambar_nama_harga_satuan";
        return jdbcTemplate.query(sql, this::mapRowToSampah);
    }

    @Override
    public Sampah mapRowToSampah(ResultSet resultSet, int rowNum) throws SQLException {
        return new Sampah(
            resultSet.getString("gambar"),
            resultSet.getString("nama"),
            resultSet.getInt("harga"),
            resultSet.getString("satuankuantitas")
        );
    }

        
}
