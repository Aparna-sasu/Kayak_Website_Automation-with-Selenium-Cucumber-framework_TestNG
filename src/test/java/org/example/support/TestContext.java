package org.example.support;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class TestContext {
    private static WebDriver driver;

    public static WebDriver getDriver() {
        return driver;

    }

    public static void setUp() {
        System.out.println("Comes first here to setup method in TestContext class");
       WebDriverManager.firefoxdriver().setup();
       driver = new FirefoxDriver();
       System.out.println("Creates new driver instance");

    }



    public static void teardown() {
        System.out.println("Comes last here to teardown method in TestContext class");
        driver.quit();
    }

}
