package SeleniumTests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class FirstSeleniumCode {


    public static void main(String[] args) {

        //1. specify driver location to the program
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\Harsha\\myOwnWorkSpace\\SoftwareTestingSeleniumProject\\drivers\\chromedriver.exe");

        //2. create driver object
        //kick off browser
        ChromeDriver driver = new ChromeDriver();


        //checked, unchecked exception
        //eg: unchecked : Arthimetic 10/0, checked: file, db, io, interupted
        //try catch


        //3. open the application
        driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
        sleep(2);
        //username
        WebElement usernameEle = driver.findElement(By.name("username"));
        usernameEle.sendKeys("Admin");
        //password
        driver.findElement(By.name("password")).sendKeys("admin123");
        //click on login button
        driver.findElement(By.className("orangehrm-login-button")).click();

        sleep(3);

        driver.quit();
    }

    public static void sleep(int seconds) {
        try {
            Thread.sleep(seconds * 1000L);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}