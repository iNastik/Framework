package framework.page;

import framework.util.Helpers;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class GoogleCloudSearchPage extends AbstractPage {
    private final Logger logger = LogManager.getRootLogger();

    @FindBy(xpath = "//b[contains(text(),'Google Cloud Platform Pricing Calculator')]")
    private WebElement searchResult;

    public GoogleCloudSearchPage(WebDriver driver) {
        super(driver);
    }

    public GoogleCloudPricingCalculatorPage switchToCalculator() {
        Helpers.clickOnVisibleElement(searchResult, driver);
        logger.info("Pricing Calculator Page opened");
        return new GoogleCloudPricingCalculatorPage(driver);
    }
}
