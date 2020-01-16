package xyz.kida.magiccardseeker.utils;

import xyz.kida.magiccardseeker.R;
import xyz.kida.magiccardseeker.presentation.model.MagicCardViewModel;

public class ColorUtil {
    private static final String BLACK = "Black";
    private static final String RED = "Red";
    private static final String BLUE = "Blue";
    private static final String WHITE = "White";
    private static final String GREEN = "Green";
    private static final String MULTICOLOR = "Multicolor";

    public int setBackgroundColor(MagicCardViewModel magicCardViewModel) {

        switch (magicCardViewModel.getMainColor()) {
            case BLACK:
                return R.color.colorBlack;
            case RED:
                return R.color.colorRed;
            case BLUE:
                return R.color.colorBlue;
            case WHITE:
                return R.color.colorWhite;
            case GREEN:
                return R.color.colorGreen;
            case MULTICOLOR:
                return R.color.colorGold;
            default:
                return R.color.colorGrey;
        }
    }
}
