package com.example.bilabonnement.Controller;

import com.example.bilabonnement.Model.kundeModel;
import com.example.bilabonnement.Service.kundeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class kundeController {

    private final kundeService kundeService;

    public kundeController(kundeService kundeService) {
        this.kundeService = kundeService;
    }

    //  Listevisning af kunder
    @GetMapping("/kunder")
    public String visAlleKunder(Model model) {
        model.addAttribute("kunder", kundeService.hentAlleKunder());
        return "kunder";
    }

    //  Vis formular til oprettelse
    @GetMapping("/kunder/opret")
    public String visOpretForm(Model model) {
        model.addAttribute("kundeModel", new kundeModel());
        return "opret-kunde";
    }

    //  Gem ny kunde
    @PostMapping("/kunder/opret")
    public String opretKunde(@ModelAttribute kundeModel kunde) {
        kundeService.opretKunde(kunde);
        return "redirect:/kunder";
    }

    //  Vis formular til redigering
    @GetMapping("/kunder/rediger/{id}")
    public String visRedigerForm(@PathVariable int id, Model model) {
        kundeModel kunde = kundeService.findById(id);
        model.addAttribute("kundeModel", kunde);
        return "rediger-kunde";
    }

    //  Gem ændret kunde
    @PostMapping("/kunder/rediger")
    public String opdaterKunde(@ModelAttribute kundeModel kunde) {
        kundeService.opdaterKunde(kunde);
        return "redirect:/kunder";
    }

    //  Slet kunde
    @PostMapping("/kunder/slet/{id}")
    public String sletKunde(@PathVariable int id) {
        kundeService.sletKunde(id);
        return "redirect:/kunder";
    }
}