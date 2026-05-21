package ziccardi.backendsistemidistribuitiprogetto.entity;

import com.amazonaws.services.dynamodbv2.datamodeling.*;

import lombok.ToString;
import java.util.List;

@ToString
@DynamoDBTable(tableName = "compilazioni_niq")
public class Compilazione {

    @DynamoDBHashKey(attributeName = "nome_utente")
    private String nome_utente;

    @DynamoDBRangeKey(attributeName = "id_questionario")
    private String id_questionario;

    @DynamoDBAttribute(attributeName = "data_ora")
    private String data_ora;

    @DynamoDBAttribute(attributeName = "risposte")
    private List<Risposta> risposte;


    //Setter
    public void setId_questionario(String id_questionario) { this.id_questionario = id_questionario; }
    public void setRisposte(List<Risposta> risposte) { this.risposte = risposte; }
    public void setNome_utente(String nome_utente) { this.nome_utente = nome_utente; }
    public void setData_ora(String data_ora) { this.data_ora = data_ora; }


    //Getter
    public String getId_questionario() { return id_questionario; }
    public List<Risposta> getRisposte() { return risposte; }
    public String getNome_utente() { return nome_utente; }
    public String getData_ora() { return data_ora;}
}