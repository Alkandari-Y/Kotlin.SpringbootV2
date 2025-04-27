
Feature: Hello World Api
    Scenario: Basic Test for the Hello world API
        When I make a GET request to "/api/v1/hello-world"
        Then I get the Http status code should be 300
        And the response body should be "Hello World!"
