package jerry_selenium_demo3;

import java.util.Arrays;
import java.util.List;

class JerryTestCase {

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
