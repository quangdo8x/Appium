package modules;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Set;
import java.util.concurrent.TimeUnit;

public class AndroidHybridApps {
    AppiumDriver driver;
    //    AndroidDriver driver;
    @BeforeClass
    public void setUp()  {
        try{
            File app = new File("./Apps/android/Hybridtestapp.apk");
            DesiredCapabilities caps = new DesiredCapabilities();
            caps.setCapability(MobileCapabilityType.APP, app);

            caps.setCapability(MobileCapabilityType.PLATFORM_NAME, "Android");
            caps.setCapability(MobileCapabilityType.PLATFORM_VERSION, "5.1");
            caps.setCapability(MobileCapabilityType.DEVICE_NAME, "Nexus 5");
            caps.setCapability("appPackage", "com.example.hybridtestapp");
            caps.setCapability("appActivity", "com.example.hybridtestapp.MainActivity");
//        caps.setCapability("noReset", true);

            driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"),caps);
            driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        } catch (MalformedURLException e){
            System.out.print(e);
//         System.err.print("");
        }

    }

    @Test
    public void testExample(){
        Set<String> contexts = driver.getContextHandles();
        for (String context : contexts) {
            System.out.println(context); //it will print NATIVE_APP \n WEBVIEW_com.example.hybridtestapp
        }
//        driver.context("WEBVIEW_com.example.hybridtestapp");
        driver.context((String) contexts.toArray()[1]);
        WebElement firstName=driver.findElement(By.name("fname"));
        firstName.sendKeys("test");
        WebElement lastName=driver.findElement(By.name("lname"));
        lastName.sendKeys("test");
        WebElement age=driver.findElement(By.name("age"));
        age.sendKeys("26");
        WebElement username=driver.findElement(By.name("username"));
        username.sendKeys("appiumTester");
        WebElement password=driver.findElement(By.id("psw"));
        password.sendKeys("appium@123");
        WebElement registerButton=driver.findElement(By.id("register"));
        registerButton.click();
		
		//	Switching back to NATIVE_APP
		driver.context("NATIVE_APP");

    }
    @AfterClass
    public void tearDown(){
        driver.closeApp();
    }
}

