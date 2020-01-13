package xyz.kida.magiccardseeker.presentation.search.model;

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

public class CardViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.item_card_name)
    TextView cardNameView;

    @BindView(R.id.item_card_description)
    TextView cardDescriptionView;

    @BindView(R.id.item_card_img)
    ImageView imageView;

    @BindView(R.id.collection_switch)
    Switch collectionSwitch;

    private View view;
    private MagicCardViewModel magicCardViewModel;
    private MagicCardOnSwitchListener magicCardOnSwitchListener;

    public CardViewHolder(View view, final MagicCardOnSwitchListener magicCardOnSwitchListener) {
        super(view);
        this.view = view;
        this.magicCardOnSwitchListener = magicCardOnSwitchListener;
        ButterKnife.bind(this, view);
        setupListeners();
    }

    private void setupListeners() {
        collectionSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                magicCardOnSwitchListener.onSwitchToggle(magicCardViewModel.getCardId(), isChecked);
            }
        });
    }


    public void updateWithCardView(MagicCardViewModel magicCardViewModel) {
        this.magicCardViewModel = magicCardViewModel;
        this.cardNameView.setText(magicCardViewModel.getCardName());
        this.collectionSwitch.setChecked(magicCardViewModel.isInMyCollection());
        Glide.with(view)
                .load(magicCardViewModel.getImageUrl())
                .circleCrop()
                .into(imageView);

    }
}
