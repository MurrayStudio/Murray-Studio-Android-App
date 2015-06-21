package com.murraystudio.murraystudio;

import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.SpannableString;
import android.text.style.UnderlineSpan;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.TextSliderView;

public class Home extends Fragment implements BaseSliderView.OnSliderClickListener {

	android.support.v7.app.ActionBar actionBar;
	Button playStore;
	Button playStore2;
	TextView changelog;
	private final static String APP_NAME = "com.murraystudio.storystudiopro";
	SliderLayout sliderShow;
	ImageButton logo1;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// Inflate the layout for this fragment

		View view = inflater.inflate(R.layout.home, container, false);

		actionBar = (android.support.v7.app.ActionBar) ((MainActivity) getActivity())
				.getSupportActionBar();
		actionBar.setTitle("About");

		//calculate slideshow height and width based off screen dimensions and
		//image size of 1400x500
		DisplayMetrics displaymetrics = new DisplayMetrics();
		getActivity().getWindowManager().getDefaultDisplay().getMetrics(displaymetrics);
		int screenHeight = displaymetrics.heightPixels;
		int screenWidth = displaymetrics.widthPixels;
		double w = 0.975;
		int imgWidth =  (int) (screenWidth* w);
		int imgHeight =  (int) (imgWidth/2.8);

		sliderShow = (SliderLayout) view.findViewById(R.id.slider);

		//set new height and width for slideshow
		sliderShow.getLayoutParams().height = imgHeight;
		sliderShow.getLayoutParams().width = imgWidth;


		//add slides into slideshow

		TextSliderView textSliderView1 = new TextSliderView(getActivity());
		textSliderView1
				.description("Murray Studio")
				.image("http://i.imgur.com/m9LupJ3.jpg")
				.setOnSliderClickListener(this);

		sliderShow.addSlider(textSliderView1);

		TextSliderView textSliderView2 = new TextSliderView(getActivity());
		textSliderView2
				.description("Story Studio")
				.image("http://i.imgur.com/jcrsUwP.jpg")
				.setOnSliderClickListener(this);

		sliderShow.addSlider(textSliderView2);

		TextSliderView textSliderView3 = new TextSliderView(getActivity());
		textSliderView3
				.description("Risk")
				.image("http://i.imgur.com/w4y8ENR.jpg")
				.setOnSliderClickListener(this);

		sliderShow.addSlider(textSliderView3);



		logo1 = (ImageButton) view.findViewById(R.id.logo1);
		logo1.setOnTouchListener(new View.OnTouchListener() {
			@Override
			public boolean onTouch(View v, MotionEvent event) {

				switch (event.getAction()) {
					case MotionEvent.ACTION_DOWN:
						logo1.setColorFilter(Color.argb(35, 255, 255, 255)); // White Tint
						return true; // if you want to handle the touch event
					case MotionEvent.ACTION_UP:
						logo1.clearColorFilter(); // White Tint
						return true; // if you want to handle the touch event
				}
				return false;
			}
		});

		
		return view;
    }

	@Override
	public void onStop() {
		sliderShow.stopAutoCycle();
		super.onStop();
	}

	//when slider is clicked
	@Override
	public void onSliderClick(BaseSliderView baseSliderView) {
		Toast.makeText(getActivity(), baseSliderView.getDescription().toString() + "",Toast.LENGTH_SHORT).show();
	}
}

