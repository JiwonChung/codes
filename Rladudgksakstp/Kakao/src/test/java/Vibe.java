import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.nio.file.Paths;

public class Vibe {

    @Test
    void 연습입니다() {

        System.setProperty("webdriver.chrome.driver",
                Paths.get("/home/jwchung/Documents/codes/Rladudgksakstp/Kakao/src/test/resources/driver/chromedriver").toString());

        WebDriver driver = new ChromeDriver();
        driver.navigate().to("https://vibe.naver.com/");
        try {
            driver.wait(5000);
        } catch (Exception e){}

        driver.findElement(By.xpath("//*[@id=\"app\"]/div[2]/div/div/a[2]")).click();
        driver.findElement(By.xpath("//*[@id=\"header\"]/div[2]/div[2]/div/ul/li[4]/a")).click();


    }
}