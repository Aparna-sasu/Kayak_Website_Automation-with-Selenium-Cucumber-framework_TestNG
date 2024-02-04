package org.example.support;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;

public class TestContext {
    private static WebDriver driver;

    public static WebDriver getDriver() {
        return driver;

    }

    public static void setUp() {
        System.out.println("Comes first here to setup method in TestContext class");
       WebDriverManager.firefoxdriver().setup();
       driver = new FirefoxDriver();
        //WebDriverManager.chromedriver().setup();
       //driver = new ChromeDriver();
       System.out.println("Creates new driver instance");

    }



    public static void teardown() {
        System.out.println("Comes last here to teardown method in TestContext class");
        driver.quit();
    }

}
