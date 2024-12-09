/**

 * This class is for WebDriver generation

 * @author NNouman

 */
package utilities;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class DriverFactory {

    //This method return a WebDriver object
    public static WebDriver open() {

        WebDriverManager.chromedriver().setup();

        //Create Chrome Options
        ChromeOptions option = new ChromeOptions();
        option.enableBiDi();
        option.addArguments("--test-type");
        option.addArguments("--disable-popup-bloacking");
        option.addArguments("--enable-javascript");
        option.addArguments("--remote-allow-origins=*");
        option.addArguments("headless");
        option.setAcceptInsecureCerts(true);
        option.setCapability(ChromeOptions.CAPABILITY, option);

        //Create driver object for Chrome
        WebDriver driver = new ChromeDriver(option);
        return driver;

    }
}
