package com.example.BankSampah.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.BankSampah.model.namahargasatuan.NamaHargaSatuan;
import com.example.BankSampah.model.namahargasatuan.NamaHargaSatuanRepository;
import com.example.BankSampah.model.transaksiKeDalam.TransaksiKeDalam;
import com.example.BankSampah.model.transaksiKeDalam.TransaksiKeDalamRepository;
import com.example.BankSampah.model.transaksiMasuk.TransaksiMasukRepository;
import com.example.BankSampah.model.user.User;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/pengguna")
public class PenggunaController {

    @Autowired
    NamaHargaSatuanRepository repoSampahView;

    @Autowired
    TransaksiKeDalamRepository repoTransaksiKeDalam;

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
        return "/pengguna/dashboard";
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

    @GetMapping("/historiSetoran")
    public String showHistory(Model model, HttpServletRequest request) {
        User user = getAuthentication(request); // Ambil data pengguna dari sesi
        if (user != null) {
            // Ambil semua transaksi milik pengguna berdasarkan ID pengguna
            List<TransaksiKeDalam> listTransaksi = repoTransaksiKeDalam.findByUsername(user.getNama());
            model.addAttribute("listTransaksiMember", listTransaksi); // Kirim data ke view
            model.addAttribute("namaPengguna", user.getNama()); // Kirim nama pengguna untuk ditampilkan
        } else {
            model.addAttribute("error", "Pengguna tidak ditemukan atau belum login.");
        }
        return "pengguna/histori_setoran"; // Pastikan path sesuai dengan lokasi file template
    }
    
    @GetMapping("/laporan_pendapatan")
    public String showLaporan(Model model, HttpServletRequest request) {
        User user = getAuthentication(request);
        return "/pengguna/laporan_pendapatan";
    }
}
