package com.example.BankSampah.model.transaksiKeDalam;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class JdbcTransaksiKeDalamRepository implements TransaksiKeDalamRepository {
    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public List<TransaksiKeDalam> findAll() {
        String sql = "SELECT * FROM transaksiMasuk";
        return jdbcTemplate.query(sql,this::mapRowToSatuanKuantitas);
    }

    @Override
    public List<TransaksiKeDalam> findByUsername(String name) {
        String sql = "SELECT * FROM transaksiMasuk WHERE namapengguna = ?";
        return jdbcTemplate.query(sql, this::mapRowToSatuanKuantitas, name);
    }

    @Override
    public List<Map<String, Object>> getLaporanSampah() {
        String sql = "SELECT namasampah, satuankuantitas, SUM(jumlahsampah) AS total_kuantitas " +
                     "FROM transaksimasuk " +
                     "GROUP BY namasampah, satuankuantitas";
        return jdbcTemplate.queryForList(sql);
    }
    
    @Override
    public List<TransaksiKeDalam> findPendapatanByDateRange(Timestamp startDate, Timestamp endDate, String name) {
        String sql = "SELECT tanggal, SUM(subtotal) AS total_pendapatan FROM transaksiMasuk " +
                     "WHERE namapengguna = ? AND tanggal BETWEEN ? AND ? GROUP BY tanggal ORDER BY tanggal ASC";
        return jdbcTemplate.query(sql, (rs, rowNum) -> {
            TransaksiKeDalam transaksi = new TransaksiKeDalam();
            transaksi.setTanggal(rs.getTimestamp("tanggal"));
            transaksi.setTotal(rs.getInt("total_pendapatan"));
            return transaksi;
        }, name, startDate, endDate); // Parameter nama pengguna harus yang pertama
    }

    @Override
    public List<TransaksiKeDalam> findTransaksiByDateRange(Timestamp startDate, Timestamp endDate){
        String sql = "SELECT * FROM transaksiMasuk WHERE tanggal BETWEEN ? AND ?";
        return jdbcTemplate.query(sql, this::mapRowToSatuanKuantitas, startDate, endDate);
    }

    @Override
    public TransaksiKeDalam mapRowToSatuanKuantitas(ResultSet resultSet, int rowNum) throws SQLException {
        return new TransaksiKeDalam(
            resultSet.getTimestamp("tanggal"), 
            resultSet.getString("namaPengguna"), 
            resultSet.getString("namaSampah"),
            resultSet.getInt("jumlahSampah"),
            resultSet.getString("satuanKuantitas"), 
            resultSet.getInt("subTotal"),
            resultSet.getInt("total"));
    }


}
