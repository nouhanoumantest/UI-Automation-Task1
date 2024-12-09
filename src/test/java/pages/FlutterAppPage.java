/**

 * This class implements all the feature tests for Flutter Web App

 * @author NNouman

 */
package pages;

import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.bidi.browsingcontext.BrowsingContext;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.RemoteWebElement;
import ru.yandex.qatools.ashot.comparison.ImageDiff;
import ru.yandex.qatools.ashot.comparison.ImageDiffer;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Base64;

public class FlutterAppPage {

    WebDriver driver;

    //Constructor
    public FlutterAppPage(WebDriver driver) {
        this.driver = driver;
    }

    public void clickIncrementButton() {

        //locate canvas element on the page
        String cssSelectorForHost = "flt-glass-pane";
        SearchContext shadow = driver.findElement(By.cssSelector(cssSelectorForHost)).getShadowRoot();
        WebElement canvas = shadow.findElement(By.cssSelector("canvas[width='640']"));

        //Calculate the position of the + button
        Dimension dimension = canvas.getSize();
        int canvas_center_x = dimension.getWidth() / 2;
        int canvas_center_y = dimension.getHeight() / 2;
        int button_x = ( canvas_center_x /4 ) * 3;
        int button_y = ( canvas_center_y / 4 ) * 3;

        //Click on the + button
        Actions action = new Actions(driver);
        action.moveToElement(canvas,button_x,button_y)
                .click()
                .perform();

    }

    public void verifyCounterIsIncreased() {

        //Get Canvas Element locator
        BrowsingContext browsingContext = new BrowsingContext(driver, driver.getWindowHandle());
        String cssSelectorForHost = "flt-glass-pane";
        SearchContext shadow = driver.findElement(By.cssSelector(cssSelectorForHost)).getShadowRoot();
        WebElement canvas = shadow.findElement(By.cssSelector("canvas[width='640']"));
        //take screenshot
        String internalElementId = ((RemoteWebElement) canvas).getId();
        String elementScreenShot = browsingContext.captureElementScreenshot(internalElementId);
        saveScreenshot(elementScreenShot, "counter_1.png");
        //compare screenshot with the one we have saved in the project
        Assert.assertTrue("Counter didn't increase as expected", compareImage());

    }

    private static void saveScreenshot(String screenshot, String filename) {
        byte[] decodedScreenshot =  Base64.getDecoder().decode(screenshot);
        try {
            String path = "./screenshots/actual/";
            Files.write(Paths.get(path + filename), decodedScreenshot);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private boolean compareImage() {
        File expectedImageFile = new File("./screenshots/expected/counter_1.png");
        File actualImageFile = new File("./screenshots/actual/counter_1.png");

        try {
            BufferedImage expectedImage = ImageIO.read(expectedImageFile);
            BufferedImage actualImage = ImageIO.read(actualImageFile);

            ImageDiffer imageDiffer = new ImageDiffer();
            ImageDiff diff = imageDiffer.makeDiff(expectedImage, actualImage);
            if (diff.hasDiff()) {
                return false;
            } else {
                return true;
            }

        } catch (IOException e) {
            e.printStackTrace();
            return true;
        }
    }
}

