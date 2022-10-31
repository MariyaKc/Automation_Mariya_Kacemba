Feature: Mood panda tests

  @smoke
  Scenario Outline: Navigation in application
    Given open home page
    When i click on menu item "<link>"
    Then i check that uri is "<url>"
    Examples:
      | link  | url                                 |
      | What? | information/monitor-your-mood       |
      | How?  | information/how-to-use-a-mood-diary |
      | About | about                               |


  @regression
  Scenario:
    Given open home page
    When i click on menu item "Login"
    Then i check that uri is "login"
    When i enter email "test@test.ru", password "123456"
    And i click login button
    Then i check error message "Your email or password is wrong"