package com.example.omdbapi.main;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.example.omdbapi.R;
import com.example.omdbapi.main.fragments.FilmInfoFragment;
import com.example.omdbapi.main.fragments.SearchFilmsFragment;

public class OmdbActivity extends AppCompatActivity implements SearchFilmsFragment.OnShowFilmInfoListener {

    private static final String SEARCH_FRAGMENT = "search_fragment";
    private static final String INFO_FRAGMENT = "info_fragment";
    private static final String TAG = OmdbActivity.class.getSimpleName();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_omdb);

        initSearchFragment();
    }
    
    public void initSearchFragment() {

        Fragment fragment = getSupportFragmentManager().findFragmentByTag(SEARCH_FRAGMENT);
        if (fragment == null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .add(R.id.container_layout, SearchFilmsFragment.newInstance(), SEARCH_FRAGMENT)
                    .addToBackStack(SEARCH_FRAGMENT)
                    .commit();
        } else {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.container_layout, fragment, SEARCH_FRAGMENT)
                    .commit();
        }
    }

    private void initInfoFragment(@NonNull String idFilm) {
        Log.d(TAG, "init FilmInfoFragment, film id: " + idFilm);
        Fragment fragment = getSupportFragmentManager().findFragmentByTag(INFO_FRAGMENT);
        if (fragment == null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .add(R.id.container_layout, FilmInfoFragment.newInstance(idFilm), INFO_FRAGMENT)
                    .addToBackStack(INFO_FRAGMENT)
                    .commit();
        } else {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.container_layout, fragment, INFO_FRAGMENT)
                    .commit();
        }
    }

    @Override
    public void onShowFilmInfo(@NonNull String idFilm) {
        initInfoFragment(idFilm);
    }

    public Fragment getCurrentFragment() { // current fragment
        return getSupportFragmentManager().findFragmentById(R.id.container_layout);
    }

    @Override
    public void onBackPressed() {
        if (getCurrentFragment() instanceof SearchFilmsFragment &&
                getSupportFragmentManager().getBackStackEntryCount() == 0)
            super.onBackPressed();
        else if (getCurrentFragment() instanceof SearchFilmsFragment &&
                getSupportFragmentManager().getBackStackEntryCount() > 0) {
            getSupportFragmentManager().popBackStack();
            super.onBackPressed();
        } else initSearchFragment();
    }
}