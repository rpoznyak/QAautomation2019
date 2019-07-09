package junk;

import api.TrelloRestClient;
import api.models.Board;
import api.models.TrelloList;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import ui.core.BrowserFactory;

import java.io.IOException;
import java.util.Date;

public class ListsTest extends BrowserFactory {

    public TrelloRestClient client =new TrelloRestClient();

    TrelloList listAction = new TrelloList();
    Board createdBoard = new Board();

    @BeforeTest
    public void listTestCreate() throws IOException {
        createdBoard = client.boardsService.createBoard("Test_Card_"+new Date().getTime()).execute().body();
        listAction = client.listsService.createList(createdBoard.id, "Test_List_"+new Date().getTime()).execute().body();
    }

    @Test(priority = 1)
    public void listTestGetId() throws IOException {
        client.listsService.getList(listAction.id).execute();
    }

    @Test(priority = 2)
    public void listTestUpdate() throws IOException {
        listAction.name = "UPDATE_LIST_NAME";
        client.listsService.updateList(listAction.id, listAction).execute();
    }

    @Test(priority = 3)
    public void listTestDelete() throws IOException {
        client.listsService.archiveList(listAction.id, true).execute();
    }

}
