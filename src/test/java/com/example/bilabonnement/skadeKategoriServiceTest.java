package com.example.bilabonnement;

import com.example.bilabonnement.Model.skadeKategoriModel;
import com.example.bilabonnement.Repository.skadeKategoriRepo;
import com.example.bilabonnement.Service.skadeKategoriService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;

class skadeKategoriServiceTest {

    private skadeKategoriRepo mockRepo;
    private skadeKategoriService service;

    @BeforeEach
    void setUp() {
        mockRepo = Mockito.mock(skadeKategoriRepo.class);
        service = new skadeKategoriService();
        service.repo = mockRepo; // direkte injektion da vi ikke bruger Spring her
    }

    @Test
    void testHentAlleKategorier() {
        // Arrange
        skadeKategoriModel kat1 = new skadeKategoriModel(1, "Kosmetisk", "Mindre skrammer");
        skadeKategoriModel kat2 = new skadeKategoriModel(2, "Totalskadet", "Ikke brugbar");
        when(mockRepo.hentAlleKategorier()).thenReturn(Arrays.asList(kat1, kat2));

        // Act
        List<skadeKategoriModel> result = service.hentAlleKategorier();

        // Assert
        assert(result.size() == 2);
        assert(result.get(0).getNavn().equals("Kosmetisk"));
        verify(mockRepo, times(1)).hentAlleKategorier();
    }

    @Test
    void testOpretKategori() {
        skadeKategoriModel ny = new skadeKategoriModel(5, "Ingen skade", "Alt ser fint ud");
        service.opretKategori(ny);
        verify(mockRepo, times(1)).opretKategori(ny);
    }

    @Test
    void testOpdaterKategori() {
        skadeKategoriModel eksisterende = new skadeKategoriModel(1, "Kosmetisk", "Let ridse");
        service.opdaterKategori(eksisterende);
        verify(mockRepo, times(1)).opdaterKategori(eksisterende);
    }

    @Test
    void testSletKategori() {
        service.sletKategori(1);
        verify(mockRepo, times(1)).sletKategori(1);
    }

    @Test
    void testHentKategoriVedId() {
        skadeKategoriModel kat = new skadeKategoriModel(3, "Skade", "Beskrivelse");
        when(mockRepo.hentKategoriVedId(3)).thenReturn(kat);

        skadeKategoriModel result = service.hentKategoriVedId(3);

        assert(result.getKategoriID() == 3);
        assert(result.getNavn().equals("Skade"));
    }
}
