package com.example.BankSampah.model.card;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public interface CardRepository {
    List<Card> findAll();
    Card mapRowToCard(ResultSet resultSet, int rowNum) throws SQLException;
}
