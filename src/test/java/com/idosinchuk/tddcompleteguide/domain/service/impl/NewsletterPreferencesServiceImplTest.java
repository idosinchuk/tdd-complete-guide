package com.idosinchuk.tddcompleteguide.domain.service.impl;

import com.idosinchuk.tddcompleteguide.application.service.UserService;
import com.idosinchuk.tddcompleteguide.domain.model.NewsletterPreferences;
import com.idosinchuk.tddcompleteguide.domain.model.NewsletterType;
import com.idosinchuk.tddcompleteguide.domain.model.User;
import com.idosinchuk.tddcompleteguide.domain.repository.NewsletterPreferencesRepository;
import com.idosinchuk.tddcompleteguide.utils.test_data.TestDataUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class NewsletterPreferencesServiceImplTest {

    @Mock
    private NewsletterPreferencesRepository newsletterPreferencesRepository;

    @Mock
    private UserService userService;

    private NewsletterPreferencesServiceImpl newsletterPreferencesService;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
        newsletterPreferencesService = new NewsletterPreferencesServiceImpl(newsletterPreferencesRepository, userService);
    }

    @Test
    void testUpdateNewsletterPreferences_WhenUserExistsAndNewsletterTypeIsAll() {
        int userId = 1;
        boolean subscribed = true;

        NewsletterPreferences newsletterPreferences = new NewsletterPreferences();
        newsletterPreferences.setUserId(userId);
        newsletterPreferences.setNewsletterType(NewsletterType.ALL);
        newsletterPreferences.setSubscribed(subscribed);

        NewsletterPreferences existingNewsletterPreference1 = new NewsletterPreferences();
        existingNewsletterPreference1.setUserId(userId);
        existingNewsletterPreference1.setNewsletterType(NewsletterType.GENERAL);
        existingNewsletterPreference1.setSubscribed(!subscribed);

        NewsletterPreferences existingNewsletterPreference2 = new NewsletterPreferences();
        existingNewsletterPreference2.setUserId(userId);
        existingNewsletterPreference2.setNewsletterType(NewsletterType.PROMOTIONAL);
        existingNewsletterPreference2.setSubscribed(!subscribed);

        List<NewsletterPreferences> existingNewsletterPreferences = new ArrayList<>();
        existingNewsletterPreferences.add(existingNewsletterPreference1);
        existingNewsletterPreferences.add(existingNewsletterPreference2);

        when(userService.getUserById(userId)).thenReturn(new User());
        when(newsletterPreferencesRepository.findByUserId(userId)).thenReturn(existingNewsletterPreferences);

        List<NewsletterPreferences> updatedNewsletterPreferences = newsletterPreferencesService.update(newsletterPreferences);

        assertEquals(existingNewsletterPreferences.size(), updatedNewsletterPreferences.size());
        assertTrue(updatedNewsletterPreferences.stream().allMatch(np -> np.isSubscribed() == subscribed));
        verify(newsletterPreferencesRepository, times(existingNewsletterPreferences.size())).update(any(NewsletterPreferences.class));
    }

    @Test
    void testUpdateNewsletterPreferences_WhenUserExistsAndNewsletterTypeIsNotAll() {
        int userId = 1;
        boolean subscribed = true;

        NewsletterPreferences newsletterPreferences = TestDataUtil.createNewsletterPreferencesMock(userId, NewsletterType.GENERAL, subscribed);

        // Mock service behavior
        var user = TestDataUtil.createUserMock(userId);
        when(userService.getUserById(userId)).thenReturn(user);

        var newsletterPreferencesList = List.of(TestDataUtil.createNewsletterPreferencesMock(userId, NewsletterType.GENERAL, subscribed));
        when(newsletterPreferencesRepository.findByUserId(userId)).thenReturn(newsletterPreferencesList);

        List<NewsletterPreferences> updatedNewsletterPreferences = newsletterPreferencesService.update(newsletterPreferences);

        assertEquals(1, updatedNewsletterPreferences.size());
        assertEquals(newsletterPreferences.getNewsletterType(), updatedNewsletterPreferences.get(0).getNewsletterType());
        assertEquals(subscribed, updatedNewsletterPreferences.get(0).isSubscribed());
        verify(newsletterPreferencesRepository, times(1)).update(newsletterPreferences);
    }

    @Test
    void testUpdateNewsletterPreferences_WhenUserDoesNotExist() {
        int userId = 1;

        NewsletterPreferences newsletterPreferences = TestDataUtil.createNewsletterPreferencesMock(userId, NewsletterType.GENERAL, true);

        when(userService.getUserById(userId)).thenReturn(null);

        assertThrows(RuntimeException.class, () -> newsletterPreferencesService.update(newsletterPreferences));
        verify(newsletterPreferencesRepository, never()).update(any(NewsletterPreferences.class));
    }

    @Test
    void testFindByUserId() {
        int userId = 1;

        List<NewsletterPreferences> newsletterPreferencesList = new ArrayList<>();
        when(newsletterPreferencesRepository.findByUserId(userId)).thenReturn(newsletterPreferencesList);

        List<NewsletterPreferences> result = newsletterPreferencesService.findByUserId(userId);

        assertEquals(newsletterPreferencesList, result);
        verify(newsletterPreferencesRepository, times(1)).findByUserId(userId);
    }
}