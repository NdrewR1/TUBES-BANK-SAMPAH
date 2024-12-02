package com.example.BankSampah.model.transaksiKePusat;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public interface TransaksiKePusatRepository {

    List<TransaksiKePusat> findAll();
    TransaksiKePusat mapRowToSatuanKuantitas(ResultSet resultSet, int rowNum) throws SQLException;
}