import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.nio.file.Paths;


public class Intagram {
    @Test
    void 테스트() {
        System.setProperty("webdriver.chrome.driver",
                Paths.get("/home/jwchung/Documents/codes/Rladudgksakstp/Kakao/src/test/resources/driver/chromedriver").toString());

        WebDriver driver = new ChromeDriver();
        driver.navigate().to("https://instagram.com/");
        try {
            driver.wait(5000);
        } catch (Exception e){}
        driver.findElement(By.xpath("//*[@id=\"loginForm\"]/div/div[5]/button/span[2]")).click();
    }
}
