package xyz.kida.magiccardseeker.presentation.search.model;

import android.view.View;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.bumptech.glide.Glide;
import xyz.kida.magiccardseeker.R;
import xyz.kida.magiccardseeker.presentation.model.MagicCardViewModel;

public class SearchViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.item_card_name)
    TextView cardNameView;

    @BindView(R.id.item_card_img)
    ImageView imageView;

    @BindView(R.id.collection_switch)
    Switch collectionSwitch;

    private View view;
    private MagicCardViewModel magicCardViewModel;
    private MagicCardOnSwitchListener listener;

    public SearchViewHolder(View view, final MagicCardOnSwitchListener listener) {
        super(view);
        this.view = view;
        this.listener = listener;
        ButterKnife.bind(this, view);
        setupListeners();
    }

    private void setupListeners() {
        collectionSwitch.setOnCheckedChangeListener((buttonView, isChecked)
                -> listener.onSwitchToggle(magicCardViewModel, isChecked));
    }


    public void updateWithCardView(MagicCardViewModel magicCardViewModel) {
        this.magicCardViewModel = magicCardViewModel;
        this.cardNameView.setText(magicCardViewModel.getCardName());
        this.collectionSwitch.setChecked(magicCardViewModel.isInMyCollection());
        Glide.with(view)
                .load(magicCardViewModel.getImageUrl())
                .circleCrop()
                .centerCrop()
                .into(imageView);

    }
}
