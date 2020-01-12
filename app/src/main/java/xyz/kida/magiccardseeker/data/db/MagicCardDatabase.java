package xyz.kida.magiccardseeker.data.db;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import xyz.kida.magiccardseeker.data.entity.MagicCardEntity;

@Database(entities = {MagicCardEntity.class}, version = 1)
public  abstract class MagicCardDatabase extends RoomDatabase {
    public abstract MagicCardDao magicCardDao();
}
