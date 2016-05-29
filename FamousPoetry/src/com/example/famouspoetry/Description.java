package com.example.famouspoetry;

import java.util.ArrayList;
import java.util.HashMap;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

public class Description extends Activity {

	Intent i;
	ListView lv;
	String title;
	ArrayList<HashMap<String, String>> map;
	ArrayList<HashMap<String, String>> re_map;
	HashMap<String, String> store = new HashMap<String, String>();
	// map = MainActivity.dbs.getAllData(getIntent().getStringExtra("click"));
	ArrayList<String> Poetry = new ArrayList<String>();
	ArrayList<String> Author = new ArrayList<String>();

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.seconddescription);
		
		AdView mAdView = (AdView) findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);
		
		lv = (ListView) findViewById(R.id.listView5);
		title = "" + "Category " + getIntent().getStringExtra("category");
		// getAllPoetry();
		map = MainActivity.dbs.getAllData(getIntent().getStringExtra("click"));
		lv.setAdapter(new PoemListAdapter(Description.this, R.layout.listitem1,
				map));

		TextView txt1 = (TextView) findViewById(R.id.textView3);
		txt1.setText(title);
		
		
		Typeface face= Typeface.createFromAsset(getAssets(), "prince_valiant.ttf");
		txt1.setTypeface(face);
		
		lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {

				HashMap<String, String> z = map.get(arg2);
				String poem = z.get("poem_name").toString();
				Log.d("id",z.get("id"));
				Intent i = new Intent(Description.this, Poem.class);
				i.putExtra("poem", poem);
				i.putExtra("id", z.get("id"));
				startActivity(i);
				
			}
		});
	}
}
