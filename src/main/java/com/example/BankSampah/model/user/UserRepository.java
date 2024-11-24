package com.example.BankSampah.model.user;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public interface UserRepository {
    List<User> findByEmail(String email);
    User mapRowToUser(ResultSet resultSet, int rowNum) throws SQLException;
    int addUser(String nama, String password, String noHp, String alamat, String email, int idKel);
    int addAdmin(String nama, String password, String noHp, String alamat, String email, int idKel, String role);
}
