package commons;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
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

        BrowserList browser = BrowserList.valueOf(browserName.toUpperCase());

        switch (browser){
            case FIREFOX:
                System.setProperty("webdriver.firefox.driver", projectPath + "/browserDrivers/geckodriver.exe");
                driver = new FirefoxDriver();
                break;
            case CHROME:
                System.setProperty("webdriver.chrome.driver", projectPath + "/browserDrivers/chromedriver.exe");
                driver = new ChromeDriver();
                break;
            case EDGE:
                System.setProperty("webdriver.edge.driver", projectPath + "/browserDrivers/msedgedriver.exe");
                driver = new EdgeDriver();
                break;
            case SAFARI:
                System.setProperty("webdriver.safari.driver", projectPath + "/browserDrivers/safaridriver.exe");
                driver = new SafariDriver();
                break;
            default:
                throw new RuntimeException("Brow ser name is not valid");
        }

        driver.manage().window().setPosition(new Point(0,0));
        driver.manage().window().setSize(new Dimension(1920,1080));
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        driver.get("https://demo.nopcommerce.com/");
        return driver;
    }
}
