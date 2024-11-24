package com.example.BankSampah.model.sampah;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Sampah {
    int idSampah;
    String urlGambar;
    String namaSampah;
    int idHargaSekarang;
    int idSatuanKuantitas;
}
