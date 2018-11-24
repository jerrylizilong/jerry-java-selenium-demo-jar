package jerry_selenium;

import java.io.*;
import java.util.Arrays;
import java.util.List;

class JerryTestCase {

    static String[] readTestCaseFromTXT(String filename){
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



    static String[] splitCases(String caseString){
        caseString = caseString.replaceAll("\r","");
        return caseString.split(",");
    }

    static String[] splitCaseList(String caseString){
        caseString=caseString.replaceAll("\r","");
        return caseString.split("\n");
    }

    static List splitSteps(String stepString){
        stepString=stepString.replaceAll("\r","");
        stepString =stepString.replace("@@","|");
//        System.out.print(stepString);
        String[] caseSteps = stepString.split("\\|");
        return Arrays.asList(caseSteps);
    }
}
