package com.murraystudio.murraystudio;

import android.app.Activity;

import android.app.ActionBar;
import android.support.v4.app.Fragment;
import android.app.FragmentManager;
import android.content.Context;
import android.content.res.Configuration;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.support.v4.widget.DrawerLayout;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;


public class MainActivity extends ActionBarActivity implements
        AdapterView.OnItemClickListener {

    //INSTANCE VARS
    protected DrawerLayout drawerLayout;
    private FrameLayout frameLayout;
    private ListView listView;
    private MyAdapter myAdapter;
    private ActionBarDrawerToggle drawerListener;
    private Fragment fragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fragment = new MadlibsAbout();

        FragmentTransaction fm = getSupportFragmentManager()
                .beginTransaction();
        fm.replace(R.id.fragment_container, fragment);
        fm.addToBackStack(null);
        fm.commit();


        Toolbar mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);


        drawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);
        frameLayout = (FrameLayout) findViewById(R.id.fragment_container);
        listView = (ListView) findViewById(R.id.drawerList);
        myAdapter = new MyAdapter(this);
        listView.setAdapter(myAdapter);
        listView.setOnItemClickListener(this);

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
        fragment = new MadlibsAbout();
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
}

}
