Feature: NHS Job Search functionality
  As a jobseeker on NHS Jobs website
  I want to search for jobs with my preferences
  So that I can see relevant and recently posted job results

  Background:
    Given I am on the NHS Jobs search page


  Scenario Outline: Search jobs with valid keyword and location
    When I enter "<jobTitle>" in the job title field
    And I enter "<location>" in the location field
    And I click on the search button
    Then I should see a list of job results

    Examples:
      | jobTitle          | location   |
      | automation tester | London     |
      | java developer    | Manchester |
      | nurse             | Birmingham |
      | data analyst      | Leeds      |
      | project manager   | Bristol    |
      | qa engineer       | Liverpool  |


  Scenario Outline: Search using all filters
    When I enter "<jobTitle>" in the job title field
    And I enter "<location>" in the location field
    And I select distance "<distance>"
    And I click on moreSearchOptions
    And I select pay range "<payRange>"
    And I click on the search button
    Then I should see a list of job results

    Examples:
      | jobTitle          | location   | distance   | payRange            |
      | Nurse             | London     | +10 Miles  | £20,000 to £30,000  |
      | Doctor            | Manchester | +20 Miles  | £30,000 to £40,000  |
      | Analyst           | Birmingham | +50 Miles  | £40,000 to £50,000  |
      | Manager           | Leeds      | +100 Miles | £70,000 to £80,000  |
      | Engineer          | Liverpool  | +30 Miles  | £50,000 to £60,000  |
      | Tester            | London    | +20 Miles   | £40,000 to £50,000  |


  Scenario: Search by job title only
    When I enter "tester" in the job title field
    And I click on the search button
    Then I should see a list of job results


  Scenario: Search by location only
    When I enter "London" in the location field
    And I click on the search button
    Then I should see a list of job results


  Scenario: Search by pay range only
    When I click on moreSearchOptions
    And I select pay range "£80,000 to £90,000"
    And I click on the search button
    Then I should see a list of job results


  Scenario: Search by employer only
    When I click on moreSearchOptions
    And I enter "NHS Business Services Authority" in the employer field
    And I click on the search button
    Then I should see a list of job results


  Scenario: Search by job reference only
    When I click on moreSearchOptions
    And I enter "C0987-23-1234 / 914-JOBREF-a" in the job reference field
    And I click on the search button
    Then  I should see message "No result found"


  Scenario: Search with invalid job title
    When I search for "xyzinvalidjob" jobs in "London"
    Then I click on the search button
    And I should see message "No result found"


  Scenario: Search with invalid job reference
    When I click on moreSearchOptions
    And I enter "999999" in the job reference field
    And I click on the search button
    Then I should see message "No result found"


  Scenario: Search without entering any criteria
    When I perform search without entering any details
    And I click on the search button
    Then I should see default job results


  Scenario: Clear all filters
    When I enter "Staff Nurse" in the job title field
    And I enter "London" in the location field
    And I select distance "+20 Miles"
    And I click on moreSearchOptions
    And I select pay range "£80,000 to £90,000"
    And I click clear filters button
    Then all fields should be empty


  Scenario: Toggle search options
    When I click on moreSearchOptions
    Then additional search fields should be visible
    When I click fewer search options
    Then only basic search fields should be visible


  Scenario: Perform search without entering any criteria
    When I perform search without entering any details
    And I click on the search button
    Then I should see default job results

