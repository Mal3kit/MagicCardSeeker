package xyz.kida.magiccardseeker.data.db;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Flowable;
import io.reactivex.Single;
import xyz.kida.magiccardseeker.data.entity.MagicCardEntity;

@Dao
public interface MagicCardDao {

    @Query("SELECT * FROM magiccardentity")
    Flowable<List<MagicCardEntity>> loadCollection();

    @Insert
    Completable addCardToCollection(MagicCardEntity magicCardEntity);

    @Query("DELETE FROM magiccardentity WHERE externalId = :id")
    Completable deleteCardFromCollection(String id);

    @Query("SELECT externalId FROM magiccardentity")
    Single<List<String>> getCollectionListId();

}
