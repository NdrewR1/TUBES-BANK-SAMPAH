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
            resultSet.getString("password")
        );
    }

    
}
