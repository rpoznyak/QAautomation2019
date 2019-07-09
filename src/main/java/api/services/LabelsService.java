package api.services;

import api.models.Labels;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.*;

public interface LabelsService {

    @POST("labels")
    Call<Labels> createLabels(@Query("idBoard") String idBoard, @Query("name") String name, @Query("color") String color);

    @GET("labels/{id}")
    Call<Labels> getLabels(@Path("id") String id);

    @PUT("labels/{id}/color")
    Call<Labels> updateLabels(@Path("id") String id, @Query("value") String value);

    @DELETE("labels/{id}")
    Call<ResponseBody> deleteLabels(@Path("id") String id);

}
