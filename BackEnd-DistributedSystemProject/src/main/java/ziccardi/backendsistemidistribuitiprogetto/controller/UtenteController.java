package ziccardi.backendsistemidistribuitiprogetto.controller;

import com.amazonaws.services.cognitoidp.model.UserType;
import com.fasterxml.jackson.databind.node.ObjectNode;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import ziccardi.backendsistemidistribuitiprogetto.service.CognitoService;
import ziccardi.backendsistemidistribuitiprogetto.entity.LoginRequest;
import org.springframework.web.bind.annotation.RequestBody;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/utente")
public class UtenteController {

    @Autowired
    private CognitoService userService;

    @PreAuthorize("hasRole('ROLE_USER')")
    @GetMapping("/informazioni")
    public Map<String, Object> user(@AuthenticationPrincipal OAuth2User principal) {
        return Collections.singletonMap("Nome utente", principal.getAttribute("nome"));
    }

    @PostMapping(value = "/login", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ObjectNode> login(@Valid @RequestBody LoginRequest loginRequest) {
        ObjectNode loggedInUser = userService.login(loginRequest);

        if (loggedInUser != null) {
            // login ok
            return ResponseEntity.ok(loggedInUser);
        } else {
            // login no
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

    @PostMapping(value = "/registrazione", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public ResponseEntity<String> registrazione(@RequestParam(value = "username") String username, @RequestParam(value = "email") String email, @RequestParam(value = "password") String password) {
        if (userService.registraUtente(username, email, password)) {
            return ResponseEntity.ok("Registrazione avvenuta con successo");
        } else {
            return ResponseEntity.badRequest().body("Qualcosa è andato male... Username già usato");
        }
    }

    @GetMapping("/utenti")
    public List<UserType> getUsers() {
        List<UserType> listaUtenti = userService.getTuttiUtenti();
        return listaUtenti;
    }

}