package xyz.kida.magiccardseeker.presentation.search.model;

import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.lang.ref.WeakReference;

import butterknife.BindView;
import butterknife.ButterKnife;
import xyz.kida.magiccardseeker.R;
import xyz.kida.magiccardseeker.data.api.models.MagicCard;
import xyz.kida.magiccardseeker.presentation.search.adapter.SearchAdapterListener;

public class CardViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    @BindView(R.id.item_card_name)
    TextView textView;

    private WeakReference<SearchAdapterListener> callback;

    public CardViewHolder(View view) {
        super(view);
        ButterKnife.bind(this, view);
    }

    public void updateWithCardView(MagicCard magicCard, SearchAdapterListener listener) {
        this.textView.setText(magicCard.getName());
        this.callback = new WeakReference<SearchAdapterListener>(listener);

    }

    @Override
    public void onClick(View v) {
        SearchAdapterListener listener = callback.get();
        if (listener != null) {
            listener.onClickDeleteButton(getAdapterPosition());
        }
    }
}
