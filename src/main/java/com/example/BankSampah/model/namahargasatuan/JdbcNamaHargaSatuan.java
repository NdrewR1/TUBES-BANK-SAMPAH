package com.example.BankSampah.model.namahargasatuan;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class JdbcNamaHargaSatuan implements NamaHargaSatuanRepository {
    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public Iterable<NamaHargaSatuan> findAll() {
        String sql =  "SELECT * FROM get_gambar_nama_harga_satuan";
        return jdbcTemplate.query(sql, this::mapRowToNamaHargaSatuan);
    }

    @Override
    public NamaHargaSatuan mapRowToNamaHargaSatuan(ResultSet resultSet, int rowNum) throws SQLException {
        return new NamaHargaSatuan(
            resultSet.getString("gambar"), 
            resultSet.getString("nama"), 
            resultSet.getInt("harga"),
            resultSet.getString("satuanKuantitas")
            );
    }

    
}
