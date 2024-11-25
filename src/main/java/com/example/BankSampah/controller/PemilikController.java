package com.example.BankSampah.controller;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.BankSampah.model.card.Card;
import com.example.BankSampah.model.card.CardRepository;
import com.example.BankSampah.model.harga.Harga;
import com.example.BankSampah.model.harga.HargaRepository;
import com.example.BankSampah.model.invent.InventoryRepository;
import com.example.BankSampah.model.kecamatan.Kecamatan;
import com.example.BankSampah.model.kecamatan.KecamatanRepository;
import com.example.BankSampah.model.kelurahan.Kelurahan;
import com.example.BankSampah.model.kelurahan.KelurahanRepository;
import com.example.BankSampah.model.namahargasatuan.NamaHargaSatuan;
import com.example.BankSampah.model.namahargasatuan.NamaHargaSatuanRepository;
import com.example.BankSampah.model.sampah.Sampah;
import com.example.BankSampah.model.sampah.SampahRepository;
import com.example.BankSampah.model.satuanKuantitas.SatuanKuantitas;
import com.example.BankSampah.model.satuanKuantitas.SatuanKuantitasRepository;
import com.example.BankSampah.model.transaksiKeDalam.TransaksiKeDalam;
import com.example.BankSampah.model.transaksiKeDalam.TransaksiKeDalamRepository;
import com.example.BankSampah.model.transaksiKePusat.TransaksiKePusat;
import com.example.BankSampah.model.transaksiKePusat.TransaksiKePusatRepository;
import com.example.BankSampah.model.transaksiKeluar.TransaksiKeluar;
import com.example.BankSampah.model.transaksiKeluar.TransaksiKeluarRepository;
import com.example.BankSampah.model.transaksiKeluarSampah.TransaksiKeluarSampahRepository;
import com.example.BankSampah.model.transaksiMasuk.TransaksiMasuk;
import com.example.BankSampah.model.transaksiMasuk.TransaksiMasukRepository;
import com.example.BankSampah.model.transaksiMasukSampah.TransaksiMasukSampahRepository;
import com.example.BankSampah.model.user.User;
import com.example.BankSampah.model.user.UserRepository;

import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/pemilik")
public class PemilikController {

    @Autowired
    SampahRepository repo;

    @Autowired
    UserRepository repoUser;

    @Autowired
    KecamatanRepository repoKec;

    @Autowired
    KelurahanRepository repoKel;

    @Autowired
    SatuanKuantitasRepository repoSK;

    @Autowired
    SampahRepository repoSampah;
    
    @Autowired
    HargaRepository repoHarga;

    @Autowired
    NamaHargaSatuanRepository repoSampahView;

    
    @Autowired
    CardRepository repoCard;
    
    @Autowired
    TransaksiKeluarRepository repoTransaksiKeluar;
    
    @Autowired
    TransaksiKeluarSampahRepository repoTransaksiKeluarSampah;
    
    @Autowired
    TransaksiMasukRepository repoTransaksiMasuk;
    
    @Autowired
    TransaksiMasukSampahRepository repoTransaksiMasukSampah;
    
    @Autowired
    InventoryRepository repoInvent;

    @Autowired
    TransaksiKePusatRepository repoTransaksiKePusat;

    @Autowired
    TransaksiKeDalamRepository repoTransaksiKeDalam;
    
    @Autowired
    PasswordEncoder passwordEncoder;

    @GetMapping("/")
    public String dashboard(Model model, HttpServletRequest request){
        HttpSession session = request.getSession(false);
        if (session != null) {
            SecurityContext securityContext = (SecurityContext) session.getAttribute("SPRING_SECURITY_CONTEXT");
            if (securityContext != null) {
                Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
                if (authentication != null && authentication.isAuthenticated()) {
                    User user = (User) authentication.getPrincipal();
                    model.addAttribute("nama", user.getNama());
                    Iterable<NamaHargaSatuan> list = repoSampahView.findAll();
                    model.addAttribute("sampahList", list);
                }
            }
        }
        return "/pemilik/dashboard";
    }

    @GetMapping("/kelolaMember")
    public String showMember(Model model, HttpServletRequest request){
        User user = getAuthentication(request);
        List<User> list = repoUser.findAll();
        model.addAttribute("listUser", list);
        return "/pemilik/kelola_member";
    }
    
    @GetMapping("/editMember")
    public String editMember(Model model, HttpServletRequest request) {
        User user = getAuthentication(request);
        return "/pemilik/edit_member";
    }

