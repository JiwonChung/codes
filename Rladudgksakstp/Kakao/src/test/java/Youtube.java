import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Youtube {
    @Test
    void fiveTimes연속() {
        for (int i = 0; i < 5; i++) {
            테스트();
        }
    }

    @Test
    void 테스트() {
        System.setProperty("webdriver.chrome.driver",
                Paths.get("/home/jwchung/Documents/codes/Rladudgksakstp/Kakao/src/test/resources/driver/chromedriver").toString());

        WebDriver driver = new ChromeDriver();
        JavascriptExecutor executor = (JavascriptExecutor) driver;

        driver.navigate().to("https://www.youtube.com/watch?v=w6IN88o9xb8");

//        try {
//            Thread.sleep(1000);
//        } catch (InterruptedException e) {e.getMessage();}


        driver.manage().window().maximize();


        List<Comment> comments = new ArrayList<>();

        try {
            executor.executeScript("window.scrollBy(0, 300)");
            Thread.sleep(3000);
            WebElement e = driver.findElement(By.cssSelector("#comments"))
                    .findElement(By.xpath("//*[@id=\"sections\"]"))
                    .findElement(By.cssSelector("#contents"));

            for (int i = 0; i < 10; i++) {
                Thread.sleep(1000);
                List<WebElement> commentList = e.findElements(By.cssSelector(".style-scope ytd-item-section-renderer"));
                System.out.println(commentList.size());
                executor.executeScript("window.scrollBy(0, 1000)");
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        // end
    }



    private static class Comment {
        String name;
        String content;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }
    }
}
