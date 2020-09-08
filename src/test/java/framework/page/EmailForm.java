package framework.page;

import framework.util.Helpers;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import framework.util.CustomConditions;

import java.util.ArrayList;

public class EmailForm extends AbstractPage {
    private final Logger logger = LogManager.getRootLogger();
    @FindBy(id = "input_419")
    private WebElement emailInput;

    @FindBy(xpath = "//button[contains(text(),'Send Email')]")
    private WebElement sendEmailButton;

    @FindBy(xpath = "//*[@id='cloud-site']//iframe")
    private WebElement firstFrame;

    @FindBy(xpath = "//*[@id='myFrame']")
    private WebElement secondFrame;

    public EmailForm(WebDriver driver) {
        super(driver);
    }

    public TenMinuteMailPage sendEmail() {
        copyGeneratedEmailFromTenMinuteMailPage();
        CustomConditions.waitForElementToBeClickable(emailInput, driver);
        emailInput.sendKeys(Keys.chord(Keys.CONTROL, "v") + Keys.ENTER);
        Helpers.clickOnVisibleElement(sendEmailButton, driver);
        logger.info("Email has been sent");
        return new TenMinuteMailPage(driver);
    }

    private void copyGeneratedEmailFromTenMinuteMailPage() {
        ((JavascriptExecutor) driver).executeScript("window.open()");

        ArrayList<String> tabs = new ArrayList<>(driver.getWindowHandles());
        driver.switchTo().window(tabs.get(1));
        new TenMinuteMailPage(driver).openPage().copyEmail();

        driver.switchTo().window(tabs.get(0));
        CustomConditions.switchToInnerFrame(firstFrame, secondFrame, driver);
    }
}
