package com.idosinchuk.tddcompleteguide.domain.repository;

import com.idosinchuk.tddcompleteguide.domain.model.NewsletterPreferences;

import java.util.List;

public interface NewsletterPreferencesRepository {
    NewsletterPreferences update(NewsletterPreferences newsletterPreferences);

    List<NewsletterPreferences> findByUserId(int userId);
}