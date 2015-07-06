package com.murraystudio.murraystudio;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.TextSliderView;

public class Home extends Fragment implements BaseSliderView.OnSliderClickListener {

	private static final String KEY_LAYOUT_MANAGER = "layoutManager";
	private static final int SPAN_COUNT = 2;
	private static final int DATASET_COUNT = 60;

	private enum LayoutManagerType {
		GRID_LAYOUT_MANAGER,
		LINEAR_LAYOUT_MANAGER
	}

	android.support.v7.app.ActionBar actionBar;
	private final static String APP_NAME = "com.murraystudio.storystudiopro";
	SliderLayout sliderShow;
	protected RecyclerView mRecyclerView;
	protected CustomAdapter mAdapter;
	protected RecyclerView.LayoutManager mLayoutManager;
	protected LayoutManagerType mCurrentLayoutManagerType;
	protected String[] mDataset;

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
		View view = inflater.inflate(R.layout.home, container, false);



		actionBar = (android.support.v7.app.ActionBar) ((MainActivity) getActivity())
				.getSupportActionBar();
		actionBar.setTitle("About");

		mRecyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);
		mLayoutManager = new LinearLayoutManager(getActivity());
		mCurrentLayoutManagerType = LayoutManagerType.LINEAR_LAYOUT_MANAGER;
		if (savedInstanceState != null) {
			// Restore saved layout manager type.
			mCurrentLayoutManagerType = (LayoutManagerType) savedInstanceState
					.getSerializable(KEY_LAYOUT_MANAGER);
		}
		setRecyclerViewLayoutManager(mCurrentLayoutManagerType);

		mAdapter = new CustomAdapter(mDataset);
		mRecyclerView.setAdapter(mAdapter);

		//calculate slideshow height and width based off screen dimensions and
		//image size of 1400x500
		DisplayMetrics displaymetrics = new DisplayMetrics();
		getActivity().getWindowManager().getDefaultDisplay().getMetrics(displaymetrics);
		int screenHeight = displaymetrics.heightPixels;
		int screenWidth = displaymetrics.widthPixels;
		double w = 1.00;
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


		
		return view;
    }

	/**
	 * Set RecyclerView's LayoutManager to the one given.
	 *
	 * @param layoutManagerType Type of layout manager to switch to.
	 */
	public void setRecyclerViewLayoutManager(LayoutManagerType layoutManagerType) {
		int scrollPosition = 0;

		// If a layout manager has already been set, get current scroll position.
		if (mRecyclerView.getLayoutManager() != null) {
			scrollPosition = ((LinearLayoutManager) mRecyclerView.getLayoutManager())
					.findFirstCompletelyVisibleItemPosition();
		}

		switch (layoutManagerType) {
			case GRID_LAYOUT_MANAGER:
				mLayoutManager = new GridLayoutManager(getActivity(), SPAN_COUNT);
				mCurrentLayoutManagerType = LayoutManagerType.GRID_LAYOUT_MANAGER;
				break;
			case LINEAR_LAYOUT_MANAGER:
				mLayoutManager = new LinearLayoutManager(getActivity());
				mCurrentLayoutManagerType = LayoutManagerType.LINEAR_LAYOUT_MANAGER;
				break;
			default:
				mLayoutManager = new LinearLayoutManager(getActivity());
				mCurrentLayoutManagerType = LayoutManagerType.LINEAR_LAYOUT_MANAGER;
		}

		mRecyclerView.setLayoutManager(mLayoutManager);
		mRecyclerView.scrollToPosition(scrollPosition);
	}

	@Override
	public void onSaveInstanceState(Bundle savedInstanceState) {
		// Save currently selected layout manager.
		savedInstanceState.putSerializable(KEY_LAYOUT_MANAGER, mCurrentLayoutManagerType);
		super.onSaveInstanceState(savedInstanceState);
	}

	/**
	 * Generates Strings for RecyclerView's adapter. This data would usually come
	 * from a local content provider or remote server.
	 */
	private void initDataset() {
		mDataset = new String[DATASET_COUNT];
		for (int i = 0; i < DATASET_COUNT; i++) {
			mDataset[i] = "This is element #" + i;
		}
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

