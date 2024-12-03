SELECT * FROM kelurahan
SELECT * FROM kecamatan

ALTER SEQUENCE public.kecamatan_idkec_seq RESTART WITH 1;
ALTER SEQUENCE public.kelurahan_idkel_seq RESTART WITH 1;

UPDATE kecamatan SET namakec = 'Andir' WHERE idkec = '1';

CREATE VIEW view_kelurahan_kecamatan AS
SELECT 
    kelurahan.namakel,
    kecamatan.namakec
FROM 
    kelurahan
INNER JOIN 
    kecamatan ON kelurahan.idkec = kecamatan.idkec;

SELECT * FROM view_kelurahan_kecamatan;

INSERT INTO kecamatan(namakec) VALUES('Andir');
INSERT INTO kecamatan(namakec) VALUES('Astana Anyar');
INSERT INTO kecamatan(namakec) VALUES('Antapani');
INSERT INTO kecamatan(namakec) VALUES('Arcamanik');
INSERT INTO kecamatan(namakec) VALUES('Babakan Ciparay');
INSERT INTO kecamatan(namakec) VALUES('Bandung Kidul');
INSERT INTO kecamatan(namakec) VALUES('Bandung Kulon');
INSERT INTO kecamatan(namakec) VALUES('Bandung Wetan');
INSERT INTO kecamatan(namakec) VALUES('Batununggal');
INSERT INTO kecamatan(namakec) VALUES('Bojongloa Kaler');
INSERT INTO kecamatan(namakec) VALUES('Bojongloa Kidul');
INSERT INTO kecamatan(namakec) VALUES('Buahbatu');
INSERT INTO kecamatan(namakec) VALUES('Cibeunying Kaler');
INSERT INTO kecamatan(namakec) VALUES('Cibeunying Kidul');
INSERT INTO kecamatan(namakec) VALUES('Cibiru');
INSERT INTO kecamatan(namakec) VALUES('Cicendo');
INSERT INTO kecamatan(namakec) VALUES('Cidadap');
INSERT INTO kecamatan(namakec) VALUES('Cinambo');
INSERT INTO kecamatan(namakec) VALUES('Coblong');
INSERT INTO kecamatan(namakec) VALUES('Gedebage');
INSERT INTO kecamatan(namakec) VALUES('Kiaracondong');
INSERT INTO kecamatan(namakec) VALUES('Lengkong');
INSERT INTO kecamatan(namakec) VALUES('Mandalajati');
INSERT INTO kecamatan(namakec) VALUES('Panyileukan');
INSERT INTO kecamatan(namakec) VALUES('Rancasari');
INSERT INTO kecamatan(namakec) VALUES('Regol');
INSERT INTO kecamatan(namakec) VALUES('Sukajadi');
INSERT INTO kecamatan(namakec) VALUES('Sukasari');
INSERT INTO kecamatan(namakec) VALUES('Sumur Bandung');
INSERT INTO kecamatan(namakec) VALUES('Ujungberung');

INSERT INTO kelurahan(namakel, idkec) VALUES ('Campaka', 1);
INSERT INTO kelurahan(namakel, idkec) VALUES ('Ciroyom', 1);
INSERT INTO kelurahan(namakel, idkec) VALUES ('Dunguscariang', 1);
INSERT INTO kelurahan(namakel, idkec) VALUES ('Garuda', 1);
INSERT INTO kelurahan(namakel, idkec) VALUES ('Kebonjeruk', 1);
INSERT INTO kelurahan(namakel, idkec) VALUES ('Maleber', 1);

INSERT INTO kelurahan(namakel, idkec) VALUES ('Cibadak', 2);
INSERT INTO kelurahan(namakel, idkec) VALUES ('Karanganyar', 2);
INSERT INTO kelurahan(namakel, idkec) VALUES ('Karasak', 2);
INSERT INTO kelurahan(namakel, idkec) VALUES ('Nyengseret', 2);
INSERT INTO kelurahan(namakel, idkec) VALUES ('Panjunan', 2);
INSERT INTO kelurahan(namakel, idkec) VALUES ('Pelindunghewan', 2);

INSERT INTO kelurahan(namakel, idkec) VALUES ('Antapani Kidul', 3);
INSERT INTO kelurahan(namakel, idkec) VALUES ('Antapani Kulon', 3);
INSERT INTO kelurahan(namakel, idkec) VALUES ('Antapani Tengah', 3);
INSERT INTO kelurahan(namakel, idkec) VALUES ('Antapani Wetan', 3);

