package com.example.bilabonnement.Controller;

import com.example.bilabonnement.Model.bilModel;
import com.example.bilabonnement.Repository.bilRepo;
import com.example.bilabonnement.Service.bilService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class bilController {

    private final bilService bilService;
    private final bilRepo bilRepo;

    public bilController(bilService bilService, bilRepo bilRepo) {
        this.bilService = bilService;
        this.bilRepo = bilRepo;
    }

    // ----- READ -----

    @GetMapping("/biler")
    public String visAlleBiler(Model model) {
        model.addAttribute("biler", bilService.hentAlleBiler());
        model.addAttribute("filter", null);
        return "biler";
    }

    @GetMapping("/biler/ledige")
    public String visLedigeBiler(Model model) {
        model.addAttribute("biler", bilService.hentLedigeBiler());
        model.addAttribute("filter", "ledige");
        return "biler";
    }

    @GetMapping("/biler/sorteret/ledighed")
    public String sorterEfterLedighed(Model model) {
        model.addAttribute("biler", bilService.hentBilerSorteretEfterLedighed());
        model.addAttribute("filter", "ledige");
        return "biler";
    }

    @GetMapping("/biler/sorteret/pris")
    public String sorterEfterPris(Model model) {
        model.addAttribute("biler", bilService.hentBilerSorteretEfterPris());
        model.addAttribute("filter", "pris");
        return "biler";
    }

    @GetMapping("/biler/sorteret/maerke")
    public String sorterEfterMaerke(Model model) {
        model.addAttribute("biler", bilService.hentBilerSorteretEfterMaerke());
        model.addAttribute("filter", "maerke");
        return "biler";
    }

    @GetMapping("/biler/sorteret/udstyr")
    public String sorterEfterUdstyrsniveau(Model model) {
        model.addAttribute("biler", bilService.hentBilerSorteretEfterUdstyrsniveauNavn());
        model.addAttribute("filter", "udstyr");
        return "biler";
    }

    @GetMapping("/biler/sorteret/id")
    public String sorterEfterId(Model model) {
        model.addAttribute("biler", bilService.hentBilerSorteretEfterId());
        model.addAttribute("filter", "id");
        return "biler";
    }

    @GetMapping("/biler/sorteret/model")
    public String sorterEfterModel(Model model) {
        model.addAttribute("biler", bilService.hentBilerSorteretEfterModel());
        model.addAttribute("filter", "model");
        return "biler";
    }

    @GetMapping("/biler/sorteret/regnr")
    public String sorterEfterRegnr(Model model) {
        model.addAttribute("biler", bilService.hentBilerSorteretEfterRegnr());
        model.addAttribute("filter", "regnr");
        return "biler";
    }

    @GetMapping("/biler/sorteret/stelnummer")
    public String sorterEfterStelnummer(Model model) {
        model.addAttribute("biler", bilService.hentBilerSorteretEfterStelnummer());
        model.addAttribute("filter", "stelnummer");
        return "biler";
    }

    @GetMapping("/biler/sorteret/vognnummer")
    public String sorterEfterVognnummer(Model model) {
        model.addAttribute("biler", bilService.hentBilerSorteretEfterVognnummer());
        model.addAttribute("filter", "vognnummer");
        return "biler";
    }

    @GetMapping("/biler/sorteret/co2")
    public String sorterEfterCO2(Model model) {
        model.addAttribute("biler", bilService.hentBilerSorteretEfterCO2());
        model.addAttribute("filter", "co2");
        return "biler";
    }


    // ----- CREATE -----

    @GetMapping("/bil/opret")
    public String visOpretForm(Model model) {
        model.addAttribute("bil", new bilModel());
        model.addAttribute("udstyrsniveauer", bilRepo.hentAlleUdstyrsniveauer());
        model.addAttribute("filter", "opret");
        return "opret-bil";
    }

    @PostMapping("/bil/opret")
    public String opretBil(@ModelAttribute bilModel bil) {
        bilRepo.opretBil(bil);
        return "redirect:/biler";
    }

    // ----- UPDATE -----

    @GetMapping("/bil/rediger/{id}")
    public String visRedigerForm(@PathVariable("id") int id, Model model) {
        bilModel bil = bilRepo.findById(id);
        model.addAttribute("bil", bil);
        model.addAttribute("udstyrsniveauer", bilRepo.hentAlleUdstyrsniveauer());
        model.addAttribute("filter", "rediger");
        return "rediger-bil";
    }

    @PostMapping("/bil/rediger")
    public String redigerBil(@ModelAttribute bilModel bil) {
        bilRepo.opdaterBil(bil);
        return "redirect:/biler";
    }

    // ----- DELETE -----

    @GetMapping("/bil/slet/{id}")
    public String sletBil(@PathVariable("id") int id) {
        bilRepo.sletBil(id);
        return "redirect:/biler";
    }
}
