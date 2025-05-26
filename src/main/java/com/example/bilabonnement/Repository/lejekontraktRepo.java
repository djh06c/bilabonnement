package com.example.bilabonnement.Repository;

import com.example.bilabonnement.Model.lejekontraktModel;
import com.example.bilabonnement.Repository.Mapper.lejekontraktRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.List;

@Repository
public class lejekontraktRepo {

    private final JdbcTemplate jdbcTemplate;

    public lejekontraktRepo(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    /* --------------- Hent/læs alle Lejekontrakt ---------------*/
    public List<lejekontraktModel> findAll(){
        String sql= "SELECT * from lejekontrakt";
        return jdbcTemplate.query(sql, new lejekontraktRowMapper());
    }

    /* --------------- Hent en Lejekontrakt fra ID --------------- */
    public lejekontraktModel findById(int kontraktId) {
        String sql = "SELECT * FROM lejekontrakt WHERE kontrakt_ID = ?";
        List<lejekontraktModel> result = jdbcTemplate.query(sql, new lejekontraktRowMapper(), kontraktId);
        return result.isEmpty() ? null : result.get(0);
    }

    /* --------------- Find alle aktive rapporter --------------- */
    public List<lejekontraktModel> findeAktiveKontrakter(){
        String sql = """
                SELECT * FROM Lejekontrakt
                WHERE start_dato <= CURRENT_DATE() AND slut_dato >= CURRENT_DATE()
                """;
        return jdbcTemplate.query(sql, new lejekontraktRowMapper());
    }

    /* --------------- Opret en ny lejekontrakk --------------- */
    public void save(lejekontraktModel lk){
        String sql = "INSERT INTO lejekontrakt (kontrakt_ID, kunde_ID, bil_ID, start_dato, slut_dato, pickupSted, afleveringsSted, pris) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql,
                lk.getKontraktID(),
                lk.getKundeID(),
                lk.getBilID(),
                Date.valueOf(lk.getStartDato()),
                Date.valueOf(lk.getSlutDato()),
                lk.getPickupSted(),
                lk.getAfleveringsSted(),
                lk.getPris());
    }

    /* --------------- Opdater en eksisterende lejekontrakt --------------- */
    public void update(lejekontraktModel lk){
        String sql = "UPDATE Lejekontrakt SET kunde_ID = ?, bil_ID = ?, Start_dato =?, Slut_dato =?, PickupSted =?, AfleveringsSted =?, Pris =? WHERE kontrakt_ID =?";
        jdbcTemplate.update(sql,
                lk.getKundeID(),
                lk.getBilID(),
                Date.valueOf(lk.getStartDato()),
                Date.valueOf(lk.getSlutDato()),
                lk.getPickupSted(),
                lk.getAfleveringsSted(),
                lk.getPris(),
                lk.getKontraktID());
    }

    /* --------------- Slet en Kontrakt --------------- */
    public void deleteById(int kontraktId){
        String sql = "DELETE FROM lejekontrakt WHERE kontrakt_ID = ?";
        jdbcTemplate.update(sql, kontraktId);
    }
}
