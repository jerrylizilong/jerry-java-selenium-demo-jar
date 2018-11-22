package jerry_selenium_demo2;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
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
        driver = JerrySelenium.initDriver("Chrome");
        driver.get("http://www.baidu.com/");

    }

    @Test
    public void testBaiduNews() throws IOException, InterruptedException {
        driver.findElement(By.partialLinkText("新闻")).click();
        String methodName = String.valueOf(Thread.currentThread().getStackTrace()[1].getMethodName());
        JerrySelenium.saveScreenshot(driver,methodName);
        JerrySelenium.assertPageTitle(driver,"百度新闻");
    }

    @Test
    public void testBaiduMap() throws IOException, InterruptedException {
        driver.findElement(By.partialLinkText("地图")).click();
        String methodName = String.valueOf(Thread.currentThread().getStackTrace()[1].getMethodName());
        JerrySelenium.saveScreenshot(driver,methodName);
        JerrySelenium.assertPageTitle(driver, "百度地图");
    }

    @Test
    public void testBaiduVedio() throws IOException, InterruptedException {
        driver.findElement(By.partialLinkText("视频")).click();
        String methodName = String.valueOf(Thread.currentThread().getStackTrace()[1].getMethodName());
        JerrySelenium.saveScreenshot(driver,methodName);
        JerrySelenium.assertPageTitle(driver, "百度视频");
    }

    @AfterMethod
    public void afterClass() throws IOException {
        System.out.printf("\nthis is after class\n");
        System.out.printf(driver.getCurrentUrl());
        driver.close();
    }
}
