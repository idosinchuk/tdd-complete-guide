package com.idosinchuk.tddcompleteguide.application.service;

import com.idosinchuk.tddcompleteguide.domain.model.NewsletterPreferences;

import java.util.List;

public interface NewsletterPreferencesService {
    List<NewsletterPreferences> update(NewsletterPreferences newsletterPreferences);

    List<NewsletterPreferences> findByUserId(int userId);
}