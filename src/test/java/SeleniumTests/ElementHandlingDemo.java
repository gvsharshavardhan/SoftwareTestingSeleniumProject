package SeleniumTests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;
import java.util.Set;

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
//        driver.get("https://rahulshettyacademy.com/AutomationPractice/");
    }

    @AfterMethod
    public void tearDown() {
        sleep(3);
        driver.quit();
    }

    @Test//annotations
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
        String[] options = {"2", "3"};
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
        if (wlist.size() > 0) {
            System.out.println("ele is present ");
        } else {
            System.out.println("ele is not present");
        }
    }

    @Test
    public void windowTest() {
        String parentWindowID = driver.getWindowHandle();
        driver.findElement(By.xpath("//button[@id='openwindow']")).click();
        Set<String> windowIDs = driver.getWindowHandles();
        for (String id : windowIDs) {
            if (!id.equals(parentWindowID)) {
                driver.switchTo().window(id);
            }
        }
//        if(driver.getTitle().equals("QA Click Academy | Selenium,Jmeter,SoapUI,Appium,Database testing,QA Training Academy")){
//            System.out.println("PASSED!");
//        }else{
//            System.out.println("FAILED!");
//        }

        Assert.assertEquals(driver.getTitle(), "QA Click Academy | Selenium,Jmeter,SoapUI,Appium,Database testing,QA Training Academy", "title is not as expected!");
    }


    //alert cannot be insepected
    //we cannot perform actions on the page until we take some action on alert
    @Test
    public void alertTest() {
        driver.navigate().to("https://automatenow.io/sandbox-automation-testing-practice-website/popups/");
        driver.findElement(By.xpath("//button[@id='prompt']")).click();
        Alert alert = driver.switchTo().alert();
        Assert.assertEquals(alert.getText(), "Hi there, what's your name?");
        sleep(1);
        alert.sendKeys("harsha");
        sleep(1);
        alert.accept();
        String promptresult = driver.findElement(By.xpath("//p[@id='promptResult']")).getText();
        Assert.assertTrue(promptresult.contains("harsha"));
    }

    @Test
    public void elementPresenceTest() {
        List<WebElement> elements = driver.findElements(By.xpath("//harshavardhan[@id='busyqa']"));
        System.out.println(elements.size());
    }

    @Test
    public void hiddentest() {
        WebElement ele = driver.findElement(By.xpath("//input[@id='displayed-text']"));
        Assert.assertTrue(ele.isDisplayed());//true or false?
        driver.findElement(By.xpath("//input[@id='hide-textbox']")).click();
        Assert.assertFalse(ele.isDisplayed());
    }


    @Test
    public void actionstest() {

        driver.manage().window().maximize();
        //scroll to the element
        WebElement mousehover = driver.findElement(By.cssSelector("button[id='mousehover']"));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView()", mousehover);

        Actions actions = new Actions(driver);
        actions.moveToElement(mousehover).click().moveToElement(driver.findElement(By.xpath("//a[text()='Reload']"))).click().perform();
    }

    @Test
    public void mouseOverTest() {
        driver.manage().window().maximize();
        driver.navigate().to("https://automatenow.io/sandbox-automation-testing-practice-website/hover/");
        Actions actions = new Actions(driver);
        actions.moveToElement(driver.findElement(By.xpath("//h3[@id='mouse_over']"))).perform();
    }

    @Test
    public void waitsTest(){
        driver.navigate().to("https://www.hyrtutorials.com/p/waits-demo.html");

        //sleep(5);
        //implicit wait
//        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
        //implicit - global wait - driver level wait
        //3, 5, 6, 9, 15, 20
        //critical element: 4, 5

        driver.findElement(By.xpath("//button[@id='btn1']")).click();
        //explicit wait -> element level wait
        WebDriverWait wait1 = new WebDriverWait(driver, Duration.ofSeconds(5));
        WebElement textbox1 = wait1.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h3[text()='Show the textboxes here:']/input[@id='txt1']")));
        textbox1.sendKeys("busyqa 5sec element");


        driver.findElement(By.xpath("//button[@id='btn2']")).click();
        //explicit wait -> element level wait
        WebDriverWait wait2 = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement textbox2 = wait2.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h3[text()='Show the textboxes here:']/input[@id='txt2']")));
        textbox2.sendKeys("busyqa 10sec element");
    }


    public static void sleep(int seconds) {
        try {
            Thread.sleep(seconds * 1000L);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

}