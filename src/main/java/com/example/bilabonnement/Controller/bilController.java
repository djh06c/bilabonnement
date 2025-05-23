package com.example.bilabonnement.Controller;

import com.example.bilabonnement.Model.bilModel;
import com.example.bilabonnement.Service.bilService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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
        } else if (filter.equals("ledige")) {
            model.addAttribute("biler", bilService.hentLedigeBiler());
        } else {
            model.addAttribute("biler", bilService.hentBilerSorteretEfter(filter));
        }

        model.addAttribute("filter", filter);
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
