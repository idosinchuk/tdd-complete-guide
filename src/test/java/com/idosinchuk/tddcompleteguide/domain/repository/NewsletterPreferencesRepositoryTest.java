package com.idosinchuk.tddcompleteguide.domain.repository;

import com.idosinchuk.tddcompleteguide.domain.model.NewsletterPreferences;
import com.idosinchuk.tddcompleteguide.domain.model.NewsletterType;
import com.idosinchuk.tddcompleteguide.domain.repository.NewsletterPreferencesRepository;
import com.idosinchuk.tddcompleteguide.utils.test_data.TestDataUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class NewsletterPreferencesRepositoryTest {

    @Mock
    private NewsletterPreferencesRepository newsletterPreferencesRepository;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testUpdateNewsletterPreferences() {
        NewsletterPreferences newsletterPreferences = TestDataUtil.createNewsletterPreferencesMock(1, NewsletterType.GENERAL, true);
        when(newsletterPreferencesRepository.update(newsletterPreferences)).thenReturn(newsletterPreferences);
        when(newsletterPreferencesRepository.update(newsletterPreferences)).thenReturn(newsletterPreferences);

        NewsletterPreferences updatedPreferences = newsletterPreferencesRepository.update(newsletterPreferences);

        assertEquals(newsletterPreferences, updatedPreferences);
        verify(newsletterPreferencesRepository, times(1)).update(newsletterPreferences);
    }

    @Test
    void testFindByUserId() {
        int userId = 1;
        List<NewsletterPreferences> preferencesList = List.of(
                TestDataUtil.createNewsletterPreferencesMock(userId, NewsletterType.EVENT, true),
                TestDataUtil.createNewsletterPreferencesMock(2, NewsletterType.GENERAL, true)
        );
        when(newsletterPreferencesRepository.findByUserId(userId)).thenReturn(preferencesList);

        List<NewsletterPreferences> retrievedPreferences = newsletterPreferencesRepository.findByUserId(userId);

        assertEquals(preferencesList, retrievedPreferences);
        verify(newsletterPreferencesRepository, times(1)).findByUserId(userId);
    }
}