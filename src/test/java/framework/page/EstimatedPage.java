package framework.page;

import framework.model.EstimateModel;
import framework.util.Helpers;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class EstimatedPage extends AbstractPage {

    @FindBy(xpath = "//b[contains(text(),'Total Estimated Cost')]")
    private WebElement totalEstimatedCost;

    @FindBy(xpath = "//button[contains(text(),'Email Estimate')]")
    private WebElement emailEstimateButton;

    public EstimatedPage(WebDriver driver) {
        super(driver);
    }

    public EmailForm clickEmailEstimateButton() {
        Helpers.clickOnVisibleElement(emailEstimateButton, driver);
        return new EmailForm(driver);
    }

    public boolean isEstimatedCostEqualTo(EstimateModel estimateModel) {
        return totalEstimatedCost.getText().contains(estimateModel.getEstimatedCost());
    }
}
