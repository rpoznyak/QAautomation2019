package ui.core;

import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.time.Duration;
import java.util.NoSuchElementException;

import static ui.core.BrowserFactory.getWebDriverWait;

public class Elem {
    private By by;
    private String name;
    private String element;


    public Elem(By by, String name){
        this.name = name;
        this.by = by;

    }

    public Elem(By by){
        this(by, "");
    }

    public WebElement find(){
        return getWebDriverWait(20).pollingEvery(Duration.ofMillis(500))
                .ignoring(NoSuchElementException.class).ignoring(StaleElementReferenceException.class)
                .until(ExpectedConditions.presenceOfElementLocated(by));
    }

    public void click(){
        find().click();
    }

    public void type(String text){
        find().clear();
        find().sendKeys(text);

    }

    public boolean isPresent(){
        try{
            getWebDriverWait(20).pollingEvery(Duration.ofMillis(500))
                    .ignoring(NoSuchElementException.class).ignoring(StaleElementReferenceException.class)
                    .until(ExpectedConditions.presenceOfElementLocated(by));
            return true;
        } catch(TimeoutException e){
            return false;
        }
    }

//    public WebElement findApi(){
//        return getWebDriverWait(10).until(ExpectedConditions.presenceOfElementLocated(element));
//    }
}
