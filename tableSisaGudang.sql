
INSERT INTO Pengguna (password, noHp, nama, alamat, email, role, idKel) VALUES
('password123', '081234567890', 'John Doe', 'Jl. Merdeka No. 1', 'johndoe@example.com', 'pengguna', 1),
('password456', '081234567891', 'Jane Smith', 'Jl. Kebangsaan No. 2', 'janesmith@example.com', 'pengguna', 2),
('password789', '081234567892', 'Alice Johnson', 'Jl. Kemerdekaan No. 3', 'alicej@example.com', 'pengguna', 3),
('password321', '081234567893', 'Bob Brown', 'Jl. Persatuan No. 4', 'bobbrown@example.com', 'pengguna', 4),
('password654', '081234567894', 'Charlie Green', 'Jl. Pahlawan No. 5', 'charliegreen@example.com', 'pengguna', 5);

INSERT INTO SatuanKuantitas (namaSatuanKuantitas, isActive) VALUES
	('Kilogram', TRUE), ('Liter', TRUE),
	('Unit', TRUE), ('Kubik', TRUE), ('Gram', TRUE);

INSERT INTO Sampah (namaSampah, urlGambar, idSatuanKuantitas, isActive) VALUES
('Plastik', 'https://example.com/plastik.jpg', 1, TRUE),
('Kaca', 'https://example.com/kaca.jpg', 1, TRUE),
('Kertas', 'https://example.com/kertas.jpg', 1, TRUE),
('Logam', 'https://example.com/logam.jpg', 3, TRUE),
('Organik', 'https://example.com/organik.jpg', 1, TRUE);

INSERT INTO Harga (idSampah, tanggalUbah, hargaSampah) VALUES
(1, CURRENT_TIMESTAMP, 5000),
(2, CURRENT_TIMESTAMP, 10000),
(3, CURRENT_TIMESTAMP, 2000),
(4, CURRENT_TIMESTAMP, 15000),
(5, CURRENT_TIMESTAMP, 1000);

UPDATE Sampah
SET idHargaSekarang = (SELECT idHarga FROM Harga WHERE Harga.idSampah = Sampah.idSampah);

INSERT INTO Inventory_Sampah (idSampah, kuantitas) VALUES
(1, 100),
(2, 50),
(3, 200),
(4, 30),
(5, 300);

INSERT INTO Transaksi_Masuk (tanggal, idPengguna) VALUES
(CURRENT_TIMESTAMP, 2),
(CURRENT_TIMESTAMP, 3),
(CURRENT_TIMESTAMP, 4),
(CURRENT_TIMESTAMP, 5),
(CURRENT_TIMESTAMP, 6);

INSERT INTO Transaksi_Keluar (tanggal) VALUES
(CURRENT_TIMESTAMP),
(CURRENT_TIMESTAMP),
(CURRENT_TIMESTAMP),
(CURRENT_TIMESTAMP),
(CURRENT_TIMESTAMP);

INSERT INTO Transaksi_Keluar_Sampah (idTransaksiKeluar, idSampah, jumlahSampah, idHarga) VALUES
(1, 1, 10, 1),
(2, 2, 5, 2),
(3, 3, 20, 3),
(4, 4, 3, 4),
(5, 5, 50, 5);

INSERT INTO Transaksi_Masuk_Sampah (idTransaksiMasuk, idSampah, idHarga, jumlahSampah) VALUES
(1, 1, 1, 15),
(2, 2, 2, 10),
(3, 3, 3, 25),
(4, 4, 4, 5),
(5, 5, 5, 60);

CREATE OR REPLACE VIEW jumlahdigudang AS
SELECT 
    s.namaSampah,
    sk.namaSatuanKuantitas,
    COALESCE(SUM(tm.jumlahSampah), 0) AS jumlahmasuk,
    COALESCE(SUM(tks.jumlahSampah), 0) AS jumlahkeluar,
    COALESCE(SUM(tm.jumlahSampah), 0) - COALESCE(SUM(tks.jumlahSampah), 0) AS sisagudang
FROM 
    Sampah s
LEFT JOIN 
    Transaksi_Masuk_Sampah tm ON s.idSampah = tm.idSampah
LEFT JOIN 
    Transaksi_Keluar_Sampah tks ON s.idSampah = tks.idSampah
JOIN 
    SatuanKuantitas sk ON s.idSatuanKuantitas = sk.idSatuanKuantitas
GROUP BY 
    s.namaSampah, sk.namaSatuanKuantitas;

SELECT * FROM jumlahdigudang;