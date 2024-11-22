package com.example.BankSampah.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.BankSampah.model.kecamatan.Kecamatan;
import com.example.BankSampah.model.kecamatan.KecamatanRepository;

@Controller
public class RegisterController {
    @Autowired
    KecamatanRepository repoKec;

    @GetMapping("/registerPage")
    public String showRegister(Model model) {
        List<Kecamatan> listKec = repoKec.findAll();
        model.addAttribute("listKec", listKec);
        return "register";
    }

    @PostMapping("/registerPage/register")
    public String register(Model model){
        return null;
    }
}
