package com.bdd.cocumber.bdd;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources/features" ,  // Path to your feature files
        glue = {"com.bdd.cocumber.bdd"},        // Path to your step definitions
        plugin = {"pretty", "html:target/cucumber-reports.html"}
)

public class CucumberTestRunner {
}
