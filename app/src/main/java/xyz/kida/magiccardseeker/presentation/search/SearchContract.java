package xyz.kida.magiccardseeker.presentation.search;

import java.util.List;

import xyz.kida.magiccardseeker.presentation.search.model.MagicCardViewModel;

public interface SearchContract {

    interface View {
        void showCards(List<MagicCardViewModel> magicCardViewModels);

        void onCardAddToCollection();

        void onCardRemovedFromCollection();
    }

    interface Presenter {
        void searchCards(String keywords);

        void attachView(View view);

        void cancelSubscription();

        void addCardToCollection(String cardId);

        void deleteCardFromCollection(String cardId);

        void detachView();
    }
}
