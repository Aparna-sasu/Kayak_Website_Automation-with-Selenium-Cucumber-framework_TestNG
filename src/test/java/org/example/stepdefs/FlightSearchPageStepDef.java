package org.example.stepdefs;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.example.pages.FlightSearchPage;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import static org.example.support.TestContext.getDriver;

public class FlightSearchPageStepDef {
    private static WebDriver driver;
    private FlightSearchPage searchPage;


   public FlightSearchPageStepDef(){
    this.driver = getDriver();

}
    @Given("I open url {string}")
    public void iOpenUrl(String url) {
        driver.get(url);
        driver.manage().window().maximize();
    }

    @Given("I close the Sign in frame if present")
    public void closeSignInFrameIfPresent() {
        searchPage = new FlightSearchPage(driver);
        System.out.println("New instance of flightSearchPage is created");
        searchPage.isSignInFramePresent();

        }

        @Given("I have selected {string} and {string} cities")
        public void iEnterOriginAndDestination (String fromPlace, String toPlace){

            searchPage.enterOriginAndDestination(fromPlace, toPlace);


        }

        @Given("I have selected Departure and Return Dates")
    public void iEnterDepartureAndReturnDates(){
           searchPage.enterDepartureAndReturnDates();
        }

        @When("I click on Search button")
        public void iClickSearchButton(){
            searchPage.clickSearchButton();
        }

        @Then("I should be redirected to Result Page")

        public void verifyResultPageDisplayed(){
            Boolean flightResults = searchPage.resultPageIsDisplayed();
            Assert.assertTrue(flightResults,"Expected page is displayed");

        }

        @And("the displayed Origin place on the Result Page should be {string}")
        public void verifyOriginPlaceIsSameAsFromPlace(String originPlace){
                   searchPage.originPlaceIsSameAsFromPlace(originPlace);

        }

        @And("the displayed Destination place on the Result Page should be {string}")
        public void verifyDestinationPlaceIsSameAsToPlace(String destinationPlace){


        }
    }

