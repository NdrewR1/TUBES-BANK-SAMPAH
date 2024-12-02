package com.example.BankSampah.model.kelurahan;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public interface KelurahanRepository {
    List<Kelurahan> findAll();
    List<Kelurahan> findByIdKec(int idKec);
    Kelurahan mapRowToKelurahan(ResultSet resultSet, int rowNum) throws SQLException;
}
