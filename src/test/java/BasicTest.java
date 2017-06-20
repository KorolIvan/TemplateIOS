import io.appium.java_client.AppiumDriver;
import io.appium.java_client.ios.IOSDriver;
import korol.ivan.data.ConfigurationProperties;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;
import java.net.URL;

/**
 * @author by Ivan Korol on 6/20/2017.
 */
public class BasicTest {

    private AppiumDriver driver;

    @Before
    public void setUp() throws MalformedURLException {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("platformName", "iOS");
        capabilities.setCapability("deviceName", ConfigurationProperties.getProperty("configuration", "divice_id"));
        capabilities.setCapability("platformVersion", ConfigurationProperties.getProperty("configuration", "iOSVersion"));
        capabilities.setCapability("browserName", "");
        //capabilities.setCapability("deviceOrientation", "portrait");
        capabilities.setCapability("bundleId", ConfigurationProperties.getProperty("configuration", "programmPath"));
        capabilities.setCapability("appiumVersion", ConfigurationProperties.getProperty("configuration", "appiumV"));

        driver = new IOSDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
    }


    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

}
