package com.example.BankSampah.model.transaksiKeDalam;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class JdbcTransaksiKeDalamRepository implements TransaksiKeDalamRepository {
    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public List<TransaksiKeDalam> findAll() {
        String sql = "SELECT * FROM transaksiMasuk";
        return jdbcTemplate.query(sql,this::mapRowToSatuanKuantitas);
    }

    @Override
    public List<TransaksiKeDalam> findByUsername(String name) {
        String sql = "SELECT * FROM transaksiMasuk WHERE namapengguna = ?";
        return jdbcTemplate.query(sql, this::mapRowToSatuanKuantitas, name);
    }

    @Override
    public TransaksiKeDalam mapRowToSatuanKuantitas(ResultSet resultSet, int rowNum) throws SQLException {
        return new TransaksiKeDalam(
            resultSet.getTimestamp("tanggal"), 
            resultSet.getString("namaPengguna"), 
            resultSet.getString("namaSampah"),
            resultSet.getInt("jumlahSampah"),
            resultSet.getString("satuanKuantitas"), 
            resultSet.getInt("subTotal"),
            resultSet.getInt("total"));
    }
}
