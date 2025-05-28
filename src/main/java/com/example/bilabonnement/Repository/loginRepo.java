package com.example.bilabonnement.Repository;

import com.example.bilabonnement.Model.loginModel;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class loginRepo {

    private final JdbcTemplate jdbcTemplate;

    public loginRepo(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    // Find bruger ud fra brugernavn – bruges til login
    public Optional<loginModel> findByBrugernavn(String brugernavn) {
        String sql = "SELECT * FROM Login WHERE brugernavn = ?";
        try {
            loginModel bruger = jdbcTemplate.queryForObject(
                    sql,
                    new Object[]{brugernavn},
                    new BeanPropertyRowMapper<>(loginModel.class)
            );
            return Optional.of(bruger);
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    // Tilføj ny bruger
    public void gemBruger(loginModel bruger) {
        String sql = "INSERT INTO Login (brugernavn, adgangskodeHash) VALUES (?, ?)";
        jdbcTemplate.update(sql, bruger.getBrugernavn(), bruger.getAdgangskodeHash());
    }
}
