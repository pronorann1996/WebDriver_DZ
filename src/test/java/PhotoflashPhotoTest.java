import factory.DriverFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class PhotoflashPhotoTest {
    private Logger logger = (Logger) LogManager.getLogger(PhotoflashPhotoTest.class);
    private String photoUrl = System.getProperty("photo.url");
    private WebDriver driver;

    @BeforeEach
    public void startDriver() {
        driver = new DriverFactory("--kiosk").create();
        driver.get(photoUrl);
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
    public void openModalWindow(){
        JavascriptExecutor js = (JavascriptExecutor) driver;
        WebElement picture = driver.findElement(By.className("content-overlay"));
        js.executeScript("arguments[0].scrollIntoView();", picture);
        picture.click();
        WebElement modalWindow = driver.findElement(By.cssSelector("div.pp_pic_holder.light_rounded"));
        Assertions.assertTrue(modalWindow.isDisplayed());
        logger.info("openModalWindow passed");
    }
}
