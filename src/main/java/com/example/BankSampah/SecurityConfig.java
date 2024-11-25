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
                .requestMatchers("/", "/loginPage", "/loginPage/login","/registerPage", "/logoutPage", "/style.css", "uploads/image/**", "/addAdmin").permitAll()
                .requestMatchers("/pemilik/**").hasRole("ADMIN")
                .requestMatchers("/pengguna/**").hasRole("PENGGUNA")
                .requestMatchers("/dashboard").authenticated()
                .anyRequest().authenticated()
                // .anyRequest().permitAll()
            )
                .formLogin(form -> form
                .loginPage("/loginPage")
                .defaultSuccessUrl("/dashboard", true)
                .permitAll()
                // .loginProcessingUrl("/loginPage/login")
            )
            .csrf(csrf -> csrf
                .disable()
            )
            .logout(logout -> logout
            .invalidateHttpSession(true)
            .clearAuthentication(true)
            .logoutUrl("/logout")
                .logoutSuccessUrl("/logoutPage")
                .deleteCookies("JSESSIONID")
                .permitAll()
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