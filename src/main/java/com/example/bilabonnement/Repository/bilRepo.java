package com.example.bilabonnement.Repository;

import com.example.bilabonnement.Model.bilModel;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import com.example.bilabonnement.Model.udstyrsniveau;

import java.util.List;

@Repository
public class bilRepo {

    private final JdbcTemplate jdbcTemplate;

    public bilRepo(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    // --- Hent alle biler ---
    public List<bilModel> hentAlleBiler() {
        String sql = """
        SELECT b.*, u.navn AS udstyrsniveauNavn
        FROM Bil b
        JOIN Udstyrsniveau u ON b.udstyrsniveau_ID = u.udstyrsniveau_ID
        """;
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(bilModel.class));
    }


    // --- Opret ny bil ---
    public void opretBil(bilModel bil) {
        String sql = "INSERT INTO Bil (vognNummer, stelNummer, regNr, model, maerke, co2, tilgaengelig, staalpris, udstyrsniveau_ID) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql,
                bil.getVognNummer(),
                bil.getStelNummer(),
                bil.getRegNr(),
                bil.getModel(),
                bil.getMaerke(),
                bil.getCo2(),
                bil.isTilgaengelig(),
                bil.getStaalpris(),
                bil.getUdstyrsniveauId()
        );
    }

    // --- Find én bil via ID ---
    public bilModel findById(int id) {
        String sql = "SELECT * FROM Bil WHERE bil_ID = ?";
        return jdbcTemplate.queryForObject(sql, new Object[]{id},
                new BeanPropertyRowMapper<>(bilModel.class));
    }

    // --- Opdater bil ---
    public void opdaterBil(bilModel bil) {
        String sql = "UPDATE Bil SET vognNummer = ?, stelNummer = ?, regNr = ?, model = ?, maerke = ?, co2 = ?, tilgaengelig = ?, staalpris = ?, udstyrsniveau_ID = ? WHERE bil_ID = ?";
        jdbcTemplate.update(sql,
                bil.getVognNummer(),
                bil.getStelNummer(),
                bil.getRegNr(),
                bil.getModel(),
                bil.getMaerke(),
                bil.getCo2(),
                bil.isTilgaengelig(),
                bil.getStaalpris(),
                bil.getUdstyrsniveauId(),
                bil.getBilId()
        );
    }

    // --- Slet bil ---
    public void sletBil(int id) {
        String sql = "DELETE FROM Bil WHERE bil_ID = ?";
        jdbcTemplate.update(sql, id);
    }

    public List<udstyrsniveau> hentAlleUdstyrsniveauer() {
        String sql = "SELECT * FROM Udstyrsniveau";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(udstyrsniveau.class));
    }

    public List<bilModel> hentLedigeBiler() {
        String sql = "SELECT * FROM Bil WHERE tilgaengelig = true";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(bilModel.class));
    }

}

