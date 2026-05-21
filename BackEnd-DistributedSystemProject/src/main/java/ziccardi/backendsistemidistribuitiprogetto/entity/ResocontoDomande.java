package ziccardi.backendsistemidistribuitiprogetto.entity;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBDocument;
import java.util.List;

@DynamoDBDocument
public class ResocontoDomande {
    private String domanda;
    private List<NumeroRisposta> numeroRisposta;
    private String url_immagine;

    public String getDomanda() {
        return domanda;
    }

    public void setDomanda(String domanda) {
        this.domanda = domanda;
    }

    public List<NumeroRisposta> getNumeroRisposta() {
        return numeroRisposta;
    }

    public void setNumeroRisposta(List<NumeroRisposta> numeroRisposta) {
        this.numeroRisposta = numeroRisposta;
    }

    public String getUrl_immagine() {
        return url_immagine;
    }

    public void setUrl_immagine(String url_immagine) {
        this.url_immagine = url_immagine;
    }
}
