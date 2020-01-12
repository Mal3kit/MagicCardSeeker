package xyz.kida.magiccardseeker.presentation.search.mapper;

import xyz.kida.magiccardseeker.data.api.models.MagicCard;
import xyz.kida.magiccardseeker.presentation.search.model.MagicCardViewModel;

public class MagicCardToViewModelMapper {

    public MagicCardViewModel toViewModel(MagicCard magicCard) {
        MagicCardViewModel magicCardViewModel = new MagicCardViewModel();
        magicCardViewModel.setCardId(magicCard.getId());
        magicCardViewModel.setCardName(magicCard.getName());
        if (magicCard.getText() != null) {
            magicCardViewModel.setDescription(magicCard.getText());
        }
        magicCard.setInCollection(magicCard.isInCollection());
        magicCardViewModel.setImageUrl(magicCard.getImageUrl());

        return magicCardViewModel;
    }

}
