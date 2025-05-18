package com.example.bilabonnement.Repository.Mapper;

import com.example.bilabonnement.Model.kundeModel;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class kundeRowMapper implements RowMapper<kundeModel> {

    @Override
    public kundeModel mapRow(ResultSet rs, int rowNum) throws SQLException {
        kundeModel kunde = new kundeModel();
        kunde.setKundeID(rs.getInt("kunde_ID"));
        kunde.setNavn(rs.getString("navn"));
        kunde.setEmail(rs.getString("mail"));
        kunde.setAdresse(rs.getString("adresse"));
        kunde.setTlfNr(rs.getString("tlf"));
        kunde.setFeodselsdato(rs.getDate("foedselsdato").toLocalDate());
        return kunde;
    }
}
