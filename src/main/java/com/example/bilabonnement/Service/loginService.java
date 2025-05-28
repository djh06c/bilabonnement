package com.example.bilabonnement.Service;

import com.example.bilabonnement.Model.loginModel;
import com.example.bilabonnement.Repository.loginRepo;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import jakarta.annotation.PostConstruct;
import org.springframework.security.crypto.password.PasswordEncoder;


@Service
public class loginService implements UserDetailsService {

    private final loginRepo loginRepo;
    private final PasswordEncoder passwordEncoder;


    public loginService(loginRepo loginRepo, PasswordEncoder passwordEncoder) {
        this.loginRepo = loginRepo;
        this.passwordEncoder = passwordEncoder;
    }


    // UserDetailsService påkræver denne metode
    @Override
    public UserDetails loadUserByUsername(String brugernavn) throws UsernameNotFoundException {
        loginModel bruger = loginRepo.findByBrugernavn(brugernavn)
                .orElseThrow(() -> new UsernameNotFoundException("Bruger ikke fundet"));

        return User.builder()
                .username(bruger.getBrugernavn())
                .password(bruger.getAdgangskodeHash())
                .roles("USER")
                .build();
    }

    // Hasher adgangskode med BCrypt
    public String hashKode(String klartekstKode) {
        return new BCryptPasswordEncoder().encode(klartekstKode);
    }

    // Gem ny bruger (bruger skal have hash i forvejen)
    public void opretBruger(loginModel bruger) {
        loginRepo.gemBruger(bruger);
    }

    public loginModel findBruger(String brugernavn) {
        return loginRepo.findByBrugernavn(brugernavn)
                .orElseThrow(() -> new UsernameNotFoundException("Bruger ikke fundet"));
    }
    @PostConstruct
    public void tilfoejTestBruger() {
        if (loginRepo.findByBrugernavn("admin").isEmpty()) {
            loginModel bruger = new loginModel();
            bruger.setBrugernavn("admin");
            bruger.setAdgangskodeHash(passwordEncoder.encode("admin123"));
            loginRepo.gemBruger(bruger);
            System.out.println("Testbruger 'admin' oprettet");
        }
    }

}
