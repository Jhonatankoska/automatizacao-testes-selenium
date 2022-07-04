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
    By botaoMenu = By.xpath("//*[@id=\"react-burger-menu-btn\"]");
    By botaoAllItens = By.xpath("//*[@id=\"inventory_sidebar_link\"]");
    By itemMochila = By.xpath("//*[@id=\"item_4_img_link\"]/img");
    By textoProdutos = By.xpath("//*[@id=\"header_container\"]/div[2]/span");
    By botaoAbout = By.xpath("//*[@id=\"about_sidebar_link\"]");
    By textoDevelop = By.xpath("//*[@id=\"entry-3qDFahnypj1KkiORyU1Zyh\"]/div/div/div/div[1]/div/h1");
    By botaoLogout = By.xpath("//*[@id=\"logout_sidebar_link\"]");
    By textoPasswordConfirmacao = By.xpath("//*[@id=\"root\"]/div/div[2]/div[2]/div/div[2]/h4");
    By botaoAddToCart = By.xpath("//*[@id=\"add-to-cart-sauce-labs-backpack\"]");
    By idBotaoRemove = By.xpath("//*[@id=\"remove-sauce-labs-backpack\"]");
    By botaoResetAppState = By.xpath("//*[@id=\"reset_sidebar_link\"]");
    By botaoBackToProducts = By.xpath("//*[@id=\"back-to-products\"]");
    By botaoFiltro = By.xpath("//*[@id=\"header_container\"]/div[2]/div[2]/span/select");
    By filtroOpcao1 = By.xpath("//*[@id=\"header_container\"]/div[2]/div[2]/span/select/option[1]");
    By filtroOpcao2 = By.xpath("//*[@id=\"header_container\"]/div[2]/div[2]/span/select/option[2]");
    By filtroOpcao3 = By.xpath("//*[@id=\"header_container\"]/div[2]/div[2]/span/select/option[3]");
    By filtroOpcao4 = By.xpath("//*[@id=\"header_container\"]/div[2]/div[2]/span/select/option[4]");
    By botaoCarrinho = By.xpath("//*[@id=\"shopping_cart_container\"]/a");
    By idTextoQty = By.xpath("//*[@id=\"cart_contents_container\"]/div/div[1]/div[1]");
    By idTextoDescription = By.xpath("//*[@id=\"cart_contents_container\"]/div/div[1]/div[2]");
    By botaoContinueShopping = By.xpath("//*[@id=\"continue-shopping\"]");
    By textoNameAToZ = By.xpath("//*[@id=\"header_container\"]/div[2]/div[2]/span/span");
    By botaoCheckout = By.xpath("//*[@id=\"checkout\"]");

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

        navegar.findElement(botaoMenu).click();
        String confirmacao = navegar.findElement(botaoAllItens).getText();
        Assert.assertEquals("ALL ITEMS",confirmacao);
    }

    @Test
    public void homepageBotaoAllItems(){
        navegar.findElement(itemMochila).click();
        navegar.findElement(botaoMenu).click();
        navegar.findElement(botaoAllItens).click();
        String confirmacao = navegar.findElement(textoProdutos).getText();
        Assert.assertEquals("PRODUCTS",confirmacao);
    }

    @Test
    public void homepageBotaoAbout(){
        navegar.findElement(botaoMenu).click();
        navegar.findElement(botaoAbout).click();
        String confirmacao = navegar.findElement(textoDevelop).getText();
        Assert.assertEquals("DEVELOP WITH CONFIDENCE",confirmacao);
    }

    @Test
    public void homepageBotaoLogout() {
        navegar.findElement(botaoMenu).click();
        navegar.findElement(botaoLogout).click();
        String confirmacao = navegar.findElement(textoPasswordConfirmacao).getText();
        Assert.assertEquals("Password for all users:",confirmacao);
    }

    /**
     * Teste ok, porém necessita do uso do refresh para atualizar a pagina,
     * caso não atualize o botão remove permanece na tela e o teste não passa.
     */
    @Test
    public void homepageBotaoRestAppStateItem() {
        navegar.findElement(itemMochila).click();
        WebElement botaoAdd = navegar.findElement(botaoAddToCart);
        botaoAdd.click();
        WebElement botaoRemove = navegar.findElement(idBotaoRemove);
        navegar.findElement(botaoMenu).click();
        navegar.manage().timeouts().implicitlyWait( 10, TimeUnit.SECONDS);
        navegar.findElement(botaoResetAppState).click();
        navegar.navigate().refresh();

        Assert.assertFalse(navegar.getPageSource().contains("Remove"));
        Assert.assertTrue(navegar.getPageSource().contains("Add to cart"));
    }

    @Test
    public void homepageBotaoRestAppState() {
        WebElement botaoAdd = navegar.findElement(botaoAddToCart);
        botaoAdd.click();
        WebElement botaoRemove = navegar.findElement(idBotaoRemove);
        navegar.findElement(botaoMenu).click();
        navegar.manage().timeouts().implicitlyWait( 10, TimeUnit.SECONDS);
        navegar.findElement(botaoResetAppState).click();
        navegar.navigate().refresh();

        Assert.assertFalse(navegar.getPageSource().contains("Remove"));
        Assert.assertTrue(navegar.getPageSource().contains("Add to cart"));
    }

    @Test
    public void homepageBotaoBackToProducts(){
        navegar.findElement(itemMochila).click();
        navegar.findElement(botaoBackToProducts).click();
        String confirmacao = navegar.findElement(textoProdutos).getText();
        Assert.assertEquals("PRODUCTS",confirmacao);
    }

    @Test
    public void homepageBotaoFiltro(){
        navegar.findElement(botaoFiltro).click();
        String filtro1 = navegar.findElement(filtroOpcao1).getText();
        String filtro2 = navegar.findElement(filtroOpcao2).getText();
        String filtro3 = navegar.findElement(filtroOpcao3).getText();
        String filtro4 = navegar.findElement(filtroOpcao4).getText();
        Assert.assertEquals("Name (A to Z)",filtro1);
        Assert.assertEquals("Name (Z to A)",filtro2);
        Assert.assertEquals("Price (low to high)",filtro3);
        Assert.assertEquals("Price (high to low)",filtro4);
    }

    @Test
    public void homepageBotaoCarrinho(){
        navegar.findElement(botaoCarrinho).click();
        String carrinho = navegar.findElement(idTextoQty).getText();
        String car = navegar.findElement(idTextoDescription).getText();
        Assert.assertEquals("QTY",carrinho);
        Assert.assertEquals("DESCRIPTION",car);
    }

    @Test
    public void homepageBotaoContinueShopping(){
        navegar.findElement(botaoCarrinho).click();
        navegar.findElement(botaoContinueShopping).click();
        String produto = navegar.findElement(textoProdutos).getText();
        String filtro = navegar.findElement(textoNameAToZ).getText();
        Assert.assertEquals("PRODUCTS",produto);
        Assert.assertEquals("NAME (A TO Z)",filtro);
    }

    @Test
    public void homepageBotaocheckout(){
        navegar.findElement(itemMochila).click();
        navegar.findElement(botaoAddToCart).click();
        navegar.findElement(botaoCarrinho).click();
        navegar.findElement(botaoCheckout).click();
        String produto = navegar.findElement(textoProdutos).getText();
        Assert.assertEquals("CHECKOUT: YOUR INFORMATION",produto);
    }

































    }




































