import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.nio.file.Paths;

public class FaceBook {
    @Test
    void 페북() {
        System.setProperty("webdriver.chrome.driver",
                Paths.get("/home/jwchung/Documents/codes/Rladudgksakstp/Kakao/src/test/resources/driver/chromedriver").toString());

        WebDriver driver = new ChromeDriver();
        driver.navigate().to("https://facebook.com/");
        try {
            driver.wait(5000);
        } catch (Exception e){}
        driver.findElement(By.xpath("//*[@id=\"email\"]")).sendKeys("jimmychung00@naver.com");
        driver.findElement(By.xpath("//*[@id=\"pass\"]")).sendKeys("Wjdwldnjs00!");
        driver.findElement(By.xpath("//*[@id=\"u_0_b\"]")).click();

    }
}
