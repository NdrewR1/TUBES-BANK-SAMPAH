package com.example.BankSampah.model.transaksiKePusat;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class JdbcTransaksiKePusatRepository implements TransaksiKePusatRepository {
    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public List<TransaksiKePusat> findAll() {
        String sql = "SELECT * FROM transaksiKePusat";
        return jdbcTemplate.query(sql, this::mapRowToSatuanKuantitas);
    }

    @Override
    public TransaksiKePusat mapRowToSatuanKuantitas(ResultSet resultSet, int rowNum) throws SQLException {
        return new TransaksiKePusat(
            resultSet.getString("tanggal"), 
        resultSet.getString("namaSampah"), 
        resultSet.getInt("jumlahSampah"), 
        resultSet.getString("satuanKuantitas"),
        resultSet.getDouble("subTotal"),
        resultSet.getInt("total"));
    }

    
}
