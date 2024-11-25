package com.example.BankSampah.model.transaksiKeluar;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

public interface TransaksiKeluarRepository {
    List<TransaksiKeluar> findAll();
    List<TransaksiKeluar> findByDate(Timestamp time);
    int addTransaksiKeluar();
    TransaksiKeluar mapRowToTransaksiKeluar(ResultSet resultSet, int rowNum) throws SQLException;
}
