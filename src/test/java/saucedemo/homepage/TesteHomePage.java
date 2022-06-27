package saucedemo.homepage;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import saucedemo.login.TesteLogin;

import java.util.concurrent.TimeUnit;

public class TesteHomePage {

    WebDriver navegar;

    @Before
    public void iniciarAutomatizador() {
        System.setProperty("webdriver.chrome.driver","src\\drive\\chromedriver.exe");
       navegar = new ChromeDriver();
       navegar.get("https://www.saucedemo.com");
       login();

    }

    @After
    public void fecharNavegador() {
        navegar.quit();
    }

    public void login(){
        navegar.findElement(By.xpath("//*[@id=\"user-name\"]")).sendKeys("standard_user", Keys.TAB);
        navegar.findElement(By.xpath("//*[@id=\"password\"]")).sendKeys("secret_sauce", Keys.ENTER);
    }


    @Test
    public void homepageBotaoMenu(){

        navegar.findElement(By.xpath("//*[@id=\"react-burger-menu-btn\"]")).click();
        String confirmacao = navegar.findElement(By.xpath("//*[@id=\"inventory_sidebar_link\"]")).getText();
        Assert.assertEquals("ALL ITEMS",confirmacao);
    }

    @Test
    public void homepageBotaoAllItems(){
        navegar.findElement(By.xpath("//*[@id=\"item_4_img_link\"]/img")).click();
        navegar.findElement(By.xpath("//*[@id=\"react-burger-menu-btn\"]")).click();
        navegar.findElement(By.xpath("//*[@id=\"inventory_sidebar_link\"]")).click();
        String confirmacao = navegar.findElement(By.xpath("//*[@id=\"header_container\"]/div[2]/span")).getText();
        Assert.assertEquals("PRODUCTS",confirmacao);
    }

    @Test
    public void homepageBotaoAbout(){
        navegar.findElement(By.xpath("//*[@id=\"react-burger-menu-btn\"]")).click();
        navegar.findElement(By.xpath("//*[@id=\"about_sidebar_link\"]")).click();
        String confirmacao = navegar.findElement(By.xpath("//*[@id=\"entry-3qDFahnypj1KkiORyU1Zyh\"]/div/div/div/div[1]/div/h1")).getText();
        Assert.assertEquals("DEVELOP WITH CONFIDENCE",confirmacao);
    }

    @Test
    public void homepageBotaoLogout() {
        navegar.findElement(By.xpath("//*[@id=\"react-burger-menu-btn\"]")).click();
        navegar.findElement(By.xpath("//*[@id=\"logout_sidebar_link\"]")).click();
        String confirmacao = navegar.findElement(By.xpath("//*[@id=\"root\"]/div/div[2]/div[2]/div/div[2]/h4")).getText();
        Assert.assertEquals("Password for all users:",confirmacao);
    }

    /**
     * Teste ok, porém necessita do uso do refresh para atualizar a pagina,
     * caso não atualize o botão remove permanece na tela e o teste não passa.
     */
    @Test
    public void homepageBotaoRestAppStateItem() {
        navegar.findElement(By.xpath("//*[@id=\"item_4_img_link\"]/img")).click();
        WebElement botaoAdd = navegar.findElement(By.xpath("//*[@id=\"add-to-cart-sauce-labs-backpack\"]"));
        botaoAdd.click();
        WebElement botaoRemove = navegar.findElement(By.xpath("//*[@id=\"remove-sauce-labs-backpack\"]"));
        navegar.findElement(By.xpath("//*[@id=\"react-burger-menu-btn\"]")).click();
        navegar.manage().timeouts().implicitlyWait( 10, TimeUnit.SECONDS);
        navegar.findElement(By.xpath("//*[@id=\"reset_sidebar_link\"]")).click();
        navegar.navigate().refresh();

        Assert.assertFalse(navegar.getPageSource().contains("Remove"));
        Assert.assertTrue(navegar.getPageSource().contains("Add to cart"));
    }

    @Test
    public void homepageBotaoRestAppState() {
        WebElement botaoAdd = navegar.findElement(By.xpath("//*[@id=\"add-to-cart-sauce-labs-backpack\"]"));
        botaoAdd.click();
        WebElement botaoRemove = navegar.findElement(By.xpath("//*[@id=\"remove-sauce-labs-backpack\"]"));
        navegar.findElement(By.xpath("//*[@id=\"react-burger-menu-btn\"]")).click();
        navegar.manage().timeouts().implicitlyWait( 10, TimeUnit.SECONDS);
        navegar.findElement(By.xpath("//*[@id=\"reset_sidebar_link\"]")).click();
        navegar.navigate().refresh();

        Assert.assertFalse(navegar.getPageSource().contains("Remove"));
        Assert.assertTrue(navegar.getPageSource().contains("Add to cart"));

    }





























    }




































