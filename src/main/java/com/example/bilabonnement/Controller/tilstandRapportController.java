package com.example.bilabonnement.Controller;

import com.example.bilabonnement.Model.tilstandRapportModel;
import com.example.bilabonnement.Model.skadeKategoriModel;
import com.example.bilabonnement.Service.tilstandRapportService;
import com.example.bilabonnement.Service.skadeKategoriService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/tilstandsrapporter")
public class tilstandRapportController {

    @Autowired
    private tilstandRapportService tilstandService;

    @Autowired
    private skadeKategoriService skadeService;

    @Autowired
    private lejekontraktService lejekontraktService;

    // ✅ HTML-visning
    @GetMapping("/vis")
    public String visTilstande(Model model) {
        List<tilstandRapportModel> rapporter = tilstandService.hentAlleRapporter();
        List<skadeKategoriModel> kategorier = skadeService.hentAlleKategorier();
        List<lejekontraktModel> kontrakter = lejekontraktService.findAll();

        model.addAttribute("rapporter", rapporter);
        model.addAttribute("kategorier", kategorier);
        model.addAttribute("kontrakter", kontrakter);
        model.addAttribute("nyRapport", new tilstandRapportModel());

        return "tilstande";
    }

    // ✅ Gem rapport fra formular
    @PostMapping("/vis")
    public String opretRapportFraForm(@ModelAttribute("nyRapport") tilstandRapportModel rapport) {
        // OBS: kontraktID skal eksistere i databasen, ellers får du SQL-fejl!
        tilstandService.opretRapport(rapport);
        return "redirect:/tilstandsrapporter/vis";
    }
}
