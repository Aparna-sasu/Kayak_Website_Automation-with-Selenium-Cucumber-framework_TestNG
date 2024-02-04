package org.example.pages;

import org.apache.commons.lang3.tuple.Pair;
import org.openqa.selenium.*;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.Set;

public class ResultPage {
    private static WebDriver driver;
    Boolean result;
    FlightSearchPage flightSearchPage;

    public ResultPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }


    public boolean resultPageIsDisplayed() {
        System.out.println("inside resultPageIsDisplayed");
        System.out.println("Current handle after clicking search button:" + driver.getWindowHandle());
        String currentHandle = driver.getWindowHandle();
        Set<String> AllHandles = driver.getWindowHandles();
        for (String newHandle : AllHandles) {
            System.out.println(newHandle);
            if (!newHandle.equals(currentHandle)) {
                driver.switchTo().window(newHandle);
            }
        }
        System.out.println("WindowHandle after switching: " + driver.getWindowHandle());

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        ((JavascriptExecutor) driver).executeScript("window.focus();");

        wait.until(webDriver -> !webDriver.getCurrentUrl().equals("about:blank"));


        System.out.println("Current URL in new tab: " + driver.getCurrentUrl());


        System.out.println(driver.getTitle());


        if (driver.getTitle().contains("to")) {
            result = true;
        }

        System.out.println(result);

        return result;

    }

    public Pair<String, String> getOriginAndDestination() {
        String destinationPlace = null;
        String originPlace = null;
        List<WebElement> detailedResult = null;
        List<WebElement> detailedResult1 = null;

        System.out.println("Inside originPlace method");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        List<WebElement> bestDeal = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(".//span[@class='btf6-badge-wrap']")));
        if (!bestDeal.isEmpty()) {
            System.out.println("page loaded");
            for (int i = 0; i < bestDeal.size(); i++) {
                System.out.println(bestDeal.get(i).getText());
                String flightResult = bestDeal.get(i).getText();
                if (flightResult.equals("Best")) {



                    bestDeal.get(i).click();
                    detailedResult = driver.findElements(By.xpath("//div[@class='o-C7-section']//div[@class='o-C7-leg-outer'][1]//div[@class='X3K_-segments']//span[@class='g16k-station']"));
                    System.out.println(detailedResult.get(0).getText());
                    detailedResult1 = driver.findElements(By.xpath("//div[@class='o-C7-section']//div[@class='o-C7-leg-outer'][2]//div[@class='X3K_-segments']//span[@class='g16k-station']"));
                    System.out.println(detailedResult1.get(0).getText());

                }

                originPlace = detailedResult.get(0).getText();
                destinationPlace = detailedResult1.get(0).getText();
                break;
            }
        }

        return Pair.of(originPlace,destinationPlace);

    }

}


