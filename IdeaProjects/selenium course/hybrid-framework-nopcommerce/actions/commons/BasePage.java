package commons;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.Set;

public class BasePage {
    // Open URL for 1 page
    public void openPageURL(WebDriver driver, String pageUrl){
        driver.get(pageUrl);
    }
    public String getPageTitle(WebDriver driver){
        return driver.getTitle();
    }
    public String getCurrentURL(WebDriver driver){
        return driver.getCurrentUrl();
    }
    public String getPageSourceCode(WebDriver driver){
        return driver.getPageSource();
    }
    public void backToPage(WebDriver driver){
        driver.navigate().back();
    }
    public void forwardToPage(WebDriver driver){
        driver.navigate().forward();
    }
    public void refreshPage(WebDriver driver){
        driver.navigate().refresh();
    }
    public Alert waitForAlertPresence(WebDriver driver){
        WebDriverWait explicitWait = new WebDriverWait(driver, 30);
        return explicitWait.until(ExpectedConditions.alertIsPresent());
    }
    public void acceptAlert(WebDriver driver){
        waitForAlertPresence(driver).accept();
    }
    public void dismissAlert(WebDriver driver){
        waitForAlertPresence(driver).dismiss();
    }
    public String getAlertText(WebDriver driver){
        return waitForAlertPresence(driver).getText();
    }
    public void sendkeyToAlert(WebDriver driver, String textValue){
        waitForAlertPresence(driver).sendKeys(textValue);
    }
    public void switchToWindowByID(WebDriver driver, String windowID){
        Set<String> allWindowsIDs = driver.getWindowHandles();
        for (String id: allWindowsIDs) {
            if (!id.equals(windowID)) {
            driver.switchTo().window(id);
            break;
            }
        }
    }
    public void switchToWindowByTitle(WebDriver driver, String tabTitle){
        Set<String> allWindowsIDs = driver.getWindowHandles();
        for (String id: allWindowsIDs) {
                driver.switchTo().window(id);
                String actualTitle = driver.getTitle();
                if (actualTitle.equals(tabTitle)){
                    break;
                }
        }
    }
    public void closeAllTabsExceptParent(WebDriver driver, String parentID){
        Set<String> allWindowsIDs = driver.getWindowHandles();
        for (String id: allWindowsIDs) {
            if (!id.equals(parentID)) {
                driver.switchTo().window(id);
                driver.close();
            }
            driver.switchTo().window(parentID);
        }
    }

    public WebElement getWebElement(WebDriver driver, String xpathLocator){
        return driver.findElement(By.xpath(xpathLocator));
    }
    public void clickToElement(WebDriver driver, String xpathLocator){
        getWebElement(driver, xpathLocator).click();
    }
    public void sendkeyToElement(WebDriver driver, String xpathLocator, String textValue){
        WebElement element = getWebElement(driver, xpathLocator);
        element.clear();
        element.sendKeys(textValue);
    }
    public String getElementText(WebDriver driver, String xpathLocator, String textValue){
        return getWebElement(driver, xpathLocator).sendKeys(textValue);
    }
    public void selectItemInDefaultDropdown(WebDriver driver, String xpathLocator, String textItem){
        Select select = new Select(getWebElement(driver, xpathLocator));
        select.selectByValue(textItem);
    }
    public String getFirstSelectedItemDefault(WebDriver driver, String xpathLocator, String textItem){
        Select select = new Select(getWebElement(driver, xpathLocator));
        return select.getFirstSelectedOption().getText();
    }
    public boolean isDropdownMultiple(WebDriver driver, String xpathLocator){
        Select select = new Select(getWebElement(driver, xpathLocator));
        return select.isMultiple();
    }
    public void selectItemInCustomDropdown(WebDriver driver, String parentXpath, String childXpath, String expectedTextItem){
        getWebElement(driver, parentXpath).click();
        sleepInSeconds(2);

        WebDriverWait explicitWait = new WebDriverWait(driver, 30);
        List<WebElement> allItems = explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(childXpath)));
        for(WebElement item : allItems){
            if (item.getText().trim().equals(expectedTextItem)){
                JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
                jsExecutor.executeScript("arguments[0].scrollIntoView(true)", item);
                sleepInSeconds(2);
                item.click();
                break;
            }
        }
    }
    public void sleepInSeconds(long time){
        try {
            Thread.sleep(time * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}