package xyz.kida.magiccardseeker.data.repository;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Flowable;
import io.reactivex.Observable;
import io.reactivex.functions.BiFunction;
import xyz.kida.magiccardseeker.data.api.models.MagicCard;
import xyz.kida.magiccardseeker.data.api.models.MagicCardSearchResponse;
import xyz.kida.magiccardseeker.data.entity.MagicCardEntity;
import xyz.kida.magiccardseeker.data.repository.local.MagicCardLocalDataSource;
import xyz.kida.magiccardseeker.data.repository.mapper.MagicCardMapper;
import xyz.kida.magiccardseeker.data.repository.remote.MagicCardRemoteDataSource;

public class MagicCardDataRepository implements MagicCardRepository {

    private MagicCardLocalDataSource magicCardLocalDataSource;
    private MagicCardRemoteDataSource magicCardRemoteDataSource;
    private MagicCardMapper magicCardMapper;

    public MagicCardDataRepository(MagicCardLocalDataSource magicCardLocalDataSource, MagicCardRemoteDataSource magicCardRemoteDataSource, MagicCardMapper magicCardMapper) {
        this.magicCardLocalDataSource = magicCardLocalDataSource;
        this.magicCardRemoteDataSource = magicCardRemoteDataSource;
        this.magicCardMapper = magicCardMapper;
    }

    @Override
    public Observable<MagicCardSearchResponse> getMagicCards(String cardname) {
        return magicCardRemoteDataSource.getMagicCards(cardname);
    }

    @Override
    public Flowable<List<MagicCardEntity>> loadCollection() {
        return magicCardLocalDataSource.loadCollection();
    }

    @Override
    public Completable addCardToCollection(MagicCardEntity magicCardEntity) {
        return null;
    }

    @Override
    public Completable deleteCardFromCollection(String id) {
        return null;
    }
}
