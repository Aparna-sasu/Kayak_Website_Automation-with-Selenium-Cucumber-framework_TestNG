package org.example.pages;


import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.sql.ClientInfoStatus;
import java.time.Duration;
import java.util.List;

public class FlightSearchPage {
    private WebDriver driver;


    @FindBy(xpath = "//input[@aria-label = 'Flight origin input']")
    WebElement originPlace;
    @FindBy(xpath = "//input[@placeholder ='To?']")
    WebElement destinationPlace;
    @FindBy(xpath = "//iframe[@title='Sign in with Google Dialog']")
    WebElement iFrame;

    @FindBy(xpath = "//span[text()='Sign in with Google']")
    WebElement signInText;
    @FindBy(xpath = "//div[@id='animated-container']/div/child::div[1]/child::div[2]")
    WebElement closeIcon;

    @FindBy(xpath = "//div[contains(@id,'credential_picker_container')]")
    WebElement parentDiv;

    @FindBy(xpath = "//div[@class = 'sR_k sR_k-mod-size-mcfly sR_k-mod-radius-base sR_k-pres-mcfly sR_k-mod-variant-inline sR_k-mod-responsive']/div[1]")
    WebElement departureDate;

    @FindBy(xpath = "//div[@class = 'sR_k sR_k-mod-size-mcfly sR_k-mod-radius-base sR_k-pres-mcfly sR_k-mod-variant-inline sR_k-mod-responsive']/div[3]")
    WebElement returnDate;

    @FindBy(xpath = "//button[@aria-label='Search']")
    WebElement searchButton;


    String citiesName;
    String citiesNameDest;
    public FlightSearchPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void isSignInFramePresent() {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(@id,'credential_picker_container')]")));
            driver.switchTo().frame(parentDiv.findElement(By.tagName("iframe")));
            System.out.println("inside iframe");
            Thread.sleep(5000);
            String text = driver.findElement(By.xpath("//h1/span[text()='Sign in with Google']")).getText();
            System.out.println(text);
            if (text.equals("Sign in with Google")) {
                closeSignInFrame();
            }

        } catch (NoSuchElementException | InterruptedException e) {
            // The frame is not present
            e.printStackTrace();
            System.out.println("Frame or signInFrame not present");
        }
    }

    public void closeSignInFrame() {

        JavascriptExecutor js = (JavascriptExecutor) driver;
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='animated-container']/div/child::div[1]/child::div[2]")));
        js.executeScript("arguments[0].click();", closeIcon);
        driver.switchTo().defaultContent();


    }


    public void enterOriginAndDestination(String fromPlace, String toPlace) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@aria-label = 'Flight origin input']")));
        WebElement remove = driver.findElement(By.xpath("//div[@class='zEiP-formField zEiP-origin']//div[contains(@class,'close')]"));
        remove.click();
        originPlace.sendKeys(fromPlace);

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//ul[@id='flight-origin-smarty-input-list']")));
        WebElement dropDown = driver.findElement(By.xpath("//ul[@id='flight-origin-smarty-input-list']"));
        List<WebElement> selectOriginPlace = dropDown.findElements(By.tagName("li"));

        for (WebElement cities : selectOriginPlace) {
            System.out.println(cities.getAttribute("id"));
            citiesName = cities.getAttribute("id");

            if (citiesName.contains(fromPlace)) {
                cities.click();
                break;
            }
        }

        destinationPlace.sendKeys(toPlace);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//ul[@id='flight-destination-smarty-input-list']")));
        WebElement dropDown1 = driver.findElement(By.xpath("//ul[@id='flight-destination-smarty-input-list']"));
        List<WebElement> selectDestinationPlace = dropDown1.findElements(By.tagName("li"));

        for (WebElement cities : selectDestinationPlace) {
            System.out.println(cities.getAttribute("id"));
            citiesNameDest = cities.getAttribute("id");

            if (citiesNameDest.contains(toPlace)) {
                cities.click();
                break;
            }
        }
    }


    public void enterDepartureAndReturnDates() {
        departureDate.click();

        WebElement dataMonthSelector = driver.findElement(By.xpath("//div[@data-month='2024-02']"));
        dataMonthSelector.findElement(By.xpath("//div[@aria-label='Thursday February 1, 2024']")).click();

        returnDate.click();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@aria-label='Saturday February 10, 2024']")));
        WebElement datePicker = dataMonthSelector.findElement(By.xpath("//div[@aria-label='Saturday February 10, 2024']"));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", datePicker);
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", datePicker);
        //datePicker.click();

    }

    public void clickSearchButton() {

        searchButton.click();

    }

    public boolean  resultPageIsDisplayed() {
        System.out.println("inside resultPageIsDisplayed");
       WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//a[@aria-label='Search for flights ']")));

        WebElement body = driver.findElement(By.xpath("//a[@aria-label='Search for flights ']"));
        System.out.println(body.getText());

        return body.isDisplayed();


    }

    public void  originPlaceIsSameAsFromPlace(String originPlace) {

     // WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        FluentWait<WebDriver> wait = new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(10))
                .pollingEvery(Duration.ofMillis(100))
                .ignoring(NoSuchElementException.class);
      WebElement bestFlight=  wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@class='btf6'] //div[text()='Best']")));

     //  WebElement bestFlight = driver.findElement(By.xpath("//div[@class='btf6'] //div[text()='Best']"));



       System.out.println(bestFlight);
       // JavascriptExecutor js = (JavascriptExecutor) driver;
        //js.executeScript("arguments[0].click();",bestFlight);






    }

}