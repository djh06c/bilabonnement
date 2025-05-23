package com.example.bilabonnement.Controller;

import com.example.bilabonnement.Model.lejekontraktModel;
import com.example.bilabonnement.Service.lejekontraktService;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class HtmlViewController {

    private final lejekontraktService service;

    public HtmlViewController(lejekontraktService service) {
        this.service = service;
    }

    // Viser OPRET-side (lejekontrakter.html)
    @GetMapping("/lejekontrakter")
    public String visOpretSide(Model model) {
        model.addAttribute("lejekontraktModel", new lejekontraktModel());
        return "lejekontrakter"; // filen: lejekontrakter.html
    }

    // Viser VIS/SLET-side (visLejekontrakter.html)
    @GetMapping("/lejekontrakter/vis")
    public String visAlleKontrakter(Model model) {
        List<lejekontraktModel> kontrakter = service.findAll();
        model.addAttribute("lejekontrakter", kontrakter);
        return "visLejekontrakter"; // filen: visLejekontrakter.html
    }

    // Opretter ny lejekontrakt fra formular
    @PostMapping("/lejekontrakter/opret")
    public String opretFraHtml(@ModelAttribute lejekontraktModel kontrakt, Model model) {
        try {
            service.save(kontrakt);
            model.addAttribute("success", "Lejekontrakt oprettet");
        } catch (DataIntegrityViolationException e) {
            model.addAttribute("error", "Fejl: Kunde eller bil findes ikke");
        }

        model.addAttribute("lejekontraktModel", new lejekontraktModel());
        return "lejekontrakter";
    }

    // Sletter en lejekontrakt fra vis-siden
    @PostMapping("/lejekontrakter/slet")
    public String sletFraHtml(@RequestParam int id, Model model) {
        service.deleteById(id);
        List<lejekontraktModel> kontrakter = service.findAll();
        model.addAttribute("lejekontrakter", kontrakter);
        return "visLejekontrakter";
    }
}
