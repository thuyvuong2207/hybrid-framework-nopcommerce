package com.nopcommerce.user;

import commons.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeClass;

public class Level_02_Apply_BasePage {
    WebDriver driver;
    String emailAddress;
    BasePage basePage;
    String projectPath = System.getProperty("user.dir");

    @BeforeClass
    public void BeforeClass() {
        driver = new ChromeDriver();

        emailAddress = "afc"  +
    }
}
