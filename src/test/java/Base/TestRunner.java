package Base;

import org.testng.TestNG;
import org.testng.xml.XmlClass;
import org.testng.xml.XmlSuite;
import org.testng.xml.XmlTest;

import java.util.ArrayList;
import java.util.List;

public class TestRunner {

    public static void main(String[] args) {
        int numberOfTests = 1;

        XmlSuite suite = new XmlSuite();
        suite.setName("Parallel Test Suite");
        suite.setParallel(XmlSuite.ParallelMode.TESTS);
        suite.setThreadCount(numberOfTests);

        List<XmlTest> tests = new ArrayList<>();

        for (int i = 1; i <= numberOfTests; i++) {
            XmlTest test = new XmlTest(suite);
            test.setName("Test_" + i);
            List<XmlClass> classes = new ArrayList<>();
            classes.add(new XmlClass("Test.BurgerMenuTest")); // use full package name if not in default
            classes.add(new XmlClass("Test.CartTest"));
            classes.add(new XmlClass("Test.CheckOutTest"));
            classes.add(new XmlClass("Test.CheckOutCompleteTest"));
            classes.add(new XmlClass("Test.CheckOutPageStepTwoTest"));
            classes.add(new XmlClass("Test.InventoryItemPageTest"));
            classes.add(new XmlClass("Test.LoginTest"));




            test.setXmlClasses(classes);
            tests.add(test);
        }

        TestNG testng = new TestNG();
        List<XmlSuite> suites = new ArrayList<>();
        suites.add(suite);
        testng.setXmlSuites(suites);
        testng.run();
    }
}