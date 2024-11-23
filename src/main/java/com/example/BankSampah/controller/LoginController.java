package com.example.BankSampah.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.BankSampah.model.kecamatan.KecamatanRepository;
import com.example.BankSampah.model.user.User;
import com.example.BankSampah.model.user.UserRepository;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import org.springframework.security.core.Authentication;
@Controller
public class LoginController {
    @Autowired
    UserRepository repo;

    @Autowired
    PasswordEncoder passwordEncoder;

    @PostMapping("/loginPage/login")
    public String login(@RequestParam(required = true)String email,@RequestParam(required = true)String password, Model model, HttpServletRequest request){
        List<User> list = repo.findByEmail(email);
        if(list.size() == 0){
            model.addAttribute("error", "wrong email");
            return "login";
        }
        User user = list.get(0);
        
        //kalo behasil
        if(passwordEncoder.matches(password, user.getPassword())){
            Authentication authentication = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authentication);
            setSecurityContextToSession(request);
            model.addAttribute("pwdijava", authentication);
            return "redirect:/dashboard";
            // return "login";
        }
        // model.addAttribute("emaildijava", user.getAuthorities());
        else{
            model.addAttribute("error", "Invalid password");
            return "login"; 
        }

    }

    private void setSecurityContextToSession(HttpServletRequest request) {
        SecurityContext securityContext = SecurityContextHolder.getContext();

        HttpSession session = request.getSession(true);  // true: create session if not exist
        session.setAttribute("SPRING_SECURITY_CONTEXT", securityContext);
    }

    // @GetMapping("/add")
    // public String addUser(Model model){
    //     String password = passwordEncoder.encode("ayam");
    //     repo.addUser(password, password, password, password, password, 1);
    //     return null;
    // }

    @GetMapping("/addAdmin")
    public String addUser(Model model){
        String password = passwordEncoder.encode("1234");
        repo.addAdmin(password, password, password, password, password, 1, password);
        return null;
    }
}
