package jerry_selenium_demo1;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.NoSuchElementException;

public class JerrySelenium {
    WebDriver driver;
    public static void main(String [] args){

    }

    public static void saveScreenshot(WebDriver driver) throws IOException {
        File pic = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        SimpleDateFormat currentTime = new SimpleDateFormat("yyyyMMdd-HHmmss");
        String time = currentTime.format(new Date());
        FileUtils.copyFile(pic, new File("test" + File.separator + time + ".jpg"));
    }

    public static boolean assertPageTitle(WebDriver driver,String target) throws NoSuchElementException{
        return driver.getTitle().contains(target);
    }
}
