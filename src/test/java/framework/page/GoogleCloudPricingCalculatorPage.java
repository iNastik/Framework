package framework.page;

import framework.model.EstimateModel;
import framework.util.CustomConditions;
import framework.util.Helpers;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class GoogleCloudPricingCalculatorPage extends AbstractPage {
    private final Logger logger = LogManager.getRootLogger();
    private final String MD_OPTION_XPATH = "//md-option//div[contains(text(), '%s')]";
    private final String MD_SELECT_MENU_CONTAINER_XPATH = "//div[@class='md-select-menu-container md-active md-clickable']//div[contains(text(), '%s')]";

    @FindBy(xpath = "//*[@id='cloud-site']//iframe")
    private WebElement firstFrame;

    @FindBy(xpath = "//*[@id='myFrame']")
    private WebElement secondFrame;

    @FindBy(xpath = "//md-tab-item//div[text()='Compute Engine']")
    private WebElement computeEngineIcon;

    @FindBy(id = "input_60")
    private WebElement numberOfInstancesInput;

    @FindBy(id = "input_61")
    private WebElement instanceAssignmentField;

    @FindBy(id = "select_value_label_53")
    private WebElement operatingSystemField;

    @FindBy(id = "select_value_label_54")
    private WebElement machineClassField;

    @FindBy(id = "select_value_label_57")
    private WebElement machineTypeField;

    @FindBy(xpath = "//md-checkbox[@aria-label='Add GPUs']")
    private WebElement addGpusCheckbox;

    @FindBy(id = "select_value_label_370")
    private WebElement numberOfGpusField;

    @FindBy(id = "select_value_label_371")
    private WebElement gpuTypeField;

    @FindBy(id = "select_value_label_192")
    private WebElement localSsdField;

    @FindBy(id = "select_value_label_58")
    private WebElement datacenterLocationField;

    @FindBy(id = "select_value_label_59")
    private WebElement committedUsageField;

    @FindBy(xpath = "//form[@name='ComputeEngineForm']//button[contains(text(),'Add to Estimate')]")
    private WebElement addToEstimateButton;

    public GoogleCloudPricingCalculatorPage(WebDriver driver) {
        super(driver);
    }

    public GoogleCloudPricingCalculatorPage setParametersForEstimation(EstimateModel estimateModel) {
        CustomConditions.switchToInnerFrame(firstFrame, secondFrame, driver);

        activateTheComputeEngineSection();
        addNumberOfInstances(estimateModel.getNumberOfInstances());
        setInstancesAssignment(estimateModel.getInstancesAssignment());
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
        Helpers.clickOnClickableElement(numberOfInstancesInput, driver);
        numberOfInstancesInput.sendKeys(requiredNumberOfInstances);
    }

    public void setInstancesAssignment(String requiredInstanceAssignment) {
        Helpers.clickOnClickableElement(instanceAssignmentField, driver);
        instanceAssignmentField.sendKeys(requiredInstanceAssignment);
    }

    public void selectOperatingSystem(String requiredOperatingSystem) {
        Helpers.selectFromTheDropdownList(operatingSystemField, MD_OPTION_XPATH,
                requiredOperatingSystem, driver);
    }

    public void selectVirtualMachineClass(String requiredVirtualMachineClass) {
        Helpers.selectFromTheDropdownList(machineClassField, MD_SELECT_MENU_CONTAINER_XPATH,
                requiredVirtualMachineClass, driver);
    }

    public void selectMachineType(String requiredMachineType) {
        Helpers.selectFromTheDropdownList(machineTypeField, MD_OPTION_XPATH,
                requiredMachineType, driver);
    }

    public void addGpus(String valueOfAddGPUsCheckbox, String requiredNumberOfGPUs,
                        String requiredGpuType) {
        if (valueOfAddGPUsCheckbox.equals("true")) {
            Helpers.clickOnVisibleElement(addGpusCheckbox, driver);

            Helpers.selectFromTheDropdownList(numberOfGpusField, MD_SELECT_MENU_CONTAINER_XPATH,
                    requiredNumberOfGPUs, driver);

            Helpers.selectFromTheDropdownList(gpuTypeField, MD_OPTION_XPATH,
                    requiredGpuType, driver);
        }
    }

    public void selectLocalSsd(String requiredLocalSsd) {
        Helpers.selectFromTheDropdownList(localSsdField, MD_OPTION_XPATH,
                requiredLocalSsd, driver);
    }

    public void selectDatacenterLocation(String requiredDatacenterLocation) {
        Helpers.selectFromTheDropdownList(datacenterLocationField, MD_SELECT_MENU_CONTAINER_XPATH,
                requiredDatacenterLocation, driver);
    }

    public void selectCommittedUsage(String requiredCommittedUsage) {
        Helpers.selectFromTheDropdownList(committedUsageField, MD_SELECT_MENU_CONTAINER_XPATH,
                requiredCommittedUsage, driver);
    }

    public EstimatedPage addToEstimate() {
        Helpers.clickOnVisibleElement(addToEstimateButton, driver);
        logger.info("Parameters added to estimate");
        return new EstimatedPage(driver);
    }
}
