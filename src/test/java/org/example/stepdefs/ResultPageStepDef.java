package org.example.stepdefs;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import org.apache.commons.lang3.tuple.Pair;
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
        boolean flightResults = resultPage.resultPageIsDisplayed();
        Assert.assertTrue(flightResults,"Expected page is displayed");

    }

    @And("the displayed Origin place and Destination place on the Result Page should be {string} and {string}")
    public void verifyOriginAndDestinationPlace(String originPlace, String destPlace)  {
       Pair<String ,String> originAndDestination = resultPage.getOriginAndDestination();
       Assert.assertTrue(originAndDestination.getLeft().contains(originPlace));
      Assert.assertTrue(originAndDestination.getRight().contains(destPlace));

    }


}
