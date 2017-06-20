package korol.ivan;

import com.asprise.ocr.Ocr;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.TouchAction;
import korol.ivan.data.ConfigurationProperties;
import korol.ivan.helpers.ToastMessages;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebElement;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author by Ivan Korol on 6/20/2017.
 */
public abstract class MainFunction {
    protected AppiumDriver driver;

    public MainFunction(AppiumDriver driver) {
        this.driver = driver;
        this.driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
    }

    protected final void takeScreenShot(String fileName) {

        File src = driver.getScreenshotAs(OutputType.FILE);
        try {
            org.apache.commons.io.FileUtils.copyFile(src, new File("./ScreenShots/" + fileName + ".png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    protected boolean isElementPresent(By locator) {

        driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
        List<WebElement> list = driver.findElements(locator);
        driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
        if (list.size() == 0) {
            return false;
        } else {
            return list.get(0).isDisplayed();
        }

    }

    protected void tapOnElement(int coor_x, int coor_y) {
        new TouchAction(driver).tap(coor_x, coor_y).perform();
    }

    protected void sleepSecond(int second) {
        try {
            Thread.sleep(second * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    protected void swipeScreen(String navigation) {
        switch (navigation) {
            case "up":
                driver.swipe(270,460, 270, 888, 2000);
                break;
            case "down":
                driver.swipe(270,460,270,1,2000);
                break;
            case "left":
                driver.swipe(270, 460, 535, 460, 2000);
                break;
            case "right":
                driver.swipe(270, 460, 1, 460, 2000);
                break;
            default:
                throw new IllegalArgumentException("Invalid direction specified " + navigation + "required: up, down, left, right");
        }
    }

    protected boolean validToastMessage(ToastMessages tm) {
        String result;
        String toastForCheck = ConfigurationProperties.getProperty("toasts.properties", tm.getToastName());
        takeScreenShot(tm.getScreenName());
        String imagePath = "./ScreenShots/Toast/" + tm.getScreenName() + ".png";
        Ocr.setUp();
        Ocr ocr = new Ocr();
        ocr.startEngine(tm.getLanguage(), Ocr.SPEED_FAST);
        result = ocr.recognize(new File[]{new File(imagePath)}, Ocr.RECOGNIZE_TYPE_TEXT, Ocr.OUTPUT_FORMAT_PLAINTEXT);
        /*System.out.println("result: "+ result);
        System.out.println("For check: "+ toastForCheck);*/
        ocr.stopEngine();

        return result.contains(toastForCheck);
    }
}
