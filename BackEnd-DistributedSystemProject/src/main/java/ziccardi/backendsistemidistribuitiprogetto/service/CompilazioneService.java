package ziccardi.backendsistemidistribuitiprogetto.service;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBSaveExpression;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.amazonaws.services.dynamodbv2.model.ExpectedAttributeValue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ziccardi.backendsistemidistribuitiprogetto.entity.*;

import java.util.ArrayList;
import java.util.List;

@Repository
public class CompilazioneService {

    @Autowired
    private DynamoDBMapper dynamoDBMapper;

    @Autowired
    private QuestionarioService questionarioService;

    @Autowired
    private RisultatiService risultatiService;

    public Compilazione salva(Compilazione compilazione){
        Risultati risultati = risultatiService.cercaById(compilazione.getId_questionario());
        System.out.println(risultati);
        risultati.setNumeroRisposte(risultati.getNumeroRisposte()+1);
        for(Risposta risposta: compilazione.getRisposte()){
            for(ResocontoDomande resocontoDomande: risultati.getResocontoDomande()){
                if(risposta.getDomanda().equals(resocontoDomande.getDomanda())){
                    for(NumeroRisposta numeroRisposta: resocontoDomande.getNumeroRisposta()){
                        for(String scelta: risposta.getScelte()){
                            if(numeroRisposta.getRisposta().equals(scelta)){
                                numeroRisposta.setNumero(numeroRisposta.getNumero()+1);
                            }
                        }

                    }
                }
            }
        }
        risultatiService.salva(risultati);
        dynamoDBMapper.save(compilazione);
        return compilazione;
    }

    public Compilazione cercaByID(String id){
        return dynamoDBMapper.load(Compilazione.class, id);
    }

    public List<Compilazione> cercaTutti(){
        return dynamoDBMapper.scan(Compilazione.class, new DynamoDBScanExpression());
    }

    public String update(String id, Compilazione compilazione){
        dynamoDBMapper.save(compilazione,
                new DynamoDBSaveExpression()
                        .withExpectedEntry("id",
                                new ExpectedAttributeValue(
                                        new AttributeValue().withS(id)
                                )));
        return id;
    }

    public List<Questionario> getQuestionariDisponibili(String userId) {
        List<Questionario> questionariDisponibili = new ArrayList<>();

        //Mi prendo tutti i questionari già compilari e da compilare
        List<Questionario> tuttiIQuestionari = questionarioService.cercaTutti();

        //Mi prendo tutte le compilazioni
        List<Compilazione> tutteLeCompilazioni = cercaTutti();

        for (Questionario questionario : tuttiIQuestionari) {
            if (!esisteCompilazione(tutteLeCompilazioni, userId, questionario.getId_questionario())) {
                System.out.println("Non esiste: "+questionario);
                questionariDisponibili.add(questionario);
            }
        }
        System.out.println(questionariDisponibili);
        return questionariDisponibili;
    }

    private boolean esisteCompilazione(List<Compilazione> tutteLeCompilazioni, String userId, String idQuestionario) {
        boolean trovato = false;
        for (Compilazione compilazione: tutteLeCompilazioni){
            if(compilazione.getId_questionario().equals(idQuestionario) && compilazione.getNome_utente().equals(userId)){
                trovato = true;
            }
        }
        return trovato;
    }

    public String elimina(String id){
        Compilazione compilazione = dynamoDBMapper.load(Compilazione.class, id);
        dynamoDBMapper.delete(compilazione);
        return "Compilazione eliminata con successo: "+id;
    }
}