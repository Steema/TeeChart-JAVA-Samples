package com.steema.teechart.android;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.steema.teechart.languages.Language;
import com.steema.teechart.styles.Series;

@SuppressWarnings("rawtypes")
public class IconTextAdapter extends ArrayAdapter<Class> {

	private LayoutInflater inflater;

	public IconTextAdapter(final Context context, int textViewResourceId,
			final Class[] objects, final LayoutInflater inflater) {
		super(context, textViewResourceId, objects);
		this.inflater = inflater;
	}

	@Override
	public View getDropDownView(int position, View convertView, ViewGroup parent) {
		return getCustomView(position, convertView, parent);
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		return getCustomView(position, convertView, parent);
	}

	public View getCustomView(int position, View convertView, ViewGroup parent) {
		View row = convertView;

		if (row == null)
			row = inflater.inflate(R.layout.icontextrow, parent, false);

		TextView label = (TextView) row.findViewById(R.id.name);

		Class c = getItem(position);

		if (c == null) {
			label.setText(Language.getString("Other"));
		} else {

			Series s = getSeries(c);

			if (s == null) {
				label.setText(c.toString());
			} else {
				label.setText(Language.getString(s.getDescription()));

				ImageView icon = (ImageView) row.findViewById(R.id.icon);
				icon.setMinimumHeight(32);
				icon.setMinimumWidth(32);

				URL dir = s.getBitmapEditor();

				if (dir != null)
					try {
						icon.setImageBitmap(BitmapFactory
								.decodeStream((InputStream) dir.getContent()));
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
			}
		}

		return row;
	}

	public static Series getSeries(Class c) {
		Series s = null;
		try {
			s = (Series) c.newInstance();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return s;
	}
}
