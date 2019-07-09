package api;

import api.interceptors.TrelloAuthInterceptor;
import api.services.*;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import java.util.concurrent.TimeUnit;
/**
 * Created by lolik on 13.06.2019
 */
public class TrelloRestClient {
    public static final String HOME_IO_BASE_URL = "https://api.trello.com/1/";
    public BoardsService boardsService;
    public ListsService listsService;
    public CardsService cardsService;
    public LabelsService labelsService;
    public MemberService memberService;
    public ChecklistService checklistService;

    public TrelloRestClient() {
        OkHttpClient client = new OkHttpClient.Builder()
                .connectTimeout(20, TimeUnit.SECONDS)
                .writeTimeout(20, TimeUnit.SECONDS)
                .readTimeout(20, TimeUnit.SECONDS)
                .addInterceptor(new TrelloAuthInterceptor())
                .build();
        Retrofit retrofit = new Retrofit.Builder()
                .client(client)
                .baseUrl(HOME_IO_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        boardsService = retrofit.create(BoardsService.class);
        listsService = retrofit.create(ListsService.class);
        cardsService = retrofit.create(CardsService.class);
        labelsService = retrofit.create(LabelsService.class);
        memberService = retrofit.create(MemberService.class);
        checklistService = retrofit.create(ChecklistService.class);
    }
}