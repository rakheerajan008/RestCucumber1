#Author: your.email@your.domain.com
#Keywords Summary :
#Feature: List of scenarios.
@tag
Feature: Title of your feature
  I want to use this template for my feature file

  @soap
  Scenario Outline: Validate the post request for REST API
    Given I have the "<baseUrl>" and "<endPoint>"
    When I make a call to the SOAP API POST request with <postDataFile>
    Then the HTTP status code from the response should be "<statusCode>"
    And the multiple keys are validated as below from XML
      | actualKey |
      | Code      |

    Examples: 
      | baseUrl  | endPoint     | statusCode | postDataFile     |
      | soapBase | soapEndPoint |        200 | soapPostData.xml |
