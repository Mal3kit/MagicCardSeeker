package xyz.kida.magiccardseeker.data.repository.mapper;

import xyz.kida.magiccardseeker.data.api.models.MagicCard;
import xyz.kida.magiccardseeker.data.entity.MagicCardEntity;

public class MagicCardMapper {

    public MagicCardEntity toMagicCardEntity(MagicCard magicCard) {

        MagicCardEntity magicCardEntity = new MagicCardEntity();
        magicCardEntity.setId(magicCard.getId());
        magicCardEntity.setCardName(magicCard.getName());
        magicCardEntity.setDescription(magicCard.getText());
        magicCardEntity.setImageUrl(magicCard.getImageUrl());
        magicCardEntity.setColors(magicCard.getColors());

        return magicCardEntity;
    }
}
