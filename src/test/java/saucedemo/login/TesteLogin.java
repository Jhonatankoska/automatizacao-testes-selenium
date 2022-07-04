package saucedemo.login;

import org.junit.*;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class TesteLogin {
    WebDriver navegar;
    By userName = By.xpath("//*[@id=\"user-name\"]");
    By passWord = By.xpath("//*[@id=\"password\"]");
    By botaoLogin = By.xpath("//*[@id=\"login-button\"]");
    By textoProdutos = By.xpath("//*[@id=\"header_container\"]/div[2]/span");
    By textoPasswordIsRequired = By.xpath("//*[@id=\"login_button_container\"]/div/form/div[3]/h3");
    By textoUsernameIsRequired = By.xpath("//*[@id=\"login_button_container\"]/div/form/div[3]/h3");
    By textoUsernameAndPassword = By.xpath("//*[@id=\"login_button_container\"]/div/form/div[3]/h3");
    @Before
    public void iniciarNavegador() {
        System.setProperty("webdriver.chrome.driver","src\\drive\\chromedriver.exe");
        navegar = new ChromeDriver();
        navegar.get("https://www.saucedemo.com");
    }


    @After
    public void fechaNavegador() {
        navegar.quit();
    }

    @Test
    public void loginComUsernamePasswordInvalido(){


        navegar.findElement(userName).click();
        navegar.findElement(userName).sendKeys("ctm", Keys.TAB);

        navegar.findElement(passWord).click();
        navegar.findElement(passWord).sendKeys("ctm", Keys.ENTER);

        String confirmacao = navegar.findElement(botaoLogin).getText();
        Assert.assertEquals("Epic sadface: Username and password do not match any user in this service",confirmacao);

    }

    @Test
    public void loginUsernameInvalidoPasswordValido(){


        navegar.findElement(userName).click();
        navegar.findElement(userName).sendKeys("ctm", Keys.TAB);

        navegar.findElement(passWord).click();
        navegar.findElement(passWord).sendKeys("secret_sauce", Keys.ENTER);

        String confirmacao = navegar.findElement(botaoLogin).getText();
        Assert.assertEquals("Epic sadface: Username and password do not match any user in this service",confirmacao);

    }

    @Test
    public void loginUsernameValidoPasswordInvalido(){


        navegar.findElement(userName).click();
        navegar.findElement(userName).sendKeys("standard_user", Keys.TAB);

        navegar.findElement(passWord).click();
        navegar.findElement(passWord).sendKeys("ctm", Keys.ENTER);

        String confirmacao = navegar.findElement(botaoLogin).getText();
        Assert.assertEquals("Epic sadface: Username and password do not match any user in this service",confirmacao);


    }

    @Test
    public void loginUsernameValidoPasswordValido(){


        navegar.findElement(userName).click();
        navegar.findElement(userName).sendKeys("standard_user", Keys.TAB);

        navegar.findElement(passWord).click();
        navegar.findElement(passWord).sendKeys("secret_sauce", Keys.ENTER);

        String confirmacao = navegar.findElement(textoProdutos).getText();
        Assert.assertEquals("PRODUCTS",confirmacao);


    }

    @Test
    public void loginUsernameSemPassword(){


        navegar.findElement(userName).click();
        navegar.findElement(userName).sendKeys("standard_user");

        navegar.findElement(botaoLogin).click();


        String confirmacao = navegar.findElement(textoPasswordIsRequired).getText();
        Assert.assertEquals("Epic sadface: Password is required",confirmacao);


    }

    @Test
    public void loginSemUsernameComPassword(){


        navegar.findElement(passWord).click();
        navegar.findElement(passWord).sendKeys("secret_sauce");

        navegar.findElement(botaoLogin).click();


        String confirmacao = navegar.findElement(textoUsernameIsRequired).getText();
        Assert.assertEquals("Epic sadface: Username is required",confirmacao);


    }

    @Test
    public void loginSemUsernameSemPassword(){


        navegar.findElement(botaoLogin).click();


        String confirmacao = navegar.findElement(textoUsernameIsRequired).getText();
        Assert.assertEquals("Epic sadface: Username is required",confirmacao);


    }

    /**
     * Campo username e password necessita de tratamento de limite de caracteres.
     */
    @Test
    public void loginUsernamePasswordMilCaractere(){


        WebElement campoUserName = navegar.findElement(userName);
        WebElement campoPassword = navegar.findElement(passWord);

        for (int i = 0; i <=50 ; i++) {
        campoUserName.sendKeys("kkkkkkkkkkkkkkkkkkkk");
        campoPassword.sendKeys("kkkkkkkkkkkkkkkkkkkk");
        i++;
        }

        navegar.findElement(botaoLogin).click();
        String confirmacao = navegar.findElement(textoUsernameAndPassword).getText();
        Assert.assertEquals("Epic sadface: Username and password do not match any user in this service",confirmacao);

    }

    @Test
    public void loginUsernamePasswordCaractereEspecial(){


        navegar.findElement(userName).click();
        navegar.findElement(userName).sendKeys("@!%$#*&=+", Keys.TAB);

        navegar.findElement(passWord).click();
        navegar.findElement(passWord).sendKeys("@!%$#*&=+", Keys.ENTER);

        String confirmacao = navegar.findElement(textoUsernameAndPassword).getText();
        Assert.assertEquals("Epic sadface: Username and password do not match any user in this service",confirmacao);


    }




































}



