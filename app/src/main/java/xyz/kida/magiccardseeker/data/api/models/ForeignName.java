package xyz.kida.magiccardseeker.data.api.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ForeignName {

    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("text")
    @Expose
    private String text;
    @SerializedName("flavor")
    @Expose
    private Object flavor;
    @SerializedName("imageUrl")
    @Expose
    private Object imageUrl;
    @SerializedName("language")
    @Expose
    private String language;
    @SerializedName("multiverseid")
    @Expose
    private Object multiverseid;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Object getFlavor() {
        return flavor;
    }

    public void setFlavor(Object flavor) {
        this.flavor = flavor;
    }

    public Object getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(Object imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public Object getMultiverseid() {
        return multiverseid;
    }

    public void setMultiverseid(Object multiverseid) {
        this.multiverseid = multiverseid;
    }

}
