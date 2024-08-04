package com.nopcommerce.account;

import commons.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pageObjects.CustomerPageObject;
import pageObjects.HomePageObject;
import pageObjects.LoginPageObject;
import pageObjects.RegisterPageObject;
import pageUIs.HomePageUI;
import pageUIs.RegisterPageUI;

import java.util.Random;
import java.util.concurrent.TimeUnit;


public class Level_03_Page_Object extends BasePage {
    private WebDriver driver;
    private String projectPath = System.getProperty("user.dir");
    private HomePageObject homePage;
    private RegisterPageObject registerPage;
    private LoginPageObject loginPage;
    private CustomerPageObject customerPage;


    private String emailAddress = getEmailRandom();


    @BeforeClass
    public void BeforeClass() {
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        openPageURL(driver, "https://demo.nopcommerce.com/");
        homePage = new HomePageObject(driver);
    }

    @Test
    public void User_01_Register_Empty_Data() {
        homePage.clickToRegisterLink();

        registerPage = new RegisterPageObject(driver);
        registerPage.clickToRegisterButton();
        Assert.assertEquals(registerPage.getFirstNameErrorMsgText(), "First name is required.");
        Assert.assertEquals(registerPage.getLastNameErrorMsgText(), "Last name is required.");
        Assert.assertEquals(registerPage.getEmailErrorMsgText(), "Email is required.");
        Assert.assertEquals(registerPage.getConfirmPasswordErrorMsgText(), "Password is required.");

    }

    @Test
    public void User_02_Register_Invalid_Email() {
        registerPage.clickNopCommerceLogo();
        homePage = new HomePageObject(driver);

        homePage.clickToRegisterLink();
        registerPage = new RegisterPageObject(driver);
        registerPage.enterToFirstNameTextbox("Thuy");
        registerPage.enterToLastNameTextbox("Vuong");
        registerPage.enterToEmailTextbox("thuyvm@gm@");
        registerPage.enterToPasswordTextbox("12345678");
        registerPage.enterToConfirmPasswordTextbox("12345678");

        registerPage.clickToRegisterButton();
        Assert.assertEquals(registerPage.getEmailErrorMsgText(), "Please enter a valid email address.");
    }

    @Test
    public void User_03_Register_Invalid_Password() {
        registerPage.clickNopCommerceLogo();
        homePage = new HomePageObject(driver);

        homePage.clickToRegisterLink();

        registerPage = new RegisterPageObject(driver);
        registerPage.enterToFirstNameTextbox("Thuy");
        registerPage.enterToLastNameTextbox("Vuong");
        registerPage.enterToEmailTextbox("thuyvm@gm@");
        registerPage.enterToPasswordTextbox("1232");
        registerPage.enterToConfirmPasswordTextbox("123");

        registerPage.clickToRegisterButton();
        Assert.assertEquals(registerPage.getConfirmPasswordErrorMsgText(), "The password and confirmation password do not match.");
    }

    @Test
    public void User_04_Register_Incorrect_Confirm_Password() {
        registerPage.clickNopCommerceLogo();
        homePage = new HomePageObject(driver);

        homePage.clickToRegisterLink();

        registerPage = new RegisterPageObject(driver);
        registerPage.enterToFirstNameTextbox("Thuy");
        registerPage.enterToLastNameTextbox("Vuong");
        registerPage.enterToEmailTextbox("thuyvm@gm@");
        registerPage.enterToPasswordTextbox("123456789");
        registerPage.enterToConfirmPasswordTextbox("123");

        registerPage.clickToRegisterButton();
        Assert.assertEquals(registerPage.getConfirmPasswordErrorMsgText(), "The password and confirmation password do not match.");
    }

    @Test
    public void User_05_Register_Success() {
        registerPage.clickNopCommerceLogo();
        homePage = new HomePageObject(driver);

        homePage.clickToRegisterLink();

        registerPage = new RegisterPageObject(driver);
        registerPage.enterToFirstNameTextbox("Thuy");
        registerPage.enterToLastNameTextbox("Vuong");
        registerPage.enterToEmailTextbox(emailAddress);
        registerPage.enterToPasswordTextbox("123456789");
        registerPage.enterToConfirmPasswordTextbox("123456789");

        registerPage.clickToRegisterButton();
        Assert.assertEquals(registerPage.getRegisterSuccessMessageText(), "Your registration completed");
    }

    @Test
    public void User_06_Login_Success() {
        registerPage.clickNopCommerceLogo();
        homePage = new HomePageObject(driver);

        homePage.clickToMyAccountLink();

        customerPage = new CustomerPageObject(driver);

        Assert.assertEquals(customerPage.getFirstNameTextboxAttributeValue(),"Thuy");
        Assert.assertEquals(customerPage.getLastNameTextboxAttributeValue(),"Vuong");
        Assert.assertEquals(customerPage.getEmailTextboxAttributeValue(),emailAddress);
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
