package Response;

import Model.Attributes;
import Model.Country;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiCorona {
    @GET("indonesia/provinsi")
    Call<List<Attributes>> getCorona();
    @GET("/")
    Call<List<Country>> getByCountry();
}
