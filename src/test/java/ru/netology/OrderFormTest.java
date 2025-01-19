package ru.netology;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import io.github.bonigarcia.wdm.WebDriverManager;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class OrderFormTest {

    @Test
    void shouldSubmitFormWithValidData() {
        // Настраиваем WebDriverManager для подбора драйвера
        WebDriverManager.chromedriver().setup();

        // Конфигурация Chrome для headless-режима (обязательно для CI)
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless"); // Без интерфейса
        options.addArguments("--disable-gpu"); // Отключение GPU
        options.addArguments("--no-sandbox"); // Обход песочницы
        options.addArguments("--disable-dev-shm-usage"); // Для небольших контейнеров

        // Запускаем браузер
        WebDriver driver = new ChromeDriver(options);

        try {
            // Открываем тестируемое приложение
            driver.get("http://localhost:9999");

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
            // Завершаем работу браузера
            driver.quit();
        }
    }
}


