package xyz.kida.magiccardseeker.data.entity;

import androidx.room.TypeConverter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.Collections;
import java.util.List;

public class ColorConverter {

    @TypeConverter
    public static List<String> stringToListOfString(String color) {
        if (color == null) {
            return Collections.emptyList();
        }

        Type stringList = new TypeToken<List<String>>(){}.getType();

        return new Gson().fromJson(color, stringList);
    }

    @TypeConverter
    public static String colorListToString(List<String> colors) {
        return new Gson().toJson(colors);
    }
}
