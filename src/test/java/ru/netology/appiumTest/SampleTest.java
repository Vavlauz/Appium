package ru.netology.appiumTest;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.remote.DesiredCapabilities;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SampleTest {

    private AndroidDriver driver;

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

        driver = new AndroidDriver(remoteUrl, desiredCapabilities);
    }

    @Test
    public void testInputNone() {
        MobileElement textBefore = (MobileElement) driver.findElementById("ru.netology.testing.uiautomator:id/textToBeChanged");
        MobileElement el2 = (MobileElement) driver.findElementById("ru.netology.testing.uiautomator:id/userInput");
        el2.click();
        MobileElement el3 = (MobileElement) driver.findElementById("ru.netology.testing.uiautomator:id/buttonChange");
        el3.click();
        MobileElement textAfter = (MobileElement) driver.findElementById("ru.netology.testing.uiautomator:id/textToBeChanged");
        assertEquals(textBefore, textAfter);
    }

    @Test
    public void testInputSpaces() {
        MobileElement textBefore = (MobileElement) driver.findElementById("ru.netology.testing.uiautomator:id/textToBeChanged");
        MobileElement el10 = (MobileElement) driver.findElementById("ru.netology.testing.uiautomator:id/userInput");
        el10.sendKeys("     ");
        MobileElement el11 = (MobileElement) driver.findElementById("ru.netology.testing.uiautomator:id/buttonChange");
        el11.click();
        MobileElement textAfter = (MobileElement) driver.findElementById("ru.netology.testing.uiautomator:id/textToBeChanged");
        assertEquals(textBefore, textAfter);
    }

    @Test
    public void testChangeTextAndNewActivity() {
        MobileElement el14 = (MobileElement) driver.findElementById("ru.netology.testing.uiautomator:id/userInput");
        el14.sendKeys("     Hallo!");
        MobileElement el15 = (MobileElement) driver.findElementById("ru.netology.testing.uiautomator:id/buttonChange");
        el15.click();
        MobileElement el16 = (MobileElement) driver.findElementById("ru.netology.testing.uiautomator:id/buttonActivity");
        el16.click();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        MobileElement textAfter = (MobileElement) driver.findElementById("ru.netology.testing.uiautomator:id/text");
        assertEquals("     Hallo!", textAfter.getText());
    }

    @AfterEach
    public void tearDown() {
        driver.quit();
    }
}

