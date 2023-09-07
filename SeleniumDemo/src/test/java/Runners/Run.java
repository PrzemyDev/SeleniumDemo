package Runners;

import io.cucumber.java.Before;
import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.BeforeClass;

@CucumberOptions(
        features = "src/test/resources/CucumberTestOne.feature",
        glue = {"StepDefinitions"}

)

public class Run extends AbstractTestNGCucumberTests {

}
