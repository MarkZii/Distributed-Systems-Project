package ziccardi.backendsistemidistribuitiprogetto.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.services.ses.SesClient;
import software.amazon.awssdk.services.ses.model.*;

@Service
public class EmailService {

    @Autowired
    private SesClient sesClient;

    private String fromEmail = "mziccardi.2028@outlook.it";

    public void singoloInvio(String to, String subject, String bodyText) {
        try {
            Destination destination = Destination.builder()
                    .toAddresses(to)
                    .build();

            Content subjectContent = Content.builder()
                    .data(subject)
                    .build();

            Content bodyContent = Content.builder()
                    .data(bodyText)
                    .build();

            Body body = Body.builder()
                    .text(bodyContent)
                    .build();

            Message message = Message.builder()
                    .subject(subjectContent)
                    .body(body)
                    .build();

            SendEmailRequest emailRequest = SendEmailRequest.builder()
                    .destination(destination)
                    .message(message)
                    .source(fromEmail) // Inserisci l'indirizzo email verificato con SES
                    .build();

            sesClient.sendEmail(emailRequest);
        } catch (SesException e) {
            e.printStackTrace();
            throw new RuntimeException("Errore durante l'invio dell'email: " + e.awsErrorDetails().errorMessage());
        }
    }

    public void inviiMultipli(String [] to, String subject, String bodyText) {
        try {
            for (String email: to){
                System.out.println(email);
                Destination destination = Destination.builder()
                        .toAddresses(email)
                        .build();

                Content subjectContent = Content.builder()
                        .data(subject)
                        .build();

                Content bodyContent = Content.builder()
                        .data(bodyText)
                        .build();

                Body body = Body.builder()
                        .text(bodyContent)
                        .build();

                Message message = Message.builder()
                        .subject(subjectContent)
                        .body(body)
                        .build();

                SendEmailRequest emailRequest = SendEmailRequest.builder()
                        .destination(destination)
                        .message(message)
                        .source(fromEmail) // Inserisci l'indirizzo email verificato con SES
                        .build();

                sesClient.sendEmail(emailRequest);
                Thread.sleep(2000);
            }

        } catch (SesException e) {
            e.printStackTrace();
            throw new RuntimeException("Errore durante l'invio dell'email: " + e.awsErrorDetails().errorMessage());
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}