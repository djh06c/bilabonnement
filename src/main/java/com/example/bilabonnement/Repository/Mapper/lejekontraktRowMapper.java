package com.example.bilabonnement.Repository.Mapper;

import com.example.bilabonnement.Model.lejekontraktModel;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Date;

public class lejekontraktRowMapper implements RowMapper<lejekontraktModel> {

    @Override
    public lejekontraktModel mapRow(ResultSet rs, int rowNum) throws SQLException {
        lejekontraktModel lk = new lejekontraktModel();
        lk.setKontraktID(rs.getInt("KontraktID"));
        lk.setKundeID(rs.getInt("KundeID"));
        lk.setBilID(rs.getInt("BilID"));
        lk.setStartDato(rs.getDate("StartDato").toLocalDate());
        lk.setSlutDato(rs.getDate("SlutDato").toLocalDate());
        lk.setPickupSted(rs.getString("PickupSted"));
        lk.setAfleverSted(rs.getString("AfleverSted"));
        lk.setPris(rs.getBigDecimal("Pris"));
        return lk;
    }
}
