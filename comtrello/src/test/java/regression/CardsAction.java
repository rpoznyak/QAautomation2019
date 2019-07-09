package regression;

import api.TrelloRestClient;
import api.models.*;
import io.qameta.allure.*;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.FluentWait;
import org.testng.Assert;
import org.testng.annotations.*;
import ui.core.BrowserFactory;
import ui.pages.BoardsPage;
import ui.pages.CardPage;
import ui.pages.LoginPage;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Objects;

@Epic("Regression")
public class CardsAction extends BrowserFactory {

    public TrelloRestClient client = new TrelloRestClient();

    TrelloList listAction = new TrelloList();
    Board createdBoard = new Board();
    Card card = new Card("Test_Card_"+new Date().getTime());
    Members members = new Members();
    Labels labels = new Labels();
    Checklist checklist = new Checklist();

    public LoginPage loginPage = new LoginPage();
    public BoardsPage boardsPage = new BoardsPage();
    public CardPage cardPage = new CardPage();


    @BeforeSuite
    public void prepareData() throws IOException {
        createdBoard = client.boardsService.createBoard("Test_Board_"+new Date().getTime()).execute().body();
        listAction = client.listsService.createList(createdBoard.id, "Test_List_"+new Date().getTime()).execute().body();
        card = client.cardsService.createCard(listAction.id, card).execute().body();
    }

    @AfterSuite
    public void clearData() throws IOException {
        client.boardsService.deleteBoard(createdBoard.id).execute();
    }
    @Feature("Preconditions")
    @Story("Login user")
    @Test(priority = 1)
    public void login(){
        loginPage.open();
        loginPage.login("test.qa9966@gmail.com", "test_Qa9966$");

    }

    @Feature("Preconditions")
    @Story("Open card created by API")
    @Test(dependsOnMethods = "login")
    @Severity(SeverityLevel.BLOCKER)
    public void openCard() throws IOException, InterruptedException {
        boardsPage.openBoard(createdBoard.url);
        cardPage.openCard(card.url);
//        card = client.cardsService.getCard(card.id).execute().body();
//        Assert.assertEquals(cardPage.getCardName(), card.name);
//        System.out.println(cardPage.getCardName());
    }

    @Feature("Add action")
    @Story("Add new members to the Card")
    @Test(dependsOnMethods = "openCard")
    @Severity(SeverityLevel.NORMAL)
    public void addMembers() throws IOException, InterruptedException {
        cardPage.addMembers();
        new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(15))
                .pollingEvery(Duration.ofMillis(500))
                .until(webdriver -> {
                    try {
                        card = client.cardsService.getCard(card.id).execute().body();
                        if(card.idMembers[0].isEmpty()){
                            return false;}
                        else{
                            return card;}
                    } catch (IOException ex) {
                        ex.printStackTrace();
                        return false;
                    }
                });

        card =  client.cardsService.getCard(card.id).execute().body();
        members = client.memberService.getMembers(card.idMembers[0]).execute().body();
        Assert.assertEquals(cardPage.getMembersUsername(), members.username);
        driver.findElement(By.cssSelector(".pop-over-header-close-btn.icon-sm.icon-close")).click();
    }

    @Feature("Add action")
    @Story("Add labels")
    @Test(priority = 3)
    @Flaky
    public void addLabels() throws IOException {
        cardPage.addLabel();
        card =  client.cardsService.getCard(card.id).execute().body();
        labels = client.labelsService.getLabels(card.idLabels[0]).execute().body();
        Assert.assertEquals(cardPage.getLabel(),labels.name);
    }

    @Feature("Add action")
    @Story("Add Desciption")
    @Test(priority = 4)
    public void addDescription() throws IOException, InterruptedException {
        cardPage.addDescription();
        new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(15))
                .pollingEvery(Duration.ofMillis(500))
                .until(webdriver -> {
                    try {
                        card = client.cardsService.getCard(card.id).execute().body();
                        if(card.desc.isEmpty()){
                            return false;}
                        else{
                            return card;}
                    } catch (IOException ex) {
                        ex.printStackTrace();
                        return false;
                    }
                });
        //waitResponse(card.desc);
        //Thread.sleep(500);
        //card = client.cardsService.getCard(card.id).execute().body();
        Assert.assertEquals(card.desc, cardPage.getDescription());
    }

    @Feature("Add action")
    @Story("Add checklist")
    @Test(priority = 5)
    @Owner("Pozniak Ruslan")
    public void addChecklist() throws IOException {
        cardPage.addChecklist();
        new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(15))
                .pollingEvery(Duration.ofMillis(500))
                .until(webdriver -> {
                    try {
                        card = client.cardsService.getCard(card.id).execute().body();
                        if(card.idChecklists[0].isEmpty()){
                            return false;}
                        else{
                            return card;}
                    } catch (IOException ex) {
                        ex.printStackTrace();
                        return false;
                    }
                });
        //waitResponse(card.idChecklists[0]);
        //card = client.cardsService.getCard(card.id).execute().body();
        checklist = client.checklistService.getChecklist(card.idChecklists[0]).execute().body();
        Assert.assertEquals(cardPage.getChecklist(),checklist.name);
    }


    @Feature("Add action")
    @Story("Add Due Date")
    @Test(priority = 6)
    public void addDueDate() throws IOException, InterruptedException {
        cardPage.addDueDate();
        new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(15))
                .pollingEvery(Duration.ofMillis(500))
                .until(webdriver -> {
                    try {
                        card = client.cardsService.getCard(card.id).execute().body();
                        if(card.due==null){
                            return false;}
                        else{
                        return !Objects.isNull(card);}
                    } catch (IOException ex) {
                        ex.printStackTrace();
                        return false;
                    }
                });
        String pattern = "d MMM yyyy 'at' HH:mm";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);

        String date = simpleDateFormat.format(card.due);
        Assert.assertEquals(cardPage.getDueDate(), date);
    }


}
