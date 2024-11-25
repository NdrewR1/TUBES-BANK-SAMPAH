package com.example.BankSampah.model.transaksiKeluarSampah;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class JdbcTransaksiKeluarSampah implements TransaksiKeluarSampahRepository {
    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public int addTransaksiKeluarSampah(int idTransaksiKeluar, int idSampah, int jumlahSampah, int idHarga) {
        String sql = "INSERT INTO Transaksi_Keluar_Sampah VALUES(?,?,?,?)";
        return jdbcTemplate.update(sql,idTransaksiKeluar,idSampah,jumlahSampah,idHarga);
    }

    @Override
    public List<TransaksiKeluarSampah> findAll() {
        String sql = "SELECT * FROM Transaksi_Keluar_Sampah";
        return jdbcTemplate.query(sql,this::mapRowToTransaksiKeluarSampah);
    }

    @Override
    public TransaksiKeluarSampah mapRowToTransaksiKeluarSampah(ResultSet resultSet, int rowNum) throws SQLException {
        return new TransaksiKeluarSampah(resultSet.getInt("idTransaksiKeluar"), 
        resultSet.getInt("idSampah"),
        resultSet.getInt("jumlahSampah"),
        resultSet.getInt("idHarga"));
    }

    
}
