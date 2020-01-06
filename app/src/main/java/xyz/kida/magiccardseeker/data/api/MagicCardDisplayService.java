package xyz.kida.magiccardseeker.data.api;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Query;
import xyz.kida.magiccardseeker.data.api.models.MagicCard;

public interface MagicCardDisplayService {
    public static final String MAGIC_API_BASE_URL = "https://api.magicthegathering.io/v1/";

    @GET("cards")
    Observable<List<MagicCard>> getMagicCards(@Query("name") String cardname);

    public static final Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(MAGIC_API_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build();
}
