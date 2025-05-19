package com.example.bilabonnement.Controller;

import com.example.bilabonnement.Model.lejekontraktModel;
import com.example.bilabonnement.Service.lejekontraktService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.List;

@RestController
@RequestMapping("/lejekontrakter")
public class lejekontraktController {

    private final lejekontraktService lkService;

    public lejekontraktController(lejekontraktService lkService) {
        this.lkService = lkService;
    }

    /* --------------- GET Lejekontrakter --------------- */
    @GetMapping
    public List<lejekontraktModel> hentAlle(){
        return lkService.findAll();
    }

    /* --------------- GET Lejekontrakt fra ID --------------- */
    @GetMapping("/{id}")
    public ResponseEntity<lejekontraktModel> hentEn(@PathVariable int id){
        lejekontraktModel kontrakt = lkService.findById(id);
        if (kontrakt != null) {
            return ResponseEntity.ok(kontrakt);
        }
        else {
            return ResponseEntity.notFound().build();
        }
    }

    /* --------------- POST lejekontrakt --------------- */
    @PostMapping
    public ResponseEntity<String> opret(@RequestBody lejekontraktModel kontrakt){
        lkService.save(kontrakt);
        return ResponseEntity.status(HttpStatus.CREATED).body("Lejekontrakt oprettet ");
    }

    /* --------------- PUT Lejekontrakt ud fra id --------------- */
    @PutMapping
    public ResponseEntity<String> opdater(@PathVariable int id, @RequestBody lejekontraktModel kontrakt){
        kontrakt.setKontraktID(id);
        lkService.update(kontrakt);
        return ResponseEntity.ok("Lejekontrakt opdatertet ");
    }

    /* --------------- DELETE Lejekontrakt ud fra id --------------- */
    @DeleteMapping("/{id}")
    public ResponseEntity<String> slet(@PathVariable int id){
        lkService.deleteById(id);
        return ResponseEntity.ok("Lejekontrakt slettet ");
    }

    @PostMapping(value="/opret", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public String opretFraHtml(@ModelAttribute lejekontraktModel kontrakt) {
        lkService.save(kontrakt);
        return "redirect:/lejekontrakter.html";
    }

    @PostMapping("/slet")
    public String sletFraHtml(@RequestParam int id) {
        lkService.deleteById(id);
        return "redirect:/lejekontrakter.html";
    }

}
