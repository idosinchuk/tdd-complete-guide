package com.idosinchuk.tddcompleteguide.utils.test_data;

import com.idosinchuk.tddcompleteguide.domain.model.Address;
import com.idosinchuk.tddcompleteguide.domain.model.NewsletterPreferences;
import com.idosinchuk.tddcompleteguide.domain.model.NewsletterType;
import com.idosinchuk.tddcompleteguide.domain.model.User;
import com.idosinchuk.tddcompleteguide.infraestructure.persistence.entity.AddressEntity;
import com.idosinchuk.tddcompleteguide.infraestructure.persistence.entity.NewsletterPreferencesEntity;
import com.idosinchuk.tddcompleteguide.infraestructure.persistence.entity.UserEntity;

import java.util.List;

public class TestDataUtil {

    public static Address createAddressMock(int addressId) {
        return new Address(
                "123 Main St",
                "New York",
                "NY",
                "10001",
                "USA"
        );
    }

    public static User createUserMock(int userId) {
        Address address = new Address(
                "123 Main St",
                "New York",
                "NY",
                "10001",
                "USA"
        );

        return new User(
                userId,
                "jhondoe",
                "Jhon",
                "Doe",
                "hhondoe@tdd.com",
                "+1234567890",
                address,
                30
        );
    }

    public static NewsletterPreferences createNewsletterPreferencesMock(int userId, NewsletterType newsletterType, boolean subscribed) {
        NewsletterPreferences newsletterPreferences = new NewsletterPreferences();
        newsletterPreferences.setUserId(userId);
        newsletterPreferences.setNewsletterType(newsletterType);
        newsletterPreferences.setSubscribed(subscribed);
        return newsletterPreferences;
    }

    public static List<NewsletterPreferences> createListNewsletterPreferencesMock(int userId, boolean subscribed) {
        return List.of(
                createNewsletterPreferencesMock(userId, NewsletterType.GENERAL, subscribed),
                createNewsletterPreferencesMock(userId, NewsletterType.PROMOTIONAL, subscribed),
                createNewsletterPreferencesMock(userId, NewsletterType.NEWS, subscribed),
                createNewsletterPreferencesMock(userId, NewsletterType.EVENT, subscribed)
        );
    }

    public static UserEntity createUserEntityMock(int userId) {
        AddressEntity addressEntity = new AddressEntity(
                1,
                "123 Main St",
                "New York",
                "NY",
                "10001",
                "USA"
        );

        return new UserEntity(
                userId,
                "jdoe",
                "Jhon",
                "Doe",
                "jdoe@tdd.com",
                "+1234567890",
                addressEntity,
                30
        );
    }

    public static NewsletterPreferencesEntity createNewsletterPreferencesEntityMock(int userId, NewsletterType newsletterType, boolean subscribed) {
        NewsletterPreferencesEntity newsletterPreferencesEntity = new NewsletterPreferencesEntity();
        newsletterPreferencesEntity.setUserId(userId);
        newsletterPreferencesEntity.setNewsletterType(newsletterType.toString());
        newsletterPreferencesEntity.setSubscribed(subscribed);
        return newsletterPreferencesEntity;
    }

    public static AddressEntity createAddressEntityMock(int addressId) {
        return new AddressEntity(
                1,
                "123 Main St",
                "New York",
                "NY",
                "10001",
                "USA"
        );
    }
}