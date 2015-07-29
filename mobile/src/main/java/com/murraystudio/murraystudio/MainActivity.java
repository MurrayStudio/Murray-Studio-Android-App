package com.murraystudio.murraystudio;

import android.support.v4.app.Fragment;
import android.content.Context;
import android.content.res.Configuration;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.transition.Explode;
import android.transition.Transition;
import android.transition.TransitionInflater;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.support.v4.widget.DrawerLayout;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends ActionBarActivity {

    //INSTANCE VARS
    protected DrawerLayout drawerLayout;
    private NavAdapter navAdapter;
    protected RecyclerView navRecyclerView;
    protected RecyclerView.LayoutManager navLayoutManager;
    private ActionBarDrawerToggle drawerListener;
    private Fragment fragment;
    private FragmentTransaction fragmentTrans;
    protected String[] mDataset;

    //holds current gallery image selected
    private static int position;

    //used to get width of relative layout for use in slidershow
    private RelativeLayout navRelative;
    private int widthOfNav;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initDataset();

        if(savedInstanceState == null) {
            //load recycler_view fragment on creation
            fragment = new Home();
            fragmentTrans = getSupportFragmentManager()
                    .beginTransaction();
            fragmentTrans.replace(R.id.fragment_container, fragment);
            fragmentTrans.commit();
        }

        //init the toolbar for use here and in fragments
        Toolbar mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);

        //sets default gallery positon to 0
        position = 0;

        //init layouts
        drawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);

        navRelative = (RelativeLayout) findViewById(R.id.drawerView);
        widthOfNav = navRelative.getLayoutParams().width;

        //listView = (ListView) findViewById(R.id.drawerList);
        navRecyclerView = (RecyclerView) findViewById(R.id.nav_recycler_view);
        navLayoutManager = new LinearLayoutManager(this);
        navRecyclerView.setLayoutManager(navLayoutManager);
        //set up adapter to display nav drawer items
        navAdapter = new NavAdapter(mDataset, this, widthOfNav);
        navRecyclerView.setAdapter(navAdapter);

        final GestureDetector mGestureDetector = new GestureDetector(MainActivity.this, new GestureDetector.SimpleOnGestureListener() {

            @Override public boolean onSingleTapUp(MotionEvent e) {
                return true;
            }

        });


        navRecyclerView.addOnItemTouchListener(new RecyclerView.OnItemTouchListener() {
            @Override
            public boolean onInterceptTouchEvent(RecyclerView recyclerView, MotionEvent motionEvent) {
                View child = recyclerView.findChildViewUnder(motionEvent.getX(), motionEvent.getY());


                if (child != null && mGestureDetector.onTouchEvent(motionEvent)) {
                    drawerLayout.closeDrawers();
                    Toast.makeText(MainActivity.this, "The Item Clicked is: " + recyclerView.getChildPosition(child), Toast.LENGTH_SHORT).show();

                    switch (recyclerView.getChildPosition(child)) {
                        case 1:
                            fragment = new Home();
                            switchFragment(fragment);
                            break;
                        case 2:
                            fragment = new Projects();
                            switchFragment(fragment);
                            break;
                        case 3:
                            fragment = new Home();
                            switchFragment(fragment);
                            break;
                        case 4:
                            fragment = new Home();
                            switchFragment(fragment);
                            break;
                    }

                    return true;
                }
                return false;
            }

            @Override
            public void onTouchEvent(RecyclerView recyclerView, MotionEvent motionEvent) {

            }

            @Override
            public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

            }
        });

        if (drawerLayout != null) {
            drawerListener = new ActionBarDrawerToggle(this, drawerLayout, mToolbar,
                    R.string.close, R.string.open) {
                @Override
                public void onDrawerOpened(View drawerView) {
                    // TODO Auto-generated method stub
                    super.onDrawerOpened(drawerView);
                }

                @Override
                public void onDrawerClosed(View drawerView) {
                    // TODO Auto-generated method stub
                    super.onDrawerClosed(drawerView);
                }

                @Override
                public void onDrawerSlide(View drawerView, float slideOffset) {
                    super.onDrawerSlide(drawerView, slideOffset);
                }
            };
            drawerLayout.setDrawerListener(drawerListener);
            getSupportActionBar().setHomeButtonEnabled(true);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            drawerListener.syncState();
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // TODO Auto-generated method stub
        if (drawerListener.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        // TODO Auto-generated method stub
        super.onConfigurationChanged(newConfig);
        if (drawerLayout != null) {
            drawerListener.onConfigurationChanged(newConfig);
        }
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onPostCreate(savedInstanceState);
        if (drawerLayout != null) {
            drawerListener.syncState();
        }
    }

    /**
     * Generates Strings for RecyclerView's adapter. This data would usually come
     * from a local content provider or remote server.
     */
    private void initDataset() {
        mDataset = getResources().getStringArray(R.array.nav_card_titles);
    }

    public void switchFragment(Fragment fragment) {
            this.fragment = fragment;
            getSupportFragmentManager()
                    .beginTransaction()
                    .setCustomAnimations(R.anim.enter_from_left, R.anim.exit_to_right)
                    .replace(R.id.fragment_container, this.fragment)
                    .addToBackStack(null).commit();
        }

    public void setCurrentImagePositon(int currentPositon){
        this.position = currentPositon;
    }

    public int getCurrentImagePosition(){
        return this.position;
    }

}
