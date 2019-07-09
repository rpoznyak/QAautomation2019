package ui.pages;

import io.qameta.allure.Step;
import javafx.scene.shape.Path;
import org.openqa.selenium.By;
import org.testng.Assert;
import ui.core.Constants;
import ui.core.Elem;
import static ui.core.BrowserFactory.*;

public class LoginPage {

    private static final String PATH = "login";

    public Elem emailFld = new Elem(By.cssSelector("#user"), "Login Field");
    public Elem passFld = new Elem(By.cssSelector("#password"), "Password");
    public Elem loginBtn = new Elem(By.cssSelector("#login"), "Login Button");

    @Step("Open login page")
    public void open(){
        get(Constants.URL+ PATH);
        Assert.assertTrue(isOpened(),"Page 'Login' ["+PATH+"] not opened");
    }

    public boolean isOpened(){

        return loginBtn.isPresent() && driver().getCurrentUrl().equals(Constants.URL+PATH);
    }

    @Step("Sign in with email: {email} and password: {password}")
    public void login(String email, String password){
        emailFld.type(email);
        passFld.type(password);
        loginBtn.click();
    }

}
