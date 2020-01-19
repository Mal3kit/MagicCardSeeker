package xyz.kida.magiccardseeker.data.repository;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Flowable;
import io.reactivex.Single;
import io.reactivex.functions.BiFunction;
import xyz.kida.magiccardseeker.data.api.models.MagicCard;
import xyz.kida.magiccardseeker.data.api.models.MagicCardSearchResponse;
import xyz.kida.magiccardseeker.data.entity.MagicCardEntity;
import xyz.kida.magiccardseeker.data.repository.local.MagicCardLocalDataSource;
import xyz.kida.magiccardseeker.data.repository.remote.MagicCardRemoteDataSource;
import xyz.kida.magiccardseeker.presentation.mappers.MagicCardEntityMapper;
import xyz.kida.magiccardseeker.presentation.model.MagicCardViewModel;

public class MagicCardDataRepository implements MagicCardRepository {

    private MagicCardLocalDataSource magicCardLocalDataSource;
    private MagicCardRemoteDataSource magicCardRemoteDataSource;
    private MagicCardEntityMapper magicCardMapper;

    public MagicCardDataRepository(MagicCardLocalDataSource magicCardLocalDataSource, MagicCardRemoteDataSource magicCardRemoteDataSource, MagicCardEntityMapper magicCardMapper) {
        this.magicCardLocalDataSource = magicCardLocalDataSource;
        this.magicCardRemoteDataSource = magicCardRemoteDataSource;
        this.magicCardMapper = magicCardMapper;
    }

    @Override
    public Single<MagicCardSearchResponse> getMagicCards(String cardname) {
        return magicCardRemoteDataSource.getMagicCardsSearchResponse(cardname)
                .zipWith(magicCardLocalDataSource.getCollectionIdList(), new BiFunction<MagicCardSearchResponse, List<String>, MagicCardSearchResponse>() {
                    @Override
                    public MagicCardSearchResponse apply(MagicCardSearchResponse magicCardSearchResponse, List<String> idList) throws Exception {
                        for (MagicCard card : magicCardSearchResponse.getCards()) {
                            if (idList.contains(card.getId())) {
                                card.setInCollection(true);
                            }
                        }
                        return magicCardSearchResponse;
                    }
                });
    }

    @Override
    public Flowable<List<MagicCardEntity>> loadCollection() {
        return magicCardLocalDataSource.loadCollection();
    }

    @Override
    public Completable addCardToCollection(MagicCardViewModel cardViewModel) {
        return magicCardLocalDataSource.addCardToCollection(magicCardMapper.toEntity(cardViewModel));
    }


    @Override
    public Completable deleteCardFromCollection(String cardId) {
        return magicCardLocalDataSource.deleteCardFromCollection(cardId);
    }
}
