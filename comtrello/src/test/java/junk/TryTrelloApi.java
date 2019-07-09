package junk;

import api.TrelloRestClient;
import api.models.Card;
import org.testng.annotations.Test;

import java.io.IOException;

public class TryTrelloApi {

    TrelloRestClient client = new TrelloRestClient();

    @Test
    public void callIt ()throws IOException{
        Card card = new Card();
        card.name = "My New CARD";
        Card createdCard = client.cardsService.createCard("5d13b031f686466a59bc91c7", card).execute().body();
        client.cardsService.deleteCard(createdCard.id).execute();
    }

}
