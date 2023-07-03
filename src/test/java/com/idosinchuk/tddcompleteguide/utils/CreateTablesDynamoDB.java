package com.idosinchuk.tddcompleteguide.utils;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;

/**
 * This class connects to the local DynamoDB to recreate all the tables.
 * <p>
 * It is used to set up the DynamoDB tables for the integration tests and local development.
 */
public class CreateTablesDynamoDB {

    private static Integer dynamoDBPort = 8000;

    /**
     * Note: Check that you have started DynamoDB locally before running it.
     * You can find more information about how to do it in the README.
     */
    public static void main(String[] args) {
        AmazonDynamoDB amazonDynamoDB = DynamoDBManagement.initializeDynamoDB(dynamoDBPort);
        DynamoDBManagement.recreateAllTables(amazonDynamoDB);
    }
}