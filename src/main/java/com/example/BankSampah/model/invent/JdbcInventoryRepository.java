package com.example.BankSampah.model.invent;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class JdbcInventoryRepository implements InventoryRepository {
    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public List<Inventory> findAll() {
        String sql = "SELECT * FROM Inventory_Sampah";
        return jdbcTemplate.query(sql, this::mapRowToInventory);
    }

    @Override
    public List<Inventory> findByIdSampah(int idSampah) {
        String sql = "SeLECT * FROM Inventory_Sampah WHERE idSampah = ?";
        return jdbcTemplate.query(sql, this::mapRowToInventory, idSampah);
    }

    @Override
    public Inventory mapRowToInventory(ResultSet resultSet, int rowNum) throws SQLException {
        return new Inventory(
            resultSet.getInt("idInventorySampah"), 
            resultSet.getInt("idSampah"), 
            resultSet.getInt("kuantitas"));
    }

    @Override
    public int updateKuantitas(int kuantitas, int idSampah) {
        String sql = "UPDATE Inventory_Sampah SET kuantitas = ? WHERE idSampah = ?";
        return jdbcTemplate.update(sql, kuantitas, idSampah);
    }

    
}
