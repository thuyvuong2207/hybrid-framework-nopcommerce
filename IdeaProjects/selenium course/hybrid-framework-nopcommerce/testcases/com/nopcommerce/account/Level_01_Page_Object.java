package com.nopcommerce.account;

import commons.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.Random;
import java.util.concurrent.TimeUnit;


public class Level_01_Page_Object extends BasePage {
    private WebDriver driver;
    private String projectPath = System.getProperty("user.dir");


    @BeforeClass
    public void BeforeClass() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
    }

    @Test
    public void Register_01_Empty_Data() {
        openPageURL(driver, "");

        clickToElement(driver, "//a[@class='ico-register']");
        clickToElement(driver, "//button[@id='register-button']");

        Assert.assertEquals(getElementText(driver, "//span[@id='FirstName-error']"), "First name is required.");
        Assert.assertEquals(getElementText(driver, "//span[@id='LastName-error']"), "Last name is required.");
        Assert.assertEquals(getElementText(driver, "//span[@id='Email-error']"), "Email is required.");
        Assert.assertEquals(getElementText(driver, "//span[@id='ConfirmPassword-error']"), "Password is required.");
    }

    @Test
    public void Register_02_Invalid_Email() {
        openPageURL(driver, "");
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
    public void Register_03_Invalid_Password() {
        openPageURL(driver, "");
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
    public void Register_04_Existing_Email() {

    }

    @Test
    public void Register_05_Valid_Register() {

    }

    @Test
    public void Register_06_Mismatch_Confirmation_Pw() {

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
