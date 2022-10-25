Feature: RabotaBy tests

  @smoke
  Scenario: Search vacancies with default region (Minsk)
    Given Open start page
    When I'm confirm with search region
    And I'm enter search data "QA Automation"
    And I'm click search button
    Then I'm go to search page and check search criteria "QA Automation"
    And I'm check search result count more 50


  @regression
  Scenario Outline: Search vacancies in region
    Given Open start page
    When I'm click change search region
    And I'm click on region item "<region>"
    And I'm enter search data "QA Automation"
    And I'm click search button
    Then I'm go to search page and check search criteria "QA Automation"
    And I'm check search result count more <count>
    Examples:
      | region | count   |
      | Брест  |  1      |
      | Витебск|  1      |
      | Гомель |  1      |
      | Гродно |  1      |
      | Могилев|  1      |
