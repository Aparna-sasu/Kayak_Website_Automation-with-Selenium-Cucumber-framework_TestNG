package org.example.pages;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.w3c.dom.NodeList;

import java.time.Duration;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

public class ResultPage {
    private static WebDriver driver;
    Boolean result;
    FlightSearchPage flightSearchPage;
    public ResultPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }


    public boolean  resultPageIsDisplayed() {
        System.out.println("inside resultPageIsDisplayed");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        System.out.println(driver.getTitle());


        if(driver.getTitle().contains("KAYAK"))
        {
            result=true;
        }
        System.out.println(result);


        return result;

    }

    public void  originPlaceIsSameAsFromPlace(String originPlace) throws InterruptedException {


        System.out.println("Inside originPlace method");
        Set<String> windowHandles = driver.getWindowHandles();

        for (String windowHandle : windowHandles) {
            driver.switchTo().window(windowHandle);
        }

        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        if (isPageLoaded(driver)) {
            System.out.println("Page is fully loaded.");
            String script = "return document.evaluate(\"//div[@class='btf6']/child::div[1]//span/div[text()='Best']\", document, null, XPathResult.FIRST_ORDERED_NODE_TYPE, null).singleNodeValue;";
            WebElement element = (WebElement) jsExecutor.executeScript(script);
            element.click();
        } else {
            System.out.println("Page is still loading.");
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(80));
            String script = "return document.evaluate(\"\", document, null, XPathResult.FIRST_ORDERED_NODE_TYPE, null).singleNodeValue;";
            WebElement element = (WebElement) jsExecutor.executeScript(script);
            element.click();
        }


        // WebElement bestDeal = driver.findElement(By.xpath("//div[@class='btf6']/child::div[1]//span/div[text()='Best']"));
        // wait.until(ExpectedConditions.elementToBeClickable(bestDeal));
        // JavascriptExecutor js = (JavascriptExecutor) driver;
        //js.executeScript("arguments[0].click();", bestDeal);


    }
    public static boolean isPageLoaded(WebDriver driver) {
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        // Check if the document.readyState is 'complete'
        return jsExecutor.executeScript("return document.readyState").equals("complete");
    }



}


