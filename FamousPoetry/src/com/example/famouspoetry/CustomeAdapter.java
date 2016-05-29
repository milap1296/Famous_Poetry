package com.example.famouspoetry;

import java.util.List;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView.FindListener;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class CustomeAdapter extends ArrayAdapter<String>{

	
	Context context;
	List<String> objects;

	public CustomeAdapter(Context context, int resource, List<String> objects) {
		super(context, resource, objects);
		this.context = context;
		this.objects = objects;
		// TODO Auto-generated constructor stub
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View view  = inflater.inflate(R.layout.listitem1, parent, false);
		TextView txt = (TextView) view.findViewById(R.id.textView1);
		TextView txtAuthor = (TextView)view.findViewById(R.id.textView2);
		ImageView img = (ImageView) view.findViewById(R.id.imageView1);
		//ImageView img2 = (ImageView) view.findViewById(R.id.imageView2);
		img.setImageResource(R.drawable.list);
		//img2.setImageResource(R.drawable.spaerator);
		txt.setText(objects.get(position));
		txt.setTextColor(Color.parseColor("#FFD700"));
		txtAuthor.setVisibility(View.GONE);
		txt.setTag(objects.get(position));
		
		
		
		
		return view;
	}
	/*public View getView1(int position,View convertView,ViewGroup parent){
		LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View view  = inflater.inflate(R.layout.listitem, parent, false);
		TextView txt = (TextView) view.findViewById(R.id.textView2);
		txt.setText(objects.get(position));
	}*/
	
}
