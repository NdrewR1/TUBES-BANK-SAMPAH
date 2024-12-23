package com.example.BankSampah.model.transaksiKeDalam;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

public interface TransaksiKeDalamRepository {
    List<TransaksiKeDalam> findAll();
    List<TransaksiKeDalam> findByUsername(String name);
    TransaksiKeDalam mapRowToSatuanKuantitas(ResultSet resultSet, int rowNum) throws SQLException;
    List<TransaksiKeDalam> findPendapatanByDateRange(Timestamp startDate, Timestamp endDate, String name);
    List<TransaksiKeDalam> findTransaksiByDateRange(Timestamp startDate, Timestamp endDate);
    List<Map<String, Object>> getLaporanSampah();
}
