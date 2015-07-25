/*
* Copyright (C) 2014 The Android Open Source Project
*
* Licensed under the Apache License, Version 2.0 (the "License");
* you may not use this file except in compliance with the License.
* You may obtain a copy of the License at
*
*      http://www.apache.org/licenses/LICENSE-2.0
*
* Unless required by applicable law or agreed to in writing, software
* distributed under the License is distributed on an "AS IS" BASIS,
* WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
* See the License for the specific language governing permissions and
* limitations under the License.
*/

package com.murraystudio.murraystudio;

import android.support.v4.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Provide views to RecyclerView with data from mDataSet.
 */
public class ProjectAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private static final String TAG = "HomeAdapter";

    //holds titles for cards
    private String[] titles;
    //holds descriptions for cards
    private String[] descriptions;
    //holds logos for cards, -1 used as header placeholder positon
    private int[] logoArrayID = {R.drawable.story_studio_logo, R.drawable.risk_logo, R.drawable.murray_studio_logo, R.drawable.graphics_logo};
    // Allows to remember the last item shown on screen
    private int lastPosition = -1;

    private Context context;

/*    //holds ids for types
    private static final int VIEW_TYPE_FIRST = 1;
    private static final int VIEW_TYPE_SECOND = 0;*/

    /**
     * Provide a reference to the type of views that you are using (custom ViewHolder)
     */
    public static class ViewHolderFirst extends RecyclerView.ViewHolder implements View.OnClickListener{
        private final TextView title;
        private final TextView description;
        private final ImageButton play;
        private final ImageButton open;
        private final ImageView logo;
        private final CardView card;
        private Context context;
        private Intent browserIntent;
        private String[] infoUrls;
        private String[] mediaUrls;


        public ViewHolderFirst(View v, Context c) {
            super(v);
            context = c;
            title = (TextView) v.findViewById(R.id.project_title);
            description = (TextView) v.findViewById(R.id.project_description);
            play = (ImageButton) v.findViewById(R.id.project_play_button);
            open = (ImageButton) v.findViewById(R.id.project_open_link_button);
            logo = (ImageView) v.findViewById(R.id.project_logo);
            card = (CardView) v.findViewById(R.id.card_view_project);

            play.setOnClickListener(this);
            open.setOnClickListener(this);
            card.setOnClickListener(this);

            infoUrls = context.getResources().getStringArray(R.array.info_urls);
            mediaUrls = context.getResources().getStringArray(R.array.media_urls);
        }

        public TextView getTitle() {
            return title;
        }

        public TextView getDescription() {
            return description;
        }

        public ImageButton getPlayButton() {
            return play;
        }

        public ImageButton getOpenButton() {
            return open;
        }

        public ImageView getLogo() {
            return logo;
        }

        public CardView getCard() {
            return card;
        }

        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.project_play_button:
                    Log.i("media url", mediaUrls[ViewHolderFirst.this.getAdapterPosition()]);

                    if(ViewHolderFirst.this.getAdapterPosition() <= 2) {
                        browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(mediaUrls[ViewHolderFirst.this.getAdapterPosition()].toString()));
                        context.startActivity(browserIntent);
                    }
                    else{
                        Fragment gallery = new Gallery();
                        FragmentTransaction ft = ((FragmentActivity)context).getSupportFragmentManager().beginTransaction();
                        ft.setCustomAnimations(R.anim.enter_from_left, R.anim.exit_to_right)
                                .replace(R.id.fragment_container, gallery)
                                .addToBackStack(null).commit();
                    }
                    break;
                case R.id.project_open_link_button:

                   //future location to place murray studio urls

                    break;
                case R.id.card_view_project:
                    Log.i("project url", infoUrls[ViewHolderFirst.this.getAdapterPosition()]);

                    browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(infoUrls[ViewHolderFirst.this.getAdapterPosition()].toString()));
                    context.startActivity(browserIntent);
                    break;
            }
        }
    }

    /**
     * Initialize the dataset of the Adapter.
     */
    public ProjectAdapter(String[] titles, String[] descriptions, Context context) {
        this.titles = titles;
        this.descriptions = descriptions;
        this.context = context;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        // Create a new view.
        View v1 = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.card_item_project, viewGroup, false);

        return new ViewHolderFirst(v1, context);
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        Log.d(TAG, "Element " + position + " set.");
        ViewHolderFirst viewHolderFirst = (ViewHolderFirst) holder;
        viewHolderFirst.getTitle().setText(titles[position]);
        viewHolderFirst.getDescription().setText((descriptions[position]));
        Drawable d = context.getResources().getDrawable(logoArrayID[position]);
        viewHolderFirst.getLogo().setImageDrawable(d);
        //setAnimation(viewHolderFirst.itemView, position);
    }

/*    private void setAnimation(View viewToAnimate, int position) {

        // If the bound view wasn't previously displayed on screen, it's animated
        if (position > lastPosition) {
            Animation animation = AnimationUtils.loadAnimation(context, R.anim.push_up);
            viewToAnimate.startAnimation(animation);
            lastPosition = position;
        }
    }*/

    @Override
    public void onViewDetachedFromWindow(RecyclerView.ViewHolder holder) {
        super.onViewDetachedFromWindow(holder);
        holder.itemView.clearAnimation();
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return titles.length;
    }

}