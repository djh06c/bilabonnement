package com.example.bilabonnement.Controller;

import com.example.bilabonnement.Model.skadeKategoriModel;
import com.example.bilabonnement.Service.skadeKategoriService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/skadekategorier")
public class skadeKategoriController {

    @Autowired
    private skadeKategoriService service;

    // 🔹 Vis alle skadekategorier (til Thymeleaf eller liste-visning)
    @GetMapping("/vis")
    public String visAlle(Model model) {
        List<skadeKategoriModel> kategorier = service.hentAlleKategorier();
        model.addAttribute("kategorier", kategorier);
        model.addAttribute("nyKategori", new skadeKategoriModel());
        return "skadekategorier"; // skal matche HTML-side i /templates
    }

    // 🔹 Opret ny kategori via formular
    @PostMapping("/opret")
    public String opretKategori(@ModelAttribute("nyKategori") skadeKategoriModel kategori) {
        service.opretKategori(kategori);
        return "redirect:/skadekategorier/vis";
    }

    // 🔹 (Valgfri) Rediger kategori
    @PostMapping("/opdater")
    public String opdaterKategori(@ModelAttribute skadeKategoriModel kategori) {
        service.opdaterKategori(kategori);
        return "redirect:/skadekategorier/vis";
    }

    // 🔹 (Valgfri) Slet kategori
    @GetMapping("/slet/{id}")
    public String sletKategori(@PathVariable int id) {
        service.sletKategori(id);
        return "redirect:/skadekategorier/vis";
    }
}
