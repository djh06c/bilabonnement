package com.example.bilabonnement.Repository.Mapper;

import com.example.bilabonnement.Model.udstyrsniveau;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class udstyrsniveauRowMapper implements RowMapper<udstyrsniveau> {
    @Override
    public udstyrsniveau mapRow(ResultSet rs, int rowNum) throws SQLException {
        udstyrsniveau u = new udstyrsniveau();
        u.setUdstyrsniveauId(rs.getInt("udstyrsniveau_ID"));
        u.setNavn(rs.getString("navn"));
        return u;
    }
}
