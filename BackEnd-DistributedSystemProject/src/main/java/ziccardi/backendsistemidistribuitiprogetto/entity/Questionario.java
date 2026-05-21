package ziccardi.backendsistemidistribuitiprogetto.entity;

import com.amazonaws.services.dynamodbv2.datamodeling.*;
import lombok.ToString;
import java.util.List;

@ToString
@DynamoDBTable(tableName = "questionari-noas")
public class Questionario {

    @DynamoDBHashKey(attributeName = "id_questionario")
    private String id_questionario;

    @DynamoDBRangeKey(attributeName = "nome_autore")
    private String nome_autore;

    @DynamoDBAttribute(attributeName = "data_ora")
    private String data_ora;

    @DynamoDBAttribute(attributeName = "titolo")
    private String titolo;

    @DynamoDBAttribute(attributeName = "descrizione")
    private String descrizione;

    @DynamoDBAttribute(attributeName = "durata")
    private int durata;

    @DynamoDBAttribute(attributeName = "domande")
    private List<Domanda> domande;

    //Setter
    public void setId_questionario(String id_questionario) { this.id_questionario = id_questionario;/*UUID.randomUUID().toString();*/ }
    public void setTitolo(String titolo) {
        this.titolo = titolo;
    }
    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }
    public void setDomande(List<Domanda> domande) { this.domande = domande; }
    public void setNome_autore(String nome_autore) {
        this.nome_autore = nome_autore;
    }
    public void setDurata(int durata) { this.durata = durata; }
    public void setData_ora(String data_ora) {
        this.data_ora = data_ora;
    }


    //Getter
    public String getId_questionario() {
        return id_questionario;
    }
    public String getTitolo() { return titolo; }
    public String getDescrizione() {
        return descrizione;
    }
    public List<Domanda> getDomande() {
        return domande;
    }
    public String getNome_autore() { return nome_autore; }
    public String getData_ora() {
        return data_ora;
    }
    public int getDurata() { return durata; }

    @Override
    public String toString() {
        return "Questionario{" +
                "id_questionario='" + id_questionario + '\'' +
                ", nome_autore='" + nome_autore + '\'' +
                ", data_ora='" + data_ora + '\'' +
                ", titolo='" + titolo + '\'' +
                ", descrizione='" + descrizione + '\'' +
                ", domande=" + domande +
                ", df=" + data_ora +
                '}';
    }
}