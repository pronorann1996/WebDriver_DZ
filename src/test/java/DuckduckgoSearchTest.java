import factory.DriverFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;


public class DuckduckgoSearchTest {
    private Logger logger = (Logger) LogManager.getLogger(DuckduckgoSearchTest.class);
    private String duckUrl = System.getProperty("duck.url");
    private WebDriver driver;

    @BeforeEach
    public void startDriver() {
        driver = new DriverFactory("--headless").create();
        driver.get(duckUrl);
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
    public void findFirstSearchResult() {
        driver.findElement(By.xpath("//*[@id='searchbox_input']"))
                .sendKeys("ОТУС"+ Keys.ENTER);
        String firstSearchResult = driver.findElement(By.xpath("//span[text()='Онлайн‑курсы для профессионалов, дистанционное обучение современным ...']"))
                .getText();
        Assertions.assertEquals(firstSearchResult,"Онлайн‑курсы для профессионалов, дистанционное обучение современным ...");
        logger.info("findFirstSearchResult passed");
    }
}
