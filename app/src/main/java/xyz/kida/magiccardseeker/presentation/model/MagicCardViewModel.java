package xyz.kida.magiccardseeker.presentation.model;

import java.util.List;

public class MagicCardViewModel {

    private String cardId;
    private String imageUrl;
    private String cardName;
    private String description;
    private List<String> colors;

    private boolean isInMyCollection;

    public String getCardId() {
        return cardId;
    }

    public void setCardId(String cardId) {
        this.cardId = cardId;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
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

    public List<String> getColors() {
        return colors;
    }

    public void setColors(List<String> colors) {
        this.colors = colors;
    }

    public boolean isInMyCollection() {
        return isInMyCollection;
    }

    public void setInMyCollection(boolean inMyCollection) {
        isInMyCollection = inMyCollection;
    }
}
