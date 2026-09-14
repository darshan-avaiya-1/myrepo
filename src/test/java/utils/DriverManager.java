package utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

public class DriverManager {

    private static ThreadLocal<WebDriver> threadLocal = new ThreadLocal<>();

    public static void initDriver() {
        String browser = ConfigReader.getInstance().getConfig("browser");
        boolean headless = Boolean.parseBoolean(ConfigReader.getInstance().getConfig("headless"));

        WebDriver driver;

        if (browser.equalsIgnoreCase("chrome")) {
            ChromeOptions chromeOptions = new ChromeOptions();
            if (headless){
                chromeOptions.addArguments("--headless=new");
            }
            driver = new ChromeDriver(chromeOptions);
        } else if (browser.equalsIgnoreCase("firefox")) {
            FirefoxOptions firefoxOptions = new FirefoxOptions();
            if (headless){
                firefoxOptions.addArguments("--headless=new");
            }
            driver = new FirefoxDriver(firefoxOptions);
        } else if (browser.equalsIgnoreCase("edge")) {
            EdgeOptions edgeOptions = new EdgeOptions();
            if (headless){
                edgeOptions.addArguments("--headless=new");
            }
            driver = new EdgeDriver(edgeOptions);
        } else {
            throw new IllegalArgumentException("Please enter correct browser name");
        }
        if (!headless){
            driver.manage().window().maximize();
        }
        threadLocal.set(driver);
    }

    public static WebDriver getDriver() {
        if (threadLocal.get() == null) {
            initDriver();
        }
        return threadLocal.get();
    }

    public static void quitDriver() {
        if (threadLocal != null) {
            threadLocal.get().quit();
            threadLocal.remove();
        }
    }

}
