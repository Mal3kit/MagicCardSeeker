package xyz.kida.magiccardseeker.presentation.collection.model;

import android.view.View;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import butterknife.BindView;
import butterknife.ButterKnife;
import xyz.kida.magiccardseeker.R;
import xyz.kida.magiccardseeker.presentation.model.MagicCardViewModel;

public class CollectionViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.item_card_name)
    TextView cardNameView;

    @BindView(R.id.item_card_description)
    TextView cardDescriptionView;

    @BindView(R.id.item_card_img)
    ImageView imageView;

    @BindView(R.id.collection_switch)
    Switch collectionSwitch;

    private View view;
    private MagicCardViewModel viewModel;
    private CollectionListener listener;

    public CollectionViewHolder(View view, CollectionListener listener) {
        super(view);
        this.view = view;
        ButterKnife.bind(this, view);
        setupListeners();
        this.listener = listener;
    }

    private void setupListeners() {
        collectionSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (!isChecked) {
                    listener.onDeleteCardFromCollection(viewModel.getCardId());
                }
            }
        });
    }

    public void updateWithCardView(MagicCardViewModel magicCardViewModel) {
        this.viewModel = magicCardViewModel;
        this.cardNameView.setText(magicCardViewModel.getCardName());
        this.collectionSwitch.setChecked(true);
        this.cardDescriptionView.setText(viewModel.getDescription());
        Glide.with(view)
                .load(magicCardViewModel.getImageUrl())
                .circleCrop()
                .into(imageView);
    }
}
