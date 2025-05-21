package com.example.bilabonnement.Repository;

import com.example.bilabonnement.Model.skadeKategoriModel;
import com.example.bilabonnement.Repository.Mapper.skadeKategoriRowMapper; // <-- dette importeres
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class skadeKategoriRepo {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<skadeKategoriModel> hentAlleKategorier() {
        String sql = "SELECT * FROM Tilstandskategori";
        return jdbcTemplate.query(sql, new skadeKategoriRowMapper()); // <-- Her bruger du den
    }

    public skadeKategoriModel hentKategoriVedId(int id) {
        String sql = "SELECT * FROM Tilstandskategori WHERE kategori_ID = ?";
        return jdbcTemplate.queryForObject(sql, new skadeKategoriRowMapper(), id);
    }

    public void opretKategori(skadeKategoriModel kategori) {
        String sql = "INSERT INTO Tilstandskategori (kategori_ID, navn, beskrivelse) VALUES (?, ?, ?)";
        jdbcTemplate.update(sql, kategori.getKategoriID(), kategori.getNavn(), kategori.getBeskrivelse());
    }

    public void opdaterKategori(skadeKategoriModel kategori) {
        String sql = "UPDATE Tilstandskategori SET navn = ?, beskrivelse = ? WHERE kategori_ID = ?";
        jdbcTemplate.update(sql, kategori.getNavn(), kategori.getBeskrivelse(), kategori.getKategoriID());
    }

    public void sletKategori(int id) {
        String sql = "DELETE FROM Tilstandskategori WHERE kategori_ID = ?";
        jdbcTemplate.update(sql, id);
    }
}
