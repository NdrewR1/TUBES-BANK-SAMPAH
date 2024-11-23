package com.example.BankSampah;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.example.BankSampah.model.user.CustomUserDetailsService;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    private CustomUserDetailsService customUserDetailsService;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .authorizeHttpRequests(authorize -> authorize
                .requestMatchers("/", "/loginPage","/login").permitAll()
                .requestMatchers("/admin/**").hasRole("ADMIN")
                .requestMatchers("/pengguna/**").hasRole("PENGGUNA")
                .requestMatchers("/", "/registerPage", "/style.css", "uploads/image/**", "/add").permitAll()
                .anyRequest().authenticated()
            )
                .formLogin(form -> form
                .loginPage("/loginPage")
                .permitAll()
                // .loginProcessingUrl("/loginPage/login")
            )
            .csrf(csrf -> csrf
                .disable()
            )
            .logout(logout -> logout
            .invalidateHttpSession(true)
            .logoutUrl("/logout")
                .logoutSuccessUrl("/logoutPage")
                .permitAll()
                .clearAuthentication(true)
            )
            .sessionManagement(session -> session
                .sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED)
                .maximumSessions(1)
                .expiredUrl("/loginPage")
            )
            .securityContext(security -> security
                .securityContextRepository(new HttpSessionSecurityContextRepository())
            )
            ;

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}