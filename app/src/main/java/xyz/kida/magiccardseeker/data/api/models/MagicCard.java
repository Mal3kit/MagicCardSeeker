package xyz.kida.magiccardseeker.data.api.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MagicCard {

    @SerializedName("name")
    @Expose
    private String name;

    @SerializedName("imageUrl")
    @Expose
    private String imageUrl;

    @SerializedName("text")
    @Expose
    private String text;

    private boolean showDetails = false;

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

    public boolean isShowDetails() {
        return showDetails;
    }

    public void setShowDetails(boolean showDetails) {
        this.showDetails = showDetails;
    }
}
