package xyz.kida.magiccardseeker.presentation.search.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import xyz.kida.magiccardseeker.R;
import xyz.kida.magiccardseeker.presentation.model.MagicCardViewModel;
import xyz.kida.magiccardseeker.presentation.search.model.MagicCardOnSwitchListener;
import xyz.kida.magiccardseeker.presentation.search.model.SearchViewHolder;

public class SearchAdapter extends RecyclerView.Adapter<SearchViewHolder> {

    private List<MagicCardViewModel> magicCardViewModels;
    private final MagicCardOnSwitchListener listener;

    public SearchAdapter(MagicCardOnSwitchListener listener) {
        this.magicCardViewModels = new ArrayList<>();
        this.listener = listener;
    }

    public void bindViewModels(List<MagicCardViewModel> magicCardViewModels) {
        this.magicCardViewModels.clear();
        this.magicCardViewModels.addAll(magicCardViewModels
                .stream()
                .filter(model -> model.getImageUrl() != null)
                .collect(Collectors.toList()));
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public SearchViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.item_card, parent, false);
        return new SearchViewHolder(view,listener);
    }

    @Override
    public void onBindViewHolder(@NonNull SearchViewHolder holder, int position) {
        holder.updateWithCardView(this.magicCardViewModels.get(position));
    }

    @Override
    public int getItemCount() {
        return this.magicCardViewModels.size();
    }

}
