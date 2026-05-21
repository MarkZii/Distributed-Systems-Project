package ziccardi.backendsistemidistribuitiprogetto.config;

import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.cognitoidp.AWSCognitoIdentityProvider;
import com.amazonaws.services.cognitoidp.AWSCognitoIdentityProviderClientBuilder;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import org.socialsignin.spring.data.dynamodb.repository.config.EnableDynamoDBRepositories;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.ses.SesClient;

@Configuration
@EnableDynamoDBRepositories(basePackages = "ziccardi.backendsistemidistribuitiprogetto.repository")
public class AWSConfig {
    @Value("${amazon.dynamodb.endpoint}")
    String endpoint;

    @Value("${amazon.aws.accesskey}")
    String accesskey;

    @Value("${spring.security.oauth2.client.registration.cognito.client-id}")
    String accesskey_cognito;

    @Value("${amazon.aws.secretkey}")
    String secretSecret;

    @Value("${amazon.aws.region}")
    String region;

    @Bean
    public DynamoDBMapper dynamoDBMapper(){
        return new DynamoDBMapper(amazonDynamoDB());
    }

    public AmazonDynamoDB amazonDynamoDB() {
        return AmazonDynamoDBClientBuilder
                .standard()
                .withEndpointConfiguration(
                        new AwsClientBuilder.EndpointConfiguration(endpoint,region))
                .withCredentials(amazonDynamoDBCredentials()).build();
    }

    @Bean
    public AmazonS3 s3client() {
        return AmazonS3ClientBuilder.standard()
                .withCredentials(amazonDynamoDBCredentials())
                .withRegion(region).build();
    }

    @Bean
    public SesClient sesClient() {
        return SesClient.builder()
                .region(Region.EU_NORTH_1)
                .credentialsProvider(StaticCredentialsProvider.create(
                        AwsBasicCredentials.create(accesskey, secretSecret)))
                .build();
    }

    @Bean
    public AWSCognitoIdentityProvider amazonCognitoIdentityProvider() {
        BasicAWSCredentials awsCreds = new BasicAWSCredentials(accesskey, secretSecret);
        return AWSCognitoIdentityProviderClientBuilder.standard()
                .withCredentials(new AWSStaticCredentialsProvider(awsCreds))
                .withRegion(region)
                .build();
    }

    private AWSCredentialsProvider amazonDynamoDBCredentials() {
        return new AWSStaticCredentialsProvider(new BasicAWSCredentials(accesskey,secretSecret));
    }

}