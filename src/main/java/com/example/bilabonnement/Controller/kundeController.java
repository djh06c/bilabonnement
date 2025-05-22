package com.example.bilabonnement.Controller;

import com.example.bilabonnement.Model.kundeModel;
import com.example.bilabonnement.Service.kundeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class kundeController {

    private final kundeService kundeService;

    public kundeController(kundeService kundeService) {
        this.kundeService = kundeService;
    }

    // Vis alle kunder + tom kunde til formular
    @GetMapping("/kunder")
    public String visAlleKunder(Model model) {
        List<kundeModel> kunder = kundeService.findAll();
        model.addAttribute("kunder", kunder);
        model.addAttribute("kundeModel", new kundeModel());
        return "kunde";
    }

    // Opret ny kunde
    @PostMapping("/kunde/opret")
    public String opretKunde(@ModelAttribute kundeModel kunde) {
        kundeService.opretKunde(kunde);
        return "redirect:/kunder";
    }

    // Vis kunde til redigering
    @GetMapping("/kunde/{id}")
    @ResponseBody
    public kundeModel hentKunde(@PathVariable int id) {
        return kundeService.findById(id);
    }

    // Opdater kunde
    @PostMapping("/kunde/rediger")
    public String redigerKunde(@ModelAttribute kundeModel kunde) {
        kundeService.redigerKunde(kunde);
        return "redirect:/kunder";
    }

    // Slet kunde
    @PostMapping("/kunde/slet/{id}")
    public String sletKunde(@PathVariable int id) {
        kundeService.deleteById(id);
        return "redirect:/kunder";
    }
}