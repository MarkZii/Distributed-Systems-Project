package ziccardi.backendsistemidistribuitiprogetto.service;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBSaveExpression;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.amazonaws.services.dynamodbv2.model.ExpectedAttributeValue;
import ziccardi.backendsistemidistribuitiprogetto.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import java.util.ArrayList;
import java.util.List;

@Repository
public class QuestionarioService {

    @Autowired
    private DynamoDBMapper dynamoDBMapper;

    @Autowired
    private RisultatiService risultatiService;

    public Questionario salva(Questionario questionario){
        Risultati risultati = new Risultati();
        risultati.setId_risultato(questionario.getId_questionario());
        risultati.setNome_autore(questionario.getNome_autore());
        risultati.setData_ora(questionario.getData_ora());
        risultati.setTitolo(questionario.getTitolo());
        risultati.setDescrizione(questionario.getDescrizione());
        risultati.setDurata(questionario.getDurata());
        risultati.setNumeroRisposte(0);

        List<ResocontoDomande> resocontoDomandeLista = new ArrayList<>();
        for(Domanda domanda: questionario.getDomande()){
            ResocontoDomande resocontoDomande = new ResocontoDomande();
            resocontoDomande.setDomanda(domanda.getDomanda());
            resocontoDomande.setUrl_immagine(domanda.getUrl_immagine());
            List<NumeroRisposta> numeroRispostaLista = new ArrayList<>();
            for(String risposta: domanda.getRisposte()){
                NumeroRisposta numeroRisposta = new NumeroRisposta();
                numeroRisposta.setRisposta(risposta);
                numeroRisposta.setNumero(0);
                numeroRispostaLista.add(numeroRisposta);
            }
            resocontoDomande.setNumeroRisposta(numeroRispostaLista);
            resocontoDomandeLista.add(resocontoDomande);
        }
        risultati.setResocontoDomande(resocontoDomandeLista);
        risultatiService.salva(risultati);
        dynamoDBMapper.save(questionario);

        return questionario;
    }

    public Questionario cercaTramiteID(String id){
        return dynamoDBMapper.load(Questionario.class, id);
    }

    public List<Questionario> cercaTutti(){
        return dynamoDBMapper.scan(Questionario.class, new DynamoDBScanExpression());
    }

    public String update(String id, Questionario questionario){
        dynamoDBMapper.save(questionario,
                new DynamoDBSaveExpression()
                        .withExpectedEntry("id",
                                new ExpectedAttributeValue(
                                        new AttributeValue().withS(id)
                                )));
        return id;
    }

    public String elimina(String id){
        Questionario questionario = dynamoDBMapper.load(Questionario.class, id);
        dynamoDBMapper.delete(questionario);
        return "Person deleted successfully:: "+id;
    }
}