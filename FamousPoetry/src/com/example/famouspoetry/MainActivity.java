package com.example.famouspoetry;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.Scanner;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import android.widget.Toast;

public class MainActivity extends Activity {
	Intent i;
	public static DatabaseHelper dbs;
	SharedPreferences sp;
	SharedPreferences.Editor editor;

	String flag = "";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		dbs = new DatabaseHelper(MainActivity.this);
		/*
		 * Toast.makeText(getApplicationContext(),
		 * getResources().getDisplayMetrics().density + "", 2000).show();
		 */
		sp = getSharedPreferences("FamousPoetrySettings", Context.MODE_APPEND);
		editor = sp.edit();
		if (sp.getString("install", null) == null) {
			editor.putString("install", "true").commit();
			new GetJsonDataTask().execute();
			// flag ="true";
		} else {
			new Thread(new Runnable() {

				@Override
				public void run() {

					try {
						Thread.sleep(2000);
						Intent i = new Intent(MainActivity.this,
								DashBoard.class);
						startActivity(i);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

				}
			}).start();

		}

	}

	public class GetJsonDataTask extends AsyncTask<String, String, String> {

		Dialog dia;
		ProgressDialog dialog;

		@Override
		protected void onPreExecute() {

			/*
			 * dia = new Dialog(MainActivity.this);
			 * dia.requestWindowFeature(Window.FEATURE_NO_TITLE);
			 * dia.setContentView(R.layout.customdialog); dia.show();
			 */
			dialog = new ProgressDialog(MainActivity.this);
			dialog.setMessage("loading data....");
			dialog.show();
			Log.d("pre execute", "pre execute ");
			super.onPreExecute();

		}

		@Override
		protected String doInBackground(String... params) {
			try {

				// URL u = new URL("http://api.androidhive.info/contacts/");
				/*
				 * URL u = new URL(
				 * "http://192.168.32.167/poem_data/poem_webservice.php");
				 */
				URL u = new URL(
						"http://192.168.32.167/poem_data/getpoemdata.php");
				HttpURLConnection c = (HttpURLConnection) u.openConnection();
				c.setRequestMethod("GET");
				c.connect();

				/*
				 * Scanner sc = new Scanner(new
				 * InputStreamReader(c.getInputStream()) {
				 * 
				 * @Override public int read() throws IOException { // TODO
				 * Auto-generated method stub return 0; } });
				 * while(sc.hasNextLine()) { json_string = sc.nextLine()+"\n"; }
				 * Log.d("json_string", json_string+"");
				 */

				String json_string = "";

				BufferedReader rd = new BufferedReader(new InputStreamReader(
						c.getInputStream()));
				StringBuilder sb = new StringBuilder();
				String line;
				while ((line = rd.readLine()) != null) {
					sb.append(line);
				}
				rd.close();
				json_string = sb.toString();
				Log.d("json data ", json_string);

				// JSONObject jsonobj = new JSONObject(json_string);
				JSONArray jsonarr = new JSONArray(json_string);
				for (int i = 0; i < jsonarr.length(); i++) {
					JSONObject jsonobj2 = jsonarr.getJSONObject(i);
					String poemname = jsonobj2.getString("vPoemName");
					String poem = jsonobj2.getString("tPoemLine");
					String auther = jsonobj2.getString("vPoetName");
					String category = jsonobj2.getString("vCategoryName");

					dbs.addPoet(category, poemname, auther, poem);
				}

				/*
				 * for(int i=0;i<jsonarr.length();i++) { JSONObject jsonobj2 =
				 * jsonarr.getJSONObject(i);
				 * 
				 * String name = jsonobj2.optString("name"); String email =
				 * jsonobj2.optString("email"); JSONObject phone =
				 * jsonobj2.getJSONObject("phone"); String mobile = null;
				 * 
				 * for(int j=0;j<phone.length();j++) { mobile =
				 * phone.optString("mobile"); }
				 * 
				 * Log.d("name -- email -- mobile ",name +"- -- "+email +
				 * "-- - "+mobile); }
				 */} catch (MalformedURLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ProtocolException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			return null;
		}

		@Override
		protected void onPostExecute(String result) {
			Log.d("post execute", "post execute ");
			dialog.dismiss();

			Intent i = new Intent(MainActivity.this, DashBoard.class);
			startActivity(i);
			// dia.cancel();
			super.onPostExecute(result);
		}

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
