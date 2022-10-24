package SeleniumTests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;

public class ElementHandlingDemo {

    //polymorphism
    WebDriver driver;

    @BeforeMethod
    public void setup() {
        String browser = "chrome";
        if (browser.equals("firefox")) {
            WebDriverManager.firefoxdriver().setup();
            driver = new FirefoxDriver();
        } else if (browser.equals("chrome")) {
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver();
        } else if (browser.equals("ie")) {
            WebDriverManager.iedriver().setup();
            driver = new InternetExplorerDriver();
        }
        driver.get("https://rahulshettyacademy.com/AutomationPractice/");
    }

    @AfterMethod
    public void tearDown() {
        sleep(3);
        driver.quit();
    }

    @Test
    public void radioButtonsTest() {
        //dynamic xpath
        String radioButtonXpath = "//label[contains(.,'Radio$$')]/input";
        //user wants to select 3rd radio button
        driver.findElement(By.xpath(radioButtonXpath.replace("$$", "1"))).click();
    }

    @Test
    public void dropdownTest() {
        WebElement dropdownele = driver.findElement(By.id("dropdown-class-example"));
        Select select = new Select(dropdownele);
        select.selectByVisibleText("Option2");
    }

    @Test
    public void checkboxesTest() {
        String checkboxesXpath = "//label[contains(.,'Option$$')]/input";
        String[] options = {"2","3"};
        for (String opt : options) {
            driver.findElement(By.xpath(checkboxesXpath.replace("$$", opt))).click();
        }
        for (String opt : options) {
            boolean actualvalue = driver.findElement(By.xpath(checkboxesXpath.replace("$$", opt))).isSelected();
            boolean expectedvalue = true;
            if (actualvalue == expectedvalue) {
                System.out.println("passed!");
            } else {
                System.out.println("failed!");
            }
        }

        List<WebElement> wlist = driver.findElements(By.xpath("//label[contains(.,'Optionskbkvbvbksdb')]"));
        if(wlist.size()>0){
            System.out.println("ele is present ");
        }else{
            System.out.println("ele is not present");
        }
    }


    public static void sleep(int seconds) {
        try {
            Thread.sleep(seconds * 1000L);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

}