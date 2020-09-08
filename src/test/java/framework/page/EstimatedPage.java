package framework.page;

import framework.model.EstimateModel;
import framework.util.Helpers;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class EstimatedPage extends AbstractPage {
//    @FindBy(xpath = "//div[contains(text(),'VM class:')]")
//    private WebElement virtualMachineClass;
//
//    @FindBy(xpath = "//div[contains(text(),'Instance type:')]")
//    private WebElement instanceType;
//
//    @FindBy(xpath = "//div[contains(text(),'Region')]")
//    private WebElement region;
//
//    @FindBy(xpath = "//div[contains(text(),'local SSD')]")
//    private WebElement localSsd;
//
//    @FindBy(xpath = "//div[contains(text(),'Commitment term')]")
//    private WebElement commitmentTerm;

    @FindBy(xpath = "//b[contains(text(),'Total Estimated Cost')]")
    private WebElement totalEstimatedCost;

    @FindBy(xpath = "//button[contains(text(),'Email EstimateModel')]")
    private WebElement emailEstimateButton;

    public EstimatedPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(this.driver, this);
    }

//    public String getEstimatedVirtualMachineClass() {
//        return virtualMachineClass.getText().toLowerCase();
//    }
//
//    public String getEstimatedInstanceType() {
//        return instanceType.getText().toLowerCase();
//    }
//
//    public String getEstimatedRegion() {
//        return region.getText().toLowerCase();
//    }
//
//    public String getEstimatedLocalSsd() {
//        return localSsd.getText().toLowerCase();
//    }
//
//    public String getEstimatedCommitmentTerm() {
//        return commitmentTerm.getText().toLowerCase();
//    }

//    public String getTotalEstimatedCost() {
//        return totalEstimatedCost.getText().toLowerCase();
//    }

    public EmailForm clickEmailEstimateButton() {
        Helpers.clickOnVisibleElement(emailEstimateButton, driver);
        return new EmailForm(driver);
    }

    public boolean isEstimatedCostEqualTo(EstimateModel estimateModel) {
        return totalEstimatedCost.getText().contains(estimateModel.getEstimatedCost());
    }
}
