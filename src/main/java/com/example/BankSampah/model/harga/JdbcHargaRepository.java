package com.example.BankSampah.model.harga;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class JdbcHargaRepository implements HargaRepository {
    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public List<Harga> findAll() {
        String sql = "SELECT * FROM Harga";
        return jdbcTemplate.query(sql, this::mapRowToHarga);
    }

    @Override
    public int addHarga(int idSampah, int hargaSampah) {
        String sql = "INSERT INTO Harga(idSampah,hargaSampah) VALUES (?,?)";
        return jdbcTemplate.update(sql, idSampah,hargaSampah);
    }

    @Override
    public List<Harga> findByIdSampahHarga(int idSampah, int hargaSampah) {
        String sql = "SELECT * FROM Harga WHERE idSampah = ? AND hargaSampah = ? ORDER BY tanggalUbah DESC";
        return jdbcTemplate.query(sql, this::mapRowToHarga,idSampah,hargaSampah);
    }

    @Override
    public List<Harga> findByIdSampah(int idSampah) {
        String sql = "SELECT * FROM Harga WHERE idSampah = ? ORDER BY tanggalUbah DESC";
        return jdbcTemplate.query(sql, this::mapRowToHarga,idSampah);
    }

    @Override
    public Harga mapRowToHarga(ResultSet resultSet, int rowNum) throws SQLException {
        return new Harga(
            resultSet.getInt("idHarga"), 
            resultSet.getInt("idSampah"), 
            resultSet.getInt("hargaSampah"));
    }

    
}
