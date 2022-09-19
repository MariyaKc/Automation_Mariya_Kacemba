package lesson9;

import org.testng.annotations.*;

public class Lesson9_1Test {

    // посмотреть,что за чем выполняется

    @BeforeTest(groups = "test9")
    public void beforeTest() {
        System.out.println("Hello i am @BeforeTest");
    }

    @BeforeMethod(groups = {"test9new", "test9"})
    public void beforeMethod() {
        System.out.println("Hello i am @BeforeMethod");
    }

    @BeforeClass(groups = "test9")
    public void beforeClass() {
        System.out.println("Hello i am @BeforeClass");
    }

    @BeforeSuite(groups = "test9")
    public void beforeSuite() {
        System.out.println("Hello i am @BeforeSuite");
    }

    @BeforeGroups(groups = "test9")
    public void beforeGroups() {
        System.out.println("Hello i am @BeforeGroups");
    }

    @Test(groups = "test9")
    public void test1() {
        System.out.println("Hello i am @Test 1");
    }

    @Test(groups = "test9new")
    public void test2() {
        System.out.println("Hello i am @Test 2");
    }

    @AfterTest(groups = "test9")
    public void afterTest() {
        System.out.println("Hello i am @AfterTest");
    }

    @AfterSuite(groups = "test9")
    public void afterSuite() {
        System.out.println("Hello i am @AfterSuite");
    }

    @AfterGroups(groups = "test9")
    public void afterGroups() {
        System.out.println("Hello i am @AfterGroups");
    }

    @AfterMethod(groups = {"test9new", "test9"})
    public void afterMethod() {
        System.out.println("Hello i am @AfterMethod");
    }

    @AfterClass(groups = "test9")
    public void afterClass() {
        System.out.println("Hello i am @AfterClass");
    }
}

