package api.services;

import api.models.Card;
import api.models.Members;
import io.qameta.allure.Step;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.*;

import java.util.ArrayList;
import java.util.List;

public interface CardsService {

    @Step
    @POST("cards")
    Call<Card> createCard(@Query("idList") String idList, @Body Card card);

    @Step
    @GET("cards/{id}")
    Call<Card> getCard(@Path("id") String id);

    @Step
    @PUT("cards/{id}")
    Call<Card> updateCard(@Path("id") String id, @Body Card card);

    @Step
    @DELETE("cards/{id}")
    Call<ResponseBody> deleteCard(@Path("id") String id);

}
