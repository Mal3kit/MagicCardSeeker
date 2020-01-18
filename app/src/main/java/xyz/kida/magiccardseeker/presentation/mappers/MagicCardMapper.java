package xyz.kida.magiccardseeker.presentation.mappers;

import xyz.kida.magiccardseeker.data.api.models.MagicCard;
import xyz.kida.magiccardseeker.presentation.model.MagicCardViewModel;

public class MagicCardMapper {

    public MagicCardViewModel toViewModel(MagicCard magicCard) {
        MagicCardViewModel magicCardViewModel = new MagicCardViewModel();
        magicCardViewModel.setCardId(magicCard.getId());
        magicCardViewModel.setCardId(magicCard.getId());
        magicCardViewModel.setCardName(magicCard.getName());
        if (magicCard.getText() != null) {
            magicCardViewModel.setDescription(magicCard.getText());
        }
        magicCardViewModel.setInMyCollection(magicCard.isInCollection());
        magicCardViewModel.setImageUrl(magicCard.getImageUrl());
        magicCardViewModel.setColors(magicCard.getColors());

        return magicCardViewModel;
    }

}
