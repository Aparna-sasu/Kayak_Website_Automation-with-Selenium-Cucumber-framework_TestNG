package org.example.pages;


import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.sql.ClientInfoStatus;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.List;
import java.util.Set;

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

    @FindBy(xpath = "//span[@aria-label = 'Start date calendar input']")
    WebElement departureDate;

    @FindBy(xpath = "//div[@class = 'sR_k sR_k-mod-size-mcfly sR_k-mod-radius-base sR_k-pres-mcfly sR_k-mod-variant-inline sR_k-mod-responsive']/div[3]")
    WebElement returnDate;

    @FindBy(xpath = "//button[@aria-label='Search']")
    WebElement searchButton;


    String citiesName;
    String citiesNameDest;
    Boolean result;


    public FlightSearchPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public Boolean isSignInFramePresent() throws InterruptedException {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            WebElement signGoogle = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(@id,'credential_picker_container')]")));
            System.out.println(signGoogle.isDisplayed());
            //if (signGoogle.isDisplayed()) {
            driver.switchTo().frame(parentDiv.findElement(By.tagName("iframe")));
            System.out.println("inside iframe");
            Thread.sleep(5000);
            String text = driver.findElement(By.xpath("//h1/span[text()='Sign in with Google']")).getText();
            System.out.println(text);
            if (text.equals("Sign in with Google")) {
                //closeSignInFrame();
                result = true;
            }
        } catch (Exception e) {
            // The frame is not present

            result = false;
            System.out.println("Frame or signInFrame not present");
        }
        return result;
    }

    public void closeSignInFrame() {

        JavascriptExecutor js = (JavascriptExecutor) driver;
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='animated-container']/div/child::div[1]/child::div[2] | //div[@aria-label = 'Remove']")));
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
        //String month = departureMonth;
        departureDate.click();
        System.out.println("clicked departure field");
        //Thread.sleep(1000);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement monthToBeSelected = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='ATGJ-monthWrapper']")));
        WebElement datePicker = monthToBeSelected.findElement(By.xpath("//div[@aria-label='Monday March 4, 2024']"));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", datePicker);
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", datePicker);
        System.out.println("Selected departure date");

        returnDate.click();

        System.out.println("clicked return date");
        WebElement returnMonthToBeSelected = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[@aria-label='End date calendar input']")));
        WebElement datePicker1 = returnMonthToBeSelected.findElement(By.xpath("//div[@aria-label='Sunday March 10, 2024']"));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", datePicker1);
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", datePicker1);

        System.out.println("Selected return date");
    }


    public void clickSearchButton() {

        boolean result = false;
        if (driver instanceof ChromeDriver) {
            Actions actions = new Actions(driver);
            actions.moveToElement(searchButton).click().perform();
        } else {
            System.out.println(driver.getWindowHandle());
            System.out.println("title of current page: " + driver.getTitle());
            searchButton.click();

        }
    }
}





