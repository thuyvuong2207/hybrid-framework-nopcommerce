package pageObjects;

import commons.BasePage;
import org.openqa.selenium.WebDriver;
import pageUIs.CustomerPageUI;

public class CustomerPageObject extends BasePage {
    WebDriver driver;

    public CustomerPageObject(WebDriver driver) {
        this.driver = driver;
    }

    public String getFirstNameTextboxAttributeValue() {
        waitForElementInvisible(driver, CustomerPageUI.FIRSTNAME_TEXTBOX);
        return getElementAttribute(driver, CustomerPageUI.FIRSTNAME_TEXTBOX, "value");
    }

    public String getLastNameTextboxAttributeValue() {
        waitForElementInvisible(driver, CustomerPageUI.LASTNAME_TEXTBOX);
        return getElementAttribute(driver, CustomerPageUI.LASTNAME_TEXTBOX, "value");
    }

    public String getEmailTextboxAttributeValue() {
        waitForElementInvisible(driver, CustomerPageUI.EMAIL_ADDRESS_TEXTBOX);
        return getElementAttribute(driver, CustomerPageUI.EMAIL_ADDRESS_TEXTBOX, "value");
    }
}
