package jerry_selenium;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

import static jerry_selenium.JerryTestCase.*;

public class jerrySeleniumRun {

    public static void main(String[] args) throws IOException, InterruptedException {

        String filename = args[0];

//         执行单条用例：
//        String caseDetail = "Chrome,前往|http://www.baidu.com,点击|name@@tj_trnews,验证标题|百度新闻";
//        runCase(caseDetail);
        boolean result;

        // 从文件中获取用例列表，逐条执行
        File direction = new File("");
//        String filename=direction.getAbsolutePath()+File.separator+"cases.txt";
        String resultfilename=direction.getAbsolutePath()+File.separator+"result.txt";
        System.out.println(filename);
        ArrayList<String> resultlist;
        resultlist = new ArrayList();

        String[] caseList = readTestCaseFromTXT(filename);
        if ((caseList != null ? caseList.length : 0) >0){
            for (String aCaseList : caseList) {
                System.out.println(aCaseList);
//                resultlist.add(aCaseList);
                result = runCase(aCaseList);
                System.out.print(result);
                resultlist.add(aCaseList+"   result is :"+String.valueOf(result)+"\n");
            }
        }

        BufferedWriter out = new BufferedWriter(
                new OutputStreamWriter(new FileOutputStream(resultfilename,true)));
        for(String runresult: resultlist){
            out.write(String.valueOf(runresult));
        }
        out.close();
    }

    private static String[] readTestCaseFromTXT(String filename){
        String encoding = "UTF-8";
        File file = new File(filename);
        Long filelength = file.length();
        byte[] fileContenct = new byte[filelength.intValue()];
        try {
            FileInputStream inFile = new FileInputStream(file);
            inFile.read(fileContenct);
            inFile.close();
        }catch (FileNotFoundException e){
            e.printStackTrace();
        }catch (IOException e){
            e.printStackTrace();
        }
        try {
            return splitCaseList( new String(fileContenct,encoding));
        }catch (UnsupportedEncodingException e){
            e.printStackTrace();
            return null;
        }
    }



    private static boolean runCase(String caseDetail) throws IOException, InterruptedException {
        // 执行单条用例，根据 ， 拆分步骤并分别执行
        boolean result = true;
        String[] caseString = splitCases(caseDetail);
        System.out.print(caseString[0]);
        System.out.print(caseString[0].contains("Chrome"));
        if(caseString[0].contentEquals("Chrome")){
            // webdriver 初始化
            WebDriver driver=JerrySelenium.initDriver(caseString[0]);

            // 逐步执行
            for(int i=1;i<caseString.length;i++) {
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
            // 执行完成后退出 webdriver
            driver.quit();
        }
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
            JerrySelenium.assertElementContainsText(JerrySelenium.findElemnt(driver,step),step.get(3));
        }else if (keyword.contentEquals("验证")){
            JerrySelenium.assertText(driver,step.get(1));
        }else if (keyword.contentEquals("验证标题")){
            JerrySelenium.assertPageTitle(driver,step.get(1));
        }else if (keyword.contentEquals("截图")){
            JerrySelenium.saveScreenshot(driver,"normal");
        }else {
            System.out.print("Keyword not defined!" + keyword);
        }

    }

}
