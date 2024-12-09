/**

 * This class implements all the feature tests for Ecommerce Fresh Website

 * @author NNouman

 */
package pages;

import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.io.File;
import java.time.Duration;
import java.util.List;

public class EcommerceFreshPage {

    WebDriver driver;
    String addProductPath = "//a[contains(@href,'add')]";
    String createProductPath = "//*[text()='Create Product']";
    int numberOfProducts;

    //Constructor
    public EcommerceFreshPage(WebDriver driver) {
        System.out.println("Started with initilizing the class just now ....");
        this.driver = driver;
        numberOfProducts = getProductCount();
    }

    public void verifyAddProductButtonisPresent() {
        Assert.assertTrue("Add product button is not present",
                driver.findElement(By.xpath(addProductPath)).isDisplayed());
    }

    public void clickAddProductButton() {
        driver.findElement(By.xpath(addProductPath)).click();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    public void verifyUserLandsOnAddProductPage() {
        Assert.assertTrue("User did not land on add product page",
                driver.getCurrentUrl().contains("add"));
    }

    public void uploadProductImage() {
        File file = new File("./iphone15.png");
        WebElement uploadLink = driver.findElement(By.name("file"));
        uploadLink.sendKeys(file.getAbsolutePath());
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    public void enterProductTitle() {
        WebElement titleField = driver.findElement(By.name("title"));
        titleField.sendKeys("Apple iphone 15");
    }

    public void enterProductDescription() {
        WebElement description = driver.findElement(By.xpath("//input[contains(@name,'description')]"));
        description.sendKeys("The new phone by Apple. Please try it and let us know!");
    }

    public void enterProductPrice() {
        WebElement price = driver.findElement(By.name("price"));
        price.sendKeys(Keys.BACK_SPACE);
        price.sendKeys("500");
    }

    public void verifyCreateProductButtonisEnabled() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(100));
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath(createProductPath)));
    }

    public void clickCreateProductButton() {
        driver.findElement(By.xpath(createProductPath)).click();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    public void verifyUserLandsOnLandingPage() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
        wait.until(ExpectedConditions.urlMatches("https://e-commerce-kib.netlify.app/"));
    }

    public void verifyNewProductisPresent() {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        WebElement product =driver.findElement(By.xpath("//*[text()='Apple iphone 15']"));
        scrollToElement(product);
        Assert.assertTrue("New Product is not present",
                product.isDisplayed());

    }

    public void clickEditProductButton() {
        WebElement editButton = findActionButton("Edit");
        editButton.click();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    public void verifyUserLandsOnEditProductPage() {
        Assert.assertTrue("User did not land on edit product page",
                driver.getCurrentUrl().contains("edit"));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    public void EditProductTitleField() {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        WebElement editTitleField = driver.findElement(By.xpath("//input[contains(@name,'title')]"));
        editTitleField.click();
        editTitleField.sendKeys("--- edited");

    }

    public void clickSaveProductButton() {
        WebElement save = driver.findElement(By.xpath("//*[text()='Save Product']"));
        save.sendKeys(Keys.ENTER);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    public void verifyEditProductisPresent() {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        WebElement product =driver.findElement(By.xpath("//*[text()='Apple iphone 15--- edited']"));
        scrollToElement(driver.findElement(By.xpath("//*[text()='Apple iphone 15--- edited']")));
        Assert.assertTrue("Edit Product is not present",
                product.isDisplayed());
    }

    public void typeSearchKeyword(String keyword) {
        WebElement searchField = driver.findElement(By.xpath("//input[contains(@placeholder,'Search for products ...')]"));
        searchField.click();
        searchField.sendKeys(keyword);
        searchField.sendKeys(Keys.ENTER);
    }

    public void verifySearchResult(String keyword) {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        Assert.assertTrue("Search engine is not working as expected",
                driver.findElement(By.xpath("//*[text()='"+ keyword +"']")).isDisplayed());
    }

    public void userDeletesProduct() {
        WebElement deleteButton = findActionButton("delete");
        deleteButton.click();
    }

    public void verifyProductIsDeleted() {
        Assert.assertEquals("Product is not deleted successfully.",
                1, numberOfProducts - getProductCount());
    }

    private int getProductCount() {
        List<WebElement> priceList = driver.findElements(By.className("card-price"));
        return priceList.size();
    }
    private WebElement findActionButton(String action) {
        List<WebElement> actionList = driver.findElements(By.className("card-actions"));
        List<WebElement> elements = driver.findElements(By.xpath("//Button"));
        if (action == "Delete")
            return elements.get(elements.size() - 1);
        else
            return elements.get(elements.size() - 2);
    }

    private void scrollToElement(WebElement element) {

        Actions actions = new Actions(driver);
        actions.moveToElement(element).perform();

    }

}
