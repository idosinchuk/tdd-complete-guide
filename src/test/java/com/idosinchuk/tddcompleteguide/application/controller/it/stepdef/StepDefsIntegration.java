package com.idosinchuk.tddcompleteguide.application.controller.it.stepdef;

import com.idosinchuk.tddcompleteguide.application.controller.UserController;
import com.idosinchuk.tddcompleteguide.application.controller.it.cucumber.CucumberIntegrationIT;
import com.idosinchuk.tddcompleteguide.domain.model.User;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class StepDefsIntegration extends CucumberIntegrationIT {
    private UserController userController;
    private ResponseEntity<User> createUserResponse;
    private ResponseEntity<User> getUserByIdResponse;
    private ResponseEntity<User> getUserByUsernameResponse;

    @Given("a user with the following details:")
    public void aUserWithTheFollowingDetails(User user) {
        userController = new UserController(null);
        createUserResponse = userController.createUser(user);
    }

    @When("the user creates a new user")
    public void theUserCreatesANewUser() {
        // The creation of a new user is already performed in the "Given" step
    }

    @Then("the user should receive a response with status code 201")
    public void theUserShouldReceiveAResponseWithStatusCode201() {
        Assert.assertEquals(HttpStatus.CREATED, createUserResponse.getStatusCode());
    }

    @Then("the response body should contain the created user details")
    public void theResponseBodyShouldContainTheCreatedUserDetails() {
        User createdUser = createUserResponse.getBody();
        Assert.assertNotNull(createdUser);
        // Perform assertions on the created user object as needed
    }

    @Given("a user with ID {int} exists in the system")
    public void aUserWithIdExistsInTheSystem(int userId) {
        userController = new UserController(null);
        getUserByIdResponse = userController.getUserById(userId);
        Assert.assertEquals(HttpStatus.OK, getUserByIdResponse.getStatusCode());
        Assert.assertNotNull(getUserByIdResponse.getBody());
    }

    @When("the user requests to get the user with ID {int}")
    public void theUserRequestsToGetTheUserWithId(int userId) {
        // The retrieval of the user by ID is already performed in the "Given" step
    }

    @Then("the response body should contain the user details with ID {int}")
    public void theResponseBodyShouldContainTheUserDetailsWithId(int userId) {
        User user = getUserByIdResponse.getBody();
        Assert.assertEquals(userId, user.getId());
        // Perform assertions on the retrieved user object as needed
    }

    @Given("a user with username {string} exists in the system")
    public void aUserWithUsernameExistsInTheSystem(String username) {
        userController = new UserController(null);
        getUserByUsernameResponse = userController.getUserByUsername(username);
        Assert.assertEquals(HttpStatus.OK, getUserByUsernameResponse.getStatusCode());
        Assert.assertNotNull(getUserByUsernameResponse.getBody());
    }

    @When("the user requests to get the user with username {string}")
    public void theUserRequestsToGetTheUserWithUsername(String username) {
        // The retrieval of the user by username is already performed in the "Given" step
    }

    @Then("the response body should contain the user details with the username {string}")
    public void theResponseBodyShouldContainTheUserDetailsWithUsername(String username) {
        User user = getUserByUsernameResponse.getBody();
        Assert.assertEquals(username, user.getUsername());
        // Perform assertions on the retrieved user object as needed
    }
}
