package com.murraystudio.murraystudio;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
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
    private int[] logoArrayID = {-1, R.drawable.home, R.drawable.project, R.drawable.people, R.drawable.contact};

    // Allows to remember the last item shown on screen
    private int lastPosition = -1;

    private Context context;

    private static final int VIEW_TYPE_FIRST = 1;
    private static final int VIEW_TYPE_SECOND = 0;

    private int widthOfNav;

    private MainActivity main;


    /**
     * Provide a reference to the type of views that you are using (custom ViewHolder)
     */
    public static class ViewHolderFirst extends RecyclerView.ViewHolder {
        private final TextView navText;
        private final ImageView logo;

        public ViewHolderFirst(View v) {
            super(v);
            navText = (TextView) v.findViewById(R.id.nav_text);
            logo = (ImageView) v.findViewById(R.id.nav_logo);
            //rowImage = (ImageView) v.findViewById(R.id.rowImage);
        }

        public TextView getNavText() {
            return navText;
        }

        public ImageView getNavLogo() {
            return logo;
        }
    }

    public static class ViewHolderSecond extends RecyclerView.ViewHolder implements BaseSliderView.OnSliderClickListener{
        private final SliderLayout sliderShow;
        private Context context;
        private String[] mediaURLs;
        //add slides into slideshow
        DefaultSliderView defaultSliderView1, defaultSliderView2, defaultSliderView3;

        public ViewHolderSecond(View v, Context c) {
            super(v);
            sliderShow = (SliderLayout) v.findViewById(R.id.slider);
            this.context = c;
            mediaURLs = c.getResources().getStringArray(R.array.media_urls);

            defaultSliderView1 = new DefaultSliderView(context);
            defaultSliderView2 = new DefaultSliderView(context);
            defaultSliderView3 = new DefaultSliderView(context);

            defaultSliderView1.setOnSliderClickListener(this);
            defaultSliderView2.setOnSliderClickListener(this);
            defaultSliderView3.setOnSliderClickListener(this);
        }

        public SliderLayout getSliderShow() {
            return sliderShow;
        }

        public DefaultSliderView getDefaultSliderView1(){
            return defaultSliderView1;
        }

        public DefaultSliderView getDefaultSliderView2(){
            return defaultSliderView2;
        }

        public DefaultSliderView getDefaultSliderView3(){
            return defaultSliderView3;
        }

        @Override
        public void onSliderClick(BaseSliderView slider) {
            if(slider.getDescription().equals("Murray Studio")){
                //open murray studio youtube video
                Intent browserIntent;
                browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(mediaURLs[2]));
                context.startActivity(browserIntent);
            }
        }
    }

    public NavAdapter (String[] dataSet, Context context, int widthOfNav){
        mDataSet = dataSet;
        this.context = context;
        //use width of relative layout nav is in
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
                return new ViewHolderSecond(v2, context);
        }

        return new ViewHolderFirst(v1);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        Log.d(TAG, "Element " + position + " set.");

        main = new MainActivity();

        //mediaURLs = context.getResources().getStringArray(R.array.media_urls);

        switch (getItemViewType(position)) {
            case VIEW_TYPE_FIRST:
                ViewHolderFirst viewHolderFirst = (ViewHolderFirst) holder;
                viewHolderFirst.getNavText().setText(mDataSet[position]);
                Drawable d = context.getResources().getDrawable(logoArrayID[position]);
                viewHolderFirst.getNavLogo().setImageDrawable(d);
                //setAnimation(viewHolderFirst.itemView, position);
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
                //DefaultSliderView defaultSliderView1 = new DefaultSliderView(context);
                viewHolderSecond.getDefaultSliderView1()
                        .description("Murray Studio")
                        .image("http://i.imgur.com/m9LupJ3.jpg");

                viewHolderSecond.getSliderShow().addSlider(viewHolderSecond.getDefaultSliderView1());

                //DefaultSliderView defaultSliderView2 = new DefaultSliderView(context);
                viewHolderSecond.getDefaultSliderView2()
                        .description("Story Studio")
                        .image("http://i.imgur.com/jcrsUwP.jpg");

                viewHolderSecond.getSliderShow().addSlider(viewHolderSecond.getDefaultSliderView2());

                //DefaultSliderView defaultSliderView3 = new DefaultSliderView(context);
                viewHolderSecond.getDefaultSliderView3()
                        .description("Risk")
                        .image("http://i.imgur.com/w4y8ENR.jpg");

                viewHolderSecond.getSliderShow().addSlider(viewHolderSecond.getDefaultSliderView3());

                viewHolderSecond.getSliderShow().setIndicatorVisibility(PagerIndicator.IndicatorVisibility.Invisible);

                //setAnimation(viewHolderSecond.itemView, position);
                break;
        }

    }

    @Override
    public int getItemCount() {
        return mDataSet.length;
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

    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            return 0;
        } else {
            return 1;
        }
    }
}
