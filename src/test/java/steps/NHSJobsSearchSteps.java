package steps;

import io.cucumber.java.en.*;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.NHSJobsSearchPage;
import driver.DriverFactory;
import utils.ConfigReader;
import utils.LoggerUtils;
import org.slf4j.Logger;
import org.testng.Assert;

import java.util.ArrayList;
import java.util.List;

public class NHSJobsSearchSteps {

    private NHSJobsSearchPage nhsPage = new NHSJobsSearchPage(DriverFactory.getDriver());
    private static final Logger log = LoggerUtils.getLogger(NHSJobsSearchSteps.class);

    @Given("I am on the NHS Jobs search page")
    public void i_am_on_the_nhs_jobs_search_page() {
        String expectedUrl = ConfigReader.getProperty("url");
        String actualUrl = DriverFactory.getDriver().getCurrentUrl();

        log.info("Validating URL");

        Assert.assertTrue(actualUrl.contains(expectedUrl), "Wrong page opened. Actual URL: " + actualUrl);

    }

    @When("I enter {string} in the job title field")
    public void i_enter_in_the_job_title_field(String jobTitle) {
        log.info("Entering job title: {}", jobTitle);
        nhsPage.enterWhat(jobTitle);

    }

    @When("I enter {string} in the location field")
    public void i_enter_in_the_location_field(String location) {
        log.info("Entering location: {}", location);
        nhsPage.enterWhere(location);

    }

    @When("I click on the search button")
    public void i_click_on_the_search_button() {
        log.info("Clicking search button");
        nhsPage.clickSearch();

    }

    @Then("I should see a list of job results")
    public void i_should_see_a_list_of_job_results() {
        log.info("Waiting for results...");

        List<WebElement> results = nhsPage.getSearchResults();

        log.info("Results count: {}", results.size());

        Assert.assertTrue(results.size() > 0, "Expected job results but none found");


    }

    @Then("the results should be sorted by newest date posted")
    public void the_results_should_be_sorted_by_newest_date_posted() {
        List<WebElement> elements = nhsPage.getOpeningDates();

        List<Integer> days = new ArrayList<>();

        for (WebElement el : elements) {
            String text = el.getText();

            int d = text.toLowerCase().contains("today") ? 0 :
                    Integer.parseInt(text.replaceAll("[^0-9]", ""));

            days.add(d);
        }

        for (int i = 0; i < days.size() - 1; i++) {
            Assert.assertTrue(days.get(i) <= days.get(i + 1),
                    " Not sorted by newest date");
        }

    }

    @When("I select distance {string}")
    public void i_select_distance(String distance) {
        log.info("Selecting distance: {}", distance);
        nhsPage.selectDistance(distance);

    }

    @When("I click on moreSearchOptions")
    public void i_click_on_more_search_options() {
        log.info("Clicking More Search Options");
        nhsPage.clickMoreSearchOptions();
    }

    @When("I select pay range {string}")
    public void i_select_pay_range(String payRange) {
        log.info("Selecting pay range: {}", payRange);
        nhsPage.selectPayRange(payRange);

    }

    @When("I enter {string} in the employer field")
    public void i_enter_in_the_employer_field(String employer) {
        log.info("Entering employer: {}", employer);
        nhsPage.enterEmployer(employer);

    }

    @When("I enter {string} in the job reference field")
    public void i_enter_in_the_job_reference_field(String jobRef) {
        log.info("Entering job reference: {}", jobRef);
        nhsPage.enterJobReference(jobRef);

    }

    @When("I search for {string} jobs in {string}")
    public void i_search_for_jobs_in(String job, String location) {
        log.info("Performing combined search: {} in {}", job, location);
        nhsPage.enterWhat(job);
        nhsPage.enterWhere(location);

    }

    @Then("I should see message {string}")
    public void i_should_see_message(String expectedMessage) {
        String actualMessage = nhsPage.getNoResultMessage();

        log.info("Actual Message: {}", actualMessage);

        Assert.assertTrue(actualMessage.contains(expectedMessage), " 'No results' message not found");
    }

    @When("I perform search without entering any details")
    public void i_perform_search_without_entering_any_details() {
        log.info("Performing search without any input");
    }

    @Then("I should see default job results")
    public void i_should_see_default_job_results() {
        List<WebElement> results = nhsPage.getSearchResults();

        log.info("Validating default results");

        Assert.assertTrue(results.size() > 0, "Default results not displayed!");
    }

    @When("I click clear filters button")
    public void i_click_clear_filters_button() {
        log.info("Clicking Clear Filters button");
        nhsPage.clickClearFilters();

    }

    @Then("all fields should be empty")
    public void all_fields_should_be_empty() {
        log.info("Validating all fields are cleared");

        Assert.assertEquals(nhsPage.getWhatValue(), "", "What field NOT cleared");
        Assert.assertEquals(nhsPage.getWhereValue(), "", "Where field NOT cleared");

    }

    @Then("additional search fields should be visible")
    public void additional_search_fields_should_be_visible() {
        log.info("Validating additional fields visibility");

        Assert.assertTrue(nhsPage.isEmployerFieldDisplayed(), "Employer field not visible");
        Assert.assertTrue(nhsPage.isJobReferenceDisplayed(), "Job Reference field not visible");

    }

    @When("I click fewer search options")
    public void i_click_fewer_search_options() {
        log.info("Clicking Fewer Search Options");
        nhsPage.clickFewerSearchOptions();

    }

    @Then("only basic search fields should be visible")
    public void only_basic_search_fields_should_be_visible() {
        log.info("Validating basic fields only");

        Assert.assertFalse(nhsPage.isEmployerFieldDisplayed(), "Employer field still visible");
        Assert.assertFalse(nhsPage.isJobReferenceDisplayed(), "Job Reference still visible");

    }

    @When("I perform search for {string} in {string}")
    public void i_perform_search_for_in(String job, String location) {
        log.info("Performing search: {} in {}", job, location);
        nhsPage.enterWhat(job);
        nhsPage.enterWhere(location);
        nhsPage.clickSearch();

    }


}