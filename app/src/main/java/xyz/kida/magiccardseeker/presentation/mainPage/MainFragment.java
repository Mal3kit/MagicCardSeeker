package xyz.kida.magiccardseeker.presentation.mainPage;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.fragment.app.Fragment;
import butterknife.BindView;
import butterknife.ButterKnife;
import xyz.kida.magiccardseeker.R;

public class MainFragment extends Fragment {

    @BindView(R.id.main_fragment_image)
    ImageView imageView;

    @BindView(R.id.main_text_view)
    TextView textView;

    public MainFragment() {
    }


    public static MainFragment newInstance() {
        return new MainFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);
        ButterKnife.bind(this, view);

        return view;
    }
}
