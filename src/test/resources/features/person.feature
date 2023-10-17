Feature: Person management

  Scenario: Retrieve all persons
    Given the system has been initialized
    When I request all persons
    Then the result should be a list of persons

#  Scenario: Add a person
#    Given the system has been initialized
#    When I add a new person named "John" with age 30
#    Then the system should have a person named "John" with age 30
#    Then the system should have a person named "John" with age 30
