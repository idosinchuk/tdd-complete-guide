package com.idosinchuk.tddcompleteguide.domain.service.impl;

import com.idosinchuk.tddcompleteguide.application.service.NewsletterPreferencesService;
import com.idosinchuk.tddcompleteguide.application.service.UserService;
import com.idosinchuk.tddcompleteguide.domain.model.NewsletterPreferences;
import com.idosinchuk.tddcompleteguide.domain.model.NewsletterType;
import com.idosinchuk.tddcompleteguide.domain.repository.NewsletterPreferencesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NewsletterPreferencesServiceImpl implements NewsletterPreferencesService {

    private final NewsletterPreferencesRepository newsletterPreferencesRepository;

    private final UserService userService;

    @Autowired
    public NewsletterPreferencesServiceImpl(NewsletterPreferencesRepository newsletterPreferencesRepository, UserService userService) {
        this.newsletterPreferencesRepository = newsletterPreferencesRepository;
        this.userService = userService;
    }

    @Override
    public List<NewsletterPreferences> update(NewsletterPreferences newsletterPreferences) {

        var user = userService.getUserById(newsletterPreferences.getUserId());
        if (user != null) {
            if (newsletterPreferences.getNewsletterType().equals(NewsletterType.ALL)) {
                var newsletterPreferencesList = findByUserId(newsletterPreferences.getUserId());
                newsletterPreferencesList.forEach(np -> {
                    np.setSubscribed(newsletterPreferences.isSubscribed());
                    newsletterPreferencesRepository.update(np);
                });
            } else {
                newsletterPreferencesRepository.update(newsletterPreferences);
            }
        } else {
            throw new RuntimeException("User does not exist");
        }
        return findByUserId(newsletterPreferences.getUserId());
    }

    @Override
    public List<NewsletterPreferences> findByUserId(int userId) {
        return newsletterPreferencesRepository.findByUserId(userId);
    }
}
