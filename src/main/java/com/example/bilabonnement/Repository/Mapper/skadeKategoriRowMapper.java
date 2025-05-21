package com.example.bilabonnement.Repository.Mapper;

import com.example.bilabonnement.Model.skadeKategoriModel;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class skadeKategoriRowMapper implements RowMapper<skadeKategoriModel> {

    @Override
    public skadeKategoriModel mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new skadeKategoriModel(
                rs.getInt("kategori_ID"),
                rs.getString("navn"),
                rs.getString("beskrivelse")
        );
    }
}