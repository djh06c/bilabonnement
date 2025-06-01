package com.example.bilabonnement.Repository.Mapper;

import com.example.bilabonnement.Model.kundeModel;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

// Denne klasse er en mapper, som bruges til at konvertere rækker fra databasen med ResultSet til kundeModel-objekter
// Hver række i databasen bliver til et kundeModel-objekt
// Når alle felter er sat, returnerer vi det færdige kundeModel-objekt
public class kundeRowMapper implements RowMapper<kundeModel> {

    // Denne metode bliver automatisk kaldt for hver række i databasen fra mySQL
    // rs er resultatet fra databasen, rowNum er hvilken række vi er på (tælles op fra 0)
    @Override
    public kundeModel mapRow(ResultSet rs, int rowNum) throws SQLException {
        kundeModel kunde = new kundeModel();
        kunde.setKundeID(rs.getInt("kunde_ID"));
        kunde.setNavn(rs.getString("navn"));
        kunde.setEmail(rs.getString("mail"));
        kunde.setAdresse(rs.getString("adresse"));
        kunde.setTlfNr(rs.getString("tlf"));
        kunde.setFoedselsdato(rs.getDate("foedselsdato").toLocalDate()); // rs.getDate("foedselsdato") henter fødselsdatoen som en java.sql.Date. Vi bruger .toLocalDate() for at konvertere det til java.time.LocalDate, som kundeModel forventer
        return kunde;
    }
}