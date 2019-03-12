Feature: Validate REST API using the rest assured library
  I want to the cucumber BDD with the rest assured library to test the REST APIs

    @singleKey
  Scenario Outline: Check the number of circuit records
    Given I have the "<baseUrl>" and "<endPoint>"
    When I make a call to the REST API GET request
    Then the HTTP status code from the response should be "<statusCode>"
    And the "<key>" is equal to "<circuitNo>"

    Examples: 
      | baseUrl   | endPoint     | statusCode | key          | circuitNo |
      | ergastDev | 2017circuits |        200 | MRData.total |        20 |

       @multiple
  Scenario Outline: Validate multiple response keys for the REST GET API
    Given I have the "<baseUrl>" and "<endPoint>"
    When I make a call to the REST API GET request
    Then the HTTP status code from the response should be "<statusCode>"
    And the multiple keys are validated as below from Json
      | actualKey    | expectedValue |
      | MRData.total |            20 |
      | MRData.limit |            30 |
       Examples: 
      | baseUrl   | endPoint     | statusCode |
      | ergastDev | 2017circuits |        200 |
      
     #  @post
  Scenario Outline: Validate the post request for REST API
    Given I have the "<baseUrl>" and "<endPoint>"
    When I make a call to the REST API POST request with <postDataFile>
    Then the HTTP status code from the response should be "<statusCode>"

    Examples: 
      | baseUrl  | endPoint         | statusCode | postDataFile  |
      | postBase | registerEndPoint |        201 | postData.json |
      
    
      