package xyz.kida.magiccardseeker;

import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import xyz.kida.magiccardseeker.data.api.MagicCardDisplayService;
import xyz.kida.magiccardseeker.data.api.models.MagicCard;

public class CardStreams {

    public static Observable<List<MagicCard>> streamGetCards(String cardname) {
        MagicCardDisplayService magicCardDisplayService = MagicCardDisplayService.retrofit.create(MagicCardDisplayService.class);
        return magicCardDisplayService.getMagicCards(cardname)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .timeout(10, TimeUnit.SECONDS);
    }
}
