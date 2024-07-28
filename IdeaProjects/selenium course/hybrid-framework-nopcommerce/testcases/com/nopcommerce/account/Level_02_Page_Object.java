package com.nopcommerce.account;

import commons.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pageObjects.HomePageObject;
import pageObjects.RegisterPageObject;

import java.util.Random;
import java.util.concurrent.TimeUnit;


public class Level_02_Page_Object extends BasePage {
    private WebDriver driver;
    private String projectPath = System.getProperty("user.dir");
    private HomePageObject homePage;
    private RegisterPageObject registerPage;


    @BeforeClass
    public void BeforeClass() {
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        openPageURL(driver, "https://demo.nopcommerce.com/");
        homePage = new HomePageObject(driver);
    }

    @Test
    public void User_01_Register_Empty_Data() {
        clickToElement(driver, "//a[@class='ico-register']");
        clickToElement(driver, "//button[@id='register-button']");

        Assert.assertEquals(getElementText(driver, "//span[@id='FirstName-error']"), "First name is required.");
        Assert.assertEquals(getElementText(driver, "//span[@id='LastName-error']"), "Last name is required.");
        Assert.assertEquals(getElementText(driver, "//span[@id='Email-error']"), "Email is required.");
        Assert.assertEquals(getElementText(driver, "//span[@id='ConfirmPassword-error']"), "Password is required.");
    }

    @Test
    public void User_02_Register_Invalid_Email() {
        clickToElement(driver, "//a[@class='ico-register']");

        sendkeyToElement(driver, "//input[@id='FirstName']", "Thuy");
        sendkeyToElement(driver, "//input[@id='LastName']", "Vuong");
        sendkeyToElement(driver, "//input[@id='Email']", "thuyvm@jj@");
        sendkeyToElement(driver, "//input[@id='Password']", "123456789");
        sendkeyToElement(driver, "//input[@id='ConfirmPassword']", "123456789");

        clickToElement(driver, "//button[@id='register-button']");
        Assert.assertEquals(getElementText(driver, "//span[@id='Email-error']"), "Wrong email");
    }

    @Test
    public void User_03_Register_Invalid_Password() {
        clickToElement(driver, "//a[@class='ico-register']");

        sendkeyToElement(driver, "//input[@id='FirstName']", "Thuy");
        sendkeyToElement(driver, "//input[@id='LastName']", "Vuong");
        sendkeyToElement(driver, "//input[@id='Email']", "thuyvm@gmail.com");
        sendkeyToElement(driver, "//input[@id='Password']", "123");
        sendkeyToElement(driver, "//input[@id='ConfirmPassword']", "123");

        clickToElement(driver, "//button[@id='register-button']");
        Assert.assertEquals(getElementText(driver, "//span[@id='Email-error']"), "<p>Password must meet the following rules: </p><ul><li>must have at least 6 characters and not greater than 64 characters</li></ul>");
    }

    @Test
    public void User_04_Register_Incorrect_Confirm_Password() {
        clickToElement(driver, "//a[@class='ico-register']");

        sendkeyToElement(driver, "//input[@id='FirstName']", "Thuy");
        sendkeyToElement(driver, "//input[@id='LastName']", "Vuong");
        sendkeyToElement(driver, "//input[@id='Email']", "thuyvm@gmail.com");
        sendkeyToElement(driver, "//input[@id='Password']", "123456789");
        sendkeyToElement(driver, "//input[@id='ConfirmPassword']", "123");

        clickToElement(driver, "//button[@id='register-button']");
        Assert.assertEquals(getElementText(driver, "//span[@id='Email-error']"), "The password and confirmation password do not match");
    }

    @Test
    public void User_05_Register_Success() {
        clickToElement(driver, "//a[@class='ico-register']");

        sendkeyToElement(driver, "//input[@id='FirstName']", "Thuy");
        sendkeyToElement(driver, "//input[@id='LastName']", "Vuong");
        sendkeyToElement(driver, "//input[@id='Email']", "thuyvm@gmail.com");
        sendkeyToElement(driver, "//input[@id='Password']", "12345678");
        sendkeyToElement(driver, "//input[@id='ConfirmPassword']", "12345678");

        clickToElement(driver, "//button[@id='register-button']");
        Assert.assertEquals(getElementText(driver, "//div[@class='result']"), "Your registration completed");
    }

    @Test
    public void User_06_Login_Success() {

    }


    @AfterClass
    public void AfterClass() {
        driver.quit();
    }

    public String getEmailRandom() {
        Random random = new Random();
        return "vuongminhthuy97+" + random.nextInt(99) + "@gmail.com";
    }
}
