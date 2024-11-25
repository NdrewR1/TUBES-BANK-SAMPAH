package com.example.BankSampah.model.invent;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public interface InventoryRepository {
    Inventory mapRowToInventory(ResultSet resultSet, int rowNum) throws SQLException;
    List<Inventory> findAll();
    List<Inventory> findByIdSampah(int idSampah);
    int updateKuantitas(int kuantitas, int idSampah);
}

