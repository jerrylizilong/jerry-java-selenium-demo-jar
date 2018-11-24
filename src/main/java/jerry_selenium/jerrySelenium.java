package jerry_selenium;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import java.io.File;
import java.io.IOException;
import java.lang.ref.WeakReference;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;

class JerrySelenium {
    // 具体的用例执行方法定义

    public static void main(String [] args){
        String parant = "百度新闻——全球最大的中文新闻平台";
        String target = "百度新闻";
        System.out.print(parant.contains(target));

    }

        // 初始化driver： 根据传入的浏览器类型初始化对应的浏览器驱动
    static WebDriver initDriver(String browserType) throws MalformedURLException {
        WebDriver driver;
        Boolean usingLocal = true;

        // 调用本地driver
        if(true==usingLocal) driver = new ChromeDriver();
        else {
            // 调用远程 driver
            DesiredCapabilities driverOption;
            if ("Firefox".equals(browserType)){
                driverOption = DesiredCapabilities.firefox();
            }else {
                driverOption= DesiredCapabilities.chrome();
            }
            driver = new RemoteWebDriver(new URL("http://172.16.100.168:4444/wd/hub/"),driverOption);
        }
        driver.manage().window().setSize(new Dimension(1920,1080));
        return driver;
    }

    // 前往指定的 url
    static WebDriver goToURL(WebDriver driver, String url){
        driver.get(url);
        return driver;
    }

    // 根据指定方法定位元素
    static WebElement findElementBy(WebDriver driver, String method, String value){
        WebElement element = null;
        if ("id".equals(method)){
            element = driver.findElement(By.id(value));
        }else if("name".equals(method)){
            element = driver.findElement(By.name(value));
        }else if("class".equals(method)){
            element = driver.findElement(By.className(value));
        }else if ("xpath".equals(method)){
            element = driver.findElement(By.xpath(value));
        }else if ("css".equals(method)){
            element = driver.findElement(By.cssSelector(value));
        }else if ("text".equals(method)){
            element = driver.findElement(By.partialLinkText(value));
        }
        return element;
    }


    // 根据指定方法定位元素, 返回元素组
    static List<WebElement> findElementsBy(WebDriver driver, String method, String value){
        List<WebElement> elementList = null;
        if ("id".equals(method)){
            elementList = driver.findElements(By.id(value));
        }else if("name".equals(method)){
            elementList = driver.findElements(By.name(value));
        }else if("class".equals(method)){
            elementList = driver.findElements(By.className(value));
        }else if ("xpath".equals(method)){
            elementList = driver.findElements(By.xpath(value));
        }else if ("css".equals(method)){
            elementList = driver.findElements(By.cssSelector(value));
        }else if ("text".equals(method)){
            elementList = driver.findElements(By.partialLinkText(value));
        }
        return elementList;
    }

    // 查找元素
    static WebElement findElemnt(WebDriver driver, List<String> step){
        return findElementBy(driver,step.get(1),step.get(2));
    }

    // 切换到指定 iframe
    static void switchIframe(WebDriver driver,WebElement element){
        driver.switchTo().frame(element);
    }

    // 切换窗口
    static void switchWindow(WebDriver driver){
        for(String name: driver.getWindowHandles()){
            if(!driver.getWindowHandle().equals(name)){
                driver.switchTo().window(name);
                break;
            }
        }
    }



    // 切换到默认 iframe
    static void switchDefaultIframe(WebDriver driver){
        driver.switchTo().defaultContent();
    }

    // 点击给定的元素
    static void click(WebElement element){
        element.click();
    }

    // 在给定的元素中输入文字
    static void sendkeys(WebElement element, String text){
        element.sendKeys(text);
    }

        // 保存截图
    static void saveScreenshot(WebDriver driver, String name) throws IOException {
        File direction = new File("");
        File pic = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        SimpleDateFormat currentTime = new SimpleDateFormat("yyyyMMdd-HHmmss");
        String time = currentTime.format(new Date());
        FileUtils.copyFile(pic, new File(direction.getAbsolutePath()+File.separator+"test" + File.separator+name +"-"+ time + ".jpg"));
    }

    // 验证网页标题是否包含指定字符串
    static void assertPageTitle(WebDriver driver, String target) throws NoSuchElementException, InterruptedException {
        Thread.sleep(2000);
        target=target.replaceAll("\r","");
        String title = driver.getTitle();
        System.out.println(title);
        System.out.println(target);
        Assert.assertTrue(title.contains(target));
    }

    // 验证网页中是否包含指定字符串
    static void assertText(WebDriver driver, String text) throws InterruptedException {
        Thread.sleep(2000);
        text=text.replaceAll("\r","");
        Assert.assertTrue(!(driver.findElements(By.xpath("//*[contains(.,'" + text + "')]")).isEmpty()));
       }

       // 验证指定元素中是否包含指定字符串
    static void assertElementContainsText(WebElement element, String text,boolean isContains) throws InterruptedException {
        Thread.sleep(2000);
        text = text.replaceAll("\r","");
        String elementText;
        if(element.getText().length()>0){
            elementText = element.getText();
        }else {
            elementText = element.getAttribute("value");
        }
        if(isContains){
            Assert.assertTrue(elementText.contains(text));
        }else {
            Assert.assertTrue(!(elementText.contains(text)));
        }

       }

    static void select(WebDriver driver,String method, String value, String optionMethod, String optionValue){
        Select selectOption=new Select(findElementBy(driver,method,value));
        if(optionMethod.equals("index")){
            selectOption.selectByIndex(Integer.parseInt(optionValue));
        }else if(optionMethod.equals("value")){
            selectOption.selectByValue(optionValue);
        }else if (optionMethod.equals("text")){
            selectOption.selectByVisibleText(optionValue);
        }else if (optionMethod.equals("text_part")){
            for (WebElement option: selectOption.getOptions()){
                if(option.getText().contains(optionValue)) option.click();
            }
        }else {
            throw new org.openqa.selenium.NoSuchElementException("option method not defined!");
        }
    }

    static void clickText(WebDriver driver,String text){
        driver.findElement(By.partialLinkText(text));
    }

    static void wait(String waitTime) throws InterruptedException {
        Thread.sleep(Integer.parseInt(waitTime)*1000);
    }



}
