package com.example.BankSampah.model.sampah;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public interface SampahRepository {
    Iterable<Sampah> findAll();
    int addSampah(String namaSampah, int idSatuanKuantitas);
    List<Sampah> findByNama(String namaSampah);
    int setHarga(String namaSampah, int idHargaSekarang);
    Sampah mapRowToSampah(ResultSet resultSet, int rowNum) throws SQLException;
}
