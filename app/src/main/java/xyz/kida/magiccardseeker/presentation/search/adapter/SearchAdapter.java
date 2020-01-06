package xyz.kida.magiccardseeker.presentation.search.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.RequestManager;

import java.util.List;

import xyz.kida.magiccardseeker.R;
import xyz.kida.magiccardseeker.data.api.models.MagicCard;
import xyz.kida.magiccardseeker.presentation.search.model.CardViewHolder;

public class SearchAdapter extends RecyclerView.Adapter<CardViewHolder> {

    private RequestManager requestManager;
    private List<MagicCard> cards;

    private final SearchAdapterListener listener;

    public SearchAdapter(List<MagicCard> cards, RequestManager requestManager, SearchAdapterListener listener) {
        this.requestManager = requestManager;
        this.cards = cards;
        this.listener = listener;
    }

    @NonNull
    @Override
    public CardViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.fragment_search, parent, false);
        return new CardViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CardViewHolder holder, int position) {
        holder.updateWithCardView(this.cards.get(position), this.listener);
    }

    @Override
    public int getItemCount() {
        return this.cards.size();
    }

    public MagicCard getMagicCard(int position) {
        return this.getMagicCard(position);
    }
}
