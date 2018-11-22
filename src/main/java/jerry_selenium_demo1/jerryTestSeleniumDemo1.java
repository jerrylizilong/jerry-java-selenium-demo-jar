package jerry_selenium_demo1;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;


public class jerryTestSeleniumDemo1 {

    public static void main(String [] args) throws IOException {

        WebDriver driver ;
        System.out.printf("\nthis is a before class");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("http://www.baidu.com/");
        driver.findElement(By.partialLinkText("新闻")).click();
        String methodName = String.valueOf(Thread.currentThread().getStackTrace()[1].getMethodName());
        File pic = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        SimpleDateFormat currentTime = new SimpleDateFormat("yyyyMMdd-HHmmss");
        String time = currentTime.format(new Date());
        FileUtils.copyFile(pic, new File("test" + File.separator +methodName+"-"+ time + ".jpg"));
        assert driver.getTitle().contains("百度新闻");
        driver.quit();
    }


}
