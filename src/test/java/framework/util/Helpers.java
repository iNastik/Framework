package framework.util;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class Helpers {
    public static void clickOnVisibleElement(WebElement element, WebDriver driver) {
        CustomConditions.waitForVisibilityOf(element, driver);
        element.click();
    }

    public static void clickOnVisibleElement(By requiredOptionLocator, WebDriver driver) {
        WebElement requiredOption = CustomConditions
                .waitForVisibilityOfElementLocated(requiredOptionLocator, driver);
        requiredOption.click();
    }

    public static void clickOnClickableElement(WebElement element, WebDriver driver) {
        CustomConditions.waitForElementToBeClickable(element, driver);
        element.click();
    }

    public static void selectFromTheDropdownList(WebElement dropdownList, String requiredOptionXpath,
                                                 String requiredOption, WebDriver driver) {
        clickOnClickableElement(dropdownList, driver);
        By requiredOptionLocator = By.xpath(String.format(requiredOptionXpath, requiredOption));
        clickOnVisibleElement(requiredOptionLocator, driver);
    }
}
