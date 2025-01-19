package ru.netology;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import io.github.bonigarcia.wdm.WebDriverManager;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class OrderFormTest {

    @Test
    void shouldSubmitFormWithValidData() {
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();

        try {
            driver.get("http://localhost:9999");
            driver.findElement(By.cssSelector("[data-test-id=name] input")).sendKeys("Иван Иванов");
            driver.findElement(By.cssSelector("[data-test-id=phone] input")).sendKeys("+79998887766");
            driver.findElement(By.cssSelector("[data-test-id=agreement]")).click();
            driver.findElement(By.cssSelector("button.button")).click();

            WebElement successMessage = driver.findElement(By.cssSelector("[data-test-id=order-success]"));
            String text = successMessage.getText().trim();
            assertEquals("Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время.", text);
        } finally {
            driver.quit();
        }
    }
}

