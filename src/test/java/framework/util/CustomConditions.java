package framework.util;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CustomConditions {
    public static void switchToInnerFrame(WebElement firstFrame, WebElement secondFrame, WebDriver driver) {
        switchToAvailableFrame(firstFrame, driver);
        switchToAvailableFrame(secondFrame, driver);
    }

    private static void switchToAvailableFrame(WebElement frame, WebDriver driver) {
        new WebDriverWait(driver, 15)
                .until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(frame));
    }

    public static void waitForVisibilityOf(WebElement element, WebDriver driver) {
        new WebDriverWait(driver, 20).until(ExpectedConditions.visibilityOf(element));
    }

    public static WebElement waitForVisibilityOfElementLocated(By locator, WebDriver driver) {
        return new WebDriverWait(driver, 20)
                .until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    public static WebElement waitForElementToBeClickable(WebElement element, WebDriver driver) {
        new WebDriverWait(driver, 15)
                .until(ExpectedConditions.elementToBeClickable(element));
        return element;
    }

}
