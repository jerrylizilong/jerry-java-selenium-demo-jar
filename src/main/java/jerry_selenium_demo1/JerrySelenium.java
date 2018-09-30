package jerry_selenium_demo1;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.NoSuchElementException;

public class JerrySelenium {
    public static void main(String [] args){

    }

        // 初始化driver： 根据传入的浏览器类型初始化对应的浏览器驱动
    public static WebDriver initDriver(String browserType) throws MalformedURLException {
        WebDriver driver;
        // 调用本地driver
//        driver = new ChromeDriver();

        // 调用远程 driver
        DesiredCapabilities driverOption;
        if ("Firefox".equals(browserType)){
            driverOption = DesiredCapabilities.firefox();
        }else {
            driverOption= DesiredCapabilities.chrome();
        }

        driver = new RemoteWebDriver(new URL("http://172.16.100.168:4444/wd/hub/"),driverOption);
        driver.manage().window().setSize(new Dimension(1920,1080));
        return driver;
    }

        // 保存截图
    public static void saveScreenshot(WebDriver driver,String name) throws IOException {
        File pic = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        SimpleDateFormat currentTime = new SimpleDateFormat("yyyyMMdd-HHmmss");
        String time = currentTime.format(new Date());
        FileUtils.copyFile(pic, new File("test" + File.separator+name +"-"+ time + ".jpg"));
    }

    public static boolean assertPageTitle(WebDriver driver,String target) throws NoSuchElementException{
        return driver.getTitle().contains(target);
    }
}
