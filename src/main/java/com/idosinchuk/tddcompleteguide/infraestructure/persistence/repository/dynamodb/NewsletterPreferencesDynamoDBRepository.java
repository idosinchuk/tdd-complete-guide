package com.idosinchuk.tddcompleteguide.infraestructure.persistence.repository.dynamodb;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMappingException;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBQueryExpression;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.idosinchuk.tddcompleteguide.domain.model.NewsletterPreferences;
import com.idosinchuk.tddcompleteguide.domain.model.NewsletterType;
import com.idosinchuk.tddcompleteguide.domain.repository.NewsletterPreferencesRepository;
import com.idosinchuk.tddcompleteguide.infraestructure.persistence.entity.NewsletterPreferencesEntity;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class NewsletterPreferencesDynamoDBRepository implements NewsletterPreferencesRepository {

    DynamoDBMapper dynamoDBMapper;

    ModelMapper modelMapper;

    @Autowired
    public NewsletterPreferencesDynamoDBRepository(
            AmazonDynamoDB amazonDynamoDB,
            ModelMapper modelMapper) {
        this.dynamoDBMapper = new DynamoDBMapper(amazonDynamoDB);
        this.modelMapper = modelMapper;
    }

    @Override
    public NewsletterPreferences update(NewsletterPreferences newsletterPreferences) {
        NewsletterPreferencesEntity newsletterPreferencesEntity = modelMapper.map(newsletterPreferences, NewsletterPreferencesEntity.class);
        try {
            dynamoDBMapper.save(newsletterPreferencesEntity);
        } catch (DynamoDBMappingException e) {
            System.out.println("Failed to save entity: " + e.getMessage());
        }
        return newsletterPreferences;
    }


    @Override
    public List<NewsletterPreferences> findByUserId(int userId) {
        NewsletterPreferencesEntity queryEntity = new NewsletterPreferencesEntity();
        queryEntity.setUserId(userId);

        DynamoDBQueryExpression<NewsletterPreferencesEntity> queryExpression = new DynamoDBQueryExpression<>();
        queryExpression.setHashKeyValues(queryEntity);

        List<NewsletterPreferencesEntity> entities = dynamoDBMapper.query(NewsletterPreferencesEntity.class, queryExpression);

        List<NewsletterPreferences> newsletterPreferencesList = new ArrayList<>();
        for (NewsletterPreferencesEntity entity : entities) {
            NewsletterPreferences newsletterPreferences = modelMapper.map(entity, NewsletterPreferences.class);
            newsletterPreferencesList.add(newsletterPreferences);
        }

        return newsletterPreferencesList;
    }
}