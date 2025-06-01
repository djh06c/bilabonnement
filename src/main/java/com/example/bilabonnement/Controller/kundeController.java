package com.example.bilabonnement.Controller;

import com.example.bilabonnement.Model.kundeModel;
import com.example.bilabonnement.Service.kundeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class kundeController {

    // Her oprettes en attribut/field af typen kundeService, som vi bruger til at kalde metoder, der håndterer forretningslogikken for kunder
    private final kundeService kundeService;

    //Dette er en constructor injection, så vi kan bruge den i hele controller-klassen
    public kundeController(kundeService kundeService) {
        this.kundeService = kundeService;
    }

    // Her er metoder med annotations med routing/mapping. Denne metode håndterer GET-anmodninger til /kunder, altså at den returnerer vores HTML side kunder.html, når brugeren vil se alle kunder
    // Model-objektet bruges til at sende data (listen af kunder) videre til Thymeleaf-skabelonen
    // Vi kalder kundeService.hentAlleKunder() for at hente alle kunder fra databasen
    @GetMapping("/kunder")
    public String visAlleKunder(Model model) {
        model.addAttribute("kunder", kundeService.hentAlleKunder());
        return "kunder";
    }

    // Denne metode viser formularen til oprettelse af en ny kunde
    // Vi opretter et tomt kundeModel-objekt, som sendes til Thymeleaf-formularen
    // Når brugeren udfylder formularen, udfyldes Model-objekterne med data
    @GetMapping("/kunder/opret")
    public String visOpretForm(Model model) {
        model.addAttribute("kundeModel", new kundeModel());
        return "opret-kunde";
    }

    // Denne metode håndterer POST-anmodninger i vores formular, altså modtager data, når formularen er udfyldt og brugeren klikker "Opret kunde"
    // @ModelAttribute gør, at formularens data automatisk sættes ind i kundeModel-objekterne
    // Der forsøges at oprette en kunde via kundeService.opretKunde()
    // Hvis det lykkes, sendes brugeren videre til kundelisten og redirecter til /kunder, som er forsiden for kunder, hvor man kan se listen af kunder
    // I vores kunde DDL er mail unique, dvs. kun én kunde kan tilhøre en bestemt mail. Hvis den bliver genbrugt, vil den smide en SQLException og vi vil få Whitelabel Error
    // Derfor bruger vi try and catch for at kaste en fejlmeddelelse og den vil sige, at mailen allerede findes
    @PostMapping("/kunder/opret")
    public String opretKunde(@ModelAttribute kundeModel kunde, Model model) {
        try {
            kundeService.opretKunde(kunde);
            return "redirect:/kunder";
        } catch (Exception e) {
            model.addAttribute("errorMessage", "Mailen '" + kunde.getEmail() + "' er allerede i brug. Vælg en anden.");
            model.addAttribute("kundeModel", kunde);
            return "opret-kunde";
        }
    }

    // Denne metode viser formularen til at redigere en eksisterende kunde
    // @PathVariable gør, at vi kan få fat i kundeID på den kunde, brugeren vil redigere (fx. /kunder/rediger/1 giver kundeID = 1
    // Vi henter kunden med dette ID fra databasen via kundeService.findById()
    // Så sender vi kunden videre til formularen, så alle felterne bliver udfyldt med de eksisterende oplysninger
    @GetMapping("/kunder/rediger/{id}")
    public String visRedigerForm(@PathVariable int id, Model model) {
        kundeModel kunde = kundeService.findById(id);
        model.addAttribute("kundeModel", kunde);
        return "rediger-kunde";
    }

    // Denne metode håndterer det, når brugeren klikker "Gem ændringer" i redigeringsformularen
    // Vi bruger @ModelAttribute igen til automatisk at fylde kundeModel-objekterne med data fra formularen
    // Systemet vil derefter opdatere kunden med de nye oplysninger, med mindre en eksisterende mail er blevet indtastet (samme koncept som i opretKunde)
    @PostMapping("/kunder/rediger")
    public String opdaterKunde(@ModelAttribute kundeModel kunde, Model model) {
        try {
            kundeService.opdaterKunde(kunde);
            return "redirect:/kunder";
        } catch (Exception e) {
            model.addAttribute("errorMessage", "Mailen '" + kunde.getEmail() + "' er allerede i brug. Vælg en anden.");
            model.addAttribute("kundeModel", kunde);
            return "rediger-kunde";
        }
    }

    // Denne metode bruges til at slette en kunde
    // Igen bruger vi @PathVariable som gør, at vi kan hente kundens ID direkte fra URL'en
    // Vi kalder kundeService.sletKunde() for at slette kunden fra databasen
    // Når det er gjort, sender vi brugeren tilbage til kundelisten
    @PostMapping("/kunder/slet/{id}")
    public String sletKunde(@PathVariable int id) {
        kundeService.sletKunde(id);
        return "redirect:/kunder";
    }
}