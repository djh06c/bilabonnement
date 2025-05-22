package com.example.bilabonnement.Repository;

import com.example.bilabonnement.Model.kundeModel;
import com.example.bilabonnement.Repository.Mapper.kundeRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.List;

@Repository
public class kundeRepo {

    private final JdbcTemplate jdbcTemplate;

    public kundeRepo(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    public List<kundeModel> findAll() {
        String sql = "SELECT * FROM Kunde";
        return jdbcTemplate.query(sql, new kundeRowMapper());
    }


    public kundeModel findById(int id) {
        String sql = "SELECT * FROM Kunde WHERE kunde_ID = ?";
        return jdbcTemplate.queryForObject(sql, new kundeRowMapper(), id);
    }


    public void save(kundeModel kunde) {
        String sql = "INSERT INTO Kunde (kunde_ID, navn, mail, adresse, tlf, foedselsdato) VALUES (?, ?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql,
                kunde.getKundeID(),
                kunde.getNavn(),
                kunde.getEmail(),
                kunde.getAdresse(),
                kunde.getTlfNr(),
                Date.valueOf(kunde.getFoedselsdato()));
    }


    public void update(kundeModel kunde) {
        String sql = "UPDATE Kunde SET navn = ?, mail = ?, adresse = ?, tlf = ?, foedselsdato = ? WHERE kunde_ID = ?";
        jdbcTemplate.update(sql,
                kunde.getNavn(),
                kunde.getEmail(),
                kunde.getAdresse(),
                kunde.getTlfNr(),
                Date.valueOf(kunde.getFoedselsdato()),
                kunde.getKundeID());
    }


    public void deleteById(int id) {
        String sql = "DELETE FROM Kunde WHERE kunde_ID = ?";
        jdbcTemplate.update(sql, id);
    }
}
