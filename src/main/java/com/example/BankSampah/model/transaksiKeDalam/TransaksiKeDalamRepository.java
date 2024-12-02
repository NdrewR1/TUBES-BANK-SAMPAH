package com.example.BankSampah.model.transaksiKeDalam;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

public interface TransaksiKeDalamRepository {
    List<TransaksiKeDalam> findAll();
    List<TransaksiKeDalam> findByNama(String namaPengguna);
    List<TransaksiKeDalam> findByDateRange(String namaPengguna, LocalDate startDate, LocalDate endDate);
    TransaksiKeDalam mapRowToSatuanKuantitas(ResultSet resultSet, int rowNum) throws SQLException;
}
