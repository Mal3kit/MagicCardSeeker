package xyz.kida.magiccardseeker.data.entity;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.List;

@Entity
public class MagicCardEntity {

    @NonNull
    @PrimaryKey
    public String id;
    public String cardName;
    private String description;
    private String imageUrl;
    private List<String> colors;

    public String getId() {
        return id;
    }

    public void setId(@NonNull String id) {
        this.id = id;
    }

    public String getCardName() {
        return cardName;
    }

    public void setCardName(String cardName) {
        this.cardName = cardName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public List<String> getColors() {
        return colors;
    }

    public void setColors(List<String> colors) {
        this.colors = colors;
    }
}
