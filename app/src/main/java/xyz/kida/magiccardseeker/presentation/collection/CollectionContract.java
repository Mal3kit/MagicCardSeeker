package xyz.kida.magiccardseeker.presentation.collection;

import xyz.kida.magiccardseeker.presentation.model.MagicCardViewModel;

import java.util.List;

public interface CollectionContract {

    interface View {
        void displayCollection(List<MagicCardViewModel> magicCardViewModels);

        void onCardRemovedFromCollection();
    }

    interface Presenter {
        void attachView(View view);

        void getCollection();

        void deleteCardFromCollection(String cardId);

        void detachView();
    }

}
