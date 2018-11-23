package jerry_selenium_demo3;


import org.openqa.selenium.WebDriver;

import java.io.IOException;

public class jerryTestSeleniumDemo3 {
    public static void main(String [] args) throws IOException {
        WebDriver driver = JerrySelenium.initDriver("Chrome");
        driver.get("http://www.baidu.com");
        JerrySelenium.findElementBy(driver,"id","kw").sendKeys("testerhome");
        JerrySelenium.findElementBy(driver,"id","su").click();
        JerrySelenium.saveScreenshot(driver,"demo3");
        System.out.print(driver.getTitle());
        driver.quit();

    }
}
