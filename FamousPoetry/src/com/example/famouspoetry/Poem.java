package com.example.famouspoetry;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

public class Poem extends Activity {

	String newaa;
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.listitem);
		
		AdView mAdView = (AdView) findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);
		
		String srs = null;
		
		ImageButton imgbtn = (ImageButton) findViewById(R.id.imageButton1);
		
		String poem = getIntent().getStringExtra("poem");
		String id = getIntent().getStringExtra("id");
		TextView title = (TextView) findViewById(R.id.textView1);
		String entry = MainActivity.dbs.getPoem(id);
		TextView txt = (TextView) findViewById(R.id.textView2);
		Log.d("poem", entry);
		String aa = entry.replace("\\n", "\n\n");
		newaa = aa.replace("&quot;", "\"");
		// entry.replace("\n","\n ");
		Log.d("poem after", aa);

		/*
		 * for(int i=0;i<entry.length();i++) { String dumb = entry.charAt(i) +
		 * entry.charAt(i+1)+""; if(dumb.equals("\n")) { srs += "\n"; i++; }
		 * else { srs += entry.charAt(i); } } Log.d("After",srs);
		 * txt.setText(srs);
		 */

		txt.setText(newaa);
		title.setText(poem);
		
		Typeface face= Typeface.createFromAsset(getAssets(), "prince_valiant.ttf");
		title.setTypeface(face);
		
		txt.setMovementMethod(new ScrollingMovementMethod());
		imgbtn.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				Intent sharingIntent = new Intent(
						android.content.Intent.ACTION_SEND);
				sharingIntent.setType("text/plain");
				String shareBody = newaa;
				sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT,
						"Subject Here");
				sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT,
						shareBody);
				startActivity(Intent.createChooser(sharingIntent, "Share via"));
			}
		});

		// TODO Auto-generated method stub
	}

}
