package xyz.kida.magiccardseeker.data.repository;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Flowable;
import io.reactivex.Single;
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
    public Single<MagicCardSearchResponse> getMagicCards(String cardname) {
        return magicCardRemoteDataSource.getMagicCardsSearchResponse(cardname);
    }

    @Override
    public Flowable<List<MagicCardEntity>> loadCollection() {
        return magicCardLocalDataSource.loadCollection();
    }

    @Override
    public Completable addCardToCollection(String cardId) {
        return magicCardRemoteDataSource.getMagicCard(cardId)
                .map(magicCardSearchResponse ->
                        magicCardMapper.toMagicCardEntity(magicCardSearchResponse.getCards().get(0)))
                .flatMapCompletable(magicCardEntity ->
                        magicCardLocalDataSource.addCardToCollection(magicCardEntity));
    }

    @Override
    public Completable deleteCardFromCollection(String cardId) {
        return magicCardLocalDataSource.deleteCardFromCollection(cardId);
    }
}
