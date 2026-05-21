package ziccardi.backendsistemidistribuitiprogetto.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ziccardi.backendsistemidistribuitiprogetto.entity.Compilazione;
import ziccardi.backendsistemidistribuitiprogetto.entity.Questionario;
import ziccardi.backendsistemidistribuitiprogetto.service.CompilazioneService;

import java.text.DateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

@RestController
@RequestMapping("/compilazione")
public class CompilazioneController {

    @Autowired
    CompilazioneService compilazioneService;
    DateFormat df = DateFormat.getDateTimeInstance(DateFormat.FULL, DateFormat.SHORT, Locale.ITALY);

    @PostMapping("/salva")
    public ResponseEntity<Compilazione> salvaCompilazione(@RequestBody Compilazione compilazione) {
        System.out.println(compilazione.toString());
        compilazione.setData_ora(df.format(new Date()).toString());
        return ResponseEntity.ok(compilazioneService.salva(compilazione));
    }

    @GetMapping("/recupera")
    public ResponseEntity<Compilazione> getProductById(@RequestParam(value = "id") String id) {
        return ResponseEntity.ok(compilazioneService.cercaByID(id));
    }

    @GetMapping("/tutti")
    public ResponseEntity<List<Compilazione>> getProductList(){
        return ResponseEntity.ok(compilazioneService.cercaTutti());
    }

    @DeleteMapping("/elimina")
    public void deleteProductById(@RequestParam(value = "id") String id) {
        compilazioneService.elimina(id);
    }


    @GetMapping("/disponibili")
    public ResponseEntity<List<Questionario>> getQuestionariDisponibili(@RequestParam(value = "nome_utente") String nome_utente) {
        List<Questionario> questionariDisponibili = compilazioneService.getQuestionariDisponibili(nome_utente);
        return ResponseEntity.ok(questionariDisponibili);
    }

}
