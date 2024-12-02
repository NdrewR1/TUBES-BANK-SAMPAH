package com.example.BankSampah.model.transaksiMasuk;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class JdbcTransaksiMasukRepository implements TransaksiMasukRepository {
    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public List<TransaksiMasuk> findAll() {
        String sql = "SELECT * FROM Transaksi_Masuk";
        return jdbcTemplate.query(sql,this::mapRowToTransaksiMasuk);
    }

    @Override
    public List<TransaksiMasuk> findByDate(Timestamp time) {
        String sql = "SELECT * FROM Transaksi_Masuk WHERE tanggal = ?";
        return jdbcTemplate.query(sql,this::mapRowToTransaksiMasuk, time);
    }

    @Override
    public int addTransaksiMasuk(int idPengguna) {
        String sql = "INSERT INTO Transaksi_Masuk(idPengguna) VALUES(?) RETURNING idTransaksiMasuk";
        int idTransaksiMasuk = jdbcTemplate.queryForObject(sql,Integer.class,idPengguna);
        return idTransaksiMasuk;
    }

    @Override
    public TransaksiMasuk mapRowToTransaksiMasuk(ResultSet resultSet, int rowNum) throws SQLException {
        return new TransaksiMasuk(resultSet.getInt("idTransaksiMasuk"), 
        resultSet.getTimestamp("tanggal"),
        resultSet.getInt("idPengguna"));
    }
    
}
