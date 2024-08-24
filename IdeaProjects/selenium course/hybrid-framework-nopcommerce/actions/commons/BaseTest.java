package commons;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;

import java.util.concurrent.TimeUnit;

public class BaseTest {
    protected WebDriver getBrowserDriver(String browserName){
        private WebDriver driver;
        private String projectPath = System.getProperty("user.dir");

        if (browserName.equalsIgnoreCase("firefox")) {
            System.setProperty("webdriver.firefox.driver", projectPath + "/browserDrivers/geckodriver.exe");
            driver = new FirefoxDriver();
        } else if (browserName.equalsIgnoreCase("chrome")){
            System.setProperty("webdriver.chrome.driver", projectPath + "/browserDrivers/chromedriver.exe");
            driver = new ChromeDriver();
        } else if (browserName.equalsIgnoreCase("edge")){
            System.setProperty("webdriver.edge.driver", projectPath + "/browserDrivers/msedgedriver.exe");
            driver = new EdgeDriver();
        } else if (browserName.equalsIgnoreCase("safari")){
            System.setProperty("webdriver.safari.driver", projectPath + "/browserDrivers/safaridriver.exe");
            driver = new SafariDriver();
        } else {
            throw new RuntimeException("Browser name is not valid");
        }
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        driver.get("https://demo.nopcommerce.com/");
        return driver;
    }
}
