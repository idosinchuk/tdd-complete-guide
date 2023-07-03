package com.idosinchuk.tddcompleteguide.application.service;

import com.idosinchuk.tddcompleteguide.domain.model.User;

public interface UserService {

    User createUser(User user);

    User getUserById(int userId);

    User getUserByUsername(String username);
}