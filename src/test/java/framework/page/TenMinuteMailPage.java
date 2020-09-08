package framework.page;

import framework.model.EstimateModel;
import framework.util.CustomConditions;
import framework.util.Helpers;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.ArrayList;

public class TenMinuteMailPage extends AbstractPage {
    private static final String TEN_MINUTE_MAIL_PAGE_URL = "https://10minutemail.com";

    @FindBy(id = "mail_address")
    private WebElement emailAddress;

    @FindBy(xpath = "//*[@id='mail_messages_content']" + "//*[contains(text(), 'Google Cloud Platform Price EstimateModel')]")
    private WebElement emailMessage;

    @FindBy(xpath = "//h3[contains(text(),'USD')]")
    private WebElement totalCost;

    public TenMinuteMailPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(this.driver, this);
    }

    public TenMinuteMailPage openPage() {
        driver.get(TEN_MINUTE_MAIL_PAGE_URL);
        CustomConditions.waitForVisibilityOf(emailAddress, driver);
        return this;
    }

    public TenMinuteMailPage copyEmail() {
        emailAddress.sendKeys(Keys.chord(Keys.CONTROL, "c"));
        return this;
    }

    public boolean isTotalEstimatedCostFromEmailEqualToExpectedValue(EstimateModel estimateModel) {
        ArrayList<String> newWindowsSet = new ArrayList<>(driver.getWindowHandles());
        driver.switchTo().window(newWindowsSet.get(1));

        Helpers.clickOnVisibleElement(emailMessage, driver);
        CustomConditions.waitForVisibilityOf(totalCost, driver);
        return totalCost.getText().contains(estimateModel.getEstimatedCost());
    }
}
