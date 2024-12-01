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

import com.example.BankSampah.model.transaksiKeDalam.TransaksiKeDalam;
import com.example.BankSampah.model.transaksiKeDalam.TransaksiKeDalamRepository;
import com.example.BankSampah.model.user.User;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/pengguna")
public class PenggunaController {

    @Autowired
    private TransaksiKeDalamRepository transaksiKeDalamRepository;

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
                }
            }
        }
        return "/pengguna/dashboard";
    }

    public User getAuthentication(HttpServletRequest request){
        HttpSession session = request.getSession(false);
        User user = null;
        if(session != null){
            SecurityContext securityContext = (SecurityContext)session.getAttribute("SPRING_SECURITY_CONTEXT");
            if(securityContext != null){
                Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
                if(authentication != null && authentication.isAuthenticated()){
                    user = (User)authentication.getPrincipal();
                }
            }
        }
        return user;
    }

    @GetMapping("/laporan_pendapatan")
    public String laporanPendapatan(Model model, HttpServletRequest request){
        User user = getAuthentication(request);
        if(user != null){
            model.addAttribute("nama", user.getNama());

            List<TransaksiKeDalam> pendapatanList =  transaksiKeDalamRepository.findByNama(user.getNama());
            model.addAttribute("pendapatanList", pendapatanList);
        }
        return "pengguna/laporan_pendapatan";

    }
}
