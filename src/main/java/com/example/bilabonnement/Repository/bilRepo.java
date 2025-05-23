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

    public List<bilModel> hentAlleBiler() {
        String sql = """
            SELECT b.*, u.navn AS udstyrsniveauNavn
            FROM Bil b
            JOIN Udstyrsniveau u ON b.udstyrsniveau_ID = u.udstyrsniveau_ID
        """;
        return jdbcTemplate.query(sql, new bilRowMapper());
    }

    public List<bilModel> hentLedigeBiler() {
        String sql = """
            SELECT b.*, u.navn AS udstyrsniveauNavn
            FROM Bil b
            JOIN Udstyrsniveau u ON b.udstyrsniveau_ID = u.udstyrsniveau_ID
            WHERE b.tilgaengelig = true
        """;
        return jdbcTemplate.query(sql, new bilRowMapper());
    }

    public bilModel findById(int id) {
        String sql = """
            SELECT b.*, u.navn AS udstyrsniveauNavn
            FROM Bil b
            JOIN Udstyrsniveau u ON b.udstyrsniveau_ID = u.udstyrsniveau_ID
            WHERE b.bil_ID = ?
        """;
        return jdbcTemplate.queryForObject(sql, new bilRowMapper(), id);
    }

    public void opretBil(bilModel bil) {
        String sql = """
            INSERT INTO Bil 
            (vognNummer, stelNummer, regNr, model, maerke, co2, tilgaengelig, staalpris, udstyrsniveau_ID)
            VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)
        """;
        jdbcTemplate.update(sql,
                bil.getVognNummer(),
                bil.getStelNummer(),
                bil.getRegNr(),
                bil.getModel(),
                bil.getMaerke(),
                bil.getCo2(),
                bil.isTilgaengelig(),
                bil.getStaalpris(),
                bil.getUdstyrsniveauId());
    }

    public void opdaterBil(bilModel bil) {
        String sql = """
            UPDATE Bil SET 
                vognNummer = ?, 
                stelNummer = ?, 
                regNr = ?, 
                model = ?, 
                maerke = ?, 
                co2 = ?, 
                tilgaengelig = ?, 
                staalpris = ?, 
                udstyrsniveau_ID = ?
            WHERE bil_ID = ?
        """;
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
                bil.getBilId());
    }

    public void sletBil(int id) {
        String sql = "DELETE FROM Bil WHERE bil_ID = ?";
        jdbcTemplate.update(sql, id);
    }

    public List<udstyrsniveau> hentAlleUdstyrsniveauer() {
        String sql = "SELECT * FROM Udstyrsniveau";
        return jdbcTemplate.query(sql, new udstyrsniveauRowMapper());
    }

    public List<bilModel> hentBilerSorteretEfter(String kolonneNavn) {
        // Sikkerhed: whitelist tilladte kolonner
        List<String> tilladteKolonner = List.of(
                "bil_ID", "model", "maerke", "regNr", "stelNummer", "vognNummer",
                "co2", "staalpris", "tilgaengelig", "udstyrsniveauNavn"
        );

        if (!tilladteKolonner.contains(kolonneNavn)) {
            kolonneNavn = "bil_ID"; // fallback for sikkerhed
        }

        String sql = String.format("""
            SELECT b.*, u.navn AS udstyrsniveauNavn
            FROM Bil b
            JOIN Udstyrsniveau u ON b.udstyrsniveau_ID = u.udstyrsniveau_ID
            ORDER BY %s
        """, kolonneNavn);

        return jdbcTemplate.query(sql, new bilRowMapper());
    }
}
