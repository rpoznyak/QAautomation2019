package ui.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import ui.core.Elem;

public class BoardsPage {

    private static final String PATH = "ruslan58200195/boards";

    public Elem boardByUrlName(String urlName){
        return new Elem(By.cssSelector(".board-tile[href*='"+urlName.substring(18)+"']"), urlName);
    }

    public void open(){

    }

    public void isOpened(){

    }

    @Step("Open board")
    public void openBoard(String urlName){
        boardByUrlName(urlName).click();
    }

}
