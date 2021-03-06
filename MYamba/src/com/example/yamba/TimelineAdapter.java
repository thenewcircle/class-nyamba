package com.example.yamba;

import winterwell.jtwitter.Twitter.Status;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class TimelineAdapter extends ArrayAdapter<Status> {

	public TimelineAdapter(Context context, int textViewResourceId) {
		super(context, textViewResourceId);
	}

	@Override
	public View getView(int position, View view, ViewGroup parent) {
		Status status = getItem(position);
		if (view == null) {
			view = View.inflate(getContext(),
					android.R.layout.simple_list_item_2, null);
		}
		((TextView) view.findViewById(android.R.id.text1))
				.setText(status.user.name);
		((TextView) view.findViewById(android.R.id.text2)).setText(status.text);
		return view;
	}

}
