package api.services;

import api.models.Members;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface MemberService {

    @GET("members/{id}")
    Call<Members> getMembers(@Path("id") String id);

}
