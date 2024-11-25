package com.example.BankSampah.model.transaksiKePusat;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TransaksiKePusat {
    private String tanggal;
    private String namaSampah;
    private int jumlahSampah;
    private double subTotal;
}


