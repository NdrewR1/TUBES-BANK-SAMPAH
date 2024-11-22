package com.example.BankSampah.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.BankSampah.model.kecamatan.KecamatanRepository;
import com.example.BankSampah.model.user.User;
import com.example.BankSampah.model.user.UserRepository;

@Controller
public class LoginController {
    @Autowired
    UserRepository repo;

    @PostMapping("/loginPage/login")
    public String login(@RequestParam(required = true)String email,@RequestParam(required = true)String password, Model model){
        List<User> list = repo.findByEmail(email);
        User now = list.get(0);
        //kalo gagal
        if(now.getPassword().equals(password)){
            return "redirect:/";
        }
        else{
            return "redirect:/loginPage";
        }
    }
}
