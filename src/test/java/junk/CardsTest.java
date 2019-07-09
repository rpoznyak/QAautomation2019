package junk;

import api.TrelloRestClient;
import api.models.Board;
import api.models.Card;
import api.models.TrelloList;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import ui.core.BrowserFactory;

import java.io.IOException;
import java.util.Date;

public class CardsTest extends BrowserFactory {

    public TrelloRestClient client =new TrelloRestClient();

    TrelloList listAction = new TrelloList();
    Board createdBoard = new Board();
    Card cardsAction = new Card("Test_Card_"+new Date().getTime());

    @BeforeTest
    public void cardTestCreate() throws IOException {
        createdBoard = client.boardsService.createBoard("Test_Card_"+new Date().getTime()).execute().body();
        listAction = client.listsService.createList(createdBoard.id, "Test_List_"+new Date().getTime()).execute().body();
        cardsAction = client.cardsService.createCard(listAction.id, cardsAction).execute().body();
    }

    @Test(priority = 1)
    public void cardTestGetId() throws IOException {
       client.cardsService.getCard(cardsAction.id).execute();
    }

    @Test(priority = 2)
    public void cardTestUpdate() throws IOException {
        cardsAction.name = "CARD_UPDATE_NAME";
        client.cardsService.updateCard(cardsAction.id, cardsAction).execute();
    }

    @Test(priority = 3)
    public void cardTestDelete() throws IOException {
        client.cardsService.deleteCard(cardsAction.id).execute();
    }


}
