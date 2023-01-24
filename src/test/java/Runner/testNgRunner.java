package Runner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
features="src/test/java/features",
glue="Stepdefinitions"
)
public class testNgRunner extends AbstractTestNGCucumberTests{

}
