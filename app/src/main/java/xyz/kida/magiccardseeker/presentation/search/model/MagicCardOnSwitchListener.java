package xyz.kida.magiccardseeker.presentation.search.model;

import xyz.kida.magiccardseeker.presentation.model.MagicCardViewModel;

public interface MagicCardOnSwitchListener {

    void onSwitchToggle(MagicCardViewModel cardId, boolean isFavorite);
}
