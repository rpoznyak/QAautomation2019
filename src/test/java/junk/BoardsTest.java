package junk;

import api.TrelloRestClient;
import api.models.Board;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import ui.core.BrowserFactory;
import ui.pages.BoardsPage;
import ui.pages.LoginPage;

import java.io.IOException;
import java.util.Date;

public class BoardsTest extends BrowserFactory {

    public TrelloRestClient client =new TrelloRestClient();

    Board createdBoard = new Board();

    @BeforeTest
    public void createTestBoard() throws IOException {
        createdBoard = client.boardsService.createBoard("Test_Card_"+new Date().getTime()).execute().body();
    }

    @AfterTest
    public void clearData() throws IOException {
       client.boardsService.deleteBoard(createdBoard.id).execute();
    }


    @Test(priority = 1)
    public void getBoardId() throws IOException {
        client.boardsService.getBoardId(createdBoard.id).execute();
    }

    @Test(priority = 2)
    public void updateBoard() throws IOException {
        createdBoard.name = "BOARD_NAME_UPDATE";
        client.boardsService.updateBoard(createdBoard.id, createdBoard).execute();
    }


}
