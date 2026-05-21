package ziccardi.backendsistemidistribuitiprogetto.entity;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;
import lombok.ToString;
import java.util.List;

@ToString
@DynamoDBTable(tableName = "risultati")
public class Risultati {

    @DynamoDBHashKey(attributeName = "id_risultato")
    private String id_risultato;

    @DynamoDBAttribute(attributeName = "nome_autore")
    private String nome_autore;

    @DynamoDBAttribute(attributeName = "data_ora")
    private String data_ora;

    @DynamoDBAttribute(attributeName = "titolo")
    private String titolo;

    @DynamoDBAttribute(attributeName = "descrizione")
    private String descrizione;

    @DynamoDBAttribute(attributeName = "durata")
    private int durata;

    @DynamoDBAttribute(attributeName = "numeroRisposte")
    private int numeroRisposte;

    @DynamoDBAttribute(attributeName = "resocontoDomande")
    private List<ResocontoDomande> resocontoDomande;

    public String getId_risultato() {
        return id_risultato;
    }

    public void setId_risultato(String id_risultato) {
        this.id_risultato = id_risultato;
    }

    public String getNome_autore() {
        return nome_autore;
    }

    public void setNome_autore(String nome_autore) {
        this.nome_autore = nome_autore;
    }

    public String getData_ora() {
        return data_ora;
    }

    public void setData_ora(String data_ora) { this.data_ora = data_ora; }//df.format(new Date()).toString(); }

    public String getTitolo() {
        return titolo;
    }

    public void setTitolo(String titolo) { this.titolo = titolo; }

    public String getDescrizione() {
        return descrizione;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

    public int getNumeroRisposte() { return numeroRisposte; }

    public void setNumeroRisposte(int numeroRisposte) {
        this.numeroRisposte = numeroRisposte;
    }

    public List<ResocontoDomande> getResocontoDomande() {
        return resocontoDomande;
    }

    public void setResocontoDomande(List<ResocontoDomande> resocontoDomande) {
        this.resocontoDomande = resocontoDomande;
    }

    public int getDurata() {
        return durata;
    }

    public void setDurata(int durata) {
        this.durata = durata;
    }
}