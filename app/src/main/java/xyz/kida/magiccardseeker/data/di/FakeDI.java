package xyz.kida.magiccardseeker.data.di;

import android.content.Context;
import androidx.room.Room;
import com.google.gson.Gson;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import xyz.kida.magiccardseeker.data.api.MagicCardDisplayService;
import xyz.kida.magiccardseeker.data.db.MagicCardDatabase;
import xyz.kida.magiccardseeker.data.repository.MagicCardDataRepository;
import xyz.kida.magiccardseeker.data.repository.MagicCardRepository;
import xyz.kida.magiccardseeker.data.repository.local.MagicCardLocalDataSource;
import xyz.kida.magiccardseeker.data.repository.remote.MagicCardRemoteDataSource;
import xyz.kida.magiccardseeker.presentation.mappers.MagicCardEntityMapper;

import java.util.concurrent.TimeUnit;

public class FakeDI {

    private static MagicCardDisplayService magicCardDisplayService;
    private static Retrofit retrofit;
    private static MagicCardRepository magicCardRepository;
    private static MagicCardDatabase database;
    private static Gson gson;
    private static Context appContext;

    public static final String MAGIC_API_BASE_URL = "https://api.magicthegathering.io/v1/";

    public static MagicCardRepository getMagicCardRepository() {
        if (magicCardRepository == null) {
            magicCardRepository = new MagicCardDataRepository(
                    new MagicCardLocalDataSource(getMagicCardDatabase()),
                    new MagicCardRemoteDataSource(getMagicCardDisplayService()),
                    new MagicCardEntityMapper());
        }

        return magicCardRepository;
    }

    public static MagicCardDatabase getMagicCardDatabase() {
        if (database == null) {
            database = Room.databaseBuilder(appContext,
                    MagicCardDatabase.class, "magic-card-database").build();
        }
        return database;
    }

    public static MagicCardDisplayService getMagicCardDisplayService() {
        if (magicCardDisplayService == null) {
            magicCardDisplayService = getRetrofit().create(MagicCardDisplayService.class);
        }
        return magicCardDisplayService;
    }

        public static Retrofit getRetrofit() {
            if (retrofit == null) {
                retrofit = new Retrofit.Builder()
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
            return retrofit;
        }

    public static void setAppContext(Context context) {
        appContext = context;
    }
}
