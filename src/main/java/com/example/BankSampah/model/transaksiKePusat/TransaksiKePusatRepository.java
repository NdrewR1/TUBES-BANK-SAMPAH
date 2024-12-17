package com.example.BankSampah.model.transaksiKePusat;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public interface TransaksiKePusatRepository {

    List<TransaksiKePusat> findAll();

    TransaksiKePusat mapRowToSatuanKuantitas(ResultSet resultSet, int rowNum) throws SQLException;

    List<Map<String, Object>> getLaporanSampah();

    List<Map<String, Object>> getSisaSampah();
}