package com.example.bilabonnement;

import com.example.bilabonnement.Model.bilModel;
import com.example.bilabonnement.Repository.bilRepo;
import com.example.bilabonnement.Service.bilService;
import org.junit.jupiter.api.Test;
import org.springframework.dao.DataAccessException;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

class bilServiceTest {

    @Test
    void testOpretBilKalderRepo() {
        // Arrange
        bilRepo mockRepo = mock(bilRepo.class);
        bilService service = new bilService(mockRepo);

        bilModel bil = new bilModel();
        bil.setModel("Golf");
        bil.setMaerke("VW");
        bil.setRegNr("AB11111");
        bil.setStelNummer("STELTEST001");
        bil.setVognNummer("VTEST001");
        bil.setCo2(110);
        bil.setStaalpris(210000);
        bil.setTilgaengelig(true);
        bil.setUdstyrsniveauId(2);

        // Act
        service.opretBil(bil);

        // Assert
        verify(mockRepo).opretBil(bil);  // Bekræfter at metoden blev kaldt
    }




        @Test
        void testOpretBilKasterExceptionHvisRepoFejler() {
            // Arrange
            bilRepo mockRepo = mock(bilRepo.class);
            bilService service = new bilService(mockRepo);

            bilModel bil = new bilModel();
            bil.setModel("Fejlmodel");

            // Simulér at repo fejler
            doThrow(new DataAccessException("Databasefejl") {}).when(mockRepo).opretBil(bil);

            // Act + Assert
            assertThrows(DataAccessException.class, () -> service.opretBil(bil));
        }
    }