INSERT INTO kelurahan(namakel, idkec) VALUES ('Cisaranten Bina Harapan', 4);
INSERT INTO kelurahan(namakel, idkec) VALUES ('Cisaranten Endah', 4);
INSERT INTO kelurahan(namakel, idkec) VALUES ('Cisaranten Kulon', 4);
INSERT INTO kelurahan(namakel, idkec) VALUES ('Sukamiskin', 4);

INSERT INTO kelurahan(namakel, idkec) VALUES ('Babakan', 5);
INSERT INTO kelurahan(namakel, idkec) VALUES ('Babakanciparay', 5);
INSERT INTO kelurahan(namakel, idkec) VALUES ('Cirangrang', 5);
INSERT INTO kelurahan(namakel, idkec) VALUES ('Margahayu Utara', 5);
INSERT INTO kelurahan(namakel, idkec) VALUES ('Margasuka', 5);
INSERT INTO kelurahan(namakel, idkec) VALUES ('Sukahaji', 5);

INSERT INTO kelurahan(namakel, idkec) VALUES ('Batununggal', 6);
INSERT INTO kelurahan(namakel, idkec) VALUES ('Kujangsari', 6);
INSERT INTO kelurahan(namakel, idkec) VALUES ('Mengger', 6);
INSERT INTO kelurahan(namakel, idkec) VALUES ('Wates', 6);

INSERT INTO kelurahan(namakel, idkec) VALUES ('Caringin', 7);
INSERT INTO kelurahan(namakel, idkec) VALUES ('Cibuntu', 7);
INSERT INTO kelurahan(namakel, idkec) VALUES ('Cigondewah Kaler', 7);
INSERT INTO kelurahan(namakel, idkec) VALUES ('Cigondewah Kidul', 7);
INSERT INTO kelurahan(namakel, idkec) VALUES ('Cigondewah Rahayu', 7);
INSERT INTO kelurahan(namakel, idkec) VALUES ('Cijerah', 7);
INSERT INTO kelurahan(namakel, idkec) VALUES ('Gempolsari', 7);
INSERT INTO kelurahan(namakel, idkec) VALUES ('Warungmuncang', 7);

INSERT INTO kelurahan(namakel, idkec) VALUES ('Cihapit', 8);
INSERT INTO kelurahan(namakel, idkec) VALUES ('Citarum', 8);
INSERT INTO kelurahan(namakel, idkec) VALUES ('Tamansari', 8);

INSERT INTO kelurahan(namakel, idkec) VALUES ('Binong', 9);
INSERT INTO kelurahan(namakel, idkec) VALUES ('Cibangkong', 9);
INSERT INTO kelurahan(namakel, idkec) VALUES ('Gumuruh', 9);
INSERT INTO kelurahan(namakel, idkec) VALUES ('Kacapiring', 9);
INSERT INTO kelurahan(namakel, idkec) VALUES ('Kebongedang', 9);
INSERT INTO kelurahan(namakel, idkec) VALUES ('Kebonwaru', 9);
INSERT INTO kelurahan(namakel, idkec) VALUES ('Maleer', 9);
INSERT INTO kelurahan(namakel, idkec) VALUES ('Samoja', 9);

INSERT INTO kelurahan(namakel, idkec) VALUES ('Babakan Asih', 10);
INSERT INTO kelurahan(namakel, idkec) VALUES ('Babakan Tarogong', 10);
INSERT INTO kelurahan(namakel, idkec) VALUES ('Jamika', 10);
INSERT INTO kelurahan(namakel, idkec) VALUES ('Kopo', 10);
INSERT INTO kelurahan(namakel, idkec) VALUES ('Suka Asih', 10);

INSERT INTO kelurahan(namakel, idkec) VALUES ('Cibaduyut', 11);
INSERT INTO kelurahan(namakel, idkec) VALUES ('Cibaduyut Kidul', 11);
INSERT INTO kelurahan(namakel, idkec) VALUES ('Cibaduyut Wetan', 11);
INSERT INTO kelurahan(namakel, idkec) VALUES ('Kebon Lega', 11);
INSERT INTO kelurahan(namakel, idkec) VALUES ('Mekarwangi', 11);
INSERT INTO kelurahan(namakel, idkec) VALUES ('Situsaeur', 11);


