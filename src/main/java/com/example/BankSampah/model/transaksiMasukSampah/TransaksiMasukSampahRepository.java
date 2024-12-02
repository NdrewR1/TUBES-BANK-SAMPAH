package com.example.BankSampah.model.transaksiMasukSampah;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;


public interface TransaksiMasukSampahRepository {
    List<TransaksiMasukSampah> findAll();
    int addTransaksiMasukSampah(int idTransaksiMasuk,
    int idSampah,
    int idHarga,
    int jumlahSampah);
    TransaksiMasukSampah mapRowToTransaksiMasukSampah(ResultSet resultSet, int rowNum) throws SQLException;
}

