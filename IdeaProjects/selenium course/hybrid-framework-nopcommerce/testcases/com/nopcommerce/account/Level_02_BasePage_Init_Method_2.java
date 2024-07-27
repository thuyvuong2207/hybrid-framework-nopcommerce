package com.nopcommerce.account;

import commons.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.Random;


public class Level_02_BasePage_Init_Method_2 {
    private WebDriver driver;
    private String projectPath = System.getProperty("user.dir");
    private BasePage basePage = BasePage.getBasePage();


    @BeforeClass
    public void BeforeClass() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @Test
    public void Register_01_Empty_Data(){
        basePage.openPageURL(driver, "");
    }

    @Test
    public void Register_02_Invalid_Email(){

    }

    @Test
    public void Register_03_Valid_Info(){

    }

    @Test
    public void Register_04_Existing_Email(){

    }

    @Test
    public void Register_05_Password_Less_Than_6_Chars(){

    }

    @Test
    public void Register_06_Mismatch_Confirmation_Pw(){

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
