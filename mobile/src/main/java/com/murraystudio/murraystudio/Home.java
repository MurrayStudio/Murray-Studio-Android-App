package com.murraystudio.murraystudio;

import android.content.Intent;
import android.content.res.Configuration;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.SpannableString;
import android.text.style.UnderlineSpan;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.TextSliderView;

public class Home extends Fragment {

	android.support.v7.app.ActionBar actionBar;
	Button playStore;
	Button playStore2;
	TextView changelog;
	private final static String APP_NAME = "com.murraystudio.storystudiopro";
	SliderLayout sliderShow;

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
		double h = 0.357;
		double w = 1.0;
		int imgWidth =  (int) (screenWidth* w);
		int imgHeight =  (int) (imgWidth/2.8);

		sliderShow = (SliderLayout) view.findViewById(R.id.slider);

		//set new height and width for slideshow
		sliderShow.getLayoutParams().height = imgHeight;
		sliderShow.getLayoutParams().width = imgWidth;

		TextSliderView textSliderView1 = new TextSliderView(getActivity());
		textSliderView1
				.description("Murray Studio")
				.image("http://i.imgur.com/m9LupJ3.jpg");

		sliderShow.addSlider(textSliderView1);

		TextSliderView textSliderView2 = new TextSliderView(getActivity());
		textSliderView2
				.description("Story Studio")
				.image("http://i.imgur.com/jcrsUwP.jpg");

		sliderShow.addSlider(textSliderView2);
		
		return view;

    }

	@Override
	public void onStop() {
		sliderShow.stopAutoCycle();
		super.onStop();
	}
}