INSERT INTO kelurahan(namakel, idkec) VALUES ('Cijawura', 12);
INSERT INTO kelurahan(namakel, idkec) VALUES ('Jatisari', 12);
INSERT INTO kelurahan(namakel, idkec) VALUES ('Margasari', 12);
INSERT INTO kelurahan(namakel, idkec) VALUES ('Sekejati', 12);

INSERT INTO kelurahan(namakel, idkec) VALUES ('Cigadung', 13);
INSERT INTO kelurahan(namakel, idkec) VALUES ('Cihaurgeulis', 13);
INSERT INTO kelurahan(namakel, idkec) VALUES ('Neglasari', 13);
INSERT INTO kelurahan(namakel, idkec) VALUES ('Sukaluyu', 13);

INSERT INTO kelurahan(namakel, idkec) VALUES ('Cicadas', 14);
INSERT INTO kelurahan(namakel, idkec) VALUES ('Cikutra', 14);
INSERT INTO kelurahan(namakel, idkec) VALUES ('Padasuka', 14);
INSERT INTO kelurahan(namakel, idkec) VALUES ('Pasirlayung', 14);
INSERT INTO kelurahan(namakel, idkec) VALUES ('Sukamaju', 14);
INSERT INTO kelurahan(namakel, idkec) VALUES ('Sukapada', 14);

INSERT INTO kelurahan(namakel, idkec) VALUES ('Cipadung', 15);
INSERT INTO kelurahan(namakel, idkec) VALUES ('Cisurupan', 15);
INSERT INTO kelurahan(namakel, idkec) VALUES ('Palasari', 15);
INSERT INTO kelurahan(namakel, idkec) VALUES ('Pasirbiru', 15);

INSERT INTO kelurahan(namakel, idkec) VALUES ('Arjuna', 16);
INSERT INTO kelurahan(namakel, idkec) VALUES ('Husein Sastranegara', 16);
INSERT INTO kelurahan(namakel, idkec) VALUES ('Pajajaran', 16);
INSERT INTO kelurahan(namakel, idkec) VALUES ('Pamoyanan', 16);
INSERT INTO kelurahan(namakel, idkec) VALUES ('Pasirkaliki', 16);
INSERT INTO kelurahan(namakel, idkec) VALUES ('Sukaraja', 16);

INSERT INTO kelurahan(namakel, idkec) VALUES ('Ciumbuleuit', 17);
INSERT INTO kelurahan(namakel, idkec) VALUES ('Hegarmanah', 17);
INSERT INTO kelurahan(namakel, idkec) VALUES ('Ledeng', 17);

INSERT INTO kelurahan(namakel, idkec) VALUES ('Babakan Penghulu', 18);
INSERT INTO kelurahan(namakel, idkec) VALUES ('Cisaranten Wetan', 18);
INSERT INTO kelurahan(namakel, idkec) VALUES ('Pakemitan', 18);
INSERT INTO kelurahan(namakel, idkec) VALUES ('Sukamulya', 18);

INSERT INTO kelurahan(namakel, idkec) VALUES ('Cipaganti', 19);
INSERT INTO kelurahan(namakel, idkec) VALUES ('Dago', 19);
INSERT INTO kelurahan(namakel, idkec) VALUES ('Lebakgede', 19);
INSERT INTO kelurahan(namakel, idkec) VALUES ('Lebaksiliwangi', 19);
INSERT INTO kelurahan(namakel, idkec) VALUES ('Sadangserang', 19);
INSERT INTO kelurahan(namakel, idkec) VALUES ('Sekeloa', 19);

INSERT INTO kelurahan(namakel, idkec) VALUES ('Cimincrang', 20);
INSERT INTO kelurahan(namakel, idkec) VALUES ('Cisaranten Kidul', 20);
INSERT INTO kelurahan(namakel, idkec) VALUES ('Rancabolang', 20);
INSERT INTO kelurahan(namakel, idkec) VALUES ('Rancanumpang', 20);

INSERT INTO kelurahan(namakel, idkec) VALUES ('Babakansari', 21);
INSERT INTO kelurahan(namakel, idkec) VALUES ('Babakansurabaya', 21);
INSERT INTO kelurahan(namakel, idkec) VALUES ('Cicaheum', 21);
INSERT INTO kelurahan(namakel, idkec) VALUES ('Kebonkangkung', 21);
INSERT INTO kelurahan(namakel, idkec) VALUES ('Kebunjayanti', 21);
INSERT INTO kelurahan(namakel, idkec) VALUES ('Sukapura', 21);

