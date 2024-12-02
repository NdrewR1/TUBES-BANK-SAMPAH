package com.example.BankSampah.model.transaksiKeluar;

import java.sql.Timestamp;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TransaksiKeluar {
    int idTransaksiKeluar;
    Timestamp tanggal;
}
