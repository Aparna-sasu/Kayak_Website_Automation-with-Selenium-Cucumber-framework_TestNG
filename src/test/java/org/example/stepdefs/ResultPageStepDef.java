package org.example.stepdefs;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import org.example.pages.FlightSearchPage;
import org.example.pages.ResultPage;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import static org.example.support.TestContext.getDriver;

public class ResultPageStepDef {


    private static WebDriver driver;
    private ResultPage resultPage;

    public ResultPageStepDef(){
        this.driver = getDriver();
    }

    @Then("I should be redirected to Result Page")
    public void verifyResultPageDisplayed(){
        resultPage = new ResultPage(driver);
        Boolean flightResults = resultPage.resultPageIsDisplayed();
        Assert.assertTrue(flightResults,"Expected page is displayed");

    }

    @And("the displayed Origin place on the Result Page should be {string}")
    public void verifyOriginPlaceIsSameAsFromPlace(String originPlace) throws InterruptedException {
        resultPage.originPlaceIsSameAsFromPlace(originPlace);

    }

    @And("the displayed Destination place on the Result Page should be {string}")
    public void verifyDestinationPlaceIsSameAsToPlace(String destinationPlace){


    }
}
