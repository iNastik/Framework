package framework.util;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class Helpers {
    public static void clickOnVisibleElement(WebElement element, WebDriver driver) {
        CustomConditions.waitForVisibilityOf(element, driver);
//        element.click();
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
    }

    public static void clickOnVisibleElement(By requiredOptionLocator, WebDriver driver) {
        WebElement requiredOption = CustomConditions
                .waitForVisibilityOfElementLocated(requiredOptionLocator, driver);
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", requiredOption);
    }

    public static void selectFromTheDropdownList(WebElement dropdownList, String requiredOptionXpath,
                                                 String requiredOption, WebDriver driver) {
//        clickOnTheVisibleElement(dropdownList, driver);
////        clickOnTheClickableElement(requiredOption, driver);
        clickOnVisibleElement(dropdownList, driver);
        By requiredOptionLocator = By.xpath(String.format(requiredOptionXpath, requiredOption));
        clickOnVisibleElement(requiredOptionLocator, driver);
    }
}
