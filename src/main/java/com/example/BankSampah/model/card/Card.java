package com.example.BankSampah.model.card;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class Card {
    String nama;
    int harga;
    String satuanKuantitas;
    int kuantitas;
}
