/*
package com.example.bilabonnement.Config;

import com.example.bilabonnement.Service.bilService;
import com.example.bilabonnement.Service.lejekontraktService;
import com.example.bilabonnement.Model.bilModel;
import com.example.bilabonnement.Model.lejekontraktModel;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.ui.Model;

import java.util.List;


@Component
@ControllerAdvice
public class globalModelAttributes {

    private final bilService bilService;
    private final lejekontraktService lejekontraktService;

    public globalModelAttributes(bilService bilService, lejekontraktService lejekontraktService) {
        this.bilService = bilService;
        this.lejekontraktService = lejekontraktService;
    }

    @ModelAttribute
    public void addGlobalAttributes(Model model) {
        List<bilModel> biler = bilService.hentAlleBiler();
        int ledige = (int) biler.stream().filter(bilModel::isTilgaengelig).count();

        List<lejekontraktModel> aktiveKontrakter = lejekontraktService.findAktiveKontrakter();
        double samletPris = aktiveKontrakter.stream()
                .mapToDouble(lejekontraktModel::getPris)
                .sum();

        model.addAttribute("ledigeBiler", ledige);
        model.addAttribute("totalBiler", biler.size());
        model.addAttribute("samletPris", samletPris);
    }
}

 */
