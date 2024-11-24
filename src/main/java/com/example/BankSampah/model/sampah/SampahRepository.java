package com.example.BankSampah.model.sampah;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface SampahRepository {
    Iterable<Sampah> findAll();
    Sampah mapRowToSampah(ResultSet resultSet, int rowNum) throws SQLException;
}
