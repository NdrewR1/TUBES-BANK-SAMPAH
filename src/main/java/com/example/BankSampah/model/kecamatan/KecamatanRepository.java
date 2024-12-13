package com.example.BankSampah.model.kecamatan;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public interface KecamatanRepository {
    List<Kecamatan> findAll();
    Kecamatan mapRowToKecamatan(ResultSet resultSet, int rowNum) throws SQLException;
    List<Kecamatan> findByIdKec(int idKec);

    
}