    @GetMapping("/tambahMember")
    public String addMemberPage(Model model, HttpServletRequest request){
        User user = getAuthentication(request);
        List<Kecamatan> listKec = repoKec.findAll();
        model.addAttribute("listKec", listKec);
        List<Kelurahan> listKel = repoKel.findAll();
        model.addAttribute("listKel", listKel);
        return "/pemilik/tambah_member";
    }

    @PostMapping("/tambahMember/add")
    public String addMember(
        @RequestParam(required = true) String nama,
        @RequestParam(required = true) String nomor_hp, 
        @RequestParam(required = true) String email, 
        @RequestParam(required = true) String password,
        @RequestParam(required = true) String confirm_password,
        @RequestParam(required = true) String alamat,
        @RequestParam(required = true) String kelurahan,
        Model model, HttpServletRequest request, RedirectAttributes redirectAttributes){
            String passwordNow = passwordEncoder.encode(password);
            if(passwordEncoder.matches(confirm_password, passwordNow)){
                repoUser.addUser(nama, passwordNow, nomor_hp, alamat, email, Integer.parseInt(kelurahan));
                // redirectAttributes.addFlashAttribute("error", kelurahan);
                // return "redirect:/pemilik/tambahMember";
            }
            else{
                redirectAttributes.addFlashAttribute("error", "password didn't match");
                return "redirect:/pemilik/tambahMember";
            }
            return "redirect:/pemilik/kelolaMember";
    }

    @GetMapping("/kelolaSampah")
    public String showSampah(Model model, HttpServletRequest request){
        User user = getAuthentication(request);
        Iterable<NamaHargaSatuan> list = repoSampahView.findAll();
        model.addAttribute("listSampah", list);
        return "/pemilik/kelola_sampah";
    }

    @GetMapping("/tambahSampah")
    public String addSampahPage(Model model, HttpServletRequest request){
        User user = getAuthentication(request);
        return "/pemilik/tambah_sampah";
    }

    @PostMapping("/tambahSampah/add")
    public String addSampah(
        @RequestParam(required = true) String namaSampah,
        @RequestParam(required = true) String satuanKuantitas, 
        @RequestParam(required = true) String harga, 
        Model model, HttpServletRequest request, RedirectAttributes redirectAttributes){
            List<SatuanKuantitas> listSK = repoSK.findBy(satuanKuantitas);
            if(listSK.isEmpty()){
                repoSK.addSK(satuanKuantitas);
            }
            listSK = repoSK.findBy(satuanKuantitas);
            SatuanKuantitas nowSK = listSK.get(0);
            repoSampah.addSampah(namaSampah, nowSK.getIdSatuanKuantitas());

            List<Sampah> listSampah = repoSampah.findByNama(namaSampah);
            Sampah nowSampah = listSampah.get(0);
            repoHarga.addHarga(nowSampah.getIdSampah(), Integer.parseInt(harga));
            repoSampah.addToInvent(nowSampah.getIdSampah());

            List<Harga> listHarga = repoHarga.findByIdSampahHarga(nowSampah.getIdSampah(), Integer.parseInt(harga));

            Harga nowHarga = listHarga.get(0);
            repoSampah.setHarga(nowSampah.getNamaSampah(), nowHarga.getIdHarga());

            return "redirect:/pemilik/kelolaSampah";
        }

    @GetMapping("/transaksi")
    public String transaksiPage(Model model, HttpServletRequest request){
        User user = getAuthentication(request);
        List<TransaksiKeDalam> listTransaksi = repoTransaksiKeDalam.findAll();
        model.addAttribute("listTransaksiMasuk", listTransaksi);

        // Map<String, List<TransaksiKeDalam>> groupedTransaksi = listTransaksi.stream()
        //     .collect(Collectors.groupingBy(t -> t.getTanggal().toString() +"="+ t.getNamaPengguna()));

        // Add the grouped transaksi to the model for display in the view
        // model.addAttribute("groupedTransaksi", groupedTransaksi);
        // model.addAttribute("error2", groupedTransaksi);

        int total = 0;
        for(int i = 0;i < listTransaksi.size();i++){
            total+= listTransaksi.get(i).getSubTotal();
        }
        model.addAttribute("total", total);
        // model.addAttribute("baris", listTransaksi.size());
        return "/pemilik/transaksi";
    }

    @GetMapping("/tambahTransaksi")
    public String addTransaksiPage(Model model, HttpServletRequest request){
        User user = getAuthentication(request);
        List<Card> list = repoCard.findAll();
        model.addAttribute("listKet", list);

        List<User> listuser = repoUser.findAll();
        model.addAttribute("listPengguna", listuser);

        return "/pemilik/tambah_transaksi";
    }

