package xyz.kida.magiccardseeker;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

import com.google.android.material.navigation.NavigationView;

import butterknife.BindView;
import butterknife.ButterKnife;
import xyz.kida.magiccardseeker.presentation.collection.CollectionFragment;
import xyz.kida.magiccardseeker.presentation.mainPage.MainFragment;
import xyz.kida.magiccardseeker.presentation.search.SearchFragment;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    private static final int MAIN_FRAGMENT = 0;
    private static final int SEARCH_FRAGMENT = 1;
    private static final int COLLECTION_FRAGMENT = 2;



    @BindView(R.id.activity_main_toolbar)
    Toolbar toolbar;

    @BindView(R.id.activity_main_drawer_layout)
    DrawerLayout drawerLayout;

    @BindView(R.id.activity_main_navigation)
    NavigationView navigationView;

    private MainFragment mainFragment;
    private SearchFragment searchFragment;
    private CollectionFragment collectionFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        setSupportActionBar(toolbar);
        this.configureDrawerLayout();
        navigationView.setNavigationItemSelectedListener(this);

        showMainFragment();
    }

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            this.drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        int id = menuItem.getItemId();

        switch (id) {
            case R.id.activity_main_home:
                this.showFragment(MAIN_FRAGMENT);
                this.drawerLayout.closeDrawer(GravityCompat.START);
                break;
            case R.id.activity_main_drawer_search:
                this.showFragment(SEARCH_FRAGMENT);
                this.drawerLayout.closeDrawer(GravityCompat.START);
                break;
            case R.id.activity_main_drawer_collection:
                this.showFragment(COLLECTION_FRAGMENT);
                this.drawerLayout.closeDrawer(GravityCompat.START);
                break;
            default:
                break;
        }

        return true;
    }


    private void configureDrawerLayout() {
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.navigation_open, R.string.navigation_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
    }

    private void showFragment(int fragmentIdentifier){
        switch (fragmentIdentifier){
            case MAIN_FRAGMENT :
                this.showMainFragment();
                break;
            case SEARCH_FRAGMENT:
                this.showSearchFragment();
                break;

            case COLLECTION_FRAGMENT:
                this.showCollectionFragment();
                break;
            default:
                break;
        }
    }


    private void showMainFragment() {

        if (mainFragment == null) {
            mainFragment = MainFragment.newInstance();
        }
        startTransactionFragment(mainFragment);
    }

    private void showSearchFragment() {

        if (searchFragment == null) {
            searchFragment = SearchFragment.newInstance();
        }
        startTransactionFragment(searchFragment);
    }

    private void showCollectionFragment() {
        if (collectionFragment == null) {
            collectionFragment = CollectionFragment.newInstance();
        }
        startTransactionFragment(collectionFragment);

    }

    private void startTransactionFragment(Fragment fragment){
        if (!fragment.isVisible()){
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.activity_main_frame_layout, fragment).commit();
        }
    }



}
