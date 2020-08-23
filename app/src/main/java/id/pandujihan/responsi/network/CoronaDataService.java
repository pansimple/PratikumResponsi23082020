package id.pandujihan.responsi.network;

import id.pandujihan.responsi.model.Corona;

import java.util.List;

import id.pandujihan.responsi.model.Corona;
import retrofit2.Call;
import retrofit2.http.GET;

public interface CoronaDataService {
    @GET("indonesia/provinsi")
    Call<List<Corona>> getCorona();
}
