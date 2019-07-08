package modules;


import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;

import javafx.scene.layout.Priority;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class Settings {

    public AndroidDriver driver;

    @BeforeClass
      public void setUp() throws MalformedURLException {
        DesiredCapabilities caps = new DesiredCapabilities();

        caps.setCapability(MobileCapabilityType.PLATFORM_NAME, "Android");
        caps.setCapability(MobileCapabilityType.PLATFORM_VERSION, "7.1.1");
        caps.setCapability(MobileCapabilityType.DEVICE_NAME, "00c274641540bbc3");
        caps.setCapability("appPackage", "com.android.settings");
        caps.setCapability("appActivity", "com.android.settings.Settings");

//        caps.setCapability("appPackage","com.kwiltapp.companion.debug");
//        caps.setCapability("appActivity","com.kwiltapp.Login.Activity.LoginActivity");

        driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"),caps);
//        driver = new AndroidDriver(new URL(url),caps);
//        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    }

    @Test
    public void scrollTest()
    {
        driver.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(new UiSelector().text(\"Printing\"));");
    }

    @Test(priority = 1)
    public void addPrint() throws InterruptedException {

        driver.findElement(By.xpath("//android.widget.TextView[@text='Printing']")).click();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.findElement(By.xpath("//android.widget.TextView[@text='Add service']")).click();
    }

    @AfterClass
    public void tearDown(){
        driver.closeApp();
    }
}
