package com.example.bilabonnement.Repository;

import com.example.bilabonnement.Model.kundeModel;
import com.example.bilabonnement.Repository.Mapper.kundeRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class kundeRepo {

    private final JdbcTemplate jdbcTemplate;

    public kundeRepo(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    // Hent alle kunder
    public List<kundeModel> hentAlleKunder() {
        String sql = "SELECT * FROM kunde";
        return jdbcTemplate.query(sql, new kundeRowMapper());
    }

    // Find én kunde via ID
    public kundeModel findById(int id) {
        String sql = "SELECT * FROM kunde WHERE kunde_ID = ?";
        return jdbcTemplate.queryForObject(sql, new kundeRowMapper(), id);
    }

    // Opret ny kunde
    public void opretKunde(kundeModel kunde) {
        String sql = "INSERT INTO kunde (navn, mail, adresse, tlf, foedselsdato) VALUES (?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql,
                kunde.getNavn(),
                kunde.getEmail(),
                kunde.getAdresse(),
                kunde.getTlfNr(),
                kunde.getFoedselsdato()
        );
    }

    // Opdater eksisterende kunde
    public void opdaterKunde(kundeModel kunde) {
        String sql = "UPDATE kunde SET navn = ?, mail = ?, adresse = ?, tlf = ?, foedselsdato = ? WHERE kunde_ID = ?";
        jdbcTemplate.update(sql,
                kunde.getNavn(),
                kunde.getEmail(),
                kunde.getAdresse(),
                kunde.getTlfNr(),
                kunde.getFoedselsdato(),
                kunde.getKundeID()
        );
    }

    // Slet kunde
    public void sletKunde(int id) {
        String sql = "DELETE FROM kunde WHERE kunde_ID = ?";
        jdbcTemplate.update(sql, id);
    }
}
