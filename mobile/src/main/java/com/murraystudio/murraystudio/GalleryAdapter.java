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

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;
import com.squareup.picasso.Transformation;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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

    private  ViewHolderFirst viewHolderFirst;

/*    //holds ids for types
    private static final int VIEW_TYPE_FIRST = 1;
    private static final int VIEW_TYPE_SECOND = 0;*/

    /**
     * Provide a reference to the type of views that you are using (custom ViewHolder)
     */
    public static class ViewHolderFirst extends RecyclerView.ViewHolder implements View.OnClickListener{
        private ImageButton imageButton;
        private Context context;
        private ProgressBar progressBar;


        public ViewHolderFirst(View v, Context c) {
            super(v);
            context = c;
            progressBar = (ProgressBar) v.findViewById(R.id.progressBar);
            imageButton = (ImageButton) v.findViewById(R.id.image_button);
            imageButton.setOnClickListener(this);
        }

        public ImageButton getImageButton() {
            return imageButton;
        }
        public ProgressBar getProgressBar() {
            return progressBar;
        }

        @Override
        public void onClick(View v) {
            MainActivity main = new MainActivity();
            main.setCurrentImagePositon(ViewHolderFirst.this.getAdapterPosition());

            Fragment galleryImage = new GalleryImageFullScreen();
            FragmentTransaction ft = ((FragmentActivity)context).getSupportFragmentManager().beginTransaction();
            ft.setCustomAnimations(R.anim.enter_from_left, R.anim.exit_to_right)
                    .replace(R.id.fragment_container, galleryImage)
                    .addToBackStack(null).commit();
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
        viewHolderFirst = (ViewHolderFirst) holder;

        viewHolderFirst.getProgressBar().setVisibility(View.VISIBLE);
        viewHolderFirst.getImageButton().setVisibility(View.GONE);

        Picasso.with(context)
                .load(imageURLS[position])
                .resize(300, 0)
                .into(viewHolderFirst.getImageButton(), new ImageLoadedCallback(viewHolderFirst.getProgressBar(), viewHolderFirst.getImageButton()) {
                    @Override
                    public void onSuccess() {
                        if (this.progressBar != null) {
                            this.progressBar.setVisibility(View.GONE);
                            this.imageBtn.setVisibility(View.VISIBLE);
                        }
                    }
                });
    }

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

    private static int calculateInSampleSize(
            int enteredWidth, int enteredHeight, int reqWidth, int reqHeight) {
        // Raw height and width of image
        final int height = enteredHeight;
        final int width = enteredWidth;
        int inSampleSize = 1;

        if (height > reqHeight || width > reqWidth) {

            final int halfHeight = height / 2;
            final int halfWidth = width / 2;

            // Calculate the largest inSampleSize value that is a power of 2 and keeps both
            // height and width larger than the requested height and width.
            while ((halfHeight / inSampleSize) > reqHeight
                    && (halfWidth / inSampleSize) > reqWidth) {
                inSampleSize *= 2;
            }
        }

        return inSampleSize;
    }

    private class ImageLoadedCallback implements Callback {
        ProgressBar progressBar;
        ImageButton imageBtn;

        public  ImageLoadedCallback(ProgressBar progBar, ImageButton image){
            progressBar = progBar;
            imageBtn = image;
        }

        @Override
        public void onSuccess() {

        }

        @Override
        public void onError() {

        }
    }

}