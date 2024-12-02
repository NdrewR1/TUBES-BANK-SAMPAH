package com.example.BankSampah.model.namahargasatuan;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface NamaHargaSatuanRepository {
    Iterable<NamaHargaSatuan> findAll();
    NamaHargaSatuan mapRowToNamaHargaSatuan(ResultSet resultSet, int rowNum) throws SQLException;
}