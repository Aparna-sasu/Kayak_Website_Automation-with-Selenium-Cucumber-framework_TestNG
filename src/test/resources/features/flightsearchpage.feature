Feature: Search flight functionality for kayak website
Background:
  Given I open url "https://www.kayak.com"

  Scenario Outline: User redirected to Result Page by providing valid inputs
    Given I close the Sign in frame if present
    Given I have selected <fromPlace> and <toPlace> cities
    Given I have selected Departure and Return Dates
    When I click on Search button
    Then I should be redirected to Result Page
    And the displayed Origin place and Destination place on the Result Page should be <fromPlace> and <toPlace>

    Examples:
      | fromPlace | toPlace |
      | "Manila"  | "Delhi" |
      | "Mexico"  | "Chennai" |
      | "Florida"  | "Atlanta" |



