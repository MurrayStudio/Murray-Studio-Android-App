package com.murraystudio.murraystudio;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

public class Gallery extends Fragment {

    private static final String KEY_LAYOUT_MANAGER = "layoutManager";
    private static final int DATASET_COUNT = 60;

    android.support.v7.app.ActionBar actionBar;
    //SliderLayout sliderShow;
    protected RecyclerView mRecyclerView;
    protected GalleryAdapter mAdapter;
    protected RecyclerView.LayoutManager mLayoutManager;
    private String[] imageURLS;
    protected CardView cardview;
    //private SwipeRefreshLayout mSwipeRefreshLayout;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Initialize dataset, this data would usually come from a local content provider or
        // remote server.
        initDataset();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.recycler_view, container, false);

        actionBar = (android.support.v7.app.ActionBar) ((MainActivity) getActivity())
                .getSupportActionBar();
        actionBar.setTitle("Murray Studio");

        mRecyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);
        mLayoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);

        mRecyclerView.setLayoutManager(mLayoutManager);

        mAdapter = new GalleryAdapter(imageURLS, getActivity());
        mRecyclerView.setAdapter(mAdapter);

/*        final GestureDetector mGestureDetector = new GestureDetector(getActivity(), new GestureDetector.SimpleOnGestureListener() {

            @Override public boolean onSingleTapUp(MotionEvent e) {
                return true;
            }

        });


        mRecyclerView.addOnItemTouchListener(new RecyclerView.OnItemTouchListener() {
            @Override
            public boolean onInterceptTouchEvent(RecyclerView recyclerView, MotionEvent motionEvent) {
                View child = recyclerView.findChildViewUnder(motionEvent.getX(), motionEvent.getY());


                if (child != null && mGestureDetector.onTouchEvent(motionEvent)) {
                    Toast.makeText(getActivity(), "The Item Clicked is: " + recyclerView.getChildPosition(child), Toast.LENGTH_SHORT).show();

                    //recyclerView.getChildAt(recyclerView.getChildPosition(child));

*//*                    Intent browserIntent;

                    //based on project card clicked open a webpage based on it.
                    switch (recyclerView.getChildPosition(child)) {
                        case 0:
                            browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://github.com/shamu11/Story-Studio-Pro"));
                            startActivity(browserIntent);
                            break;
                        case 1:
                            browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://github.com/shamu11/RiskProj"));
                            startActivity(browserIntent);
                            break;
                        case 2:
                            browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://github.com/shamu11/Murray-Studio"));
                            startActivity(browserIntent);
                            break;
                        case 3:
                            browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://imgur.com/a/NwODz"));
                            startActivity(browserIntent);
                            break;
                    }*//*
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
        });*/

        //mSwipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.swipeRefreshLayout);

/*        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                // Refresh items
                refreshItems();
            }
        });*/

        return view;
    }

    /**
     * Generates Strings for RecyclerView's adapter. This data would usually come
     * from a local content provider or remote server.
     */
    private void initDataset() {
        imageURLS = getResources().getStringArray(R.array.image_urls);
    }

/* used for refresh swipe

    //refresh swipe methods
    void refreshItems() {
        mDataset = new String[DATASET_COUNT];
        for (int i = 0; i < DATASET_COUNT; i++) {
            mDataset[i] = "This is butt #" + i;
        }

        mAdapter = new HomeAdapter(mDataset, getActivity());
        mRecyclerView.setAdapter(mAdapter);

        // Load complete
        onItemsLoadComplete();
    }

    void onItemsLoadComplete() {
        // Update the adapter and notify data set changed
        // ...

        // Stop refresh animation
        mSwipeRefreshLayout.setRefreshing(false);
    }*/
}

