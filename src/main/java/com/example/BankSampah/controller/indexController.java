package com.example.BankSampah.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.BankSampah.model.sampah.Sampah;
import com.example.BankSampah.model.sampah.SampahRepository;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@Controller
public class indexController {
    
    @Autowired
    SampahRepository repo;

    @GetMapping("/")
    public String showAllSampah(Model model){
        Iterable<Sampah> list = repo.findAll();
        model.addAttribute("sampahList", list);
        return "index";
    }

    @GetMapping("/loginPage")
    public String showLogin() {
        return "login";
    }

    @GetMapping("/logoutPage")
    public String showLogout(HttpServletRequest request, HttpServletResponse response) {
        SecurityContextHolder.clearContext(); // Clear the security context
        request.getSession().invalidate(); // Invalidate the session
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.removeAttribute("SPRING_SECURITY_CONTEXT");
        }
        return "logout";
    }
}
