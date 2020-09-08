package framework.test;

import framework.model.EstimateModel;
import framework.page.GoogleCloudHomePage;
import framework.service.EstimateModelCreator;
import org.testng.Assert;
import org.testng.annotations.Test;

public class PricingCalculatorTest extends CommonConditions {

    @Test
    public void checkIfTheCurrentEstimatedCostMatchesTheCostFromEmail() {
        EstimateModel estimateModel = EstimateModelCreator.createEstimateWithDataFromProperty();
        boolean expectedValue = new GoogleCloudHomePage(driver)
                .openPage()
                .searchForGoogleCloudPlatformPricingCalculator()
                .switchToCalculator()
                .setParametersForEstimation(estimateModel)
                .addToEstimate()
                .isEstimatedCostEqualTo(estimateModel);

        Assert.assertTrue(expectedValue,
                "Total estimated monthly cost received on Pricing Calculator page is not equal to manual test result!");
    }
}
