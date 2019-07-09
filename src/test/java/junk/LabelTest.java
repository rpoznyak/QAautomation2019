package junk;

import api.TrelloRestClient;
import api.models.Board;
import api.models.Labels;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import ui.core.BrowserFactory;

import java.io.IOException;
import java.util.Date;

public class LabelTest extends BrowserFactory {

    public TrelloRestClient client =new TrelloRestClient();

    Board createdBoard = new Board();
    Labels labelsAction = new Labels("Test_Label_"+new Date().getTime(), "red");

    @BeforeTest
    public void labelsTestCreate() throws IOException {
        createdBoard = client.boardsService.createBoard("Test_Card_"+new Date().getTime()).execute().body();
        labelsAction = client.labelsService.createLabels(createdBoard.id, labelsAction.name, labelsAction.color).execute().body();
    }


    @Test(priority = 1)
    public void labelsTestGetId() throws IOException {
        client.labelsService.getLabels(labelsAction.id).execute();
    }

    @Test(priority = 2)
    public void labelsTestUpdateColor() throws IOException {
        client.labelsService.updateLabels(labelsAction.id, "yellow").execute();
    }

    @Test(priority = 3)
    public void labelsTestDelete() throws IOException {
        client.labelsService.deleteLabels(labelsAction.id).execute();
    }

}