    @PostMapping("/tambahTransaksi/add")
    public String addTransaksi(
        @RequestParam("member") String member,
        @RequestParam("nama[]") List<String> nama,
        @RequestParam("harga[]") List<String> harga,
        @RequestParam("satuan[]") List<String> satuan,
        @RequestParam("kuantitas[]") List<String> kuantitas,
        Model model, HttpServletRequest request)
    {
        User user = getAuthentication(request);
        if(nama.size()>0){
            int idTransaksiMasuk = repoTransaksiMasuk.addTransaksiMasuk(Integer.parseInt(member));
            for (int i = 0; i < nama.size(); i++) {
                String itemNama = nama.get(i);
                String itemHarga = harga.get(i);
                String itemSatuan = satuan.get(i);
                String itemKuantitas = kuantitas.get(i);

                Sampah yangDipilih = repo.findByNama(itemNama).get(0);
                int idSampah = yangDipilih.getIdSampah();
                int idHarga = yangDipilih.getIdHargaSekarang();
                int kuantitasSaatIni = Integer.parseInt(itemKuantitas);
                repoTransaksiMasukSampah.addTransaksiMasukSampah(idTransaksiMasuk, idSampah, idHarga, kuantitasSaatIni);
                
                int kuantitasDiDB = repoInvent.findByIdSampah(idSampah).get(0).getKuantitas();
                kuantitasDiDB += kuantitasSaatIni;
                repoInvent.updateKuantitas(kuantitasDiDB, idSampah);
            }
        }
        return "redirect:/pemilik/transaksi";
    }

    @GetMapping("/transaksiKePusat")
    public String transaksiPusatPage(Model model, HttpServletRequest request){
        User user = getAuthentication(request);
        List<TransaksiKePusat> list = repoTransaksiKePusat.findAll();
        model.addAttribute("listTransaksiMasuk", list);
        return "/pemilik/transaksi_ke_pusat";
    }

    @GetMapping("/tambahTransaksiKePusat")
    public String addTransaksiPusatPage(Model model, HttpServletRequest request){
        User user = getAuthentication(request);
        List<Card> list = repoCard.findAll();
        model.addAttribute("listKet", list);
        return "/pemilik/tambah_transaksi_ke_pusat";
    }

    @PostMapping("/tambahTransaksiKePusat/add")
    public String addTransaksiPusat(
        @RequestParam("nama[]") List<String> nama,
        @RequestParam("harga[]") List<String> harga,
        @RequestParam("satuan[]") List<String> satuan,
        @RequestParam("kuantitas[]") List<String> kuantitas,    
    Model model, HttpServletRequest request){
        User user = getAuthentication(request);
        if(nama.size()>0){
            int idTransaksiKeluar = repoTransaksiKeluar.addTransaksiKeluar();
            for (int i = 0; i < nama.size(); i++) {
                String itemNama = nama.get(i);
                String itemHarga = harga.get(i);
                String itemSatuan = satuan.get(i);
                String itemKuantitas = kuantitas.get(i);

                Sampah yangDipilih = repo.findByNama(itemNama).get(0);
                int idSampah = yangDipilih.getIdSampah();
                int idHarga = yangDipilih.getIdHargaSekarang();
                int kuantitasSaatIni = Integer.parseInt(itemKuantitas);
                repoTransaksiKeluarSampah.addTransaksiKeluarSampah(idTransaksiKeluar, idSampah, kuantitasSaatIni, idHarga);

                int kuantitasDiDB = repoInvent.findByIdSampah(idSampah).get(0).getKuantitas();
                kuantitasDiDB -= kuantitasSaatIni;
                repoInvent.updateKuantitas(kuantitasDiDB, idSampah);
            }
        }
        return "redirect:/pemilik/transaksiKePusat";
    }

    @GetMapping("/laporan")
    public String laporanPage(Model model, HttpServletRequest request){
        User user = getAuthentication(request);
        return "/pemilik/laporan";
    }
    
    public User getAuthentication(HttpServletRequest request){
        HttpSession session = request.getSession(false);
        User user = null;
        if (session != null) {
            SecurityContext securityContext = (SecurityContext) session.getAttribute("SPRING_SECURITY_CONTEXT");
            if (securityContext != null) {
                Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
                if (authentication != null && authentication.isAuthenticated()) {
                    user = (User) authentication.getPrincipal();
                }
            }
        }
        return user;
    }
}
