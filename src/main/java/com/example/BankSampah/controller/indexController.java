package com.example.BankSampah.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.BankSampah.sampah.Sampah;
import com.example.BankSampah.sampah.SampahRepository;

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
}
