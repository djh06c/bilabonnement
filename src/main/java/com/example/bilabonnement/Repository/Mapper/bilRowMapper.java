package com.example.bilabonnement.Repository.Mapper;

import com.example.bilabonnement.Model.bilModel;
import com.example.bilabonnement.Model.Udstyrsniveau;
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
        bil.setMaanedspris(rs.getDouble("maanedspris"));

        // Hent ENUM direkte fra databasefeltet
        bil.setUdstyrsniveau(Udstyrsniveau.valueOf(rs.getString("udstyrsniveau")));

        return bil;
    }
}
