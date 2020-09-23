package Model;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

import Response.ApiCorona;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ViewModelAttributes extends ViewModel {
    private MutableLiveData<List<Attributes>> listAttributes;
    private MutableLiveData<List<Country>> listCountry;
    private String url = "https://api.kawalcorona.com/";

    public LiveData<List<Attributes>> getAttributes(){
        if (listAttributes == null) {
            listAttributes = new MutableLiveData<List<Attributes>>();
            loadAttributes();
        }
        return listAttributes;
    }

    public LiveData<List<Country>> getCountry(){
        if (listCountry == null) {
            listCountry = new MutableLiveData<List<Country>>();
            loadCountry();
        }

        return listCountry;
    }

    private void loadCountry() {
        Retrofit retrofit = new Retrofit.Builder().baseUrl(url).
                addConverterFactory(GsonConverterFactory.create()).build();
        ApiCorona api = retrofit.create(ApiCorona.class);
        Call<List<Country>> call = api.getByCountry();
        call.enqueue(new Callback<List<Country>>() {
            @Override
            public void onResponse(Call<List<Country>> call, Response<List<Country>> response) {
                listCountry.setValue(response.body());
            }

            @Override
            public void onFailure(Call<List<Country>> call, Throwable t) {

            }
        });
    }

    private void loadAttributes(){
        Retrofit retrofit = new Retrofit.Builder().baseUrl(url).
                addConverterFactory(GsonConverterFactory.create()).build();
        ApiCorona api = retrofit.create(ApiCorona.class);
        Call<List<Attributes>> call = api.getCorona();
        call.enqueue(new Callback<List<Attributes>>() {
            @Override
            public void onResponse(Call<List<Attributes>> call, Response<List<Attributes>> response) {
                listAttributes.setValue(response.body());
            }

            @Override
            public void onFailure(Call<List<Attributes>> call, Throwable t) {

            }
        });
    }
}
