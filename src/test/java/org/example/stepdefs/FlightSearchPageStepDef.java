package org.example.stepdefs;


import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import org.example.pages.FlightSearchPage;
import org.openqa.selenium.WebDriver;


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
    public void closeSignInFrameIfPresent() throws InterruptedException {
        searchPage = new FlightSearchPage(driver);

        System.out.println("New instance of flightSearchPage is created");

        Boolean result = searchPage.isSignInFramePresent();
        if(result==true){

            System.out.println("Sign in Google is present");
            searchPage.closeSignInFrame();
        }
        else{
            System.out.println("sign in google not present");
        }

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

    }

