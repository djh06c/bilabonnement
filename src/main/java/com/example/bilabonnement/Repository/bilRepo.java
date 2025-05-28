package com.example.bilabonnement.Repository;

import com.example.bilabonnement.Model.bilModel;
import com.example.bilabonnement.Repository.Mapper.bilRowMapper;
import com.example.bilabonnement.Model.Udstyrsniveau;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.Arrays;
import java.util.List;

@Repository
public class bilRepo {

    private final JdbcTemplate jdbcTemplate;

    public bilRepo(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    // Opret bil
    public void opretBil(bilModel bil) {
        String sql = """
        INSERT INTO Bil 
        (vognNummer, stelNummer, regNr, model, maerke, co2, tilgaengelig, staalpris, udstyrsniveau, maanedspris)
        VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)
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
                bil.getUdstyrsniveau().name(),
                bil.getMaanedspris()
        );
    }

    // Hent alle biler
    public List<bilModel> hentAlleBiler() {
        String sql = "SELECT * FROM Bil";
        return jdbcTemplate.query(sql, new bilRowMapper());
    }

    // Find bil efter ID
    public bilModel findById(int id) {
        String sql = "SELECT * FROM Bil WHERE bil_ID = ?";
        return jdbcTemplate.queryForObject(sql, new bilRowMapper(), id);
    }

    // Opdater bil
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
            udstyrsniveau = ?, 
            maanedspris = ?
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
                bil.getUdstyrsniveau().name(), // ENUM som STRING
                bil.getMaanedspris(),
                bil.getBilId()
        );
    }

    // Slet bil
    public void sletBil(int id) {
        String sql = "DELETE FROM Bil WHERE bil_ID = ?";
        jdbcTemplate.update(sql, id);
    }

    // Hent alle udstyrsniveauer fra ENUM
    public List<Udstyrsniveau> hentAlleUdstyrsniveauer() {
        return Arrays.asList(Udstyrsniveau.values());
    }

    // Hent biler sorteret efter kolonne
    public List<bilModel> hentBilerSorteretEfter(String kolonneNavn) {
        List<String> tilladteKolonner = List.of(
                "bil_ID", "model", "maerke", "regNr", "stelNummer", "vognNummer",
                "co2", "staalpris", "maanedspris", "tilgaengelig", "udstyrsniveau"
        );

        if (!tilladteKolonner.contains(kolonneNavn)) {
            kolonneNavn = "bil_ID"; // fallback
        }

        String sql = "SELECT * FROM Bil ORDER BY " + kolonneNavn;
        return jdbcTemplate.query(sql, new bilRowMapper());
    }

    public bilModel findNyesteBil() {
        String sql = "SELECT * FROM Bil ORDER BY bil_ID DESC LIMIT 1";
        return jdbcTemplate.queryForObject(sql, new bilRowMapper());
    }
}
