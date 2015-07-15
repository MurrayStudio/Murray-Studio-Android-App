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

import com.daimajia.slider.library.Indicators.PagerIndicator;
import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.DefaultSliderView;
import com.daimajia.slider.library.SliderTypes.TextSliderView;

/**
 * Created by sushi_000 on 7/14/2015.
 */
public class NavAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private static final String TAG = "NavAdapter";

    //holds titles for cards, position 0 contains header placeholder string
    private String[] mDataSet;
    //holds logos for cards, -1 used as header placeholder positon
    private int[] logoArrayID = {-1, R.drawable.story_studio_logo, R.drawable.murray_studio_logo, R.drawable.graphics_logo};
    // Allows to remember the last item shown on screen
    private int lastPosition = -1;

    private Context context;

    private static final int VIEW_TYPE_FIRST = 1;
    private static final int VIEW_TYPE_SECOND = 0;

    private int widthOfNav;


    /**
     * Provide a reference to the type of views that you are using (custom ViewHolder)
     */
    public static class ViewHolderFirst extends RecyclerView.ViewHolder {
        private final TextView textView;
        private final ImageView rowImage;

        public ViewHolderFirst(View v) {
            super(v);
            textView = (TextView) v.findViewById(R.id.rowText);
            rowImage = (ImageView) v.findViewById(R.id.rowImage);
        }

        public TextView getTextView() {
            return textView;
        }

        public ImageView getRowImage() {
            return rowImage;
        }
    }

    public static class ViewHolderSecond extends RecyclerView.ViewHolder {
        private final SliderLayout sliderShow;

        public ViewHolderSecond(View v) {
            super(v);
            sliderShow = (SliderLayout) v.findViewById(R.id.slider);
        }

        public SliderLayout getSliderShow() {
            return sliderShow;
        }
    }


    public NavAdapter (String[] dataSet, Context context, int widthOfNav){
        mDataSet = dataSet;
        this.context = context;
        this.widthOfNav = widthOfNav;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        // Create a new view.
        View v1 = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.custom_row, viewGroup, false);

        View v2 = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.slider_item, viewGroup, false);

        switch (viewType) {
            case VIEW_TYPE_FIRST:
                return new ViewHolderFirst(v1);
            case VIEW_TYPE_SECOND:
                return new ViewHolderSecond(v2);
        }

        return new ViewHolderFirst(v1);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        Log.d(TAG, "Element " + position + " set.");

        switch (getItemViewType(position)) {
            case VIEW_TYPE_FIRST:
                ViewHolderFirst viewHolderFirst = (ViewHolderFirst) holder;
                viewHolderFirst.getTextView().setText(mDataSet[position]);
                Drawable d = context.getResources().getDrawable(logoArrayID[position]);
                viewHolderFirst.getRowImage().setImageDrawable(d);
                setAnimation(viewHolderFirst.itemView, position);
                break;
            case VIEW_TYPE_SECOND:
                ViewHolderSecond viewHolderSecond = (ViewHolderSecond) holder;

                //calculate slideshow height and width based off screen dimensions and
                //image size of 1400x500
                DisplayMetrics displaymetrics = new DisplayMetrics();
                WindowManager windowManager = (WindowManager) this.context.getSystemService(Context.WINDOW_SERVICE);
                windowManager.getDefaultDisplay().getMetrics(displaymetrics);
                int screenHeight = displaymetrics.heightPixels;
                int screenWidth = widthOfNav;
                double w = 1.00;
                int imgWidth = (int) (screenWidth * w);
                int imgHeight = (int) (imgWidth / 2.8);


                //set new height and width for slideshow
                viewHolderSecond.getSliderShow().getLayoutParams().height = imgHeight;
                viewHolderSecond.getSliderShow().getLayoutParams().width = imgWidth;


                //add slides into slideshow

                DefaultSliderView defaultSliderView1 = new DefaultSliderView(context);
                defaultSliderView1
                        .description("Murray Studio")
                        .image("http://i.imgur.com/m9LupJ3.jpg")
                        .setOnSliderClickListener(new BaseSliderView.OnSliderClickListener() {
                            @Override
                            public void onSliderClick(BaseSliderView baseSliderView) {
                                Toast.makeText(context, baseSliderView.getDescription().toString() + "", Toast.LENGTH_SHORT).show();
                            }
                        });

                viewHolderSecond.getSliderShow().addSlider(defaultSliderView1);

                DefaultSliderView defaultSliderView2 = new DefaultSliderView(context);
                defaultSliderView2
                        .description("Story Studio")
                        .image("http://i.imgur.com/jcrsUwP.jpg")
                        .setOnSliderClickListener(new BaseSliderView.OnSliderClickListener() {
                            @Override
                            public void onSliderClick(BaseSliderView baseSliderView) {
                                Toast.makeText(context, baseSliderView.getDescription().toString() + "", Toast.LENGTH_SHORT).show();
                            }
                        });

                viewHolderSecond.getSliderShow().addSlider(defaultSliderView2);

                DefaultSliderView defaultSliderView3 = new DefaultSliderView(context);
                defaultSliderView3
                        .description("Risk")
                        .image("http://i.imgur.com/w4y8ENR.jpg")
                        .setOnSliderClickListener(new BaseSliderView.OnSliderClickListener() {
                            @Override
                            public void onSliderClick(BaseSliderView baseSliderView) {
                                Toast.makeText(context, baseSliderView.getDescription().toString() + "", Toast.LENGTH_SHORT).show();
                            }
                        });

                viewHolderSecond.getSliderShow().addSlider(defaultSliderView3);
                viewHolderSecond.getSliderShow().setIndicatorVisibility(PagerIndicator.IndicatorVisibility.Invisible);

                setAnimation(viewHolderSecond.itemView, position);
                break;
        }

    }

    @Override
    public int getItemCount() {
        return mDataSet.length;
    }

    private void setAnimation(View viewToAnimate, int position) {

        // If the bound view wasn't previously displayed on screen, it's animated
        if (position > lastPosition) {
            Animation animation = AnimationUtils.loadAnimation(context, R.anim.push_up);
            viewToAnimate.startAnimation(animation);
            lastPosition = position;
        }
    }

    @Override
    public void onViewDetachedFromWindow(RecyclerView.ViewHolder holder) {
        super.onViewDetachedFromWindow(holder);
        holder.itemView.clearAnimation();
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
