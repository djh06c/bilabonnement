package com.example.bilabonnement.Repository;

import com.example.bilabonnement.Model.kundeModel;
import com.example.bilabonnement.Repository.Mapper.kundeRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

// Denne klasse er et repository, altså et lager hvor den håndterer al kommunikation med databasen for kundedata for at hente og gemme
// Den bruger JdbcTemplate til at sende SQL-spørgsmål og modtage svar fra databasen
@Repository
public class kundeRepo {

    // JdbcTemplate er et Spring-værktøj, der gør det nemt at sende SQL-forespørgsler til databasen. Her bruger vi det til at lave en attribut/field
    private final JdbcTemplate jdbcTemplate;

    // Constructor injection. Her sender vi JdbcTemplate ind som parameter, så vi kan bruge det i metoderne
    public kundeRepo(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    // Denne metode henter alle kunder fra databasen
    // Den sender en SQL-forespørgsel hvor vi benytter os af SQL syntaks og siger "SELECT * FROM kunde" (vælg alle attributter fra kunde), og bruger kundeRowMapper til at konvertere alle rækker til kundeModel-objekter
    public List<kundeModel> hentAlleKunder() {
        String sql = "SELECT * FROM kunde";
        return jdbcTemplate.query(sql, new kundeRowMapper());
    }

    // Denne metode henter en enkelt kunde fra databasen baseret på kundeID
    // Vi bruger id som parameter fra kundemodel
    // Vi bruger "?"-tegnet som placeholder for ID-værdien. Vi spørger databasen om at finde kunden med det angivne ID
    // queryForObject er en metode fra Spring, som sender SQL-forespørgsel til databasen
    // queryForObject modtager præcis ét resultat fra databasen (om der findes en kunde med dette ID). Den vil kaste en exception hvis ID'et ikke eksisterer
    public kundeModel findById(int id) {
        String sql = "SELECT * FROM kunde WHERE kunde_ID = ?";
        return jdbcTemplate.queryForObject(sql, new kundeRowMapper(), id);
    }

    // Denne metode opretter en ny kunde i databasen
    // Vi bruger INSERT SQL syntaks med placeholders (?), og jdbcTemplate.update udfylder dem med data fra kunde-objektet. Vi bruger getters til at få fat på metoderne i kundeModel-klassen, da de er private
    public void opretKunde(kundeModel kunde) {
        String sql = "INSERT INTO kunde (navn, mail, adresse, tlf, foedselsdato) VALUES (?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql,
                kunde.getNavn(),
                kunde.getEmail(),
                kunde.getAdresse(),
                kunde.getTlfNr(),
                kunde.getFoedselsdato()
        );
    }

    // Denne metode opdaterer en eksisterende kundes data i databasen
    // Vi bruger UPDATE SQL syntaks og sætter nye værdier for alle felter baseret på kundeID
    public void opdaterKunde(kundeModel kunde) {
        String sql = "UPDATE kunde SET navn = ?, mail = ?, adresse = ?, tlf = ?, foedselsdato = ? WHERE kunde_ID = ?";
        jdbcTemplate.update(sql,
                kunde.getNavn(),
                kunde.getEmail(),
                kunde.getAdresse(),
                kunde.getTlfNr(),
                kunde.getFoedselsdato(),
                kunde.getKundeID()
        );
    }

    // Denne metode sletter en kunde fra databasen baseret på ID
    // Vi bruger DELETE SQL syntaks og fjerner rækken, hvor kundeID matcher det angivne id
    public void sletKunde(int id) {
        String sql = "DELETE FROM kunde WHERE kunde_ID = ?";
        jdbcTemplate.update(sql, id);
    }
}
