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
    public lejekontraktModel findById(int kontraktId){
        String sql= "SELECT * from lejekontrakt where id=?";
        return jdbcTemplate.queryForObject(sql, new lejekontraktRowMapper(), kontraktId);
    }

    /* --------------- Opret en ny lejekontrakk --------------- */
    public void save(lejekontraktModel lk){
        String sql = "INSERT INTO lejekontrakt (KontraktID, KundeID, BilID, StartDato, SlutDato, PickupSted, AfleverSted, Pris) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql,
                lk.getKontraktID(),
                lk.getKundeID(),
                lk.getBilID(),
                Date.valueOf(lk.getStartDato()),
                Date.valueOf(lk.getSlutDato()),
                lk.getPickupSted(),
                lk.getAfleverSted(),
                lk.getPris());
    }

    /* --------------- Opdater en eksisterende lejekontrakt --------------- */
    public void update(lejekontraktModel lk){
        String sql = "UPDATE Lejekontrakt SET KundeID = ?, BilID = ?, StartDato =?, SlutDato =?, PickupSted =?, AfleverSted =?, Pris =? WHERE KontraktID =?";
        jdbcTemplate.update(sql,
                lk.getKundeID(),
                lk.getBilID(),
                Date.valueOf(lk.getStartDato()),
                Date.valueOf(lk.getSlutDato()),
                lk.getPickupSted(),
                lk.getAfleverSted(),
                lk.getPris());
    }

    /* --------------- Slet en Kontrakt --------------- */
    public void deleteById(int kontraktId){
        String sql = "DELETE FROM lejekontrakt WHERE KontraktID = ?";
        jdbcTemplate.update(sql, kontraktId);
    }
}
