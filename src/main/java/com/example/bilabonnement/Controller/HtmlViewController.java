package com.example.bilabonnement.Controller;

import com.example.bilabonnement.Model.lejekontraktModel;
import com.example.bilabonnement.Service.lejekontraktService;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class HtmlViewController {

    private final lejekontraktService service;

    public HtmlViewController(lejekontraktService service) {
        this.service = service;
    }

    @GetMapping("/lejekontraktvisning")
    public String visSide(Model model) {
        List<lejekontraktModel> kontrakter = service.findAll();
        model.addAttribute("lejekontrakter", kontrakter);
        model.addAttribute("lejekontraktModel", new lejekontraktModel());
        return "lejekontrakter";
    }

    @PostMapping("/lejekontrakter/opret")
    public String opretFraHtml(@ModelAttribute lejekontraktModel kontrakt, RedirectAttributes ra) {
        try{
            service.save(kontrakt);
            ra.addFlashAttribute("success", "lejekontrakt opret");
        } catch (DataIntegrityViolationException e) {
            ra.addFlashAttribute("error", "Fejl: Kunde eller Bil findes ikke i database");
        }
        return "lejekontrakter";
    }
}
