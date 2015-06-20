package com.murraystudio.murraystudio;

import android.content.Intent;
import android.content.res.Configuration;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.SpannableString;
import android.text.style.UnderlineSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

public class MadlibsAbout extends Fragment {

	android.support.v7.app.ActionBar actionBar;
	Button playStore;
	Button playStore2;
	TextView changelog;
	private final static String APP_NAME = "com.murraystudio.storystudiopro";

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// Inflate the layout for this fragment

		View view = inflater.inflate(R.layout.madlibs_about, container, false);

		actionBar = (android.support.v7.app.ActionBar) ((MainActivity) getActivity())
				.getSupportActionBar();
		actionBar.setTitle("About");
		
		changelog = (TextView) view.findViewById(R.id.changelog);
		SpannableString content = new SpannableString("Changelog");
		content.setSpan(new UnderlineSpan(), 0, content.length(), 0);
		changelog.setText(content);
		
		
		playStore = (Button) view.findViewById(R.id.playStore);
		playStore.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				try {
				    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + APP_NAME)));
				} catch (android.content.ActivityNotFoundException anfe) {
				    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("http://play.google.com/store/apps/details?id=" + APP_NAME)));
				}				
			}
		});
		
/*		playStore2 = (Button) view.findViewById(R.id.playStore2);
		playStore2.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) { 
				Fragment newFragment = new MadlibsPay();
				FragmentTransaction transaction = getFragmentManager()
						.beginTransaction();

				transaction.replace(R.id.fragment_container, newFragment);
				transaction.addToBackStack(null);

				transaction.commit();			
			}
		});*/
		
		
		return view;
		
		

	}
    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        LayoutInflater inflater = LayoutInflater.from(getActivity());
        populateViewForOrientation(inflater, (ViewGroup) getView());
    }
 
    private void populateViewForOrientation(LayoutInflater inflater, ViewGroup viewGroup) {
        viewGroup.removeAllViewsInLayout();
        View subview = inflater.inflate(R.layout.madlibs_about, viewGroup);
 
        // Find your buttons in subview, set up onclicks, set up callbacks to your parent fragment or activity here.
        
        // You can create ViewHolder or separate method for that.
        // example of accessing views: TextView textViewExample = (TextView) view.findViewById(R.id.text_view_example);
        // textViewExample.setText("example");
        
		playStore = (Button) subview.findViewById(R.id.playStore);
		playStore.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				try {
				    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + APP_NAME)));
				} catch (android.content.ActivityNotFoundException anfe) {
				    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("http://play.google.com/store/apps/details?id=" + APP_NAME)));
				}				
			}
		});
		
/*		playStore2 = (Button) subview.findViewById(R.id.playStore2);
		playStore2.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) { 
				Fragment newFragment = new MadlibsPay();
				FragmentTransaction transaction = getFragmentManager()
						.beginTransaction();

				transaction.replace(R.id.fragment_container, newFragment);
				transaction.addToBackStack(null);

				transaction.commit();			
			}
		});*/
    }
}

