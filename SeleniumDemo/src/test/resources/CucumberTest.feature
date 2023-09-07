Feature: Bank demo site tested

  Scenario: Logging into demo bank site
    Given Test attributes
    When Enter the main bank site
    And Navigate to demo site
    Then Gives credentials
    Then Check if logged in
    Then User logout
    Then Finish test


  Scenario: Verify displayed user info
    Given Test attributes
    When Enter the main bank site
    And Navigate to demo site
    Then Gives credentials
    Then Check if logged in
    And Verify user data
    And Verify balance amount
    When Navigate to new elixir transfer site
    And New elixir page is displayed properly
    Then Fill new elixir transfer data
    Then User logout
    Then Finish test