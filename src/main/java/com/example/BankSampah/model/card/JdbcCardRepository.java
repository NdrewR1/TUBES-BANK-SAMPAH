package com.example.BankSampah.model.card;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class JdbcCardRepository implements CardRepository {
    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public List<Card> findAll() {
        String sql = "SELECT * FROM card";
        return jdbcTemplate.query(sql,this::mapRowToCard);
    }

    @Override
    public Card mapRowToCard(ResultSet resultSet, int rowNum) throws SQLException {
        return new Card(
            resultSet.getString("nama"), 
            resultSet.getInt("harga"),
            resultSet.getString("satuanKuantitas"), 
            resultSet.getInt("kuantitas"));
    }

    
}
