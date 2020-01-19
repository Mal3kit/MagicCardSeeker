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
import xyz.kida.magiccardseeker.utils.ColorEnum;

public class CollectionViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.item_card_name)
    TextView cardNameView;

    @BindView(R.id.item_card_description)
    TextView cardDescriptionView;

    @BindView(R.id.item_card_img)
    ImageView imageView;

    @BindView(R.id.item_card_red_img)
    ImageView redManaView;

    @BindView(R.id.item_card_blue_img)
    ImageView blueManaView;

    @BindView(R.id.item_card_green_img)
    ImageView greenManaView;

    @BindView(R.id.item_card_black_img)
    ImageView blackManaView;

    @BindView(R.id.item_card_white_img)
    ImageView whiteManaView;

    @BindView(R.id.item_card_colorless_img)
    ImageView colorlessManaView;

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
                .into(imageView);
        setUpColorsImages(magicCardViewModel);
    }

    private void setUpColorsImages(MagicCardViewModel magicCardViewModel) {
        if (!magicCardViewModel.getColors().isEmpty()) {
            colorlessManaView.setVisibility(View.GONE);
        }
        if (!magicCardViewModel.getColors().contains(ColorEnum.BLACK.getColor())) {
            blackManaView.setVisibility(View.GONE);
        }
        if (!magicCardViewModel.getColors().contains(ColorEnum.BLUE.getColor())) {
            blueManaView.setVisibility(View.GONE);
        }
        if (!magicCardViewModel.getColors().contains(ColorEnum.GREEN.getColor())) {
            greenManaView.setVisibility(View.GONE);
        }
        if (!magicCardViewModel.getColors().contains(ColorEnum.RED.getColor())) {
            redManaView.setVisibility(View.GONE);
        }
        if (!magicCardViewModel.getColors().contains(ColorEnum.WHITE.getColor())) {
            whiteManaView.setVisibility(View.GONE);
        }
    }
}
