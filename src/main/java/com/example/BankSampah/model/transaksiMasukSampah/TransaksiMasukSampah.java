package com.example.BankSampah.model.transaksiMasukSampah;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TransaksiMasukSampah {
    int idTransaksiMasuk;
    int idSampah;
    int idHarga;
    int jumlahSampah;
}
