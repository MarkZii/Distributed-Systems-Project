package ziccardi.backendsistemidistribuitiprogetto.entity;

import com.amazonaws.services.dynamodbv2.datamodeling.*;

import java.util.List;

@DynamoDBDocument
public class Domanda {
    @DynamoDBAttribute(attributeName = "domanda")
    private String domanda;
    @DynamoDBAttribute(attributeName = "risposte")
    private List<String> risposte;
    @DynamoDBAttribute(attributeName = "url_immagine")
    private String url_immagine;

    // Setter
    public void setDomanda(String domanda) {
        this.domanda = domanda;
    }
    public void setRisposte(List<String> risposte) { this.risposte = risposte; }
    public void setUrl_immagine(String url_immagine) {
        if(url_immagine == "NO"){
            this.url_immagine = " ";
        }else{
            this.url_immagine = url_immagine;
        }
    }

    // Getter
    public String getDomanda() { return domanda; }
    public List<String> getRisposte() { return risposte; }
    public String getUrl_immagine() { return url_immagine; }

}