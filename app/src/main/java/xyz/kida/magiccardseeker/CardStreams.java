package xyz.kida.magiccardseeker;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import xyz.kida.magiccardseeker.data.api.MagicCardDisplayService;
import xyz.kida.magiccardseeker.data.api.models.MagicCardSearchResponse;

public class CardStreams {

    public static Observable<MagicCardSearchResponse> streamGetCards(String cardname) {
        MagicCardDisplayService magicCardDisplayService = MagicCardDisplayService.retrofit.create(MagicCardDisplayService.class);
        return magicCardDisplayService.getMagicCards(cardname)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .timeout(20, TimeUnit.SECONDS);
    }
}
