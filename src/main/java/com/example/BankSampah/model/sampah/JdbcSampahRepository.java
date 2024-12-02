package com.example.BankSampah.model.sampah;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class JdbcSampahRepository implements SampahRepository {
    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public Iterable<Sampah> findAll() {
        String sql =  "SELECT * FROM get_gambar_nama_harga_satuan";
        return jdbcTemplate.query(sql, this::mapRowToSampah);
    }

    @Override
    public int addSampah(String namaSampah, int idSatuanKuantitas) {
        String sql = "INSERT INTO Sampah(namaSampah,idSatuanKuantitas) VALUES(?,?)";
        return jdbcTemplate.update(sql, namaSampah,idSatuanKuantitas);
    }

    @Override
    public List<Sampah> findByNama(String namaSampah) {
        String sql = "SELECT * FROM Sampah WHERE namaSampah = ?";
        return jdbcTemplate.query(sql, this::mapRowToSampah, namaSampah);
    }

    @Override
    public int setHarga(String namaSampah, int idHargaSekarang) {
        String sql = "UPDATE Sampah SET idHargaSekarang = ? WHERE namaSampah = ?";
        return jdbcTemplate.update(sql,idHargaSekarang,namaSampah);
    }

    @Override
    public int addToInvent(int idSampah) {
        String sql = "INSERT INTO Inventory_Sampah(idSampah) VALUES(?)";
        return jdbcTemplate.update(sql, idSampah);
    }

    @Override
    public Sampah mapRowToSampah(ResultSet resultSet, int rowNum) throws SQLException {
        return new Sampah(
            resultSet.getInt("idSampah"),
            resultSet.getString("urlGambar"),
            resultSet.getString("namaSampah"),
            resultSet.getInt("idHargaSekarang"),
            resultSet.getInt("idSatuanKuantitas")
        );
    }

        
}
