package jerry_selenium_demo1;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.IOException;
import java.net.MalformedURLException;


public class JerryTestngSelenium {
    WebDriver driver ;

    @BeforeMethod
    public void beforClass() throws MalformedURLException {
        System.out.printf("\nthis is a before class");

        // 调用本地driver
        driver = new ChromeDriver();

        // 调用远程 driver
//        DesiredCapabilities chromeOption= DesiredCapabilities.chrome();
//        driver = new RemoteWebDriver(new URL("http://172.16.100.168:4444/wd/hub/"),chromeOption);

        driver.manage().window().setSize(new Dimension(1920,1080));
        driver.get("http://www.baidu.com/");

    }

    @Test
    public void testBaiduNews(){
        driver.findElement(By.partialLinkText("新闻")).click();
//        Assert.assertTrue(driver.getTitle().contains("百度新闻"));
        Assert.assertTrue(JerrySelenium.assertPageTitle(driver,"百度新闻"));
    }

    @Test
    public void testBaiduMap(){
        driver.findElement(By.partialLinkText("地图")).click();
        Assert.assertTrue(JerrySelenium.assertPageTitle(driver, "百度地图"));
    }

    @Test
    public void testBaiduVedio(){
        driver.findElement(By.partialLinkText("视频")).click();
        Assert.assertTrue(JerrySelenium.assertPageTitle(driver, "百度视频"));
    }

    @AfterMethod
    public void afterClass() throws IOException {
        System.out.printf("\nthis is after class\n");
        System.out.printf(driver.getCurrentUrl());
        // 保存截图
        JerrySelenium.saveScreenshot(driver);
        driver.close();
    }
}
