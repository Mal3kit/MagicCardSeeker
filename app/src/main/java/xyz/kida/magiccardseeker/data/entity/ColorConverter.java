package xyz.kida.magiccardseeker.data.entity;

import androidx.room.TypeConverter;

import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.Collections;
import java.util.List;

import xyz.kida.magiccardseeker.data.di.FakeDI;

public class ColorConverter {

    @TypeConverter
    public static List<String> stringToListOfString(String color) {
        if (color == null) {
            return Collections.emptyList();
        }

        Type stringList = new TypeToken<List<String>>(){}.getType();

        return FakeDI.getGson().fromJson(color, stringList);
    }

    @TypeConverter
    public static String colorListToString(List<String> colors) {
        return FakeDI.getGson().toJson(colors);
    }
}
