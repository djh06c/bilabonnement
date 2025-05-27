package com.example.bilabonnement.Controller;

import com.example.bilabonnement.Model.lejekontraktModel;
import com.example.bilabonnement.Service.lejekontraktService;
import com.example.bilabonnement.Model.bilModel;
import com.example.bilabonnement.Service.bilService;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/lejekontrakter")
public class lejekontraktController {

    private final lejekontraktService service;
    private final bilService bilService;

    public lejekontraktController(lejekontraktService service, bilService bilservice) {
        this.service = service;
        this.bilService = bilservice;
    }

    // -------------------- HTML VIEW ENDPOINTS --------------------

    @GetMapping
    public String visOpretSide(Model model) {
        model.addAttribute("lejekontraktModel", new lejekontraktModel());
        return "lejekontrakter"; // opret-side
    }


    @PostMapping("/opret")
    public String opretFraHtml(@ModelAttribute lejekontraktModel kontrakt, Model model) {
        try {
            int maaneder = kontrakt.getMaaneder();

            // 1. Valider antal måneder
            if (maaneder < 4 || maaneder > 36) {
                model.addAttribute("error", "Lejeperioden skal være mellem 4 og 36 måneder!");
                model.addAttribute("lejekontraktModel", kontrakt);
                return "lejekontrakter";
            }

            // 2. Udregn slutdato
            kontrakt.setSlutDato(kontrakt.getStartDato().plusMonths(maaneder));

            // 3. Hent bilen og udregn pris
            bilModel bil = bilService.findBilById(kontrakt.getBilID());
            if (bil == null) {
                model.addAttribute("error", "Den valgte bil findes ikke.");
                model.addAttribute("lejekontraktModel", kontrakt);
                return "lejekontrakter";
            }
            double totalPris = bil.getMaanedspris() * maaneder;
            kontrakt.setPris(totalPris);
            // 4. Gem kontrakten
            service.save(kontrakt);
            model.addAttribute("success", "Lejekontrakt oprettet!");

        } catch (DataIntegrityViolationException e) {
            model.addAttribute("error", "Fejl: Kunde eller bil findes ikke");
        } catch (Exception e) {
            model.addAttribute("error", "Uventet fejl: " + e.getMessage());
        }

        model.addAttribute("lejekontraktModel", new lejekontraktModel());
        return "lejekontrakter";
    }



    @GetMapping("/vis")
    public String visAlleKontrakter(Model model) {
        List<lejekontraktModel> aktiveKontrakter = service.findAktiveKontrakter();
        model.addAttribute("lejekontrakter", aktiveKontrakter);

        double samletPris = aktiveKontrakter.stream()
                .mapToDouble(lejekontraktModel::getPris)
                .sum();
        model.addAttribute("samletPris", samletPris);

        return "visLejekontrakter";
    }


    @PostMapping("/slet")
    public String sletFraHtml(@RequestParam int id, Model model) {
        service.deleteById(id);
        model.addAttribute("lejekontrakter", service.findAll());
        return "visLejekontrakter";
    }


    @GetMapping("/rediger/{id}")
    public String visRedigerForm(@PathVariable("id") int id, Model model) {
        lejekontraktModel kontrakt = service.findById(id);
        model.addAttribute("lejekontraktModel", kontrakt);
        model.addAttribute("filter", "rediger");
        return "rediger-Lejekontrakter";
    }

    @PostMapping("/rediger")
    public String redigerKontrakt(@ModelAttribute lejekontraktModel kontrakt) {
        service.update(kontrakt);
        return "redirect:/lejekontrakter/vis";
    }

    @GetMapping(params = "id")
    public String findKontraktViaRequestParam(@RequestParam(required = false) String id, Model model) {
        if (id == null || id.isBlank()) {
            model.addAttribute("fejlbesked", "Indtast venligst et kontrakt ID før du søger.");
        } else {
            try {
                int parsedId = Integer.parseInt(id);
                lejekontraktModel kontrakt = service.findById(parsedId);
                if (kontrakt != null) {
                    model.addAttribute("fundetKontrakt", kontrakt);
                } else {
                    model.addAttribute("fejlbesked", "Ingen lejekontrakt fundet med ID: " + parsedId);
                }
            } catch (NumberFormatException e) {
                model.addAttribute("fejlbesked", "Ugyldigt ID. Indtast venligst et heltal.");
            }
        }

        model.addAttribute("lejekontrakter", service.findAll());
        return "visLejekontrakter";
    }

    @GetMapping("/arkiv")
    public String visUdløbneKontrakter(Model model) {
        model.addAttribute("lejekontrakter", service.findUdløbneKontrakter());
        model.addAttribute("titel", "Udløbne Lejekontrakter");
        return "arkiv-lejekontrakter";

    }


    // -------------------- REST API ENDPOINTS --------------------

    @ResponseBody
    @GetMapping("/api")
    public List<lejekontraktModel> hentAlle() {
        return service.findAll();
    }

    @ResponseBody
    @GetMapping("/api/{id}")
    public ResponseEntity<lejekontraktModel> hentEn(@PathVariable int id) {
        lejekontraktModel kontrakt = service.findById(id);
        if (kontrakt != null) {
            return ResponseEntity.ok(kontrakt);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @ResponseBody
    @PostMapping(value = "/api", consumes = "application/json")
    public ResponseEntity<String> opret(@RequestBody lejekontraktModel kontrakt) {
        service.save(kontrakt);
        return ResponseEntity.status(HttpStatus.CREATED).body("Lejekontrakt oprettet");
    }

    @ResponseBody
    @PutMapping("/api/{id}")
    public ResponseEntity<String> opdater(@PathVariable int id, @RequestBody lejekontraktModel kontrakt) {
        kontrakt.setKontraktID(id);
        service.update(kontrakt);
        return ResponseEntity.ok("Lejekontrakt opdateret");
    }

    @ResponseBody
    @DeleteMapping("/api/{id}")
    public ResponseEntity<String> slet(@PathVariable int id) {
        service.deleteById(id);
        return ResponseEntity.ok("Lejekontrakt slettet");
    }


}