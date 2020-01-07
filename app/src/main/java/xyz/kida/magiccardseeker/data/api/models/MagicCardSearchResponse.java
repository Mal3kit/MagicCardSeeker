package xyz.kida.magiccardseeker.data.api.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class MagicCardSearchResponse {


    @SerializedName("cards")
    @Expose
    private List<MagicCard> cards = null;

    public List<MagicCard> getCards() {
        return cards;
    }

    public void setCards(List<MagicCard> cards) {
        this.cards = cards;
    }
}
