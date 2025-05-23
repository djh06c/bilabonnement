package com.example.bilabonnement.Controller;

import com.example.bilabonnement.Model.kundeModel;
import com.example.bilabonnement.Service.kundeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/kunde")
public class kundeController {

    private final kundeService kundeService;

    public kundeController(kundeService kundeService) {
        this.kundeService = kundeService;
    }

    // Vis alle kunder
    @GetMapping("/kunder")
    public String visAlleKunder(Model model) {
        model.addAttribute("kunder", kundeService.hentAlleKunder());
        return "kunder";
    }

    @GetMapping("/opret")
    public String visOpretForm(Model model) {
        model.addAttribute("kundeModel", new kundeModel());
        return "opret-kunde";
    }

    @PostMapping("/opret")
    public String opretKunde(@ModelAttribute kundeModel kunde) {
        kundeService.opretKunde(kunde);
        return "redirect:/kunde/kunder";
    }

    @GetMapping("/rediger/{id}")
    public String visRedigerForm(@PathVariable int id, Model model) {
        kundeModel kunde = kundeService.findById(id);
        model.addAttribute("kundeModel", kunde);
        return "rediger-kunde";
    }

    @PostMapping("/rediger")
    public String opdaterKunde(@ModelAttribute kundeModel kunde) {
        kundeService.opdaterKunde(kunde);
        return "redirect:/kunde/kunder";
    }

    @PostMapping("/slet/{id}")
    public String sletKunde(@PathVariable int id) {
        kundeService.sletKunde(id);
        return "redirect:/kunde/kunder";
    }
}