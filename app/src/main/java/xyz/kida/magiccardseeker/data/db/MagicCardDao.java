package xyz.kida.magiccardseeker.data.db;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import io.reactivex.Completable;
import io.reactivex.Flowable;
import io.reactivex.Single;
import xyz.kida.magiccardseeker.data.entity.MagicCardEntity;

import java.util.List;

@Dao
public interface MagicCardDao {

    @Query("SELECT * FROM magiccardentity")
    Flowable<List<MagicCardEntity>> loadCollection();

    @Insert
    Completable addCardToCollection(MagicCardEntity magicCardEntity);

    @Query("DELETE FROM magiccardentity WHERE id = :id")
    Completable deleteCardFromCollection(String id);

    @Query("SELECT id FROM magiccardentity")
    Single<List<String>> getCollectionListId();

}
