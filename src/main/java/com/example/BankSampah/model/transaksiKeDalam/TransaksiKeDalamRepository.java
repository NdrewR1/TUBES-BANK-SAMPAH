package com.example.BankSampah.model.transaksiKeDalam;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public interface TransaksiKeDalamRepository {
    List<TransaksiKeDalam> findAll();
    List<TransaksiKeDalam> findByNama(String namapengguna);
    TransaksiKeDalam mapRowToSatuanKuantitas(ResultSet resultSet, int rowNum) throws SQLException;
}
