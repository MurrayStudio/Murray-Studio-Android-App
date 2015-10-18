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

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.TextSliderView;

import org.w3c.dom.Text;

/**
 * Provide views to RecyclerView with data from mDataSet.
 */
public class HomeAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private static final String TAG = "HomeAdapter";

    //holds titles for cards, position 0 contains header placeholder string
    private String[] mDataSet;
    //holds logos for cards, -1 used as header placeholder positon
    private int[] logoArrayID = {-1, R.drawable.story_studio_logo, R.drawable.murray_studio_logo, R.drawable.contact_logo2};
    // Allows to remember the last item shown on screen
    private int lastPosition = -1;

    private Context context;

    //holds ids for types
    private static final int VIEW_TYPE_FIRST = 1;
    private static final int VIEW_TYPE_SECOND = 0;

    /**
     * Provide a reference to the type of views that you are using (custom ViewHolder)
     */
    public static class ViewHolderFirst extends RecyclerView.ViewHolder {
        private final TextView textView;
        private final ImageView logo;

        public ViewHolderFirst(View v) {
            super(v);
            textView = (TextView) v.findViewById(R.id.textView);
            logo = (ImageView) v.findViewById(R.id.logo);
        }

        public TextView getTextView() {
            return textView;
        }

        public ImageView getLogo() {
            return logo;
        }
    }

    public static class ViewHolderSecond extends RecyclerView.ViewHolder {
        private final ImageView header;

        public ViewHolderSecond(View v) {
            super(v);
            header = (ImageView) v.findViewById(R.id.header);
        }

        public ImageView getHeader(){
            return header;
        }

    }


    /**
     * Initialize the dataset of the Adapter.
     *
     * @param dataSet String[] containing the data to populate views to be used by RecyclerView.
     */
    public HomeAdapter(String[] dataSet, Context context) {
        mDataSet = dataSet;
        this.context = context;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        // Create a new view.
        View v1 = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.card_item, viewGroup, false);

        View v2 = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.header, viewGroup, false);

        switch (viewType){
            case VIEW_TYPE_FIRST:
                return new ViewHolderFirst(v1);
            case VIEW_TYPE_SECOND:
                return new ViewHolderSecond(v2);
        }

        return new ViewHolderFirst(v1);
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        Log.d(TAG, "Element " + position + " set.");

        switch (getItemViewType(position)){
            case VIEW_TYPE_FIRST:
                ViewHolderFirst viewHolderFirst = (ViewHolderFirst) holder;
                viewHolderFirst.getTextView().setText(mDataSet[position]);
                Drawable d = context.getResources().getDrawable(logoArrayID[position]);
                viewHolderFirst.getLogo().setImageDrawable(d);
                //setAnimation(viewHolderFirst.itemView, position);
                break;
            case VIEW_TYPE_SECOND:
                ViewHolderSecond viewHolderSecond = (ViewHolderSecond) holder;

                //calculate header height and width based off screen dimensions and
                //image size of 1400x500
                DisplayMetrics displaymetrics = new DisplayMetrics();
                WindowManager windowManager = (WindowManager) this.context.getSystemService(Context.WINDOW_SERVICE);
                windowManager.getDefaultDisplay().getMetrics(displaymetrics);
                int screenHeight = displaymetrics.heightPixels;
                int screenWidth = displaymetrics.widthPixels;
                double w = 1.00;
                int imgWidth = (int) (screenWidth * w);
                int imgHeight = (int) (imgWidth / 2.8);

                viewHolderSecond.getHeader().getLayoutParams().width = imgWidth;
                viewHolderSecond.getHeader().getLayoutParams().height = imgHeight;
                //setAnimation(viewHolderSecond.itemView, position);
                break;
        }
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
        return mDataSet.length;
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            return 0;
        } else {
            return 1;
        }
    }
}