INSERT INTO kelurahan(namakel, idkec) VALUES ('Burangrang', 22);
INSERT INTO kelurahan(namakel, idkec) VALUES ('Cijagra', 22);
INSERT INTO kelurahan(namakel, idkec) VALUES ('Cikawao', 22);
INSERT INTO kelurahan(namakel, idkec) VALUES ('Lingkar Selatan', 22);
INSERT INTO kelurahan(namakel, idkec) VALUES ('Malabar', 22);
INSERT INTO kelurahan(namakel, idkec) VALUES ('Paledang', 22);
INSERT INTO kelurahan(namakel, idkec) VALUES ('Turangga', 22);

INSERT INTO kelurahan(namakel, idkec) VALUES ('Jatihandap', 23);
INSERT INTO kelurahan(namakel, idkec) VALUES ('Karangpamulang', 23);
INSERT INTO kelurahan(namakel, idkec) VALUES ('Pasir Impun', 23);
INSERT INTO kelurahan(namakel, idkec) VALUES ('Sindangjaya', 23);

INSERT INTO kelurahan(namakel, idkec) VALUES ('Cipadung Kidul', 24);
INSERT INTO kelurahan(namakel, idkec) VALUES ('Cipadung Kulon', 24);
INSERT INTO kelurahan(namakel, idkec) VALUES ('Cipadung Wetan', 24);
INSERT INTO kelurahan(namakel, idkec) VALUES ('Mekarmulya', 24);

INSERT INTO kelurahan(namakel, idkec) VALUES ('Cipamokolan', 25);
INSERT INTO kelurahan(namakel, idkec) VALUES ('Darwati', 25);
INSERT INTO kelurahan(namakel, idkec) VALUES ('Manjahlega', 25);
INSERT INTO kelurahan(namakel, idkec) VALUES ('Mekar Jaya', 25);

INSERT INTO kelurahan(namakel, idkec) VALUES ('Ancol', 26);
INSERT INTO kelurahan(namakel, idkec) VALUES ('Balonggede', 26);
INSERT INTO kelurahan(namakel, idkec) VALUES ('Ciateul', 26);
INSERT INTO kelurahan(namakel, idkec) VALUES ('Cigeleng', 26);
INSERT INTO kelurahan(namakel, idkec) VALUES ('Ciseureuh', 26);
INSERT INTO kelurahan(namakel, idkec) VALUES ('Pasirluyu', 26);
INSERT INTO kelurahan(namakel, idkec) VALUES ('Pungkur', 26);

INSERT INTO kelurahan(namakel, idkec) VALUES ('Cipedes', 27);
INSERT INTO kelurahan(namakel, idkec) VALUES ('Pasteur', 27);
INSERT INTO kelurahan(namakel, idkec) VALUES ('Sukabungah', 27);
INSERT INTO kelurahan(namakel, idkec) VALUES ('Sukagalih', 27);
INSERT INTO kelurahan(namakel, idkec) VALUES ('Sukawarna', 27);

INSERT INTO kelurahan(namakel, idkec) VALUES ('Gegerkalong', 28);
INSERT INTO kelurahan(namakel, idkec) VALUES ('Isola', 28);
INSERT INTO kelurahan(namakel, idkec) VALUES ('Sarijadi', 28);
INSERT INTO kelurahan(namakel, idkec) VALUES ('Sukarasa', 28);

INSERT INTO kelurahan(namakel, idkec) VALUES ('Babakanciamis', 29);
INSERT INTO kelurahan(namakel, idkec) VALUES ('Braga', 29);
INSERT INTO kelurahan(namakel, idkec) VALUES ('Kebonpisang', 29);
INSERT INTO kelurahan(namakel, idkec) VALUES ('Merdeka', 29);

INSERT INTO kelurahan(namakel, idkec) VALUES ('Cigending', 30);
INSERT INTO kelurahan(namakel, idkec) VALUES ('Pasanggrahan', 30);
INSERT INTO kelurahan(namakel, idkec) VALUES ('Pasirendah', 30);
INSERT INTO kelurahan(namakel, idkec) VALUES ('Pasirjati', 30);
INSERT INTO kelurahan(namakel, idkec) VALUES ('Pasirwangi', 30);