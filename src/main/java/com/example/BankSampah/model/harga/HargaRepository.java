package com.example.BankSampah.model.harga;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public interface HargaRepository {
    Harga mapRowToHarga(ResultSet resultSet, int rowNum) throws SQLException;
    List<Harga> findAll();
    int addHarga(int idSampah, int hargaSampah);
    List<Harga> findByIdSampahHarga(int idSampah, int hargaSampah);
    List<Harga> findByIdSampah(int idSampah);
}
