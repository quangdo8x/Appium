package modules;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;
//import android.objects.Button;
import java.io.File;

public class UiApp {
    AndroidDriver driver;
    @BeforeClass
    public void setUp() throws MalformedURLException {
//        File app = new File("D:\\Appium\\Softs\\AndroidUi.apk");
        DesiredCapabilities caps = new DesiredCapabilities();
//        caps.setCapability(MobileCapabilityType.APP, app);

        caps.setCapability(MobileCapabilityType.PLATFORM_NAME, "Android");
        caps.setCapability(MobileCapabilityType.PLATFORM_VERSION, "7.1.1");
        caps.setCapability(MobileCapabilityType.DEVICE_NAME, "00c274641540bbc3");
        caps.setCapability("appPackage", "com.android.androidui");
        caps.setCapability("appActivity", "com.android.androidui.MainActivity");
        caps.setCapability("noReset", true);

        driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"),caps);
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    }
    @Test(priority = 1)
    public void switch_test(){
        WebElement switchButton = driver.findElementById("com.android.androidui:id/mySwitch");
        switchButton.click();
    }
    @Test(priority = 2)
    public void spinner_test() throws InterruptedException {
        /**
         * Spinners allow you to select a value from dropdown list and also show the currently selected value.
         * Now, we are going to take a sample of Android UI app, which we used in alert handling
         */
        WebElement spinner = driver.findElement(By.id("android:id/text1"));
        spinner.click(); // Click on the spinner, it will open the list of values
        driver.findElement(By.xpath(".//android.widget.CheckedTextView[@text=\"India\"]")).click();


    }
    @Test(priority = 3)
    public void alert_test(){
        driver.findElement(By.xpath(".//android.widget.Button[@text='Show Alert']")).click();
        driver.findElement(By.xpath(".//android.widget.Button[@text='Yes']")).click();
    }


    @AfterClass
    public void tearDown(){
        driver.closeApp();
    }

}