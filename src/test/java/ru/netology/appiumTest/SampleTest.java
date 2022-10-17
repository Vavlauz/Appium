package ru.netology.appiumTest;

import io.appium.java_client.AppiumDriver;

import java.net.MalformedURLException;
import java.net.URL;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.remote.DesiredCapabilities;

import org.junit.jupiter.api.*;


@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class SampleTest {
    private AppiumDriver driver;

    @BeforeEach
    public void setUp() throws MalformedURLException {
        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
        desiredCapabilities.setCapability("platformName", "Android");
        desiredCapabilities.setCapability("appium:deviceName", "Some name");
        desiredCapabilities.setCapability("appium:appPackage", "ru.netology.testing.uiautomator");
        desiredCapabilities.setCapability("appium:appActivity", "ru.netology.testing.uiautomator.MainActivity");
        desiredCapabilities.setCapability("appium:ensureWebviewsHavePages", true);
        desiredCapabilities.setCapability("appium:nativeWebScreenshot", true);
        desiredCapabilities.setCapability("appium:newCommandTimeout", 3600);
        desiredCapabilities.setCapability("appium:connectHardwareKeyboard", true);

        URL remoteUrl = new URL("http://127.0.0.1:4723/wd/hub");

        driver = new AppiumDriver(remoteUrl, desiredCapabilities);
    }


    @Test
    public void setEmptyString() {
        MainActivityPage page = new MainActivityPage(driver);
        page.userInput.sendKeys(" ");
        page.buttonChange.click();
        Assertions.assertEquals("Hello UiAutomator!", page.textToBeChanged.getText());

    }

    @Test
    public void activityTest() {
        MainActivityPage page = new MainActivityPage(driver);
        page.userInput.sendKeys("test");
        page.buttonActivity.click();
        Assertions.assertEquals("test", page.activityText.getText());

    }

    @AfterAll
    public void tearDown() {
        driver.quit();
    }

}

