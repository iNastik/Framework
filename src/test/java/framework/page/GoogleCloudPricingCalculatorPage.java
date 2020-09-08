package framework.page;

import framework.model.EstimateModel;
import framework.util.CustomConditions;
import framework.util.Helpers;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class GoogleCloudPricingCalculatorPage extends AbstractPage {
    private final Logger logger = LogManager.getRootLogger();

    @FindBy(xpath = "//*[@id='cloud-site']//iframe")
    private WebElement firstFrame;

    @FindBy(xpath = "//*[@id='myFrame']")
    private WebElement secondFrame;

    @FindBy(xpath = "//md-tab-item//div[text()='Compute Engine']")
    private WebElement computeEngineIcon;

    @FindBy(id = "input_61")
    private WebElement numberOfInstancesInput;

    @FindBy(id = "select_value_label_54")
    private WebElement operatingSystemField;

    @FindBy(id = "select_value_label_55")
    private WebElement machineClassField;

    @FindBy(id = "select_value_label_58")
    private WebElement machineTypeField;

    @FindBy(xpath = "//md-checkbox[@aria-label='Add GPUs']")
    private WebElement addGpusCheckbox;

    @FindBy(id = "select_value_label_350")
    private WebElement numberOfGpusField;

    @FindBy(id = "select_354")
    private WebElement gpuTypeField;

    @FindBy(id = "select_value_label_184")
    private WebElement localSsdField;

    @FindBy(id = "select_value_label_59")
    private WebElement datacenterLocationField;

    @FindBy(id = "select_value_label_60")
    private WebElement committedUsageField;

    @FindBy(xpath = "//form[@name='ComputeEngineForm']//button[contains(text(),'Add to EstimateModel')]")
    private WebElement addToEstimateButton;

    public GoogleCloudPricingCalculatorPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(this.driver, this);
    }

    public GoogleCloudPricingCalculatorPage setParametersForEstimation(EstimateModel estimateModel) {
        CustomConditions.switchToInnerFrame(firstFrame, secondFrame, driver);

        activateTheComputeEngineSection();
        addNumberOfInstances(estimateModel.getNumberOfInstances());
        selectOperatingSystem(estimateModel.getOperatingSystem());
        selectVirtualMachineClass(estimateModel.getVirtualMachineClass());
        selectMachineType(estimateModel.getMachineType());
        addGpus(estimateModel.getValueOfAddGpuCheckbox(), estimateModel.getNumberOfGpus(), estimateModel.getGpuType());
        selectLocalSsd(estimateModel.getLocalSsd());
        selectDatacenterLocation(estimateModel.getDatacenterLocation());
        selectCommittedUsage(estimateModel.getCommittedUsage());
        return this;
    }

    public void activateTheComputeEngineSection() {
        Helpers.clickOnVisibleElement(computeEngineIcon, driver);
    }

    public void addNumberOfInstances(String requiredNumberOfInstances) {
        numberOfInstancesInput.click();
        numberOfInstancesInput.sendKeys(requiredNumberOfInstances);
    }

    public void selectOperatingSystem(String requiredOperatingSystem) {
        String requiredOperatingSystemXpath = "//md-option//div[contains(text(), '%s')]";
        Helpers.selectFromTheDropdownList(operatingSystemField, requiredOperatingSystemXpath,
                requiredOperatingSystem, driver);
    }

    public void selectVirtualMachineClass(String requiredVirtualMachineClass) {
        String requiredVirtualMachineClassXpath = "//md-option//div[contains(text(), '%s')]";
        Helpers.selectFromTheDropdownList(machineClassField, requiredVirtualMachineClassXpath,
                requiredVirtualMachineClass, driver);
    }

    public void selectMachineType(String requiredMachineType) {
        String requiredMachineTypeXpath = "//md-option//div[contains(text(), '%s')]";
        Helpers.selectFromTheDropdownList(machineTypeField, requiredMachineTypeXpath,
                requiredMachineType, driver);
    }

    public void addGpus(String valueOfAddGPUsCheckbox, String requiredNumberOfGPUs,
                                                    String requiredGpuType) {
        if (valueOfAddGPUsCheckbox.equals("true")) {
            Helpers.clickOnVisibleElement(addGpusCheckbox, driver);

            String requiredNumberOfGPUsXpath = "//div[@class='md-select-menu-container md-active md-clickable']" +
                    "//div[contains(text(),'%s')]";
            Helpers.selectFromTheDropdownList(numberOfGpusField, requiredNumberOfGPUsXpath,
                    requiredNumberOfGPUs, driver);

            String requiredGpuTypeXpath = "//md-option//div[contains(text(),'%s')]";
            Helpers.selectFromTheDropdownList(gpuTypeField, requiredGpuTypeXpath,
                    requiredGpuType, driver);
        }
    }

    public void selectLocalSsd(String requiredLocalSsd) {
        String requiredLocalSsdXpath = "//md-option//div[contains(text(),'%s')]";
        Helpers.selectFromTheDropdownList(localSsdField, requiredLocalSsdXpath,
                requiredLocalSsd, driver);
    }

    public void selectDatacenterLocation(String requiredDatacenterLocation) {
        String requiredDatacenterLocationXpath = "//div[@id='select_container_89']" + "//div[contains(text(),'%s')]";
        Helpers.selectFromTheDropdownList(datacenterLocationField, requiredDatacenterLocationXpath,
                requiredDatacenterLocation, driver);
    }

    public void selectCommittedUsage(String requiredCommittedUsage) {
        String requiredCommittedUsageXpath = "//div[@id='select_container_96']" + "//div[contains(text(),'%s')]";
        Helpers.selectFromTheDropdownList(committedUsageField, requiredCommittedUsageXpath,
                requiredCommittedUsage, driver);
    }

    public EstimatedPage addToEstimate() {
        Helpers.clickOnVisibleElement(addToEstimateButton, driver);
        logger.info("EstimateModel added to estimate");
        return new EstimatedPage(driver);
    }
}
