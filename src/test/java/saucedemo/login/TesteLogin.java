package saucedemo.login;

import org.junit.*;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class TesteLogin {
    WebDriver navegar;
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


        navegar.findElement(By.xpath("//*[@id=\"user-name\"]")).click();
        navegar.findElement(By.xpath("//*[@id=\"user-name\"]")).sendKeys("ctm", Keys.TAB);

        navegar.findElement(By.xpath("//*[@id=\"password\"]")).click();
        navegar.findElement(By.xpath("//*[@id=\"password\"]")).sendKeys("ctm", Keys.ENTER);

        String confirmacao = navegar.findElement(By.xpath("//*[@id=\"login_button_container\"]/div/form/div[3]/h3")).getText();
        Assert.assertEquals("Epic sadface: Username and password do not match any user in this service",confirmacao);

    }

    @Test
    public void loginUsernameInvalidoPasswordValido(){


        navegar.findElement(By.xpath("//*[@id=\"user-name\"]")).click();
        navegar.findElement(By.xpath("//*[@id=\"user-name\"]")).sendKeys("ctm", Keys.TAB);

        navegar.findElement(By.xpath("//*[@id=\"password\"]")).click();
        navegar.findElement(By.xpath("//*[@id=\"password\"]")).sendKeys("secret_sauce", Keys.ENTER);

        String confirmacao = navegar.findElement(By.xpath("//*[@id=\"login_button_container\"]/div/form/div[3]/h3")).getText();
        Assert.assertEquals("Epic sadface: Username and password do not match any user in this service",confirmacao);

    }

    @Test
    public void loginUsernameValidoPasswordInvalido(){


        navegar.findElement(By.xpath("//*[@id=\"user-name\"]")).click();
        navegar.findElement(By.xpath("//*[@id=\"user-name\"]")).sendKeys("standard_user", Keys.TAB);

        navegar.findElement(By.xpath("//*[@id=\"password\"]")).click();
        navegar.findElement(By.xpath("//*[@id=\"password\"]")).sendKeys("ctm", Keys.ENTER);

        String confirmacao = navegar.findElement(By.xpath("//*[@id=\"login_button_container\"]/div/form/div[3]/h3")).getText();
        Assert.assertEquals("Epic sadface: Username and password do not match any user in this service",confirmacao);


    }

    @Test
    public void loginUsernameValidoPasswordValido(){


        navegar.findElement(By.xpath("//*[@id=\"user-name\"]")).click();
        navegar.findElement(By.xpath("//*[@id=\"user-name\"]")).sendKeys("standard_user", Keys.TAB);

        navegar.findElement(By.xpath("//*[@id=\"password\"]")).click();
        navegar.findElement(By.xpath("//*[@id=\"password\"]")).sendKeys("secret_sauce", Keys.ENTER);

        String confirmacao = navegar.findElement(By.xpath("//*[@id=\"header_container\"]/div[2]/span")).getText();
        Assert.assertEquals("PRODUCTS",confirmacao);


    }

    @Test
    public void loginUsernameSemPassword(){


        navegar.findElement(By.xpath("//*[@id=\"user-name\"]")).click();
        navegar.findElement(By.xpath("//*[@id=\"user-name\"]")).sendKeys("standard_user");

        navegar.findElement(By.xpath("//*[@id=\"login-button\"]")).click();


        String confirmacao = navegar.findElement(By.xpath("//*[@id=\"login_button_container\"]/div/form/div[3]/h3")).getText();
        Assert.assertEquals("Epic sadface: Password is required",confirmacao);


    }

    @Test
    public void loginSemUsernameComPassword(){


        navegar.findElement(By.xpath("//*[@id=\"password\"]")).click();
        navegar.findElement(By.xpath("//*[@id=\"password\"]")).sendKeys("secret_sauce");

        navegar.findElement(By.xpath("//*[@id=\"login-button\"]")).click();


        String confirmacao = navegar.findElement(By.xpath("//*[@id=\"login_button_container\"]/div/form/div[3]/h3")).getText();
        Assert.assertEquals("Epic sadface: Username is required",confirmacao);


    }

    @Test
    public void loginSemUsernameSemPassword(){


        navegar.findElement(By.xpath("//*[@id=\"login-button\"]")).click();


        String confirmacao = navegar.findElement(By.xpath("//*[@id=\"login_button_container\"]/div/form/div[3]/h3")).getText();
        Assert.assertEquals("Epic sadface: Username is required",confirmacao);


    }

    /**
     * Campo username e password necessita de tratamento de limite de caracteres.
     */
    @Test
    public void loginUsernamePasswordMilCaractere(){


        WebElement campoUserName = navegar.findElement(By.xpath("//*[@id=\"user-name\"]"));
        WebElement campoPassword = navegar.findElement(By.xpath("//*[@id=\"password\"]"));

        for (int i = 0; i <=50 ; i++) {
        campoUserName.sendKeys("kkkkkkkkkkkkkkkkkkkk");
        campoPassword.sendKeys("kkkkkkkkkkkkkkkkkkkk");
        i++;
        }

        navegar.findElement(By.xpath("//*[@id=\"login-button\"]")).click();
        String confirmacao = navegar.findElement(By.xpath("//*[@id=\"login_button_container\"]/div/form/div[3]/h3")).getText();
        Assert.assertEquals("Epic sadface: Username and password do not match any user in this service",confirmacao);

    }

    @Test
    public void loginUsernamePasswordCaractereEspecial(){


        navegar.findElement(By.xpath("//*[@id=\"user-name\"]")).click();
        navegar.findElement(By.xpath("//*[@id=\"user-name\"]")).sendKeys("@!%$#*&=+", Keys.TAB);

        navegar.findElement(By.xpath("//*[@id=\"password\"]")).click();
        navegar.findElement(By.xpath("//*[@id=\"password\"]")).sendKeys("@!%$#*&=+", Keys.ENTER);

        String confirmacao = navegar.findElement(By.xpath("//*[@id=\"login_button_container\"]/div/form/div[3]/h3")).getText();
        Assert.assertEquals("Epic sadface: Username and password do not match any user in this service",confirmacao);


    }




































}



