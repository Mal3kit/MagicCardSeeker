package xyz.kida.magiccardseeker.data.repository.remote;

import io.reactivex.Observable;
import retrofit2.http.Query;
import xyz.kida.magiccardseeker.data.api.MagicCardDisplayService;
import xyz.kida.magiccardseeker.data.api.models.MagicCardSearchResponse;

public class MagicCardRemoteDataSource {

    private MagicCardDisplayService magicCardDisplayService;

    public MagicCardRemoteDataSource(MagicCardDisplayService magicCardDisplayService) {
        this.magicCardDisplayService = magicCardDisplayService;
    }

    public Observable<MagicCardSearchResponse> getMagicCards(String cardname) {
        return magicCardDisplayService.getMagicCards(cardname);
    }
}
