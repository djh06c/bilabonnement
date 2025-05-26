package com.example.bilabonnement.Controller;

import com.example.bilabonnement.Model.tilstandRapportModel;
import com.example.bilabonnement.Model.skadeKategoriModel;
import com.example.bilabonnement.Model.lejekontraktModel;
import com.example.bilabonnement.Service.tilstandRapportService;
import com.example.bilabonnement.Service.skadeKategoriService;
import com.example.bilabonnement.Service.lejekontraktService;

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
    // Slet rapport
    @GetMapping("/slet/{id}")
    public String sletRapport(@PathVariable int id) {
        tilstandService.sletRapport(id);
        return "redirect:/tilstandsrapporter/vis";
    }

    // Gå til redigeringsside
    @GetMapping("/rediger/{id}")
    public String redigerRapport(@PathVariable int id, Model model) {
        tilstandRapportModel rapport = tilstandService.hentRapportVedId(id);
        model.addAttribute("redigerRapport", rapport);
        model.addAttribute("kategorier", skadeService.hentAlleKategorier());
        model.addAttribute("kontrakter", lejekontraktService.findAll());
        return "tilstande-rediger";
    }

    // Gem ændringer
    @PostMapping("/rediger")
    public String opdaterRapport(@ModelAttribute("redigerRapport") tilstandRapportModel rapport) {
        tilstandService.opdaterRapport(rapport);
        return "redirect:/tilstandsrapporter/vis";
    }
}
