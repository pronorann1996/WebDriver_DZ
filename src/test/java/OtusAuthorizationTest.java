import factory.DriverFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import tools.WaitTools;

public class OtusAuthorizationTest {
    private Logger logger = (Logger) LogManager.getLogger(OtusAuthorizationTest.class);
    private String otusUrl = System.getProperty("otus.url");
    private String login = System.getProperty("login");
    private String password = System.getProperty("password");
    private WebDriver driver;
    private WaitTools waitTools;

    @BeforeEach
    public void startDriver() {
        driver = new DriverFactory("--start-fullsreen").create();
        waitTools = new WaitTools(driver);
        driver.get(otusUrl);
        logger.info("Start driver");
    }

    @AfterEach
    public void stopDriver() {
        if (driver != null) {
            driver.quit();
        }
        logger.info("Stop driver");
    }

    @Test
    public void authorization(){
        String signInButtonLocator = "//button[text()='Войти']";
        waitTools.waitForCondition(ExpectedConditions.presenceOfElementLocated(By.xpath(signInButtonLocator)));
        waitTools.waitForCondition( ExpectedConditions.elementToBeClickable(By.xpath(signInButtonLocator)));
        driver.findElement(By.xpath(signInButtonLocator)).click();
        driver.findElement(By.xpath("//div[./input[@name='email']]")).click();
        driver.findElement(By.cssSelector("input[name='email']")).sendKeys("otus51423@mailto.plus");
        driver.findElement(By.xpath("//div[./input[@type='password']]")).click();
        driver.findElement(By.cssSelector("input[type='password']")).sendKeys("12345678Qw1!");
        driver.findElement(By.xpath("//button[./*[text() = 'Войти']]")).click();
        String cookies = driver.manage().getCookies().toString();
        logger.info("Cookies" + cookies);
        Assertions.assertTrue(waitTools.waitForCondition(ExpectedConditions
                        .presenceOfElementLocated(By.cssSelector("img[src*='blue-owl']"))));
               logger.info("authorization passed");
    }
}
