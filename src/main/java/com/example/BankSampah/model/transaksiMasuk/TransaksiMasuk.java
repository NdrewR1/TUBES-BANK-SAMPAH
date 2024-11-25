package com.example.BankSampah.model.transaksiMasuk;

import java.sql.Timestamp;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TransaksiMasuk {
    int idTransaksiMasuk;
    Timestamp tanggal;
    int idPengguna;
}
