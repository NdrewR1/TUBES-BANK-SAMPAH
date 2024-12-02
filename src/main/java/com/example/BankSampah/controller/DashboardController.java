package com.example.BankSampah.controller;

import java.util.Collection;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
public class DashboardController {
    @GetMapping("/dashboard")
    public String dashboard(Model model , HttpServletRequest request){
        // HttpSession session = request.getSession(false);
        // if (session != null) {
        //     SecurityContext securityContext = (SecurityContext) session.getAttribute("SPRING_SECURITY_CONTEXT");
        //     // Get the current authentication object
        //     Authentication authentication = securityContext.getAuthentication();
        //     // model.addAttribute("emaildijava", authentication);
        //     // Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        //     model.addAttribute("pwdijava", authentication);
        // }
        
        // return "login";
        
        
        model.addAttribute("tes", "tes");

        //false ga bikin session baru
        HttpSession session = request.getSession(false); 
        if (session != null) {
            System.out.println("masuk");
            SecurityContext securityContext = (SecurityContext) session.getAttribute("SPRING_SECURITY_CONTEXT");
            if (securityContext != null) {
                Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
                if (authentication != null && authentication.isAuthenticated()) {
                    //ambil authorities
                    Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
    
                    boolean isAdmin = authorities.stream()
                            .anyMatch(grantedAuthority -> grantedAuthority.getAuthority().equals("ROLE_ADMIN"));
                    boolean isUser  = authorities.stream()
                            .anyMatch(grantedAuthority -> grantedAuthority.getAuthority().equals("ROLE_PENGGUNA"));
            
                    model.addAttribute("emaildijava", isAdmin);
                    model.addAttribute("pwdijava", isUser);
            
                    if (isAdmin) {
                        return "redirect:/pemilik/";
                        // return "/pemilik/dashboard"; // Return admin dashboard view
                        // return "login";
                    } else if (isUser ) {
                        return "redirect:/pengguna/";
                        // return "/pengguna/dashboard"; // Return user dashboard view
                        // return "login";
                    }
                }
            }
        }
        return "login";
    }
}
