package jerry_selenium_demo3;

import org.openqa.selenium.WebDriver;

import java.io.IOException;
import java.util.List;

public class jerryTestCaseTest {

    public static void main(String[] arg) throws IOException, InterruptedException {
        String caseDetail;
        caseDetail = "Chrome,前往|http://www.baidu.com,填写|id@@kw@@selenium,验证文字|id@@su@@百度一下,点击|id@@su,验证|Web Browser Automation,截图";
        runCase(caseDetail);

    }


    public static void runCase(String caseDetail) throws IOException, InterruptedException {
        // 执行单条用例，根据 ， 拆分步骤并分别执行
        String[] caseString = JerryTestCase.splitCases(caseDetail);
        System.out.print(caseString[0]);
        System.out.print(caseString[0].contains("Chrome"));
        if(caseString[0].contentEquals("Chrome")){
            // webdriver 初始化
            WebDriver driver=JerrySelenium.initDriver(caseString[0]);

            // 逐步执行
            for(int i=1;i<caseString.length;i++) {
                List<String> stepString = JerryTestCase.splitSteps(caseString[i]);
                String keyword = stepString.get(0);
                runStep(driver,keyword, stepString);
            }
            // 执行完成后退出 webdriver
            driver.quit();
        }
    }

    public static void runStep(WebDriver driver,String keyword, List<String> step) throws IOException, InterruptedException {
        // 根据关键字匹配对应的步骤执行
        System.out.printf("keyword is :"+keyword+"\n");
        System.out.printf("steps is :"+step+"\n");
        if(keyword.contentEquals("前往")){
            driver = JerrySelenium.goToURL(driver,step.get(1).toString());
        }else if(keyword.contentEquals("点击")){
            JerrySelenium.click(JerrySelenium.findElemnt(driver,step));
        }else if(keyword.contentEquals("填写")){
            JerrySelenium.sendkeys(JerrySelenium.findElemnt(driver,step),step.get(3).toString());
        }else if(keyword.contentEquals("验证文字")){
            JerrySelenium.assertElementContainsText(JerrySelenium.findElemnt(driver,step),step.get(3).toString());
        }else if (keyword.contentEquals("验证")){
            JerrySelenium.assertText(driver,step.get(1).toString());
        }else if (keyword.contentEquals("验证标题")){
            JerrySelenium.assertPageTitle(driver,step.get(1).toString());
        }else if (keyword.contentEquals("截图")){
            JerrySelenium.saveScreenshot(driver,"normal");
        }else {
            System.out.printf("Keyword not defined!"+keyword);
        }

    }

}
