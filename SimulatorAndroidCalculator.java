package modules;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidKeyCode;
import io.appium.java_client.remote.MobileCapabilityType;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.AssertJUnit;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by QuangDo on 19/03/2017.
 */
public class SimulatorAndroidCalculator {

    AndroidDriver driver;

    @Parameters({"deviceName","platformName","platformVersion","appPackage","appActivity","url"})
    @BeforeMethod
    public void setUp(String deviceName, String platformName, String platformVersion, String appPackage, String appActivity, String url) throws MalformedURLException {
        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability(MobileCapabilityType.PLATFORM_NAME, platformName);
        caps.setCapability(MobileCapabilityType.PLATFORM_VERSION, platformVersion);
        caps.setCapability(MobileCapabilityType.DEVICE_NAME, deviceName);

        //Set Android app - only for simulator
        caps.setCapability("appPackage",appPackage);
        caps.setCapability("appActivity",appActivity);

        driver = new AndroidDriver(new URL(url),caps);
    }

    @Test
    public void CalculatorTest(){

        //Press digit contains many numbers
        String numPad = "//android.widget.Button[@text='%s']";
        String btn2 = String.format(numPad,"2");
        String btnAdd = String.format(numPad,"+");
        String btnEqual = String.format(numPad,"=");

        //driver.findElement(By.xpath("//android.widget.Button[@text='2']")).click();
        driver.findElement(By.xpath(btn2)).click();
        //driver.findElement(By.xpath("//android.widget.Button[@text='+']")).click();
        driver.findElement(By.xpath(btnAdd)).click();
        //driver.findElement(By.xpath("//android.widget.Button[@text='2']")).click();
        driver.findElement(By.xpath(btn2)).click();
        //driver.findElement(By.xpath("//android.widget.Button[@text='=']")).click();
        driver.findElement(By.xpath(btnEqual)).click();

        //By.id = By.resourse-id
        String result = driver.findElement(By.id("com.android.calculator:id/formula")).getText();

        AssertJUnit.assertEquals("4",result);

        //press any keyboards on device - Home
        //driver.pressKeyCode(AndroidKeyCode.HOME);

        //rotate the screen of device
        //driver.rotation();
    }

    @AfterMethod
    public void tearDown(){

    }
}
