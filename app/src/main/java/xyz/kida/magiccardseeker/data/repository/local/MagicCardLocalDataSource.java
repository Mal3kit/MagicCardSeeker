package xyz.kida.magiccardseeker.data.repository.local;

import io.reactivex.Completable;
import io.reactivex.Flowable;
import io.reactivex.Single;
import xyz.kida.magiccardseeker.data.db.MagicCardDatabase;
import xyz.kida.magiccardseeker.data.entity.MagicCardEntity;

import java.util.List;

public class MagicCardLocalDataSource {

    private MagicCardDatabase magicCardDatabase;

    public MagicCardLocalDataSource(MagicCardDatabase magicCardDatabase) {
        this.magicCardDatabase = magicCardDatabase;
    }

    public Flowable<List<MagicCardEntity>> loadCollection() {
        return magicCardDatabase.magicCardDao().loadCollection();
    }

    public Completable addCardToCollection(MagicCardEntity magicCardEntity) {
        return magicCardDatabase.magicCardDao().addCardToCollection(magicCardEntity);
    }

    public Completable deleteCardFromCollection(String id) {
        return magicCardDatabase.magicCardDao().deleteCardFromCollection(id);
    }

    public Single<List<String>> getCollectionIdList() {
        return magicCardDatabase.magicCardDao().getCollectionListId();
    }

}
