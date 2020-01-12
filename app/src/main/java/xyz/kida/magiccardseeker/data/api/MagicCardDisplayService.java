package xyz.kida.magiccardseeker.data.api;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.Single;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Query;
import xyz.kida.magiccardseeker.data.api.models.MagicCardSearchResponse;

public interface MagicCardDisplayService {
    public static final String MAGIC_API_BASE_URL = "https://api.magicthegathering.io/v1/";

    @GET("cards")
    Single<MagicCardSearchResponse> getMagicCards(@Query("name") String cardname);

    public static final Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(MAGIC_API_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(new OkHttpClient
                    .Builder()
                    .addInterceptor(new HttpLoggingInterceptor()
                            .setLevel(HttpLoggingInterceptor.Level.BODY))
                    .connectTimeout(30, TimeUnit.SECONDS)
                    .writeTimeout(30, TimeUnit.SECONDS)
                    .callTimeout(30, TimeUnit.SECONDS)
                    .readTimeout(30, TimeUnit.SECONDS)
                    .build())
            .build();
}
