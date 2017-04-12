package edu.uw.fragmentdemo;

import android.os.Bundle;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.support.v4.view.ViewPager;
import android.support.v4.app.*;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity implements MoviesFragment.OnMovieSelectedListener {

    private static final String TAG = "MainActivity";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ViewPager vp = (ViewPager)findViewById(R.id.viewpager);
        vp.setAdapter(new MoviePagerAdapter(getSupportFragmentManager()));
        SearchFragment sf = new SearchFragment();

    }


    //respond to search button clicking
    public void handleSearchClick(View v){
        EditText text = (EditText)findViewById(R.id.txtSearch);
        String searchTerm = text.getText().toString();

        //add a new results fragment to the page
        MoviesFragment fragment = MoviesFragment.newInstance(searchTerm);
        ViewPager vp = (ViewPager)findViewById(R.id.viewpager);
        vp.setAdapter(new MoviePagerAdapter(getSupportFragmentManager()));
        vp.getCount();
    }

    @Override
    public void onMovieSelected(Movie movie) {
        DetailFragment detailFragment = DetailFragment.newInstance(movie.toString(), movie.imdbId);

        getSupportFragmentManager().beginTransaction()
                //.replace(R.id.container, detailFragment, null)
                .addToBackStack(null)
                .commit();
    }

    public class MoviePagerAdapter extends FragmentStatePagerAdapter{
        public MoviePagerAdapter(FragmentManager fm){
            super(fm);
        }
        public Fragment getItem(int item){
            if(item == 0){
                return new SearchFragment();
            }
            if(item == 1){
                return new MoviesFragment();
            }
            if (item == 2){
                return new DetailFragment();
            }
            return null;
        }
        public int getCount(){
            return 0;
        }
        public int getItemPosition(Object object){
            return POSITION_NONE;
        }
    }
}
