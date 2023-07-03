package com.idosinchuk.tddcompleteguide.domain.model;

public class NewsletterPreferences {
    private int userId;

    private NewsletterType newsletterType;

    private boolean isSubscribed;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public NewsletterType getNewsletterType() {
        return newsletterType;
    }

    public void setNewsletterType(NewsletterType newsletterType) {
        this.newsletterType = newsletterType;
    }

    public boolean isSubscribed() {
        return isSubscribed;
    }

    public void setSubscribed(boolean subscribed) {
        isSubscribed = subscribed;
    }
}