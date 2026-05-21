package ziccardi.backendsistemidistribuitiprogetto.service;

import com.amazonaws.services.cognitoidp.AWSCognitoIdentityProvider;
import com.amazonaws.services.cognitoidp.model.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ziccardi.backendsistemidistribuitiprogetto.entity.LoginRequest;
import com.amazonaws.services.cognitoidp.model.UserType;
import java.util.List;
import java.util.Map;

@Service
public class CognitoService {

    @Autowired
    private AWSCognitoIdentityProvider cognitoIdentityProvider;

    private String clientId = "64hvqo93b8vjpvaggtg1cal4me";

    public ObjectNode login(LoginRequest loginRequest) {
        InitiateAuthRequest loginRichiesta = new InitiateAuthRequest()
                .withAuthFlow("USER_PASSWORD_AUTH")
                .withClientId(clientId)
                .withAuthParameters(
                        Map.of(
                                "USERNAME", loginRequest.getEmail(),   // Use email as the username
                                "PASSWORD", loginRequest.getPassword()
                        )
                );

        try {
            InitiateAuthResult authResult = cognitoIdentityProvider.initiateAuth(loginRichiesta);
            AuthenticationResultType authResponse = authResult.getAuthenticationResult();

            ObjectMapper objectMapper = new ObjectMapper();

            // Crea un nuovo oggetto JSON
            ObjectNode json = objectMapper.createObjectNode();

            // Aggiungi campi all'oggetto JSON
            json.put("AccessToken", authResponse.getAccessToken());
            json.put("RefreshToken", authResponse.getIdToken());
            json.put("IdToken", authResponse.getRefreshToken());

            return json;

        } catch (Exception e) {
            //throw new RuntimeException("User registration failed: " + e.getMessage(), e);
            return null;
        }
    }

    public boolean registraUtente(String username, String email, String password) {
        // Set up the AWS Cognito registration request
        SignUpRequest signUpRequest = new SignUpRequest()
                .withClientId(clientId)
                .withUsername(username)
                .withPassword(password)
                .withUserAttributes(
                        new AttributeType().withName("email").withValue(email)
                );

        try {
            SignUpResult signUpResponse = cognitoIdentityProvider.signUp(signUpRequest);
            return true;
        } catch (Exception e) {
            //throw new RuntimeException("User registration failed: " + e.getMessage(), e);
            return false;
        }
    }

    public List<UserType> getTuttiUtenti() {
        ListUsersRequest listaUtenti = new ListUsersRequest().withUserPoolId("eu-north-1_Xb7fdfkd4");
        ListUsersResult listaUtentiRisultati = cognitoIdentityProvider.listUsers(listaUtenti);
        return listaUtentiRisultati.getUsers();
    }
}