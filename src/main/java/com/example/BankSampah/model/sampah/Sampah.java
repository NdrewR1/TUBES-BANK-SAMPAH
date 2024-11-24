package com.example.BankSampah.model.sampah;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Sampah {
    String gambar;
    String nama;
    int harga;
    String satuankuantitas;
}
