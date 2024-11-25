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
    nama VARCHAR(255) NOT NULL,
    alamat VARCHAR(255), 
    email VARCHAR(255) UNIQUE,
    role rolePengguna NOT NULL DEFAULT 'pengguna',
    idKel INT REFERENCES Kelurahan(idKel) NOT NULL
);

CREATE TYPE tipeTransaksiEnum AS ENUM ('SatuanKuantitas', 'keluar');

CREATE TABLE SatuanKuantitas (
    idSatuanKuantitas SERIAL PRIMARY KEY,
    namaSatuanKuantitas VARCHAR(10) NOT NULL UNIQUE,
    isActive BOOLEAN DEFAULT TRUE NOT NULL
);

CREATE TABLE Sampah (
    idSampah SERIAL PRIMARY KEY,
    namaSampah VARCHAR(40) NOT NULL UNIQUE,
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

CREATE TABLE Inventory_Sampah(
    idInventorySampah SERIAL PRIMARY KEY,
    idSampah INT NOT NULL REFERENCES Sampah(idSampah) UNIQUE,
    kuantitas INT NOT NULL DEFAULT 0
);

CREATE TABLE Transaksi_Masuk (
    idTransaksiMasuk SERIAL PRIMARY KEY,
    tanggal TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    idPengguna INT REFERENCES pengguna(idPengguna) NOT NULL
);

CREATE TABLE Transaksi_Keluar(
    idTransaksiKeluar SERIAL PRIMARY KEY,
    tanggal TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE Transaksi_Keluar_Sampah(
    idTransaksiKeluar INT REFERENCES Transaksi_Keluar(idTransaksiKeluar),
    idSampah INT NOT NULL REFERENCES Sampah(idSampah),
    jumlahSampah INT NOT NULL,
    idHarga INT REFERENCES Harga(idHarga) NOT NULL,
    PRIMARY KEY (idTransaksiKeluar, idSampah)
);


CREATE TABLE Transaksi_Masuk_Sampah (
    idTransaksiMasuk INT REFERENCES Transaksi_Masuk(idTransaksiMasuk),
    idSampah INT REFERENCES Sampah(idSampah) NOT NULL,
    idHarga INT REFERENCES Harga(idHarga) NOT NULL,
    jumlahSampah INT NOT NULL,
    PRIMARY KEY (idTransaksiMasuk, idSampah)
);

CREATE OR REPLACE FUNCTION check_jumlahSampah()
RETURNS TRIGGER AS $$
BEGIN
    PERFORM 1 
    FROM Inventory_Sampah 
    WHERE idSampah = NEW.idSampah AND kuantitas >= NEW.jumlahSampah;

    IF NOT FOUND THEN
        RAISE EXCEPTION 'Insufficient inventory for idSampah: %, requested: %, available: %', 
                        NEW.idSampah, NEW.jumlahSampah, 
                        (SELECT kuantitas FROM Inventory_Sampah WHERE idSampah = NEW.idSampah);
    END IF;

    RETURN NEW;
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER before_insert_transaksi_keluar_sampah
BEFORE INSERT ON Transaksi_Keluar_Sampah
FOR EACH ROW
EXECUTE FUNCTION check_jumlahSampah();



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

CREATE VIEW card AS
SELECT 
    s.namaSampah AS nama, 
    h.hargaSampah AS harga, 
    sk.namaSatuanKuantitas AS satuanKuantitas, 
    inven.kuantitas as kuantitas
FROM 
    Inventory_Sampah inven 
JOIN 
    Sampah s ON inven.idSampah = s.idSampah
JOIN 
    Harga h ON s.idHargaSekarang = h.idHarga
JOIN 
    SatuanKuantitas sk ON sk.idSatuanKuantitas = s.idSatuanKuantitas;

CREATE VIEW transaksiKePusat AS
SELECT
    tk.tanggal AS tanggal,
    s.namaSampah AS namaSampah,
    tks.jumlahSampah AS jumlahSampah,
    (tks.jumlahSampah * h.hargaSampah) AS subTotal
FROM
    Transaksi_Keluar tk
JOIN
    Transaksi_Keluar_Sampah tks ON tk.idTransaksiKeluar = tks.idTransaksiKeluar
JOIN
    Sampah s ON tks.idSampah = s.idSampah
JOIN
    Harga h ON s.idHargaSekarang = h.idHarga;

CREATE VIEW transaksiMasuk AS
SELECT
    tm.tanggal AS tanggal,
    p.nama AS namaPengguna,
    s.namaSampah AS namaSampah,
    tms.jumlahSampah AS jumlahSampah,
    sk.namaSatuanKuantitas AS satuanKuantitas,
    (tms.jumlahSampah * h.hargaSampah) AS subTotal,
    SUM(tms.jumlahSampah * h.hargaSampah) OVER (PARTITION BY tm.tanggal, p.nama) AS total
FROM
    Transaksi_Masuk tm
JOIN
    Transaksi_Masuk_Sampah tms ON tm.idTransaksiMasuk = tms.idTransaksiMasuk
JOIN
    Sampah s ON tms.idSampah = s.idSampah
JOIN
    Harga h ON tms.idHarga = h.idHarga
JOIN
    Pengguna p ON tm.idPengguna = p.idPengguna
JOIN
    SatuanKuantitas sk ON s.idSatuanKuantitas = sk.idSatuanKuantitas;

INSERT INTO Kecamatan(namaKec) VALUES('kecamatan1');
INSERT INTO Kelurahan(namaKel,idKec) VALUES('kelurahan1','1');
