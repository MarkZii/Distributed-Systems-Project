package ziccardi.backendsistemidistribuitiprogetto.controller;

import ziccardi.backendsistemidistribuitiprogetto.entity.Questionario;
import ziccardi.backendsistemidistribuitiprogetto.service.QuestionarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/questionari")
public class QuestionarioController {
    @Autowired
    QuestionarioService questionarioService;
    DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm");

    @PostMapping("/salva")
    public ResponseEntity<Questionario> saveQuestionario(@RequestBody Questionario questionario) {
        questionario.setData_ora(df.format(new Date()).toString());
        return ResponseEntity.ok(questionarioService.salva(questionario));
    }

    @GetMapping("/recupera")
    public ResponseEntity<Questionario> getQuestionarioById(@RequestParam(value = "id") String id) {
        return ResponseEntity.ok(questionarioService.cercaTramiteID(id));
    }

    @GetMapping("/tutti")
    public ResponseEntity<List<Questionario>> getTuttiQuestionari(){
        return ResponseEntity.ok(questionarioService.cercaTutti());
    }

    @DeleteMapping("/elimina")
    public void deleteProductById(@RequestParam(value = "id") String id) {
        questionarioService.elimina(id);
    }

}