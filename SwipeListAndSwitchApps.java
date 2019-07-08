package modules;

import io.appium.java_client.android.Activity;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.*;

import java.net.MalformedURLException;
import java.net.URL;

public class ContactTest {

    public AndroidDriver driver;
    Dimension size;

    @BeforeMethod
    public void setUp() throws MalformedURLException {
        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability(MobileCapabilityType.PLATFORM_NAME, "android");
        caps.setCapability(MobileCapabilityType.PLATFORM_VERSION, "7.1.1");
        caps.setCapability(MobileCapabilityType.DEVICE_NAME, "00c274641540bbc3");
        caps.setCapability("appPackage", "com.google.android.contacts");
        caps.setCapability("appActivity", "com.android.contacts.activities.PeopleActivity");

        driver = new AndroidDriver(new URL("http://0.0.0.0:4723/wd/hub"),caps);
    }
    @Test
    public void scrollTest()
    {
        driver.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(new UiSelector().text(\"Xuan\"));");

    }

    @Test(priority = 1)
    public void swipeTest() throws InterruptedException{
        size = driver.manage().window().getSize();
        System.out.println(size);
        int startx = (int) (size.width * 0.70);
        int endx = (int) (size.width * 0.30);
        int starty = size.height / 2;
        System.out.println("startx = " + startx + " ,endx = " + endx + " , starty = " + starty);
        driver.swipe(startx, starty, endx, starty, 3000);
        Thread.sleep(2000);
        driver.swipe(endx, starty, startx, starty, 3000);
        Thread.sleep(2000);
    }

    @Test(priority = 2)
    public void addContact(){
        driver.findElementById("com.android.contacts:id/floating_action_button").click();
        driver.findElement(By.xpath("//android.widget.TextView[@text='ngayxuamaixa90@gmail.com']")).click();

        WebElement nameField = driver.findElement(By.xpath("//android.widget.EditText[@text='Name']"));
        nameField.sendKeys("Contact 01");

        driver.findElementById("com.android.contacts:id/menu_save").click();
    }

    @Test(priority = 3)
    public void switchApps()
    {
////         * For java-client older versions
////         *
//        // Switch to dial app
//        driver.startActivity("com.android.dialer", ".DialtactsActivity");
//        //Switch back to contact app again
//        driver.startActivity("com.android.contacts", "com.android.contacts.activities.PeopleActivity");

        /**
         * For Java-client version 5
         */
        Activity activity = new Activity("com.google.android.dialer", ".DialtactsActivity");
        driver.startActivity(activity);

        Activity activity1 = new Activity("com.google.android.contacts", "com.android.contacts.activities.PeopleActivity");
        driver.startActivity(activity1);
    }

    @AfterMethod
    public void tearDown(){

        driver.closeApp();
    }
}