package com.murraystudio.murraystudio;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.v4.app.Fragment;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Toast;

import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.DefaultSliderView;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by sushi_000 on 7/28/2015.
 */
public class GalleryImageFullScreen extends Fragment {

    protected android.support.v7.app.ActionBar actionBar;
    private SliderLayout imageSlide;
    private String[] imageURLs;

    public int height;
    public int width;

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
        View view = inflater.inflate(R.layout.gallery_slider, container, false);

        actionBar = (android.support.v7.app.ActionBar) ((MainActivity) getActivity())
                .getSupportActionBar();

        MainActivity main = new MainActivity();
        if(main.getGalleryType() == main.RISK_GALLERY){
            actionBar.setTitle("Risk Gallery");
        }
        else{
            actionBar.setTitle("Graphic Design Gallery");
        }

        imageSlide = (SliderLayout) view.findViewById(R.id.gallery_slider);

        ArrayList<DefaultSliderView> slides = new ArrayList<DefaultSliderView>();

        for(int i = 0; i < imageURLs.length; i++){
            DefaultSliderView slide = new DefaultSliderView(getActivity());
            slide.image(imageURLs[i]);
            slide.setScaleType(BaseSliderView.ScaleType.CenterInside);
            slides.add(slide);
            imageSlide.addSlider(slide);
        }

        imageSlide.setCurrentPosition(main.getCurrentImagePosition());
        imageSlide.stopAutoCycle();

        return view;
    }

    @Override
    public void onStop() {
        imageSlide.stopAutoCycle();
        super.onStop();
    }

    /**
     * Generates Strings for RecyclerView's adapter. This data would usually come
     * from a local content provider or remote server.
     */
    private void initDataset() {
        MainActivity main = new MainActivity();
        if(main.getGalleryType() == main.RISK_GALLERY){
            imageURLs = getResources().getStringArray(R.array.risk_image_urls);
        }
        else{
            imageURLs = getResources().getStringArray(R.array.graphic_design_image_urls);
        }
    }

}
