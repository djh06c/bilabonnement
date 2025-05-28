package com.example.bilabonnement.Repository;
import com.example.bilabonnement.Repository.Mapper.tilstandRapportRowMapper;
import com.example.bilabonnement.Model.tilstandRapportModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class     tilstandRapportRepo {

    @Autowired
    private JdbcTemplate jdbcTemplate;


    public List<tilstandRapportModel> hentAlleRapporter() {
        String sql = "SELECT * FROM Tilstandsrapport ORDER BY kontrakt_ID, bil_ID";
        return jdbcTemplate.query(sql, new tilstandRapportRowMapper());
    }

    public tilstandRapportModel hentRapportVedId(int id) {
        String sql = "SELECT * FROM Tilstandsrapport WHERE rapport_ID = ?";
        return jdbcTemplate.queryForObject(sql, new tilstandRapportRowMapper(), id);
    }

    public void opretRapport(tilstandRapportModel rapport) {
        String sql = "INSERT INTO Tilstandsrapport (kontrakt_ID, bil_ID, kategori_ID, beskrivelse) VALUES (?, ?, ?, ?)";
        jdbcTemplate.update(sql,
                rapport.getKontraktID(),
                rapport.getBilID(),
                rapport.getKategoriID(),
                rapport.getBeskrivelse());
    }

    public void opdaterRapport(tilstandRapportModel rapport) {
        String sql = "UPDATE Tilstandsrapport SET kontrakt_ID = ?, bil_ID = ?, kategori_ID = ?, beskrivelse = ? WHERE rapport_ID = ?";
        jdbcTemplate.update(sql,
                rapport.getKontraktID(),
                rapport.getBilID(),
                rapport.getKategoriID(),
                rapport.getBeskrivelse(),
                rapport.getRapportID());
    }

    public void sletRapport(int id) {
        String sql = "DELETE FROM Tilstandsrapport WHERE rapport_ID = ?";
        jdbcTemplate.update(sql, id);
    }

    public int hentNaesteRapportID() {
        String sql = "SELECT AUTO_INCREMENT FROM INFORMATION_SCHEMA.TABLES " +
                "WHERE TABLE_SCHEMA = 'bilabonnement' AND TABLE_NAME = 'Tilstandsrapport'";
        return jdbcTemplate.queryForObject(sql, Integer.class);
    }

}