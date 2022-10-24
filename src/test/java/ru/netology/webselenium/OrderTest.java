package ru.netology.webselenium;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class OrderTest {
    private WebDriver driver;


    @BeforeAll

    public static void setUp() { WebDriverManager.chromedriver().setup();
        System.setProperty("web-driver.chrome.driver", "/driver/win/chromedriver.exe");

    }


    @BeforeEach

    public void beforeEach() { driver = new ChromeDriver();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--no-sandbox");
        options.addArguments("--headless");
        driver = new ChromeDriver(options);
        this.driver.get("http://localhost:9999/");



    }


    @AfterEach

    void tearDown() {
        driver.quit();
        driver = null;
    }

    @Test

    void shouldTest1() throws InterruptedException {
     driver.get("http://localhost:9999/");
     List<WebElement> inputs = driver.findElements(By.tagName("input"));
     inputs.get(0).sendKeys("Сергей Иванов");
     inputs.get(1).sendKeys("+79999999991");

     Thread.sleep(8000);
     driver.findElement(By.cssSelector("[data-test-id=agreement]")).click();
    driver.findElement(By.cssSelector("button.button")).click();
    String expected = "Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время.";
    String actual = driver.findElement(By.cssSelector("[data-test-id=order-success]")).getText();
    assertEquals(expected, actual);




    }


}
