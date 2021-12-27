package com.steema.teechart.android;

import java.util.List;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

// With this class we create a custom Linearlayout with one thumb nail and one title
// per line, we differentiate of the different custom linearlayout we have 
// in TeeChart Android demo  with the parameter Device and his tooltype attribute
// if device.getDeviceToolType() = 0 the linearlayout selected is Series
// if device.getDeviceToolType() = 1 the linearlayout selected is Tools
// if device.getDeviceToolType() = 2 the linearlayout selected is Themes
// if device.getDeviceToolType() = 3 the linearlayout selected is Android Phone Stastistics

class CustomAdapterView extends LinearLayout {
	public CustomAdapterView(Context context, Device device) {
		super(context);

		// container is a horizontal layer
		setOrientation(LinearLayout.HORIZONTAL);
		setPadding(0, 6, 0, 6);

		// image:params
		LinearLayout.LayoutParams Params = new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
		Params.setMargins(6, 0, 6, 0);
		// image:itself
		ImageView ivLogo = new ImageView(context);
		// load image
		switch (device.getDeviceToolType()) {

		case 0:
			switch (device.getDeviceType()) {
			case 0:
				ivLogo.setImageDrawable(context.getResources().getDrawable(R.drawable.pie));
				break;
			case 1:
				ivLogo.setImageDrawable(context.getResources().getDrawable(R.drawable.line));
				break;
			case 2:
				ivLogo.setImageDrawable(context.getResources().getDrawable(R.drawable.bar));
				break;
			case 3:
				ivLogo.setImageDrawable(context.getResources().getDrawable(R.drawable.area));
				break;
			case 4:
				ivLogo.setImageDrawable(context.getResources().getDrawable(R.drawable.points));
				break;
			case 5:
				ivLogo.setImageDrawable(context.getResources().getDrawable(R.drawable.horizbar));
				break;
			case 6:
				ivLogo.setImageDrawable(context.getResources().getDrawable(R.drawable.candle));
				break;
			case 7:
				ivLogo.setImageDrawable(context.getResources().getDrawable(R.drawable.surface));
				break;
			case 8:
				ivLogo.setImageDrawable(context.getResources().getDrawable(R.drawable.tower));
				break;
			case 9:
				ivLogo.setImageDrawable(context.getResources().getDrawable(R.drawable.contour));
				break;
			case 10:
				ivLogo.setImageDrawable(context.getResources().getDrawable(R.drawable.colorgrid));
				break;
			case 11:
				ivLogo.setImageDrawable(context.getResources().getDrawable(R.drawable.polar));
				break;

			case 12:
				ivLogo.setImageDrawable(context.getResources().getDrawable(R.drawable.smith));
				break;

			case 13:
				ivLogo.setImageDrawable(context.getResources().getDrawable(R.drawable.radar));
				break;

			case 14:
				ivLogo.setImageDrawable(context.getResources().getDrawable(R.drawable.fastline));
				break;

			case 15:
				ivLogo.setImageDrawable(context.getResources().getDrawable(R.drawable.isosurface));
				break;

			case 16:
				ivLogo.setImageDrawable(context.getResources().getDrawable(R.drawable.bubbles));
				break;

			case 17:
				ivLogo.setImageDrawable(context.getResources().getDrawable(R.drawable.circulargauge));
				break;

			case 18:
				ivLogo.setImageDrawable(context.getResources().getDrawable(R.drawable.lineargauge));
				break;

			case 19:
				ivLogo.setImageDrawable(context.getResources().getDrawable(R.drawable.map));
				break;

			case 20:
				ivLogo.setImageDrawable(context.getResources().getDrawable(R.drawable.candle));
				break;
			}
			break;

		case 2:
			switch (device.getDeviceType()) {
			case 0:
				ivLogo.setImageDrawable(context.getResources().getDrawable(R.drawable.opera));
				break;
			case 1:
				ivLogo.setImageDrawable(context.getResources().getDrawable(R.drawable.blackisback));
				break;
			case 2:
				ivLogo.setImageDrawable(context.getResources().getDrawable(R.drawable.teechart));
				break;
			case 3:
				ivLogo.setImageDrawable(context.getResources().getDrawable(R.drawable.excel));
				break;
			case 4:
				ivLogo.setImageDrawable(context.getResources().getDrawable(R.drawable.classic));
				break;
			case 5:
				ivLogo.setImageDrawable(context.getResources().getDrawable(R.drawable.xp));
				break;
			case 6:
				ivLogo.setImageDrawable(context.getResources().getDrawable(R.drawable.web));
				break;
			case 7:
				ivLogo.setImageDrawable(context.getResources().getDrawable(R.drawable.business));
				break;
			case 8:
				ivLogo.setImageDrawable(context.getResources().getDrawable(R.drawable.blues));
				break;
			case 9:
				ivLogo.setImageDrawable(context.getResources().getDrawable(R.drawable.grayscale));
				break;

			}
			break;

		case 1:
			switch (device.getDeviceType()) {
			case 0: // Tool : AnnotationClick
				ivLogo.setImageDrawable(context.getResources().getDrawable(R.drawable.annotationclick));
				break;
			case 1: // Tool : AxisArrow
				ivLogo.setImageDrawable(context.getResources().getDrawable(R.drawable.axisarrow));
				break;
			case 2: // Tool : dragmarks
				ivLogo.setImageDrawable(context.getResources().getDrawable(R.drawable.dragmarks));
				break;
			case 3: // Tool : dragpoint
				ivLogo.setImageDrawable(context.getResources().getDrawable(R.drawable.dragpoint));
				break;
			case 4: // Tool : drawline
				ivLogo.setImageDrawable(context.getResources().getDrawable(R.drawable.drawline));
				break;
			case 5: // Tool : extralegend
				ivLogo.setImageDrawable(context.getResources().getDrawable(R.drawable.extralegend));
				break;
			case 6: // Tool : Gantt Mouse Drag
				ivLogo.setImageDrawable(context.getResources().getDrawable(R.drawable.seriesband));
				break;
			case 7: // Tool : markstip
				ivLogo.setImageDrawable(context.getResources().getDrawable(R.drawable.markstip));
				break;
			case 8: // Tool : movingcursor
				ivLogo.setImageDrawable(context.getResources().getDrawable(R.drawable.movingcursor));
				break;
			case 9: // Tool : seriesband
				ivLogo.setImageDrawable(context.getResources().getDrawable(R.drawable.seriesband));
				break;
			case 10: // Tool : scrollPager
				ivLogo.setImageDrawable(context.getResources().getDrawable(R.drawable.scrollpager));
				break;

			}
			break;
		case 3:
			switch (device.getDeviceType()) {
			case 0: // Phone : Volume
				ivLogo.setImageDrawable(context.getResources().getDrawable(R.drawable.music));
				break;
			case 1: // Phone : Battery
				ivLogo.setImageDrawable(context.getResources().getDrawable(R.drawable.battery));
				break;

			case 2: // Phone : WIFI
				ivLogo.setImageDrawable(context.getResources().getDrawable(R.drawable.wifi));
				break;
			case 3: // Phone : Camera
				ivLogo.setImageDrawable(context.getResources().getDrawable(R.drawable.camera));
				break;

			}
			break;

		}
		// image:add
		addView(ivLogo, Params);

		// vertical layer for text
		Params = new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
		LinearLayout PanelV = new LinearLayout(context);
		PanelV.setOrientation(LinearLayout.VERTICAL);
		PanelV.setGravity(Gravity.CENTER);
		TextView textName = new TextView(context);
		textName.setGravity(Gravity.CENTER);
		textName.setTextSize(16);
		textName.setTextColor(Color.rgb(255, 255, 255));
		textName.setTypeface(Typeface.DEFAULT, Typeface.BOLD);
		textName.setText(device.getDeviceName());
		PanelV.addView(textName);
		addView(PanelV, Params);
	}
}

public class CustomAdapter extends BaseAdapter /* implements OnClickListener */{

	/*
	 * private class OnItemClickListener implements OnClickListener{ private int
	 * mPosition; OnItemClickListener(int position){ mPosition = position; }
	 * public void onClick(View arg0) { Log.v("ddd", "onItemClick at position" +
	 * mPosition); } }
	 */

	private Context context;
	private List<Device> deviceList;

	public CustomAdapter(Context context, List<Device> deviceList) {
		this.context = context;
		this.deviceList = deviceList;
	}

	public int getCount() {
		return deviceList.size();
	}

	public Object getItem(int position) {
		return deviceList.get(position);
	}

	public long getItemId(int position) {
		return position;
	}

	public View getView(int position, View convertView, ViewGroup parent) {
		Device device = deviceList.get(position);
		View v = new CustomAdapterView(this.context, device);
		v.setBackgroundColor((position % 2) == 1 ? Color.BLACK : Color.BLACK);
		/* v.setOnClickListener(new OnItemClickListener(position)); */
		return v;
	}

	/*
	 * public void onClick(View v) { Log.v(LOG_TAG, "Row button clicked"); }
	 */

}
