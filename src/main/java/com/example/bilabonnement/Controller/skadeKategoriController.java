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

    @GetMapping("/vis")
    public String visAlle(Model model) {
        model.addAttribute("kategoriForm", new skadeKategoriModel());
        model.addAttribute("kategorier", service.hentAlleKategorier());
        model.addAttribute("rediger", false);
        return "skadekategorier";
    }

    @GetMapping("/rediger/{id}")
    public String redigerKategori(@PathVariable int id, Model model) {
        model.addAttribute("kategoriForm", service.hentKategoriVedId(id));
        model.addAttribute("kategorier", service.hentAlleKategorier());
        model.addAttribute("rediger", true);
        return "skadekategorier";
    }

    @PostMapping("/opret")
    public String opretKategori(@ModelAttribute("kategoriForm") skadeKategoriModel kategori) {
        service.opretKategori(kategori);
        return "redirect:/skadekategorier/vis";
    }

    @PostMapping("/opdater")
    public String opdaterKategori(@ModelAttribute("kategoriForm") skadeKategoriModel kategori) {
        service.opdaterKategori(kategori);
        return "redirect:/skadekategorier/vis";
    }

    @GetMapping("/slet/{id}")
    public String sletKategori(@PathVariable int id) {
        service.sletKategori(id);
        return "redirect:/skadekategorier/vis";
    }
}