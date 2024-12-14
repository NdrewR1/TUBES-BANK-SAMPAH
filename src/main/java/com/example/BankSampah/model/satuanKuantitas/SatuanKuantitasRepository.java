package com.example.BankSampah.model.satuanKuantitas;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public interface SatuanKuantitasRepository {
    List<SatuanKuantitas> findAll();
    List<SatuanKuantitas> findBy(String namaSatuanKuantitas);
    List<SatuanKuantitas> findByIdSK(int idSK);
    int addSK(String nama);
    SatuanKuantitas mapRowToSatuanKuantitas(ResultSet resultSet, int rowNum) throws SQLException;
}
