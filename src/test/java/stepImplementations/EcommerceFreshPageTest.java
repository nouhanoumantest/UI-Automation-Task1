/**

 * This class calls all the feature tests for Ecommerce Fresh Website

 * @author NNouman

 */
package stepImplementations;

import io.cucumber.java.Scenario;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.java.Before;
import io.cucumber.java.After;
import org.junit.Test;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import pages.EcommerceFreshPage;
import java.time.Duration;

public class EcommerceFreshPageTest {

    WebDriver driver;
    EcommerceFreshPage ecommerceFreshPage;
    String landingPage = "https://e-commerce-kib.netlify.app/";

    @Before
    public void setup() {
        System.out.println("The setup is being run now ....");
        driver = utilities.DriverFactory.open();
        System.out.println("Done with initilizing the browser...." + driver);
        driver.manage().window().maximize();
        System.out.println("initilizing the class is about to start ....");
        ecommerceFreshPage = new EcommerceFreshPage(driver);
        System.out.println("Done with initilizing the class....");
    }

    @Given("user lands on Ecommerce Fresh landing page")
    public void user_lands_on_Ecommerce_Fresh_landing_page() {
        driver.get(landingPage);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    @Test
    @Then("add product button is present")
    public void add_product_button_is_present() { ecommerceFreshPage.verifyAddProductButtonisPresent(); }

    @When("user clicks on add product button")
    public void user_clicks_on_add_product_button() { ecommerceFreshPage.clickAddProductButton(); }

    @Test
    @Then("user lands on add product page")
    public void user_lands_on_add_product_page() { ecommerceFreshPage.verifyUserLandsOnAddProductPage(); }

    @When("user uploads product image")
    public void user_uploads_product_image() { ecommerceFreshPage.uploadProductImage(); }

    @And("user enters product title")
    public void user_enters_product_title() { ecommerceFreshPage.enterProductTitle(); }

    @And("user enters product description")
    public void user_enters_product_description() { ecommerceFreshPage.enterProductDescription(); }

    @And("user enters product price")
    public void user_enters_product_price() { ecommerceFreshPage.enterProductPrice(); }

    @Test
    @Then("Create Product button is enabled")
    public void Create_Product_button_is_enabled(){ ecommerceFreshPage.verifyCreateProductButtonisEnabled(); }

    @And("user clicks on Create Product button")
    public void user_clicks_on_Create_Product_button() { ecommerceFreshPage.clickCreateProductButton(); }

    @Test
    @Then("user lands on the landing page")
    public void user_lands_on_the_landing_page() { ecommerceFreshPage.verifyUserLandsOnLandingPage(); }

    @Test
    @And("newly added product is present")
    public void newly_added_product_is_present() { ecommerceFreshPage.verifyNewProductisPresent(); }

    @When("user clicks on edit product button")
    public void user_clicks_on_edit_product_button() { ecommerceFreshPage.clickEditProductButton(); }

    @Test
    @Then("user lands on edit product page")
    public void user_lands_on_edit_product_page() { ecommerceFreshPage.verifyUserLandsOnEditProductPage(); }

    @When("user edits product title field")
    public void user_edits_product_title_field() { ecommerceFreshPage.EditProductTitleField(); }

    @When("user clicks on Save Product button")
    public void user_clicks_on_Save_Product_button() { ecommerceFreshPage.clickSaveProductButton(); }

    @Test
    @And("newly edited product is present")
    public void newly_edited_product_is_present(){ ecommerceFreshPage.verifyEditProductisPresent(); }

    @When("user types search {string}")
    public void user_types_search(String keyword){ ecommerceFreshPage.typeSearchKeyword(keyword); }

    @Test
    @Then("product with the same {string} will appear")
    public void product_with_the_same_will_appear(String keyword){ }

    @When("user deletes a product")
    public void user_deletes_a_product(){  }

    @Test
    @Then("product is deleted successfully")
    public void product_is_deleted_successfully(){ }

    @After
    public void TearDown(Scenario scenario) {

        final byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
        scenario.attach(screenshot, "image/png", "Screenshots for Ecommerce Fresh Website tests");
        driver.quit();

    }
}
