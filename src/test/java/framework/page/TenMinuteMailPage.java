package framework.page;

import framework.model.EstimateModel;
import framework.util.CustomConditions;
import framework.util.Helpers;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.ArrayList;

public class TenMinuteMailPage extends AbstractPage {
    private static final String BASE_URL = "https://10minutemail.com";
    private final Logger logger = LogManager.getRootLogger();

    @FindBy(id = "mail_address")
    private WebElement emailAddress;

    @FindBy(xpath = "//*[@id='mail_messages_content']" + "//*[contains(text(), 'Google Cloud Platform Price Estimate')]")
    private WebElement emailMessage;

    @FindBy(xpath = "//h3[contains(text(),'USD')]")
    private WebElement totalCost;

    public TenMinuteMailPage(WebDriver driver) {
        super(driver);
    }

    public TenMinuteMailPage openPage() {
        driver.get(BASE_URL);
        CustomConditions.waitForVisibilityOf(emailAddress, driver);
        logger.info("Ten Minute Mail Page opened");
        return this;
    }

    public void copyEmail() {
        emailAddress.sendKeys(Keys.chord(Keys.CONTROL, "c"));
    }

    public boolean isTotalEstimatedCostFromEmailEqualToExpectedValue(EstimateModel estimateModel) {
        ArrayList<String> newWindowsSet = new ArrayList<>(driver.getWindowHandles());
        driver.switchTo().window(newWindowsSet.get(1));

        Helpers.clickOnVisibleElement(emailMessage, driver);
        CustomConditions.waitForVisibilityOf(totalCost, driver);
        return totalCost.getText().contains(estimateModel.getEstimatedCost());
    }
}
