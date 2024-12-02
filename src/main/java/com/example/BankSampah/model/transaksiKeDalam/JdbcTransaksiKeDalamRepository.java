package com.example.BankSampah.model.transaksiKeDalam;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
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
    public List<TransaksiKeDalam> findByNama(String namapengguna) {
        String sql = "SELECT * FROM transaksimasuk WHERE namapengguna = ?";
        return jdbcTemplate.query(sql, this::mapRowToSatuanKuantitas, namapengguna);
    }

    @Override
    public List<TransaksiKeDalam> findByDateRange(String namaPengguna, LocalDate starDate, LocalDate endDate) {
        String sql = "SELECT * FROM transaksimasuk WHERE namapengguna = ? AND tanggal BETWEEN ? AND ?";
        return jdbcTemplate.query(sql, this::mapRowToSatuanKuantitas, namaPengguna, starDate, endDate);
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
