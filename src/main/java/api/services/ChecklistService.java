package api.services;

import api.models.Checklist;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ChecklistService {

    @GET("checklists/{id}")
    Call<Checklist> getChecklist(@Path("id") String id);

}
