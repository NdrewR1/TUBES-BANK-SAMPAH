package com.example.BankSampah.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.BankSampah.model.kecamatan.Kecamatan;
import com.example.BankSampah.model.kecamatan.KecamatanRepository;
import com.example.BankSampah.model.kelurahan.Kelurahan;
import com.example.BankSampah.model.kelurahan.KelurahanRepository;

@Controller
public class RegisterController {
    @Autowired
    KecamatanRepository repoKec;

    @Autowired
    KelurahanRepository repoKel;

    @GetMapping("/registerPage")
    public String showRegister(Model model) {
        List<Kecamatan> listKec = repoKec.findAll();
        List<Kelurahan> listKel = repoKel.findAll();
        model.addAttribute("listKec", listKec);
        model.addAttribute("listKel", listKel);
        return "register";
    }

    @GetMapping("/pemilik/kelurahan")
    @ResponseBody
    public List<Kelurahan> getKelurahanByKecamatan(@RequestParam("kecamatanId") int kecamatanId) {
        return repoKel.findByIdKec(kecamatanId);
    }

    @PostMapping("/registerPage/register")
    public String register(Model model){
        return null;
    }
}
