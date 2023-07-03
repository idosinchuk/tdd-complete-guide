Scenario: Create a User
Given a user with the following details:
| username | john.doe |
| email | john.doe@example.com |
| name | John |
| surname | Doe |
| phoneNumber | +1 1234567890 |
| age | 25 |
| street | 123 Main St |
| city | New York |
| state | NY |
| postalCode | 10001 |
| country | USA |
When the user creates a new user
Then the user should receive a response with status code 201
And the response body should contain the created user details

Scenario: Get User by ID
Given a user with ID 1 exists in the system
When the user requests to get the user with ID 1
Then the user should receive a response with status code 200
And the response body should contain the user details with ID 1

Scenario: Get User by Username
Given a user with username "john.doe" exists in the system
When the user requests to get the user with username "john.doe"
Then the user should receive a response with status code 200
And the response body should contain the user details with the username "john.doe"