package tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;
import utils.DriverManager;

import java.time.Duration;

public class TestLoginTC1 {

    @Test
    public void testLoginTC1(){
        WebDriver driver = DriverManager.getDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get("https://tutorialsninja.com/demo/index.php?route=account/login");
        driver.findElement(By.xpath("//input[@id='input-email']")).sendKeys("darshan@test.com");
        driver.findElement(By.xpath("//input[@id='input-password']")).sendKeys("Aq@1234567890");
        driver.findElement(By.xpath("//input[@value='Login']")).click();
        driver.quit();
    }

}
