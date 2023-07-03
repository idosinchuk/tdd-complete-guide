package com.idosinchuk.tddcompleteguide.application.controller.unit;

import com.idosinchuk.tddcompleteguide.application.controller.NewsletterPreferencesController;
import com.idosinchuk.tddcompleteguide.application.service.NewsletterPreferencesService;
import com.idosinchuk.tddcompleteguide.domain.model.NewsletterPreferences;
import com.idosinchuk.tddcompleteguide.domain.model.NewsletterType;
import com.idosinchuk.tddcompleteguide.utils.test_data.TestDataUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class NewsletterPreferencesControllerTest {

    @Mock
    private NewsletterPreferencesService newsletterPreferencesService;

    private NewsletterPreferencesController newsletterPreferencesController;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
        newsletterPreferencesController = new NewsletterPreferencesController(newsletterPreferencesService);
    }

    @Test
    void testUpdateNewsletterPreferences() {
        int userId = 1;
        NewsletterPreferences newsletterPreferences = TestDataUtil.createNewsletterPreferencesMock(userId, NewsletterType.GENERAL, true);

        List<NewsletterPreferences> updatedNewsletterPreferences = List.of(TestDataUtil.createNewsletterPreferencesMock(userId, NewsletterType.GENERAL, true));
        when(newsletterPreferencesService.update(newsletterPreferences)).thenReturn(updatedNewsletterPreferences);

        ResponseEntity<List<NewsletterPreferences>> response = newsletterPreferencesController.updateNewsletterPreferences(userId, newsletterPreferences);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(updatedNewsletterPreferences, response.getBody());
        verify(newsletterPreferencesService, times(1)).update(newsletterPreferences);
    }
}