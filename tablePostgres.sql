CREATE TABLE Kecamatan (
    idKec SERIAL PRIMARY KEY,
    namaKec VARCHAR(20) NOT NULL
);

CREATE TABLE Kelurahan (
    idKel SERIAL PRIMARY KEY,
    namaKel VARCHAR(50) NOT NULL,
    idKec INT REFERENCES Kecamatan(idKec) NOT NULL
);

CREATE TYPE rolePengguna AS ENUM ('admin', 'pengguna');

CREATE TABLE Pengguna (
    idPengguna SERIAL PRIMARY KEY,
    password VARCHAR(255) NOT NULL,
    noHp VARCHAR(30),
    alamat VARCHAR(255), 
    email VARCHAR(255) UNIQUE,
    role rolePengguna NOT NULL DEFAULT 'pengguna',
    idKel INT REFERENCES Kelurahan(idKel) NOT NULL
);

CREATE TYPE tipeTransaksiEnum AS ENUM ('maSatuanKuantitas', 'keluar');

CREATE TABLE Transaksi (
    idTransaksi SERIAL PRIMARY KEY,
    tanggal TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    tipeTransaksi tipeTransaksiEnum NOT NULL DEFAULT 'maSatuanKuantitas',
    idPengguna INT REFERENCES pengguna(idPengguna) NOT NULL
);

CREATE TABLE Jenis_Sampah (
    idJenisSampah SERIAL PRIMARY KEY,
    namaJenisSampah VARCHAR(20) NOT NULL UNIQUE,
    isActive BOOLEAN DEFAULT TRUE NOT NULL
);

CREATE TABLE SatuanKuantitas (
    idSatuanKuantitas SERIAL PRIMARY KEY,
    namaSatuanKuantitas VARCHAR(10) NOT NULL UNIQUE,
    isActive BOOLEAN DEFAULT TRUE NOT NULL
);

CREATE TABLE Sampah (
    idSampah SERIAL PRIMARY KEY,
    namaSampah VARCHAR(40) NOT NULL UNIQUE,
    idJenisSampah INT NOT NULL REFERENCES Jenis_Sampah(idJenisSampah),
    urlGambar VARCHAR(255),
    idSatuanKuantitas INT REFERENCES SatuanKuantitas(idSatuanKuantitas),
    isActive BOOLEAN DEFAULT TRUE NOT NULL
);

CREATE TABLE Harga (
    idHarga SERIAL PRIMARY KEY,
    idSampah INT REFERENCES Sampah(idSampah),
    tanggalUbah TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    hargaSampah INT NOT NULL,
    UNIQUE (idSampah, tanggalUbah)
);

ALTER TABLE Sampah
ADD COLUMN idHargaSekarang INT REFERENCES Harga(idHarga);

CREATE TABLE Transaksi_Sampah (
    idTransaksi INT REFERENCES Transaksi(idTransaksi),
    idSampah INT REFERENCES Sampah(idSampah) NOT NULL,
    idHarga INT REFERENCES Harga(idHarga) NOT NULL,
    jumlahSampah INT NOT NULL,
    PRIMARY KEY (idTransaksi, idSampah)
);

CREATE VIEW get_gambar_nama_harga_satuan AS
SELECT
    s.urlGambar AS gambar,
    s.namaSampah AS nama,
    h.hargaSampah AS harga,
    sq.namaSatuanKuantitas AS satuanKuantitas
FROM
    Sampah s
JOIN
    Harga h ON s.idSampah = h.idSampah
JOIN
    SatuanKuantitas sq ON s.idSatuanKuantitas = sq.idSatuanKuantitas;