package com.example.bilabonnement.Repository.Mapper;

import com.example.bilabonnement.Model.bilModel;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class bilRowMapper implements RowMapper<bilModel> {
    @Override
    public bilModel mapRow(ResultSet rs, int rowNum) throws SQLException {
        bilModel bil = new bilModel();
        bil.setBilId(rs.getInt("bil_ID"));
        bil.setVognNummer(rs.getString("vognNummer"));
        bil.setStelNummer(rs.getString("stelNummer"));
        bil.setRegNr(rs.getString("regNr"));
        bil.setModel(rs.getString("model"));
        bil.setMaerke(rs.getString("maerke"));
        bil.setCo2(rs.getDouble("co2"));
        bil.setTilgaengelig(rs.getBoolean("tilgaengelig"));
        bil.setStaalpris(rs.getDouble("staalpris"));
        bil.setUdstyrsniveauId(rs.getInt("udstyrsniveau_ID"));

        // Hvis vi har lavet JOIN med navn
        try {
            bil.setUdstyrsniveauNavn(rs.getString("udstyrsniveauNavn"));
        } catch (SQLException ignored) {
            // Feltet findes ikke i alle queries
        }

        return bil;
    }
}
