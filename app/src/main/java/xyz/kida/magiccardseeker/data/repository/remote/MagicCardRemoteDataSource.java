package xyz.kida.magiccardseeker.data.repository.remote;

import io.reactivex.Single;
import xyz.kida.magiccardseeker.data.api.MagicCardDisplayService;
import xyz.kida.magiccardseeker.data.api.models.MagicCardSearchResponse;

public class MagicCardRemoteDataSource {

    private MagicCardDisplayService magicCardDisplayService;

    public MagicCardRemoteDataSource(MagicCardDisplayService magicCardDisplayService) {
        this.magicCardDisplayService = magicCardDisplayService;
    }

    public Single<MagicCardSearchResponse> getMagicCardsSearchResponse(String cardname) {
        return magicCardDisplayService.getMagicCards(cardname);
    }
}
