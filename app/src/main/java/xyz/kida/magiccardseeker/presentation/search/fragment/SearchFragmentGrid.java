package xyz.kida.magiccardseeker.presentation.search.fragment;

import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.GridLayoutManager;

public class SearchFragmentGrid extends SearchFragment {



    public static SearchFragmentGrid newInstance() {
        return new SearchFragmentGrid();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2));
    }
}
