package com.example.BankSampah.model.transaksiKeDalam;

import java.sql.Timestamp;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TransaksiKeDalam {
    Timestamp tanggal;
    String namaPengguna;
    String namaSampah;
    int jumlahSampah;
    String satuanKuantitas;
    int subTotal;
    int total;

    public TransaksiKeDalam() {}
}
