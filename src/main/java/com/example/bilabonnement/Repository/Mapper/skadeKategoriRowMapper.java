package com.example.bilabonnement.Repository.Mapper;

import com.example.bilabonnement.Model.skadeKategoriModel;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class skadeKategoriRowMapper implements RowMapper<skadeKategoriModel> {

    @Override
    public skadeKategoriModel mapRow(ResultSet rs, int rowNum) throws SQLException {
        skadeKategoriModel kategori = new skadeKategoriModel();
        kategori.setKategoriID(rs.getInt("kategori_ID"));
        kategori.setNavn(rs.getString("navn"));
        kategori.setBeskrivelse(rs.getString("beskrivelse"));
        return kategori;
    }
}
