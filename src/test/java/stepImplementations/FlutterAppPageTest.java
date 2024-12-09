/**

 * This class calls all the feature tests for Flutter Web App

 * @author NNouman

 */
package stepImplementations;

import io.cucumber.java.Scenario;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import org.junit.Test;
import org.openqa.selenium.*;
import pages.FlutterAppPage;
import java.time.Duration;

public class FlutterAppPageTest {

    WebDriver driver;
    FlutterAppPage flutterAppPage;
    String landingPage = "https://flutter-angular.web.app/";

    @Before
    public void setup() {
        driver = utilities.DriverFactory.open();
        driver.manage().window().maximize();
        flutterAppPage = new FlutterAppPage(driver);
    }

    @Given("user lands on flutter web app landing page")
    public void user_lands_on_flutter_web_app_landing_page() {
        driver.get(landingPage);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
    }

    @When("user clicks on increment button")
    public void user_clicks_on_increment_button() { flutterAppPage.clickIncrementButton(); }

    @Test
    @Then("counter increases by one")
    public void counter_increases_by_one() { flutterAppPage.verifyCounterIsIncreased(); }

    @After
    public void TearDown(Scenario scenario) {

        final byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
        scenario.attach(screenshot, "image/png", "screenshots for Flutter Web App tests");
        driver.quit();

    }
}
