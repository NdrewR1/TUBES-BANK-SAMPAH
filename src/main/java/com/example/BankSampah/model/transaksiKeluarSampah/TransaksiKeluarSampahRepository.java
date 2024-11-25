package com.example.BankSampah.model.transaksiKeluarSampah;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public interface TransaksiKeluarSampahRepository {
    List<TransaksiKeluarSampah> findAll();
    int addTransaksiKeluarSampah(int idTransaksiKeluar,
    int idSampah,
    int jumlahSampah,
    int idHarga);
    TransaksiKeluarSampah mapRowToTransaksiKeluarSampah(ResultSet resultSet, int rowNum) throws SQLException;
}
