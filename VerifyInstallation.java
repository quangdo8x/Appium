package modules;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;
import java.net.URL;

public class VerifyInstallation {
    public static void main(String[] args) throws MalformedURLException {
        AppiumDriver driver;
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("platformName", "Android");
        capabilities.setCapability("deviceName", "192.168.0.2:5555");
        capabilities.setCapability("appActivity", "com.ebay.mobile.activities.eBay");
        capabilities.setCapability("appPackage", "com.ebay.mobile");
        driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);



        if(!driver.isAppInstalled("com.experitest.ExperiBank"))
        {
            driver.installApp("F:\\Learning\\automation_latest\\PolicyBazar\\apps\\EriBank.apk");
            System.out.println("App Installed");
        }
        else
        {
            driver.removeApp(("com.experitest.ExperiBank"));
            System.out.println("App removed");
            driver.installApp("F:\\Learning\\automation_latest\\PolicyBazar\\apps\\EriBank.apk");
            System.out.println("App Installed");

        }
    }
}
