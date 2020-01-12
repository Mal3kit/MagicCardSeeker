package xyz.kida.magiccardseeker.presentation.search.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import xyz.kida.magiccardseeker.R;
import xyz.kida.magiccardseeker.presentation.search.model.CardViewHolder;
import xyz.kida.magiccardseeker.presentation.search.model.MagicCardOnSwitchListener;
import xyz.kida.magiccardseeker.presentation.search.model.MagicCardViewModel;

public class SearchAdapter extends RecyclerView.Adapter<CardViewHolder> {

    private List<MagicCardViewModel> cards;
    private final MagicCardOnSwitchListener listener;

    public SearchAdapter(MagicCardOnSwitchListener listener) {
        this.cards = new ArrayList<>();
        this.listener = listener;
    }

    public void bindViewModels(List<MagicCardViewModel> magicCardViewModels) {
        this.cards.clear();
        this.cards.addAll(magicCardViewModels);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public CardViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.item_card, parent, false);
        return new CardViewHolder(view,listener);
    }

    @Override
    public void onBindViewHolder(@NonNull CardViewHolder holder, int position) {
        holder.updateWithCardView(this.cards.get(position));
    }

    @Override
    public int getItemCount() {
        return this.cards.size();
    }

}
