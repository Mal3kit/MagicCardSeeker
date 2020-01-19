package xyz.kida.magiccardseeker.presentation.collection.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import xyz.kida.magiccardseeker.R;
import xyz.kida.magiccardseeker.presentation.collection.model.CollectionListener;
import xyz.kida.magiccardseeker.presentation.collection.model.CollectionViewHolder;
import xyz.kida.magiccardseeker.presentation.model.MagicCardViewModel;

import java.util.ArrayList;
import java.util.List;

public class CollectionCardAdapter extends RecyclerView.Adapter<CollectionViewHolder> {

    private List<MagicCardViewModel> magicCardViewModels;
    private CollectionListener listener;

    public CollectionCardAdapter(CollectionListener listener) {
        magicCardViewModels = new ArrayList<>();
        this.listener = listener;
    }

    public void bindViewModels(List<MagicCardViewModel> magicCardViewModels) {
        this.magicCardViewModels.clear();
        this.magicCardViewModels.addAll(magicCardViewModels);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public CollectionViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.item_card_collection, parent, false);
        return new CollectionViewHolder(view, listener);
    }

    @Override
    public void onBindViewHolder(@NonNull CollectionViewHolder holder, int position) {
        holder.updateWithCardView(magicCardViewModels.get(position));
    }

    @Override
    public int getItemCount() {
        return magicCardViewModels.size();
    }
}
