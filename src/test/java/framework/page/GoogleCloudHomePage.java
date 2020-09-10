package framework.page;

import framework.util.CustomConditions;
import framework.util.Helpers;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class GoogleCloudHomePage extends AbstractPage {
    private static final String BASE_URL = "https://cloud.google.com/";
    private static final String SEARCH_QUERY = "Google Cloud Platform Pricing Calculator";
    private final Logger logger = LogManager.getRootLogger();

    @FindBy(xpath = "//input[@name='q']")
    private WebElement searchInput;

    @FindBy(xpath = "//b[contains(text(),'Google Cloud Platform Pricing Calculator')]")
    private WebElement searchResult;

    @FindBy(xpath = "//*[@id='input_60']")
    private WebElement numberOfInstancesField;


    public GoogleCloudHomePage(WebDriver driver) {
        super(driver);
    }

    public GoogleCloudHomePage openPage() {
        driver.get(BASE_URL);
        CustomConditions.waitForVisibilityOf(searchInput, driver);
        logger.info("Google Cloud Home Page opened");
        return this;
    }

    public GoogleCloudSearchPage searchForGoogleCloudPlatformPricingCalculator() {
        Helpers.clickOnVisibleElement(searchInput, driver);
        searchInput.sendKeys(SEARCH_QUERY + Keys.ENTER);
        return new GoogleCloudSearchPage(driver);
    }
}
