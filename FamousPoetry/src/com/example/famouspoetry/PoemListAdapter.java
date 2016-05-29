package com.example.famouspoetry;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView.FindListener;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class PoemListAdapter extends BaseAdapter {

	Context context;
	ArrayList<HashMap<String, String>> objects;
HashMap<String, String> result ;
	public static String first;

	public PoemListAdapter(Context context, int resource, ArrayList<HashMap<String, String>> objects) {
		super();
		this.context = context;
		this.objects = objects;
		// TODO Auto-generated constructor stub
	}
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View view  = inflater.inflate(R.layout.listitem1, parent, false);
		
		result = objects.get(position);
		
		TextView txt1 = (TextView) view.findViewById(R.id.textView1);
		TextView txt2 = (TextView) view.findViewById(R.id.textView2);
		String text2 = "-"+result.get("auther");
		
		first = result.get("poem_name").toString();
		
		txt1.setText(result.get("poem_name"));
		txt2.setText(text2);
		txt1.setTextColor(Color.parseColor("#FFD700"));
		txt2.setTextColor(Color.parseColor("#FFD700"));
		ImageView img = (ImageView) view.findViewById(R.id.imageView1);
		img.setImageResource(R.drawable.list);
		return view;
	}
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return objects.size();
	}

	@Override
	public Object getItem(int arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long getItemId(int arg0) {
		// TODO Auto-generated method stub
		return 0;
	}
}
