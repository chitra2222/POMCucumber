package TestCases;


import org.testng.annotations.Listeners;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;


@Listeners(Utility.MyTestNGListeners.class)
@CucumberOptions(
		features = {"src/test/resources/SalesForceFeature.feature"},
		glue = {}
		)
public class RunnerForLogin extends AbstractTestNGCucumberTests {

}
