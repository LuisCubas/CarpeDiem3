Feature: Automation Challenge

  @one
  Scenario: Create, Read, Update and Delete (CRUD) a Project

    Given I have access to App "https://todo.ly/"

    When I send a POST request to "http://todo.ly/api/projects.json" using the body
    """
    {
      "Content":"Project X",
      "Icon":2
    }
    """
    Then The Status Code should be 200
    Then The Content should be "Project X"
    Then The Icon should be 2
    Then I verify the Schema using jsonSchemaFactory

    And I capture the Project ID into a variable

    When I send a PUT request to "http://todo.ly/api/projects/ID_PROYECT.json" using the body
    """
    {
      "Content":"Project X Update",
      "Icon":4
    }
    """
    Then The Status Code should be 200
    Then The Content should be "Project X Update"
    Then The Icon should be 4

    When I send a DELETE request to "http://todo.ly/api/projects/ID_PROYECT.json"
    Then The Status Code should be 200
    Then The flag Deleted should be "true"


