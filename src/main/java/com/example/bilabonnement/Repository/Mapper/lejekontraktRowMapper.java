package com.example.bilabonnement.Repository.Mapper;

import com.example.bilabonnement.Model.lejekontraktModel;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class lejekontraktRowMapper implements RowMapper<lejekontraktModel> {

    @Override
    public lejekontraktModel mapRow(ResultSet rs, int rowNum) throws SQLException {
        lejekontraktModel lk = new lejekontraktModel();
        lk.setKontraktID(rs.getInt("kontrakt_ID"));
        lk.setKundeID(rs.getInt("kunde_ID"));
        lk.setBilID(rs.getInt("bil_ID"));
        lk.setStartDato(rs.getDate("start_dato").toLocalDate());
        lk.setSlutDato(rs.getDate("slut_dato").toLocalDate());
        lk.setPickupSted(rs.getString("pickupSted"));
        lk.setAfleveringsSted(rs.getString("afleveringsSted"));
        lk.setPris(rs.getBigDecimal("pris"));
        return lk;
    }
}
