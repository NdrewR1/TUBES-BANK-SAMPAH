package com.example.BankSampah.model.transaksiMasuk;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

public interface TransaksiMasukRepository {
    List<TransaksiMasuk> findAll();
    List<TransaksiMasuk> findByDate(Timestamp time);
    int addTransaksiMasuk(int idPengguna);
    TransaksiMasuk mapRowToTransaksiMasuk(ResultSet resultSet, int rowNum) throws SQLException;
}
