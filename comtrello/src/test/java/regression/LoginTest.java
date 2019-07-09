package regression;

import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Step;
import io.qameta.allure.Story;
import org.testng.annotations.Test;
import ui.core.BrowserFactory;
import ui.pages.BoardsPage;
import ui.pages.LoginPage;

@Epic("Хуесия")
@Feature("Some shit")
public class LoginTest extends BrowserFactory {

    LoginPage loginPage = new LoginPage();
    BoardsPage boardsPage = new BoardsPage();

    @Test
    @Story("dawdwadwa")
    public void login(){
        loginPage.open();
        loginPage.login("test.qa9966@gmail.com", "test_Qa9966$");
        boardsPage.openBoard("testName");
    }
}
