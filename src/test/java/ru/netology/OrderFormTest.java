
package ru.netology;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import io.github.bonigarcia.wdm.WebDriverManager;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class OrderFormTest {

    private static WebDriver driver;

    @BeforeAll
    public static void setupAll() {
        WebDriverManager.chromedriver().setup(); // Устанавливаем ChromeDriver
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless"); // Без интерфейса
        options.addArguments("--disable-gpu");
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");
        driver = new ChromeDriver(options); // Инициализируем драйвер
    }

    @Test
    void shouldSubmitFormWithValidData() throws InterruptedException {
        try {
            driver.get("http://localhost:9999"); // Открываем приложение
            Thread.sleep(5000); // Ждем, чтобы приложение загрузилось

            // Заполняем форму
            driver.findElement(By.cssSelector("[data-test-id=name] input")).sendKeys("Иван Иванов");
            driver.findElement(By.cssSelector("[data-test-id=phone] input")).sendKeys("+79998887766");
            driver.findElement(By.cssSelector("[data-test-id=agreement]")).click();
            driver.findElement(By.cssSelector("button.button")).click();

            // Проверяем сообщение об успешной отправке
            WebElement successMessage = driver.findElement(By.cssSelector("[data-test-id=order-success]"));
            String text = successMessage.getText().trim(); // Убираем лишние пробелы

            assertEquals("Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время.", text);
        } finally {
            driver.quit(); // Завершаем работу драйвера
        }
    }
}
