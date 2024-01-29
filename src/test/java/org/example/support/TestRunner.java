package org.example.support;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.Test;

@CucumberOptions(
        plugin = {"pretty", "html:target/report.html", "json:target/report.json"},
        features = "src/test/resources/features",
        glue = {"org.example.stepdefs", "org.example.support"}
)

@Test
public class TestRunner extends AbstractTestNGCucumberTests {

}
