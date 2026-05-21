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
public class RisultatiService {

    @Autowired
    private DynamoDBMapper dynamoDBMapper;


    public Risultati salva(Risultati risultati){
        dynamoDBMapper.save(risultati);
        return risultati;
    }

    public Risultati cercaById(String id){
        return dynamoDBMapper.load(Risultati.class, id);
    }

    public List<Risultati> cercaTutti(){
        return dynamoDBMapper.scan(Risultati.class, new DynamoDBScanExpression());
    }

}