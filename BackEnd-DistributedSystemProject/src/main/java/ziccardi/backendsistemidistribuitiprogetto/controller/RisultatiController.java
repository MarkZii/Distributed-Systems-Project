package ziccardi.backendsistemidistribuitiprogetto.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ziccardi.backendsistemidistribuitiprogetto.entity.Risultati;
import ziccardi.backendsistemidistribuitiprogetto.service.RisultatiService;
import java.util.List;

@RestController
@RequestMapping("/risultati")
public class RisultatiController {
    @Autowired
    RisultatiService risultatiService;

    @PostMapping("/salva")
    public ResponseEntity<Risultati> salvaRisultato(@RequestBody Risultati product) {
        System.out.println(product.toString());
        return ResponseEntity.ok(risultatiService.salva(product));
    }

    @GetMapping("/recupera")
    public ResponseEntity<Risultati> getProductById(@RequestParam(value = "id") String id) {
        return ResponseEntity.ok(risultatiService.cercaById(id));
    }

    @GetMapping("/tutti")
    public ResponseEntity<List<Risultati>> getTuttiRisultati() {
        return ResponseEntity.ok(risultatiService.cercaTutti());
    }

}