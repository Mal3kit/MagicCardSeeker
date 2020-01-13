package xyz.kida.magiccardseeker.data.api;

import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Query;
import xyz.kida.magiccardseeker.data.api.models.MagicCardSearchResponse;

public interface MagicCardDisplayService {

    @GET("cards")
    Single<MagicCardSearchResponse> getMagicCards(@Query("name") String cardname);

    @GET("cards")
    Single<MagicCardSearchResponse> getMagicCard(@Query("id") String id);
}
