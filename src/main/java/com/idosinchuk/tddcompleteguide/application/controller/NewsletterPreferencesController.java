package com.idosinchuk.tddcompleteguide.application.controller;

import com.idosinchuk.tddcompleteguide.application.service.NewsletterPreferencesService;
import com.idosinchuk.tddcompleteguide.domain.model.NewsletterPreferences;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(value = "Controller for Newsletter Preferences operations")
@RestController
@RequestMapping(value = "/newsletter")
public class NewsletterPreferencesController {

    private final NewsletterPreferencesService newsletterPreferencesService;

    @Autowired
    public NewsletterPreferencesController(NewsletterPreferencesService newsletterPreferencesService) {
        this.newsletterPreferencesService = newsletterPreferencesService;
    }

    @ResponseBody
    @PutMapping(path = "/update/{userId}")
    public ResponseEntity<List<NewsletterPreferences>> updateNewsletterPreferences(@PathVariable int userId,
                                                                                   @RequestBody NewsletterPreferences newsletterPreferences) {
        newsletterPreferences.setUserId(userId);
        List<NewsletterPreferences> updatedNewsletterPreferences = newsletterPreferencesService.update(newsletterPreferences);
        return ResponseEntity.status(HttpStatus.OK).body(updatedNewsletterPreferences);
    }
}