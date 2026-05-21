package ziccardi.backendsistemidistribuitiprogetto.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ziccardi.backendsistemidistribuitiprogetto.service.EmailService;

@RestController
@RequestMapping("/email")
public class EmailController {

    @Autowired
    private EmailService emailService;

    @PostMapping(value ="/invii", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public ResponseEntity<String> inviiEmail(@RequestParam(value = "toEmail") String toEmail, @RequestParam(value = "oggetto") String oggetto, @RequestParam(value = "corpo") String corpo) {
        try {
            String [] email = toEmail.split(",");
            emailService.inviiMultipli(email, oggetto, corpo);
            return ResponseEntity.ok("Email inviata con successo");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body("Email non inviata");
        }
    }
}
