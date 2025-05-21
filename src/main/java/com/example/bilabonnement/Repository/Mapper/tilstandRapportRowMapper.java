package com.example.bilabonnement.Repository.Mapper;
import com.example.bilabonnement.Model.tilstandRapportModel;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class tilstandRapportRowMapper implements RowMapper<tilstandRapportModel> {

    @Override
    public tilstandRapportModel mapRow(ResultSet rs, int rowNum) throws SQLException {
        tilstandRapportModel rapport = new tilstandRapportModel();

        rapport.setRapportID(rs.getInt("rapport_ID"));
        rapport.setKontraktID(rs.getInt("kontrakt_ID"));
        rapport.setBilID(rs.getInt("bil_id"));
        rapport.setKategoriID(rs.getInt("kategori_ID"));
        rapport.setBeskrivelse(rs.getString("beskrivelse"));

        // erTotalskadet sættes automatisk baseret på kategoriID i model
        return rapport;
    }
}
