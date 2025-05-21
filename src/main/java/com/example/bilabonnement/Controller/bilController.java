package com.example.bilabonnement.Controller;

import com.example.bilabonnement.Model.bilModel;
import com.example.bilabonnement.Service.bilService;
import com.example.bilabonnement.Repository.bilRepo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller // Spring registrerer denne klasse som en controller i web-laget (Spring MVC)
public class bilController {

    private final bilService bilService;
    private final bilRepo bilRepo;

    // Dependency injection via constructor
    public bilController(bilService bilService, bilRepo bilRepo) {
        this.bilService = bilService;
        this.bilRepo = bilRepo;
    }

    // ----- READ -----

    // Viser alle biler
    @GetMapping("/biler")
    public String visAlleBiler(Model model) {
        model.addAttribute("biler", bilService.hentAlleBiler());
        return "biler";
    }

    // Viser kun biler hvor tilgaengelig = true
    @GetMapping("/biler/ledige")
    public String visLedigeBiler(Model model) {
        model.addAttribute("biler", bilService.hentLedigeBiler());
        return "biler";
    }

    // Sorteret visning – ledige biler først
    @GetMapping("/biler/sorteret/ledighed")
    public String sorterEfterLedighed(Model model) {
        model.addAttribute("biler", bilService.hentBilerSorteretEfterLedighed());
        return "biler";
    }

    // Sorteret visning – efter stålpris (lav til høj)
    @GetMapping("/biler/sorteret/pris")
    public String sorterEfterPris(Model model) {
        model.addAttribute("biler", bilService.hentBilerSorteretEfterPris());
        return "biler";
    }

    // Sorteret visning – efter mærke (alfabetisk)
    @GetMapping("/biler/sorteret/maerke")
    public String sorterEfterMaerke(Model model) {
        model.addAttribute("biler", bilService.hentBilerSorteretEfterMaerke());
        return "biler";
    }

    // Sorteret visning – efter udstyrsniveau-navn (alfabetisk)
    @GetMapping("/biler/sorteret/udstyr")
    public String sorterEfterUdstyrsniveau(Model model) {
        model.addAttribute("biler", bilService.hentBilerSorteretEfterUdstyrsniveauNavn());
        return "biler";
    }

    // ----- CREATE -----

    // Viser formular til at oprette en ny bil
    @GetMapping("/bil/opret")
    public String visOpretForm(Model model) {
        model.addAttribute("bil", new bilModel()); // Tomt bil-objekt til formularen
        model.addAttribute("udstyrsniveauer", bilRepo.hentAlleUdstyrsniveauer()); // Dropdown-data
        return "opret-bil";
    }

    // Modtager og gemmer ny bil fra formularen
    @PostMapping("/bil/opret")
    public String opretBil(@ModelAttribute bilModel bil) {
        bilRepo.opretBil(bil);
        return "redirect:/biler";
    }

    // ----- UPDATE -----

    // Viser formular med eksisterende bil-data
    @GetMapping("/bil/rediger/{id}")
    public String visRedigerForm(@PathVariable("id") int id, Model model) {
        bilModel bil = bilRepo.findById(id);
        model.addAttribute("bil", bil);
        model.addAttribute("udstyrsniveauer", bilRepo.hentAlleUdstyrsniveauer()); // Dropdown igen
        return "rediger-bil";
    }

    // Gemmer ændringer på en bil
    @PostMapping("/bil/rediger")
    public String redigerBil(@ModelAttribute bilModel bil) {
        bilRepo.opdaterBil(bil);
        return "redirect:/biler";
    }

    // ----- DELETE -----

    // Sletter en bil via dens ID
    @GetMapping("/bil/slet/{id}")
    public String sletBil(@PathVariable("id") int id) {
        bilRepo.sletBil(id);
        return "redirect:/biler";
    }
}
