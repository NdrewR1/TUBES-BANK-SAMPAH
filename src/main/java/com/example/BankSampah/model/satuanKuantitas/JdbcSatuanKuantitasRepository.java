package com.example.BankSampah.model.satuanKuantitas;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class JdbcSatuanKuantitasRepository implements SatuanKuantitasRepository {
    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public List<SatuanKuantitas> findAll() {
        String sql = "SELECT * FROM SatuanKuantitas";
        return jdbcTemplate.query(sql,this::mapRowToSatuanKuantitas);
    }

    @Override
    public List<SatuanKuantitas> findBy(String namaSatuanKuantitas) {
        String sql = "SELECT * FROM SatuanKuantitas WHERE namaSatuanKuantitas = ?";
        return jdbcTemplate.query(sql, this::mapRowToSatuanKuantitas, namaSatuanKuantitas);
    }

    @Override
    public int addSK(String nama) {
        String sql = "INSERT INTO SatuanKuantitas(namaSatuanKuantitas) VALUES(?) ";
        return jdbcTemplate.update(sql, nama);
    }

    @Override
    public SatuanKuantitas mapRowToSatuanKuantitas(ResultSet resultSet, int rowNum) throws SQLException {
        return new SatuanKuantitas(
            resultSet.getInt("idSatuanKuantitas"), 
            resultSet.getString("namaSatuanKuantitas"));
    }

    
}
