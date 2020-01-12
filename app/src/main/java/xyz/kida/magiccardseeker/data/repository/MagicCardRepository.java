package xyz.kida.magiccardseeker.data.repository;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Flowable;
import io.reactivex.Single;
import xyz.kida.magiccardseeker.data.api.models.MagicCardSearchResponse;
import xyz.kida.magiccardseeker.data.entity.MagicCardEntity;

public interface MagicCardRepository {

    Single<MagicCardSearchResponse> getMagicCards(String cardname);

    Flowable<List<MagicCardEntity>> loadCollection();

    Completable addCardToCollection(String magicCardId);

    Completable deleteCardFromCollection(String id);
}
