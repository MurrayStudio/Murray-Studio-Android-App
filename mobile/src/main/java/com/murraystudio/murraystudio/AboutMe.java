package com.murraystudio.murraystudio;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class AboutMe extends Fragment {

    private static final String KEY_LAYOUT_MANAGER = "layoutManager";
    private static final int DATASET_COUNT = 60;

    android.support.v7.app.ActionBar actionBar;
    protected String titleText;
    protected String descriptionText;
    protected int logoID;
    private ImageView logo;
    private TextView title;
    private TextView description;
    private CardView card;
    private Intent browserIntent;


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
        View view = inflater.inflate(R.layout.about_me, container, false);

        actionBar = (android.support.v7.app.ActionBar) ((MainActivity) getActivity())
                .getSupportActionBar();
        actionBar.setTitle("About Me");

        logoID = R.drawable.shamus_logo;

        logo = (ImageView) view.findViewById(R.id.about_me_logo);
        title = (TextView) view.findViewById(R.id.about_me_title);
        description = (TextView) view.findViewById(R.id.about_me_description);
        card = (CardView) view.findViewById(R.id.card_view_about_me);

        logo.setImageResource(logoID);
        title.setText(titleText);
        description.setText(descriptionText);
        card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.linkedin.com/pub/shamus-murray/a0/230/a89"));
                getActivity().startActivity(browserIntent);
            }
        });

        return view;
    }

    /**
     * Generates Strings for AboutMe
     */
    private void initDataset() {
        titleText = getActivity().getResources().getString(R.string.about_me_title);
        descriptionText = getActivity().getResources().getString(R.string.about_me_description);
    }
}

