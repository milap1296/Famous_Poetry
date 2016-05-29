package com.example.famouspoetry;

import java.util.ArrayList;

import android.R.id;
import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

public class DashBoard extends Activity {
	ListView lv;
	Cursor cursor;
	// String[] listItems = { "Jonathon", "Chris", "Henry",
	// "Jan Nisar","Javed Akhtar","Salman Akhtar","Emily","Langston","William","Rabindranath Tagore","Robert Frost"
	// };
	ArrayList<String> listItems = new ArrayList<String>();
	ArrayList<String> ids = new ArrayList<String>();
	ArrayList<String> poetry = new ArrayList<String>();

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.description);
		
		AdView mAdView = (AdView) findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);
		
		lv = (ListView) findViewById(R.id.listView1);
		getAllCats();

		TextView txt = (TextView)findViewById(R.id.textTitle);
		Typeface face = Typeface.createFromAsset(getAssets(), "prince_valiant.ttf");
		txt.setTypeface(face);

		// TODO Auto-generated method stub
		lv.setAdapter(new CustomeAdapter(getApplicationContext(),
				R.layout.listitem1, listItems));
		lv.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {

			//	txt.setText("Famous Poetry");
			//	txt.setTypeface(Typeface.createFromAsset(getAssets(), "prince_valiant.ttf"));
				//txt.setTag(listItems.get(arg2));
				Intent intent = new Intent(DashBoard.this, Description.class);
				intent.putExtra("category",listItems.get(arg2).toString());
				intent.putExtra("click",ids.get(arg2).toString());
				startActivity(intent);

				/*
				 * Intent intent = new Intent(DashBoard.this,
				 * Description.class); intent.putExtra("PoetName",
				 * txt.getText().toString()); intent.putExtra("id",
				 * txt.getTag().toString()); txt.setTag(poetry.get(arg2));
				 * intent.putExtra("Poetry",txt.getTag().toString());
				 * startActivity(intent); // TODO Auto-generated method stub
				 */
			}
		});
	}

	public void getAllCats() {
		// listItems.clear();
		cursor = MainActivity.dbs.getAllCategories();
		cursor.moveToFirst();
		while (cursor.isAfterLast() == false) {
			String input = cursor.getString(cursor.getColumnIndex("category"))
					.toString();
			String str[] = input.split(" ");
			String y = "";
			for (int i = 0; i < str.length; i++) {
				if (!str[i].trim().equals(""))
					y += str[i].trim() + " ";
			}
			Log.d("input string ", y);

			listItems.add(y);
			ids.add(input);
			/*
			 * ids.add(cursor.getString(cursor.getColumnIndex("id")));
			 * poetry.add(cursor.getString(cursor.getColumnIndex("poetry")));
			 * Log.d("id ",ids+"");
			 */
			cursor.moveToNext();
		}
		cursor.close();
	}

}
