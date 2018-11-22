package jerry_selenium_demo3;

import java.util.Arrays;
import java.util.List;

public class JerryTestCase {

    public static String[] splitCases(String caseString){
        String[] caseSteps = caseString.split(",");
        return caseSteps;
    }

    public static List splitSteps(String stepString){
        stepString =stepString.replace("@@","|");
//        System.out.print(stepString);
        String[] caseSteps = stepString.split("\\|");
        List<String> caseStep = Arrays.asList(caseSteps);
        return caseStep;
    }
}
