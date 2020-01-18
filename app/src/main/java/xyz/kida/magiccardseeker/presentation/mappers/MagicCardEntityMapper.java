package xyz.kida.magiccardseeker.presentation.mappers;

import xyz.kida.magiccardseeker.data.entity.MagicCardEntity;
import xyz.kida.magiccardseeker.presentation.model.MagicCardViewModel;

public class MagicCardEntityMapper {

    public MagicCardViewModel toViewModel(MagicCardEntity magicCardEntity) {
        MagicCardViewModel magicCardViewModel = new MagicCardViewModel();
        magicCardViewModel.setCardId(magicCardEntity.getId());
        magicCardViewModel.setCardName(magicCardEntity.getCardName());
        if (magicCardEntity.getDescription() != null) {
            magicCardViewModel.setDescription(magicCardEntity.getDescription());
        }
        magicCardViewModel.setImageUrl(magicCardEntity.getImageUrl());
        magicCardViewModel.setColors(magicCardEntity.getColors());

        return magicCardViewModel;
    }

    public MagicCardEntity toEntity(MagicCardViewModel magicCardViewModel) {
        MagicCardEntity magicCardEntity = new MagicCardEntity();
        magicCardEntity.setId(magicCardViewModel.getCardId());
        magicCardEntity.setCardName(magicCardViewModel.getCardName());
        magicCardEntity.setImageUrl(magicCardViewModel.getImageUrl());
        if (magicCardViewModel.getDescription() != null) {
            magicCardEntity.setDescription(magicCardViewModel.getDescription());
        }
        magicCardEntity.setColors(magicCardViewModel.getColors());
        return magicCardEntity;
    }
}
