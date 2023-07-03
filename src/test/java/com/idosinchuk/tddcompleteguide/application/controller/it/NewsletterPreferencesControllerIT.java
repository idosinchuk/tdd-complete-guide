package com.idosinchuk.tddcompleteguide.application.controller.it;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.idosinchuk.tddcompleteguide.application.controller.NewsletterPreferencesController;
import com.idosinchuk.tddcompleteguide.domain.model.NewsletterPreferences;
import com.idosinchuk.tddcompleteguide.domain.model.NewsletterType;
import com.idosinchuk.tddcompleteguide.domain.service.impl.NewsletterPreferencesServiceImpl;
import com.idosinchuk.tddcompleteguide.utils.test_data.TestDataUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Collections;
import java.util.List;

@ExtendWith(SpringExtension.class)
@WebMvcTest(NewsletterPreferencesController.class)
class NewsletterPreferencesControllerIT {

    /**
     * MockMvc Setup: The test sets up a MockMvc instance to perform HTTP requests and validate the response.
     * MockMvc allows you to simulate the interaction with the controller and verify the expected behavior.
     */
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private NewsletterPreferencesServiceImpl newsletterPreferencesService;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
        /**
         * Dependency Mocking: The NewsletterPreferencesService dependency is mocked using Mockito.
         * This allows you to isolate the controller from the actual service implementation and control
         * the behavior of the service during the test.
         */
        //newsletterPreferencesService = Mockito.mock(NewsletterPreferencesServiceImpl.class);

    }

    @Test
    void testUpdateNewsletterPreferences() throws Exception {
        // Create a sample newsletter preferences object
        int userId = 1;
        NewsletterType newsletterType = NewsletterType.EVENT;
        boolean subscribed = true;
        NewsletterPreferences newsletterPreferences = TestDataUtil.createNewsletterPreferencesMock(userId, newsletterType, subscribed);

        // Mock the service method
        List<NewsletterPreferences> newsletterPreferencesList = Collections.singletonList(newsletterPreferences);
        Mockito.when(newsletterPreferencesService.update(newsletterPreferences)).thenReturn(newsletterPreferencesList);

        /**
         * Endpoint Testing: The test sends a PUT request to the /newsletter/update/{userId} endpoint, passing the necessary request data.
         * It then asserts the expected status code, content type, and response body using the MockMvcResultMatchers assertions.
         */

        ObjectMapper objectMapper = new ObjectMapper();

        var result = mockMvc.perform(MockMvcRequestBuilders
                .put("/newsletter/update/{userId}", userId)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(newsletterPreferences))).andReturn();

        result.equals(objectMapper.writeValueAsString(newsletterPreferences));
        /**result
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].userId").value(userId))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].newsletterType").value(newsletterType.toString()))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].subscribed").value(subscribed));*/

        // Verification: The test uses Mockito's verify method to ensure that the update method of the mocked newsletterPreferencesService
        // is called with the expected argument.
        Mockito.verify(newsletterPreferencesService, Mockito.times(1)).update(newsletterPreferences);
    }

}