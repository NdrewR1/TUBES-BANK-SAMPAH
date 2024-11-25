package com.example.BankSampah.model.transaksiKeluarSampah;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TransaksiKeluarSampah {
    int idTransaksiKeluar;
    int idSampah;
    int jumlahSampah;
    int idHarga;
}
