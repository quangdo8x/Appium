package modules;

import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidKeyCode;
import io.appium.java_client.remote.MobileCapabilityType;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;

public class DialTest {
    AndroidDriver driver;

    @BeforeClass
    public void setUp() throws MalformedURLException {
        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability(MobileCapabilityType.PLATFORM_NAME, "android");
        caps.setCapability(MobileCapabilityType.PLATFORM_VERSION, "7.1.1");
        caps.setCapability(MobileCapabilityType.DEVICE_NAME, "00c274641540bbc3");

        caps.setCapability("appPackage", "com.android.contacts");
        caps.setCapability("appActivity", "com.android.dialer.DialtactsActivity");

        driver = new AndroidDriver(new URL("http://0.0.0.0:4723/wd/hub"),caps);
    }

    @Test
    public void dialerTest(){
        WebElement dialPad = driver.findElementByAccessibilityId("dial pad");
        dialPad.click();
        TouchAction tAction = new TouchAction(driver);
        tAction.longPress(driver.findElement(By.id("com.android.contacts:id/one"))).perform();
        WebElement results = driver.findElementByClassName("android.widget.EditText");
        assert results.getText().equals("+"):"Actual value is : " + results.getText()+" did not match with expected value";
    }

//    @Test
//    public void alert_test() throws InterruptedException {
//        WebElement showAlert =  driver.findElement(By.id("com.android.androidui:id/buttonAlert"));
//        showAlert.click();
//
//        Thread.sleep(10000);
//        WebElement yes = driver.findElement(By.id("android:id/button1"));
//        yes.click();
//    }
//
    @Test
    public void notificationPanel()
    {
        driver.openNotifications();
//        driver.findElement(By.xpath(".//android.view.View[containts(@content-desc,'Screen will rotate automatically.')")).click();
        driver.pressKeyCode(AndroidKeyCode.BACK);
    }

    @AfterClass
    public void tearDown(){

        driver.closeApp();

    }
}