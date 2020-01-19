package xyz.kida.magiccardseeker.data.db;

import androidx.room.Database;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import xyz.kida.magiccardseeker.data.entity.ColorConverter;
import xyz.kida.magiccardseeker.data.entity.MagicCardEntity;

@Database(entities = {MagicCardEntity.class}, version = 1)
@TypeConverters({ColorConverter.class})
public  abstract class MagicCardDatabase extends RoomDatabase {
    public abstract MagicCardDao magicCardDao();
}
