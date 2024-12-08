package com.example.BankSampah.model.user;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class JdbcUserRepository implements UserRepository{
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<User> findAll() {
        String sql = "SELECT * FROM Pengguna";
        return jdbcTemplate.query(sql, this::mapRowToUser);
    }

    @Override
    public List<User> findByEmail(String email) {
        String sql = "SELECT * FROM Pengguna WHERE email = ?";
        return jdbcTemplate.query(sql, this::mapRowToUser,email);
    }

    public List<User> findByName(String nama){
        String sql = "SELECT * FROM Pengguna WHERE nama iLIKE ?";
        return jdbcTemplate.query(sql, this::mapRowToUser, "%" + nama + "%");
    }
    

    @Override
    public int updateUser(int idPengguna, String nama, String noHp, String alamat, String email) {
        String sql = "UPDATE Pengguna SET nama = ?, noHp = ?, alamat = ?, email = ? WHERE idPengguna = ?";
        return jdbcTemplate.update(sql, nama, noHp, alamat, email, idPengguna);
    }

    @Override
    public User mapRowToUser(ResultSet resultSet, int rowNum) throws SQLException {
        return new User(
            resultSet.getString("email"),
            resultSet.getString("password"),
            resultSet.getString("role"),
            resultSet.getInt("idPengguna"),
            resultSet.getString("noHp"),
            resultSet.getString("alamat"),
            resultSet.getInt("idKel"),
            resultSet.getString("nama")
        );
    }

    @Override
    public int addUser(String nama, String password, String noHp, String alamat, String email, int idKel) {
        String sql = "INSERT INTO pengguna(nama,password,noHp,alamat,email,idKel) VALUES(?,?,?,?,?,?)";
        return jdbcTemplate.update(sql,nama,password,noHp,alamat,email,idKel);
    }

    @Override
    public int addAdmin(String nama, String password, String noHp, String alamat, String email, int idKel,String role) {
        String sql = "INSERT INTO pengguna(nama,password,noHp,alamat,email,idKel,role) VALUES('Admin',?,'111','jln cim','admin','1','admin')";
        return jdbcTemplate.update(sql,password);
    }
}
