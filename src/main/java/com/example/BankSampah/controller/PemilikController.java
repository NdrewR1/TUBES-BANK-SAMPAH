package com.example.BankSampah.controller;

import java.util.List;

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

import com.example.BankSampah.model.kecamatan.Kecamatan;
import com.example.BankSampah.model.kecamatan.KecamatanRepository;
import com.example.BankSampah.model.kelurahan.Kelurahan;
import com.example.BankSampah.model.kelurahan.KelurahanRepository;
import com.example.BankSampah.model.sampah.Sampah;
import com.example.BankSampah.model.sampah.SampahRepository;
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
                    Iterable<Sampah> list = repo.findAll();
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
        Iterable<Sampah> list = repo.findAll();
        model.addAttribute("listSampah", list);
        return "/pemilik/kelola_sampah";
    }

    @GetMapping("/tambahSampah")
    public String addSampahPage(Model model, HttpServletRequest request){
        User user = getAuthentication(request);
        return "/pemilik/tambah_sampah";
    }

    @GetMapping("/transaksi")
    public String transaksiPage(Model model, HttpServletRequest request){
        User user = getAuthentication(request);
        return "/pemilik/transaksi";
    }

    @GetMapping("/tambahTransaksi")
    public String addTransaksiPage(Model model, HttpServletRequest request){
        User user = getAuthentication(request);
        return "/pemilik/tambah_transaksi";
    }

    @GetMapping("/transaksiKePusat")
    public String transaksiPusatPage(Model model, HttpServletRequest request){
        User user = getAuthentication(request);
        return "/pemilik/transaksi_ke_pusat";
    }

    @GetMapping("/tambahTransaksiKePusat")
    public String addTransaksiPusatPage(Model model, HttpServletRequest request){
        User user = getAuthentication(request);
        return "/pemilik/tambah_transaksi_ke_pusat";
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
