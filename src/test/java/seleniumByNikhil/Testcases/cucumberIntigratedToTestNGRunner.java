package seleniumByNikhil.Testcases;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(features ="src/main/java/objectRepository",glue = "endToEndFramework.StepDefination",monochrome = true,
plugin = {"html:target/cucumber.html"} ,tags = "@Reggresion")

public class cucumberIntigratedToTestNGRunner extends AbstractTestNGCucumberTests {

}
