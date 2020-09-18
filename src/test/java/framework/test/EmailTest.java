package framework.test;

import framework.model.EstimateModel;
import framework.page.GoogleCloudHomePage;
import framework.service.EstimateModelCreator;
import org.testng.Assert;
import org.testng.annotations.Test;

public class EmailTest extends CommonConditions {
    @Test
    public void checkIfCurrentEstimatedCostMatchesCostFromEmail() {
        EstimateModel estimateModel = EstimateModelCreator.createEstimateWithDataFromProperty();
        boolean expectedValue = new GoogleCloudHomePage(driver)
                .openPage()
                .searchForGoogleCloudPlatformPricingCalculator()
                .switchToCalculator()
                .setParametersForEstimation(estimateModel)
                .addToEstimate()
                .clickEmailEstimateButton()
                .sendEmail()
                .isTotalEstimatedCostFromEmailEqualToExpectedValue(estimateModel);

        Assert.assertTrue(expectedValue,
                "Total estimated monthly cost received in email is not equal to manual test result!");
    }
}
