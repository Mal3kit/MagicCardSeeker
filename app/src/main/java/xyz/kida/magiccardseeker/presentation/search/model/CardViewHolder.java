package xyz.kida.magiccardseeker.presentation.search.model;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.RequestManager;
import com.bumptech.glide.request.RequestOptions;

import java.lang.ref.WeakReference;

import butterknife.BindView;
import butterknife.ButterKnife;
import xyz.kida.magiccardseeker.R;
import xyz.kida.magiccardseeker.data.api.models.MagicCard;
import xyz.kida.magiccardseeker.presentation.search.adapter.SearchAdapterListener;

public class CardViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    @BindView(R.id.item_card_name)
    TextView cardNameView;

    @BindView(R.id.item_card_description)
    TextView cardDescriptionView;

    @BindView(R.id.item_card_img)
    ImageView imageView;

    private WeakReference<SearchAdapterListener> callback;

    public CardViewHolder(View view) {
        super(view);
        ButterKnife.bind(this, view);
    }

    public void updateWithCardView(MagicCard magicCard, RequestManager requestManager, SearchAdapterListener listener) {

        this.cardNameView.setText(magicCard.getName());
        requestManager.load(magicCard.getImageUrl()).apply(RequestOptions.circleCropTransform()).into(imageView);
        if (magicCard.isShowDetails()) {
            this.cardDescriptionView.setText(magicCard.getText());
        }
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
