package com.example.BankSampah.model.transaksiKeluar;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class JdbcTransaksiKeluarRepository implements TransaksiKeluarRepository {
    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public List<TransaksiKeluar> findAll() {
        String sql = "SELECT * FROM Transaksi_Keluar";
        return jdbcTemplate.query(sql,this::mapRowToTransaksiKeluar);
    }

    @Override
    public List<TransaksiKeluar> findByDate(Timestamp time) {
        String sql = "SELECT * FROM Transaksi_Keluar WHERE tanggal = ?";
        return jdbcTemplate.query(sql,this::mapRowToTransaksiKeluar, time);
    }

    @Override
    public int addTransaksiKeluar(Timestamp time) {
        String sql = "INSERT INTO Transaksi_Keluar(tanggal) VALUES(?)";
        return jdbcTemplate.update(sql,time);
    }

    @Override
    public TransaksiKeluar mapRowToTransaksiKeluar(ResultSet resultSet, int rowNum) throws SQLException {
        return new TransaksiKeluar(resultSet.getInt("idTransaksiKeluar"), resultSet.getTimestamp("tanggal"));
    }
    
}
