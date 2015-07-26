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
public class GalleryAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private static final String TAG = "HomeAdapter";

    //holds logos for cards
    private int[] logoArrayID = {R.drawable.story_studio_logo, R.drawable.risk_logo, R.drawable.murray_studio_logo, R.drawable.graphics_logo};
    // Allows to remember the last item shown on screen
    private int lastPosition = -1;

    private String[] imageURLS;

    private Context context;

/*    //holds ids for types
    private static final int VIEW_TYPE_FIRST = 1;
    private static final int VIEW_TYPE_SECOND = 0;*/

    /**
     * Provide a reference to the type of views that you are using (custom ViewHolder)
     */
    public static class ViewHolderFirst extends RecyclerView.ViewHolder implements View.OnClickListener{
        private ImageButton imageButton;
        private Context context;
        private Intent browserIntent;
        private String[] imageURLS;


        public ViewHolderFirst(View v, Context c) {
            super(v);
            context = c;
            imageButton = (ImageButton) v.findViewById(R.id.image_button);

            imageButton.setOnClickListener(this);

            imageURLS = context.getResources().getStringArray(R.array.image_urls);
        }

        public ImageButton getImageButton() {
            return imageButton;
        }

        @Override
        public void onClick(View v) {



        }
    }

    /**
     * Initialize the dataset of the Adapter.
     */
    public GalleryAdapter(String[] imageURLS, Context context) {
        this.imageURLS = imageURLS;
        this.context = context;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        // Create a new view.
        View v1 = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.gallery_image, viewGroup, false);

        return new ViewHolderFirst(v1, context);
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        Log.d(TAG, "Element " + position + " set.");
        ViewHolderFirst viewHolderFirst = (ViewHolderFirst) holder;
        //setAnimation(viewHolderFirst.itemView, position);
        ImageDownloader imageDownLoader = new ImageDownloader(viewHolderFirst.getImageButton());
        imageDownLoader.execute(imageURLS[position]);
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
        return imageURLS.length;
    }

}