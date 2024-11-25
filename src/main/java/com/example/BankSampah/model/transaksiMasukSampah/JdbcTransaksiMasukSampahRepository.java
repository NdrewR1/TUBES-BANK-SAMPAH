package com.example.BankSampah.model.transaksiMasukSampah;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class JdbcTransaksiMasukSampahRepository implements TransaksiMasukSampahRepository {
    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public int addTransaksiMasukSampah(int idTransaksiMasuk, int idSampah, int idHarga, int jumlahSampah) {
        String sql = "INSERT INTO Transaksi_Masuk_Sampah(idTransaksiMasuk, idSampah, idHarga, jumlahSampah) VALUES(?,?,?,?)";
        return jdbcTemplate.update(sql, idTransaksiMasuk,idSampah,idHarga,jumlahSampah);
    }

    @Override
    public List<TransaksiMasukSampah> findAll() {
        String sql = "SELECT * FROM Transaksi_Masuk_Sampah";
        return jdbcTemplate.query(sql, this::mapRowToTransaksiMasukSampah);
    }

    @Override
    public TransaksiMasukSampah mapRowToTransaksiMasukSampah(ResultSet resultSet, int rowNum) throws SQLException {
        return new TransaksiMasukSampah(
            resultSet.getInt("idTransaksiMasuk"), 
            resultSet.getInt("idSampah"), 
            resultSet.getInt("idHarga"), 
            resultSet.getInt("jumlahSampah"));
    }

    
}
