package com.idosinchuk.tddcompleteguide.infraestructure.persistence.entity;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBRangeKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;

@DynamoDBTable(tableName = NewsletterPreferencesEntity.NEWSLETTER_PREFERENCES_TABLE_NAME)
public class NewsletterPreferencesEntity {

    public static final String NEWSLETTER_PREFERENCES_TABLE_NAME = "newsletter_preferences";
    public static final String ATTR_USER_ID = "user_id";
    public static final String ATTR_NEWSLETTER_TYPE = "newsletter_type";
    public static final String ATTR_SUBSCRIBED = "subscribed";
    @DynamoDBHashKey(attributeName = ATTR_USER_ID)
    private int userId;

    @DynamoDBRangeKey(attributeName = ATTR_NEWSLETTER_TYPE)
    private String newsletterType;

    @DynamoDBAttribute(attributeName = ATTR_SUBSCRIBED)
    private boolean subscribed;

    public NewsletterPreferencesEntity() {
    }

    public NewsletterPreferencesEntity(int userId, String newsletterType, boolean subscribed) {
        this.userId = userId;
        this.newsletterType = newsletterType;
        this.subscribed = subscribed;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getNewsletterType() {
        return newsletterType;
    }

    public void setNewsletterType(String newsletterType) {
        this.newsletterType = newsletterType;
    }

    public boolean isSubscribed() {
        return subscribed;
    }

    public void setSubscribed(boolean subscribed) {
        this.subscribed = subscribed;
    }
}