import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import io.github.bonigarcia.wdm.WebDriverManager;


public class Ozon {
    WebDriver driver;

    @BeforeAll
    static void setupClass() {
        WebDriverManager.chromedriver().setup();
    }

    @BeforeEach
    void setupTest() {
        driver = new ChromeDriver();
    }

    @Test
    void AddToCart() throws InterruptedException {
        var OZON_URL = "https://www.ozon.ru/";
        driver.get(OZON_URL);
        Thread.sleep(1000);

        driver.findElement(By.xpath("//*[@id=\"stickyHeader\"]/div[2]/div/div[1]/button")).click();
        Thread.sleep(1000);
        driver.findElement(By.xpath("//*[@id=\"stickyHeader\"]/div[2]/div/div[2]/div/div[2]/div/div[2]/div/div[1]/div[1]/div/a[6]")).click();
        Thread.sleep(1000);
        driver.findElement(By.xpath("//*[@id=\"layoutPage\"]/div[1]/div[3]/div[2]/div[2]/div[3]/div[1]/div/div/div[1]/div[3]/div[3]/div/div/div/button")).click();
        Thread.sleep(1000);
        driver.findElement(By.xpath("//*[@id=\"layoutPage\"]/div[1]/div[3]/div[2]/div[2]/div[3]/div[1]/div/div/div[1]/div[3]/div[3]/div/div/div[2]/div/button")).click();

        Thread.sleep(1000);
        WebElement itemsInCart = driver.findElement(By.xpath("//*[@id=\"stickyHeader\"]/div[4]/a[2]/span[1]"));
        String countItemsInCart = itemsInCart.getText();

        Thread.sleep(1000);
        // Проверяем количество элементов в корзине после добавления туда двух телефончиков
        Assertions.assertEquals(String.format("Товаров в корзине %s", countItemsInCart), "Товаров в корзине 2");

        Thread.sleep(1000);
        driver.findElement(By.xpath("//*[@id=\"stickyHeader\"]/div[4]/a[2]")).click();

        Thread.sleep(1000);
        driver.findElement(By.xpath("//*[@id=\"layoutPage\"]/div[1]/div/div/div[3]/div/div/div/div[2]/div/div/div/div/div[3]/div/button")).click();

        Thread.sleep(1000);
        driver.findElement(By.xpath("//*[@id=\"layoutPage\"]/div[1]/div/div/div[4]/div[4]/div[1]/div[1]/div/div[2]/div[2]/div[1]/div/div/div/div[2]/div/div[2]/div/div[2]/button")).click();

        Thread.sleep(1000);
        driver.findElement(By.xpath("/html/body/div[8]/div/div[2]/div/div/section/div[3]/div/button")).click();

        Thread.sleep(1000);
        WebElement itemsInCartAfterDelete = driver.findElement(By.xpath("//*[@id=\"stickyHeader\"]/div[4]/a[2]/span[1]"));
        String countItemsInCartAfterDelete = itemsInCartAfterDelete.getText();

        Thread.sleep(1000);
        String emptyCartText = driver.findElement(By.xpath("//*[@id=\"layoutPage\"]/div[1]/div/div/div[3]/div[1]/div/div/h1")).getText();
        Assertions.assertEquals(String.format(
                "Текст после удаления элементов в корзине -> %s", emptyCartText),
                "Текст после удаления элементов в корзине -> Корзина пуста"
        );

        Thread.sleep(1000);
        // Проверяем количество элементов в корзине после удаления элементиков из корзиночки
        Assertions.assertEquals(String.format("Товаров в корзине %s", countItemsInCartAfterDelete), "Товаров в корзине 0");
    }

    @AfterEach
    void teardown() {
        driver.quit();
    }
}
