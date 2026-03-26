🏥 NHS Jobs Search Automation – BDD Test Suite
📌 Project Overview
This repository contains an automated Functional Acceptance Test Suite for the NHS Jobs Search functionality:

🔗 https://www.jobs.nhs.uk/candidate/search

The solution is built using BDD (Behavior Driven Development) principles with Cucumber, Selenium, Java 21, and TestNG.

🎯 Objective
To validate the following user story:

As a jobseeker on NHS Jobs website I want to search for a job with my preferences So that I can get recently posted job results

✅ Acceptance Criteria
Perform job search using preferences
Validate matching job results
Verify results are sorted by Newest Date Posted
🧰 Tech Stack
Tool	Purpose
Java 21	Programming Language
Selenium 4.41.0	UI Automation
Cucumber 7.x	BDD Framework
TestNG 7.11.0	Test Execution
WebDriverManager 6.3.3	Driver Management
Maven	Build Tool
📁 Project Structure

 ├── .idea
 ├── .mvn
 ├── src
 │   ├── main
 │   │   ├── java
 │   │   └── resources
 │   └── test
 │       ├── java
 │       │   ├── driver        → Driver setup (DriverFactory)
 │       │   ├── hooks         → Test setup & teardown (Hooks)
 │       │   ├── pages         → Page Object Model classes (NHSJobsSearchPage)
 │       │   ├── runner        → Test runner class (TestRunner)
 │       │   ├── steps         → Step definitions (NHSJobsSearchSteps)
 │       │   └── utils         → Utilities (ConfigReader, LoggerUtils)
 │       └── resources
 │           ├── config.properties
 │           └── search.feature
 ├── target
 ├── .gitignore
 ├── pom.xml
 └── testng.xml

🧪 BDD Feature Example
Feature: NHS Job Search

  Scenario: Search job and validate newest results
    Given I am on NHS Jobs search page
    When I enter job preferences
    And I perform search
    Then I should see job results
    And results should be sorted by newest date posted
🚀 How to Run Tests
🔧 Prerequisites
Java 21 installed
Maven installed
Chrome & Firefox browsers installed
▶️ Run via Command Line
mvn clean test

✔ Runs tests on configured browser ✔ No local driver setup required (WebDriverManager handles it)

🌐 Cross-Browser Support
✅ Chrome
✅ Firefox
🧠 Test Design Approach
✔ User-Centric BDD
Scenarios written in business-readable language
Focus on user journey, not implementation
✔ Design Patterns
Page Object Model (POM)
Reusable utilities
Separation of concerns
✔ Reusability
Config-driven execution
Centralized locators
Common hooks
🔍 Element Locator Strategy
Prefer:

id
name
Avoid brittle XPath where possible

Dynamic waits used for stability

⚠️ Issues Identified
Potential delay in job results loading
Sorting validation requires careful handling of date formats
Dynamic elements may cause flakiness without waits
🐞 Defect Analysis & Observations (Senior QA Perspective)
During exploratory and functional testing, multiple usability and state-management issues were identified.

🐞 Defect 1: Clear Filter button not persistent after search
Description: Clear Filter button is visible on initial load but disappears after performing search.

Impact:

Poor user experience
Increased effort to reset filters
Violates standard UX practices
Steps to Reproduce:

Open search page
Enter job title/location
Click Search
Expected: Clear Filter button should remain visible

Actual: Button disappears

Severity: Medium Priority: High

Recommendation: Ensure filter state persistence and UI visibility after search.

🐞 Defect 2: Search fields retain values after page reload
Description: Input fields retain previously entered values after refresh.

Impact:

Confusing UX
Incorrect assumption of fresh state
Steps to Reproduce:

Enter values
Perform search
Refresh page
Expected: Fields should reset

Actual: Values remain

Severity: Medium Priority: Medium

Recommendation: Clear fields on reload or clearly indicate persisted state.

🐞 Defect 3: UI state mismatch after reload
Description: Filters remain applied but Clear Filter button is not visible.

Impact:

Broken UI consistency
No way to reset filters easily
Expected: UI should reflect applied filters

Actual: Mismatch between UI and backend state

Severity: Medium Priority: High

Recommendation: Synchronize UI controls with filter state.

🔄 Test Coverage Enhancement
To avoid regression, the following scenarios are recommended:

Scenario: Verify Clear Filter button persists after search
  When I search for "tester" jobs in "London"
  Then I should see Clear Filter button

Scenario: Verify search fields reset on page refresh
  When I search for "tester" jobs
  And I refresh the page
  Then search fields should be empty

Scenario: Verify filter state and UI consistency
  When I apply filters and refresh the page
  Then UI should reflect applied filters correctly
🐞 Bug Reports (Discovered During Testing)
🐞 Bug 1: Clear Filter button disappears after search
Description: Clear Filter button is visible on initial page load. However, after performing a search (with job title or location), the button disappears.

Steps to Reproduce:

Open NHS Jobs search page
Observe Clear Filter button is visible
Enter job title or location
Click Search
Expected Result: Clear Filter button should remain visible after search

Actual Result: Clear Filter button disappears

Severity: Medium Priority: High

🐞 Bug 2: Search fields retain values after page reload
Description: After performing a search and refreshing the page, previously entered values remain in the input fields instead of resetting.

Steps to Reproduce:

Enter job title and/or location
Click Search
Refresh the page
Expected Result: Search fields should be cleared

Actual Result: Previously entered values are still displayed

Severity: Medium Priority: Medium

🐞 Bug 3: Clear Filter button missing after page reload
Description: After refreshing the page, Clear Filter button is not visible even though filters are still applied.

Expected Result: Clear Filter button should be visible

Actual Result: Button is missing

Severity: Medium Priority: Medium

🔧 Improvements
Add explicit wait utilities
Integrate reporting (Extent Reports / Allure)
Add CI/CD (GitHub Actions / Jenkins)
Parallel execution support
📊 PART 2 – TEST STRATEGY PRESENTATION
♿ Accessibility Testing Approach
Tools:
Axe DevTools
Lighthouse
Wave
Key Checks:
Color contrast
ARIA roles
Keyboard navigation
Screen reader compatibility
🌍 Compatibility Testing Approach
Browsers:
Chrome
Firefox
Edge (optional)
Devices:
Desktop
Tablet
Mobile responsiveness
OS:
Windows
macOS
⚡ Performance Testing Considerations
Tools:
JMeter
Gatling
Scenarios:
Load testing search functionality
Response time validation
Concurrent users simulation
Metrics:
Response time
Throughput
Error rate
🔄 Data Migration Testing Approach
When migrating NHS Trust data:

✔ Data Validation
Source vs Target comparison
Data completeness
✔ Data Integrity
No corruption or loss
Referential integrity maintained
✔ Functional Validation
Migrated jobs searchable
Applications linked correctly
✔ Performance Check
Large dataset handling
✔ Security Validation
Sensitive data protection
📌 Key Highlights
✔ Clean BDD implementation ✔ Scalable framework design ✔ Cross-browser testing ✔ Real bug identification and reporting ✔ Industry best practices followed

👨‍💻 Author
[Akshipt]

⭐ Final Note
This project demonstrates a real-world scalable test automation framework aligned with modern QA practices, focusing on maintainability, readability, and business value, along with real defect discovery and reporting experience.
