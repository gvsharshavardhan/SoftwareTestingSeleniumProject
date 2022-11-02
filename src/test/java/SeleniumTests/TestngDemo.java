package SeleniumTests;

import org.testng.annotations.*;

public class TestngDemo {

    @BeforeSuite
    public void bs() {
        System.out.println("before suite");
    }

    @BeforeClass
    public void bc() {
        System.out.println("before class");
    }

    @BeforeTest
    public void bt() {
        System.out.println("before test");
    }

    @BeforeMethod
    public void setup() {
        System.out.println("start test");
    }

    @AfterMethod
    public void closing() {
        System.out.println("stop test");
        System.out.println("_________________________________________________");
    }

    @Test(priority = 8)
    @Parameters({"name"})
    public void appleTest(@Optional("all the best!") String name) {
        System.out.println("apple website :: "+ name);
    }

    @Test(priority = 1)
    @Parameters("name")
    public void orangeTest(String name) {
        System.out.println("orange website :: " + name);
    }

    @Test(priority = 18)
    @Parameters("browser")
    public void mangoTest(String browser) {
        System.out.println("mango website :: " + browser);
    }

}