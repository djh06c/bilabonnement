package com.example.bilabonnement.Controller;

import com.example.bilabonnement.Model.bilModel;
import com.example.bilabonnement.Service.bilService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
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

        List<bilModel> biler = (filter == null || filter.isEmpty())
                ? bilService.hentAlleBiler()
                : bilService.hentBilerSorteretEfter(filter);

        List<bilModel> senesteBiler = bilService.hentSenesteBiler(3);

        model.addAttribute("biler", biler);
        model.addAttribute("senesteBiler", senesteBiler);
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
    public String opretBil(@Valid @ModelAttribute("bil") bilModel bil,
                           BindingResult bindingResult,
                           Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("bil", bil);
            model.addAttribute("udstyrsniveauer", bilService.hentAlleUdstyrsniveauer());
            return "opret-bil";
        }

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
    public String redigerBil(@Valid @ModelAttribute bilModel bil,
                             BindingResult bindingResult,
                             Model model) {
        System.out.println("HVIS DU SER MIG SÅ ER VI INDE I METODEN!!");
        if (bindingResult.hasErrors()) {
            model.addAttribute("bil", bil);
            model.addAttribute("udstyrsniveauer", bilService.hentAlleUdstyrsniveauer());

            return "rediger-bil";
        }
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
