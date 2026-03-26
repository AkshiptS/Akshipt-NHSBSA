package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.slf4j.Logger;
import utils.LoggerUtils;

import java.util.List;

public class NHSJobsSearchPage {

    private WebDriver driver;
    private static final Logger log = LoggerUtils.getLogger(NHSJobsSearchPage.class);


    // What (job title / skills)
    @FindBy(id = "keyword")
    private WebElement whatField;

    // Where (location)
    @FindBy(id = "location")
    private WebElement whereField;

    // Distance dropdown
    @FindBy(id = "distance")
    private WebElement distanceDropdown;


    // Job reference
    @FindBy(id = "jobReference")
    private WebElement jobReferenceField;

    // Employer
    @FindBy(id = "employer")
    private WebElement employerField;

    // Pay range dropdown
    @FindBy(id = "payRange")
    private WebElement payRangeDropdown;

    // Search button
    @FindBy(id = "search")
    private WebElement searchButton;

    // Clear filters button
    @FindBy(id = "clearFilters")
    private WebElement clearFiltersButton;

    // Fewer search options link
    @FindBy(linkText = "Fewer search options")
    private WebElement fewerSearchOptionsLink;

    // More search options
    @FindBy(linkText = "More search options")
    private WebElement moreSearchOptionsLink;

    // Search Outout
    @FindBy(xpath = "//li[@data-test=\'search-result\']")
    private List<WebElement> searchOutput;

    // opening date
    @FindBy(css = "li[data-test='search-result-publicationDate']")
    private List<WebElement> openingDates;

    // Message for no result found
    @FindBy(id = "search-results-heading")
    private WebElement message;


    public NHSJobsSearchPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void enterWhat(String keyword) {
        log.info("Entering What field: {}", keyword);
        whatField.clear();
        whatField.sendKeys(keyword);
    }

    public void enterWhere(String location) {
        log.info("Entering Where field: {}", location);
        whereField.clear();
        whereField.sendKeys(location);
    }

    public void enterJobReference(String ref) {
        log.info("Entering Job Reference: {}", ref);
        jobReferenceField.clear();
        jobReferenceField.sendKeys(ref);
    }

    public void enterEmployer(String employer) {
        log.info("Entering Employer: {}", employer);
        employerField.clear();
        employerField.sendKeys(employer);
    }

    public void clickSearch() {
        log.info("Clicking Search button");
        searchButton.click();
    }

    public void clickClearFilters() {
        log.info("Clicking Clear Filters button");
        clearFiltersButton.click();
    }

    public void clickFewerSearchOptions() {
        log.info("Clicking Fewer Search Options");
        fewerSearchOptionsLink.click();
    }

    public void clickMoreSearchOptions() {
        log.info("Clicking More Search Options");
        moreSearchOptionsLink.click();
    }

    public List<WebElement> getSearchResults() {
        log.info("Fetching search results");
        return searchOutput;
    }

    public List<WebElement> getOpeningDates() {
        log.info("Fetching opening dates");
        return openingDates;
    }

    public String getNoResultMessage() {
        log.info("Fetching no result message");
        return message.getText();
    }

    public void selectDistance(String distance) {
        log.info("Selecting distance: {}", distance);
        new Select(distanceDropdown).selectByVisibleText(distance);
    }

    public void selectPayRange(String pay) {
        log.info("Selecting pay range: {}", pay);
        new Select(payRangeDropdown).selectByVisibleText(pay);
    }

    public String getWhatValue() {
        return whatField.getAttribute("value");
    }

    public String getWhereValue() {
        return whereField.getAttribute("value");
    }

    public boolean isEmployerFieldDisplayed() {
        return employerField.isDisplayed();
    }

    public boolean isJobReferenceDisplayed() {
        return jobReferenceField.isDisplayed();
    }
}

