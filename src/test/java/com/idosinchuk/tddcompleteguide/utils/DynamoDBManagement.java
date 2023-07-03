package com.idosinchuk.tddcompleteguide.utils;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.model.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import java.util.List;

import static com.idosinchuk.tddcompleteguide.infraestructure.persistence.entity.NewsletterPreferencesEntity.*;

@Configuration
public class DynamoDBManagement {
    private static final Logger logger = LogManager.getLogger(DynamoDBManagement.class);

    public static AmazonDynamoDB initializeDynamoDB(int dynamoDBPort) {
        logger.info("Using local DynamoDB.");
        String dynamoDBUrl = "http://localhost";
        var dynamoDBEndpoint = dynamoDBUrl + ":" + dynamoDBPort;
        String region = "eu-central-1";
        String secretKey = ":dynamodb-password";
        String accessKeyId = "dynamodb-user";
        return AmazonDynamoDBClientBuilder.standard()
                .withCredentials(new AWSStaticCredentialsProvider(new BasicAWSCredentials(accessKeyId, secretKey)))
                .withEndpointConfiguration(new AwsClientBuilder.EndpointConfiguration(dynamoDBEndpoint, region))
                .build();
    }

    public static void deleteAllTables(AmazonDynamoDB amazonDynamoDB) {
        amazonDynamoDB.listTables().getTableNames().forEach(tableName -> {
            DeleteTableRequest deleteTableRequest = new DeleteTableRequest().withTableName(tableName);
            amazonDynamoDB.deleteTable(deleteTableRequest);
            logger.info("Table deleted: " + tableName);
        });
    }

    public static void recreateAllTables(AmazonDynamoDB amazonDynamoDB) {
        deleteAllTables(amazonDynamoDB);
        createTables(amazonDynamoDB);
    }

    public static void createTables(AmazonDynamoDB amazonDynamoDB) {
        createNewsletterPreferencesTable(amazonDynamoDB);
    }

    private static void createNewsletterPreferencesTable(AmazonDynamoDB amazonDynamoDB) {
        String tableName = NEWSLETTER_PREFERENCES_TABLE_NAME;

        List<AttributeDefinition> attributeDefinitions = List.of(
                new AttributeDefinition(ATTR_USER_ID, ScalarAttributeType.N),
                new AttributeDefinition(ATTR_NEWSLETTER_TYPE, ScalarAttributeType.S)
        );

        List<KeySchemaElement> keySchema = List.of(
                new KeySchemaElement(ATTR_USER_ID, KeyType.HASH),
                new KeySchemaElement(ATTR_NEWSLETTER_TYPE, KeyType.RANGE)
        );

        CreateTableRequest createTableRequest = createTableRequest(tableName, attributeDefinitions, keySchema);
        amazonDynamoDB.createTable(createTableRequest);
        logger.info("Table created: " + tableName);
    }

    private static CreateTableRequest createTableRequest(
            String tableName,
            List<AttributeDefinition> attributeDefinitions,
            List<KeySchemaElement> keySchema
    ) {
        ProvisionedThroughput provisionedThroughput = new ProvisionedThroughput(10L, 10L);
        return new CreateTableRequest()
                .withTableName(tableName)
                .withAttributeDefinitions(attributeDefinitions)
                .withKeySchema(keySchema)
                .withProvisionedThroughput(provisionedThroughput);
    }
}