package com.example.bilabonnement.Controller;

import com.example.bilabonnement.Model.bilModel;
import com.example.bilabonnement.Service.bilService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.List;



@Controller
public class bilController {

    private final bilService bilService;

    public bilController(bilService bilService) {
        this.bilService = bilService;
    }

    // ----- READ -----

    @GetMapping("/biler")
    public String visAlleBiler(
            @RequestParam(name = "filter", required = false) String filter,
            Model model) {

        if (filter == null || filter.isEmpty()) {
            model.addAttribute("biler", bilService.hentAlleBiler());
        } else {
            model.addAttribute("biler", bilService.hentBilerSorteretEfter(filter));
        }

        List<bilModel> senesteBiler = bilService.hentSenesteBiler(3);
        model.addAttribute("senesteBiler", senesteBiler);
        model.addAttribute("filter", filter);

        // 🔍 Debug: Udskriv alle biler
        System.out.println("=== Alle biler ===");
        for (bilModel b : bilService.hentAlleBiler()) {
            System.out.println("ID: " + b.getBilId() + ", Model: " + b.getModel() + ", RegNr: " + b.getRegNr());
        }

        // 🔍 Debug: Udskriv seneste biler
        System.out.println("=== Seneste biler (top 3) ===");
        for (bilModel b : senesteBiler) {
            System.out.println("ID: " + b.getBilId() + ", Model: " + b.getModel() + ", RegNr: " + b.getRegNr());
        }

        return "biler";
    }


    // ----- CREATE -----

    @GetMapping("/bil/opret")
    public String visOpretForm(Model model) {
        model.addAttribute("bil", new bilModel());
        model.addAttribute("udstyrsniveauer", bilService.hentAlleUdstyrsniveauer());
        model.addAttribute("filter", "opret");
        return "opret-bil";
    }

    @PostMapping("/bil/opret")
    public String opretBil(@ModelAttribute bilModel bil) {
        bilService.opretBil(bil);
        return "redirect:/biler";
    }

    // ----- UPDATE -----

    @GetMapping("/bil/rediger/{id}")
    public String visRedigerForm(@PathVariable("id") int id, Model model) {
        bilModel bil = bilService.findBilById(id);
        model.addAttribute("bil", bil);
        model.addAttribute("udstyrsniveauer", bilService.hentAlleUdstyrsniveauer());
        model.addAttribute("filter", "rediger");
        return "rediger-bil";
    }

    @PostMapping("/bil/rediger")
    public String redigerBil(@ModelAttribute bilModel bil) {
        bilService.opdaterBil(bil);
        return "redirect:/biler";
    }

    // ----- DELETE -----

    @GetMapping("/bil/slet/{id}")
    public String sletBil(@PathVariable("id") int id) {
        bilService.sletBil(id);
        return "redirect:/biler";
    }
}
