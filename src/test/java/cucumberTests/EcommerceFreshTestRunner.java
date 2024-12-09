package cucumberTests;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(features={"src/main/resources/features/FlutterApp.feature"},glue= "stepImplementations", publish = true)
public class EcommerceFreshTestRunner {
}
