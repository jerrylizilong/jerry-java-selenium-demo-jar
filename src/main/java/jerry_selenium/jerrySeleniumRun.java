package jerry_selenium;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

import static jerry_selenium.JerryTestCase.*;

public class jerrySeleniumRun {

    public static void main(String[] args) throws IOException, InterruptedException {

        String filename = args[0];
        boolean result;

        // 从文件中获取用例列表，逐条执行
        File direction = new File("");
        String resultfilename=direction.getAbsolutePath()+File.separator+"result.txt";
        System.out.println(filename);
        ArrayList<String> resultlist;
        resultlist = new ArrayList();

        String[] caseList = JerryTestCase.readTestCaseFromTXT(filename);
        // 逐条执行用例
        if ((caseList != null ? caseList.length : 0) >0){
            for (String aCaseList : caseList) {
                System.out.println(aCaseList);
                result = runCase(aCaseList);
                System.out.print(result);
                resultlist.add(aCaseList+"   result is :"+String.valueOf(result)+"\n");
            }
        }

        // 输出测试结果，保存到 result 文件
        BufferedWriter out = new BufferedWriter(
                new OutputStreamWriter(new FileOutputStream(resultfilename,true)));
        for(String runresult: resultlist){
            out.write(String.valueOf(runresult));
        }
        out.close();
    }



    private static boolean runCase(String caseDetail) throws IOException, InterruptedException {
        // 执行单条用例，根据 ， 拆分步骤并分别执行
        boolean result = true;
        // 拆分用例步骤
        String[] caseString = splitCases(caseDetail);
               // webdriver 初始化
        WebDriver driver=JerrySelenium.initDriver(caseString[0]);

            // 逐步执行
        for(int i=1;i<caseString.length;i++) {
            //拆分步骤格式
            List<String> stepString = splitSteps(caseString[i]);
            String keyword = stepString.get(0);
            try {
                runStep(driver,keyword, stepString);
            }catch (NoSuchElementException e){
                e.printStackTrace();
                result = false;
                break;
            }catch (AssertionError e){
                e.printStackTrace();
                result = false;
                break;
            }
        }

        if(!result){
            JerrySelenium.saveScreenshot(driver,"error");
        }

        // 执行完成后退出 webdriver
        driver.quit();
        return result;
    }

    private static void runStep(WebDriver driver, String keyword, List<String> step) throws IOException, InterruptedException {
        // 根据关键字匹配对应的步骤执行
        System.out.print("keyword is :" + keyword + "\n");
        System.out.print("steps is :" + step + "\n");
        Thread.sleep(200);
        if(keyword.contentEquals("前往")){
            JerrySelenium.goToURL(driver,step.get(1));
        }else if(keyword.contentEquals("点击")){
            JerrySelenium.click(JerrySelenium.findElemnt(driver,step));
        }else if(keyword.contentEquals("填写")){
            JerrySelenium.sendkeys(JerrySelenium.findElemnt(driver,step),step.get(3));
        }else if(keyword.contentEquals("验证文字")){
            JerrySelenium.assertElementContainsText(JerrySelenium.findElemnt(driver,step),step.get(3),true);
        }else if(keyword.contentEquals("验证文字非")){
            JerrySelenium.assertElementContainsText(JerrySelenium.findElemnt(driver,step),step.get(3),false);
        }else if (keyword.contentEquals("验证")){
            JerrySelenium.assertText(driver,step.get(1));
        }else if (keyword.contentEquals("验证标题")){
            JerrySelenium.assertPageTitle(driver,step.get(1));
        }else if (keyword.contentEquals("截图")){
            JerrySelenium.saveScreenshot(driver,"normal");
        }else if (keyword.contentEquals("切换主页")){
            JerrySelenium.switchDefaultIframe(driver);
        }else if (keyword.contentEquals("切换窗口")){
            JerrySelenium.switchWindow(driver);
        }else if (keyword.contentEquals("切换")){
            JerrySelenium.switchIframe(driver,JerrySelenium.findElemnt(driver,step));
        }else if (keyword.contentEquals("点击文字")){
            JerrySelenium.clickText(driver,step.get(1));
        }else if (keyword.contentEquals("选择")){
            JerrySelenium.select(driver,step.get(1),step.get(2),step.get(3),step.get(4));
        }else if (keyword.contentEquals("等待")){
            JerrySelenium.wait(step.get(1));
        }else {
            System.out.print("Keyword not defined!" + keyword);
        }

    }

}
