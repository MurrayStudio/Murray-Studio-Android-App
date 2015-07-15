package com.murraystudio.murraystudio;

import android.support.v4.app.Fragment;
import android.content.Context;
import android.content.res.Configuration;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.support.v4.widget.DrawerLayout;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;


public class MainActivity extends ActionBarActivity implements
        AdapterView.OnItemClickListener {

    //INSTANCE VARS
    protected DrawerLayout drawerLayout;
    private FrameLayout frameLayout;
    private ListView listView;
    private NavAdapter navAdapter;
    protected RecyclerView navRecyclerView;
    protected RecyclerView.LayoutManager navLayoutManager;
    private ActionBarDrawerToggle drawerListener;
    private Fragment fragment;
    private FragmentTransaction fragmentTrans;
    protected String[] mDataset;

    //used to get width of relative layout for use in slidershow
    private RelativeLayout navRelative;
    private int widthOfNav;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initDataset();

        //load home fragment on creation
        fragment = new Home();
        fragmentTrans = getSupportFragmentManager()
                .beginTransaction();
        fragmentTrans.replace(R.id.fragment_container, fragment);
        fragmentTrans.addToBackStack(null);
        fragmentTrans.commit();

        //init the toolbar for use here and in fragments
        Toolbar mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);

        //init layouts
        drawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);
        frameLayout = (FrameLayout) findViewById(R.id.fragment_container);

        navRelative = (RelativeLayout) findViewById(R.id.drawerView);
        widthOfNav = navRelative.getLayoutParams().width;

        //listView = (ListView) findViewById(R.id.drawerList);
        navRecyclerView = (RecyclerView) findViewById(R.id.nav_recycler_view);
        navLayoutManager = new LinearLayoutManager(this);
        navRecyclerView.setLayoutManager(navLayoutManager);
        //set up adapter to display nav drawer items
        navAdapter = new NavAdapter(mDataset, this, widthOfNav);
        navRecyclerView.setAdapter(navAdapter);

        //listView.setAdapter(navAdapter);
        //listView.setOnItemClickListener(this);

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

    public void onItemClick(AdapterView<?> parent, View view, int position,
                            long id) {
        selectItem(position);

        TextView txtview = ((TextView) view.findViewById(R.id.rowText));
        txtview.setTypeface(null, Typeface.BOLD);
    }

    private void selectItem(int position) {
        fragment = new Home();
        // FragmentManager fm = getSupportFragmentManager();

/*        switch (position) {
            case 0:
                fragment = new MadlibsSelect();
                break;
            case 1:
                fragment = new MadlibsCreate();
                break;
        }*/


        FragmentTransaction fm = getSupportFragmentManager()
                .beginTransaction();
        fm.replace(R.id.fragment_container, fragment);
        fm.addToBackStack(null);
        fm.commit();

        if (drawerLayout != null) {
            drawerLayout.closeDrawers();
        }


    }

    /**
     * Generates Strings for RecyclerView's adapter. This data would usually come
     * from a local content provider or remote server.
     */
    private void initDataset() {
        mDataset = getResources().getStringArray(R.array.card_titles);
    }

/*    //adapter for nav drawer use
    class MyAdapter extends BaseAdapter {
        private Context context;
        String[] Madlibs;
        int[] images = {R.drawable.note, R.drawable.pen, R.drawable.stack,
                R.drawable.settings, R.drawable.user};

        public MyAdapter(Context context) {
            this.context = context;
            Madlibs = context.getResources().getStringArray(R.array.Madlibs);
        }

        @Override
        public int getCount() {
            // TODO Auto-generated method stub
            return Madlibs.length;
        }

        @Override
        public Object getItem(int position) {
            // TODO Auto-generated method stub
            return Madlibs[position];
        }

        @Override
        public long getItemId(int position) {
            // TODO Auto-generated method stub
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            // TODO Auto-generated method stub
            View row = null;
            if (convertView == null) {
                LayoutInflater inflater = (LayoutInflater) context
                        .getSystemService(context.LAYOUT_INFLATER_SERVICE);
                row = inflater.inflate(R.layout.custom_row, parent, false);
            } else {
                row = convertView;
            }
            TextView titleTextView = (TextView) row.findViewById(R.id.rowText);
            ImageView titleImageView = (ImageView) row
                    .findViewById(R.id.imageView1);

            //titleTextView.setTypeface(null, Typeface.BOLD);

            titleTextView.setText(Madlibs[position]);
            titleImageView.setImageResource(images[position]);

            return row;
        }
    }*/

}
