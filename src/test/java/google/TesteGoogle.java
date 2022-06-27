package google;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class TesteGoogle {

    @Test
    public void pesquisarGoogle(){
        System.setProperty("webdriver.chrome.driver","src\\drive\\chromedriver.exe");
        WebDriver navegar = new ChromeDriver();

        navegar.get("https://google.com");

        navegar.findElement(By.xpath("/html/body/div[1]/div[3]/form/div[1]/div[1]/div[1]/div/div[2]/input")).click();
        navegar.findElement(By.xpath("/html/body/div[1]/div[3]/form/div[1]/div[1]/div[1]/div/div[2]/input")).sendKeys("bolsa de valores", Keys.ENTER);

        navegar.findElement(By.xpath("//*[@id=\"tads\"]/div[1]/div/div/div/div[1]/a/div[1]/span")).click();
    }
}

