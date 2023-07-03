package com.idosinchuk.tddcompleteguide.application.service;

import com.idosinchuk.tddcompleteguide.domain.model.NewsletterPreferences;
import com.idosinchuk.tddcompleteguide.domain.model.NewsletterType;
import com.idosinchuk.tddcompleteguide.domain.repository.NewsletterPreferencesRepository;
import com.idosinchuk.tddcompleteguide.domain.service.impl.NewsletterPreferencesServiceImpl;
import com.idosinchuk.tddcompleteguide.utils.test_data.TestDataUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class NewsletterPreferencesServiceTest {

    @Mock
    private NewsletterPreferencesRepository newsletterPreferencesRepository;

    @Mock
    private UserService userService;

    @InjectMocks
    private NewsletterPreferencesServiceImpl newsletterPreferencesService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testUpdateNewsletterPreferences() {
        // Mock data
        int userId = 1;
        NewsletterPreferences newsletterPreferences = TestDataUtil.createNewsletterPreferencesMock(userId, NewsletterType.GENERAL, true);

        // Mock repository behavior
        when(newsletterPreferencesRepository.update(newsletterPreferences)).thenReturn(newsletterPreferences);
        when(newsletterPreferencesRepository.findByUserId(userId)).thenReturn(new ArrayList<>());

        // Mock service behavior
        var user = TestDataUtil.createUserMock(userId);
        when(userService.getUserById(userId)).thenReturn(user);

        // Call the service method
        List<NewsletterPreferences> updatedPreferences = newsletterPreferencesService.update(newsletterPreferences);

        // Verify the repository method calls
        verify(newsletterPreferencesRepository, times(1)).update(newsletterPreferences);
        verify(newsletterPreferencesRepository, times(1)).findByUserId(userId);

        // Verify the result
        assertEquals(0, updatedPreferences.size());
    }

    @Test
    void testFindByUserId() {
        int userId = 1;
        List<NewsletterPreferences> expectedPreferences = List.of(TestDataUtil.createNewsletterPreferencesMock(userId, NewsletterType.EVENT, true));

        // Mock repository behavior
        when(newsletterPreferencesRepository.findByUserId(userId)).thenReturn(expectedPreferences);

        // Mock service behavior
        var user = TestDataUtil.createUserMock(userId);
        when(userService.getUserById(userId)).thenReturn(user);

        // Call the service method
        List<NewsletterPreferences> preferences = newsletterPreferencesService.findByUserId(userId);

        // Verify the repository method call
        verify(newsletterPreferencesRepository, times(1)).findByUserId(userId);

        // Verify the result
        assertEquals(expectedPreferences, preferences);
    }
}