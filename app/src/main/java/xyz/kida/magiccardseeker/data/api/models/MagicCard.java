package xyz.kida.magiccardseeker.data.api.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class MagicCard {

    @SerializedName("id")
    @Expose
    private String id;

    @SerializedName("name")
    @Expose
    private String name;

    @SerializedName("imageUrl")
    @Expose
    private String imageUrl;

    @SerializedName("colors")
    @Expose
    private List<String> colors;

    @SerializedName("text")
    @Expose
    private String text;

    private boolean isInCollection;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public List<String> getColors() {
        return colors;
    }

    public void setColors(List<String> colors) {
        this.colors = colors;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public boolean isInCollection() {
        return isInCollection;
    }

    public void setInCollection(boolean inCollection) {
        isInCollection = inCollection;
    }

}
