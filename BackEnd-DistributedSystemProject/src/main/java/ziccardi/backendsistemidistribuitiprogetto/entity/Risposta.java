package ziccardi.backendsistemidistribuitiprogetto.entity;

import com.amazonaws.services.dynamodbv2.datamodeling.*;
import lombok.ToString;
import java.util.List;

@ToString
@DynamoDBDocument
public class Risposta {

    private String domanda;
    private List<String> scelte;

    //Setter
    public void setDomanda(String domanda) { this.domanda = domanda; }
    public void setScelte(List<String> scelte) { this.scelte = scelte; }

    // Getter
    public String getDomanda() { return domanda; }
    public List<String> getScelte() { return scelte; }

}

