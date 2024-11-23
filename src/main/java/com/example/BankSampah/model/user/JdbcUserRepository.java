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
    public List<User> findByEmail(String email) {
        String sql = "SELECT * FROM Pengguna WHERE email = ?";
        return jdbcTemplate.query(sql, this::mapRowToUser,email);
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
            resultSet.getInt("idKel")
            ,resultSet.getString("nama")
        );
    }

    @Override
    public int addUser(String nama, String password, String noHp, String alamat, String email, int idKel) {
        String sql = "INSERT INTO pengguna(nama,password,noHp,alamat,email,idKel) VALUES('jos',?,'000','jln cig','email','1')";
        return jdbcTemplate.update(sql,password);
    }

    @Override
    public int addAdmin(String nama, String password, String noHp, String alamat, String email, int idKel,String role) {
        String sql = "INSERT INTO pengguna(nama,password,noHp,alamat,email,idKel,role) VALUES('drw',?,'111','jln cim','yahoo','1','admin')";
        return jdbcTemplate.update(sql,password);
    }
}