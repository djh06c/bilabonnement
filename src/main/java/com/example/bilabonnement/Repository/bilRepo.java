package com.example.bilabonnement.Repository;

import com.example.bilabonnement.Model.bilModel;
import com.example.bilabonnement.Model.udstyrsniveau;
import com.example.bilabonnement.Repository.Mapper.bilRowMapper;
import com.example.bilabonnement.Repository.Mapper.udstyrsniveauRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class bilRepo {

    private final JdbcTemplate jdbcTemplate;

    public bilRepo(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    // --- Hent alle biler (med JOIN for udstyrsniveauNavn) ---
    public List<bilModel> hentAlleBiler() {
        String sql = """
            SELECT b.*, u.navn AS udstyrsniveauNavn
            FROM Bil b
            JOIN Udstyrsniveau u ON b.udstyrsniveau_ID = u.udstyrsniveau_ID
        """;
        return jdbcTemplate.query(sql, new bilRowMapper());
    }

    // --- Hent kun ledige biler ---
    public List<bilModel> hentLedigeBiler() {
        String sql = """
        SELECT b.*, u.navn AS udstyrsniveauNavn
        FROM Bil b
        JOIN Udstyrsniveau u ON b.udstyrsniveau_ID = u.udstyrsniveau_ID
        WHERE b.tilgaengelig = true
    """;
        return jdbcTemplate.query(sql, new bilRowMapper());
    }


    // --- Find én bil via ID ---
    public bilModel findById(int id) {
        String sql = "SELECT * FROM Bil WHERE bil_ID = ?";
        return jdbcTemplate.queryForObject(sql, new bilRowMapper(), id);
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

    // --- Opdater eksisterende bil ---
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

    // --- Slet bil via ID ---
    public void sletBil(int id) {
        String sql = "DELETE FROM Bil WHERE bil_ID = ?";
        jdbcTemplate.update(sql, id);
    }

    // --- Hent alle udstyrsniveauer ---
    public List<udstyrsniveau> hentAlleUdstyrsniveauer() {
        String sql = "SELECT * FROM Udstyrsniveau";
        return jdbcTemplate.query(sql, new udstyrsniveauRowMapper());
    }

    public List<bilModel> hentBilerSorteretEfter(String kolonneNavn) {
        String sql = String.format("""
        SELECT b.*, u.navn AS udstyrsniveauNavn
        FROM Bil b
        JOIN Udstyrsniveau u ON b.udstyrsniveau_ID = u.udstyrsniveau_ID
        ORDER BY %s
        """, kolonneNavn);
        return jdbcTemplate.query(sql, new bilRowMapper());
    }

}
