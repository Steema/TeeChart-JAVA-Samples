/*
 * Copyright (C) 2007 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.steema.teechart.android.editors;

import com.steema.teechart.languages.Language;

import android.os.Bundle;
import android.app.Dialog;
import android.content.Context;
import android.graphics.*;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

public class ColorPickerDialog extends Dialog {

	private interface OnLumChangeListener {
		public void onLumChanged(View view, float position);
	}
	
	public class LumBar extends View {

		private Paint mPaint;
		private OnLumChangeListener lumListener; 
		public int color;
		
		public LumBar(Context context, OnLumChangeListener listener) {
			super(context);
			
			lumListener = listener;
			
			mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
			mPaint.setStyle(Paint.Style.FILL);
			mPaint.setFilterBitmap(true);
		}

		
		protected void onDraw(Canvas canvas) {
			Rect r = new Rect();
			getLocalVisibleRect(r);

			int[] mColors = new int[3];
			mColors[0] = Color.BLACK;
			mColors[1] = color; 
			mColors[2] = Color.WHITE;
			
			Shader s = new LinearGradient(0, 0, r.width(), r.height(),
					mColors, null, Shader.TileMode.CLAMP);

			mPaint.setShader(s);
			canvas.drawRect(r, mPaint);
		}
		
		
		protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
			setMeasuredDimension(ColorPickerView.CENTER_X / 2, 
					ColorPickerView.CENTER_Y * 2);
		}

		
		public boolean onTouchEvent(MotionEvent event) {
			@SuppressWarnings("unused")
			float x = event.getX();
			float y = event.getY();

			switch (event.getAction()) {
			case MotionEvent.ACTION_DOWN: {
				lumListener.onLumChanged(this, y / getHeight());
				break;
			}
			case MotionEvent.ACTION_MOVE: {
				lumListener.onLumChanged(this, y / getHeight());
				break;
			}
			}
		
			return true;
		}

		public void setColor(int newColor) {
			color = newColor;
			invalidate();
		}		
	}

	public interface OnColorChangedListener {
		void colorChanged(int color);
	}

	private OnColorChangedListener mListener;
	private int mInitialColor;
	private LumBar lum;

	private static class ColorPickerView extends View {
		
		private Paint mPaint;
		private Paint mCenterPaint;
		private OnColorChangedListener mListener;
		private float lum = 1;
		private final int[] mColors = new int[] { 0xFFFF0000, 0xFFFF00FF, 0xFF0000FF,
				0xFF00FFFF, 0xFF00FF00, 0xFFFFFF00, 0xFFFF0000 };

		
		ColorPickerView(Context c, OnColorChangedListener l, int color) {
			super(c);
			mListener = l;

			mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
			mPaint.setFilterBitmap(true);
			final Shader s = new SweepGradient(0, 0, mColors, null);
			mPaint.setShader(s);

			mPaint.setStyle(Paint.Style.STROKE);
			mPaint.setStrokeWidth(32);

			mCenterPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
			mCenterPaint.setColor(color);
			mCenterPaint.setStrokeWidth(5);
			mCenterPaint.setFilterBitmap(true);
		}

		private void setCenterColor(int newColor) {
			final float[] hsv = new float[3];
			Color.colorToHSV(newColor, hsv);
			hsv[2] = lum;
			newColor = Color.HSVToColor(hsv);
			
 			mCenterPaint.setColor(newColor);
		}

		private boolean mTrackingCenter;
		private boolean mHighlightCenter;
		public LumBar lumBar;

		
		protected void onDraw(Canvas canvas) {
			float r = CENTER_X - mPaint.getStrokeWidth() * 0.5f;

			canvas.translate(CENTER_X, CENTER_X);

			canvas.drawOval(new RectF(-r, -r, r, r), mPaint);
			canvas.drawCircle(0, 0, CENTER_RADIUS, mCenterPaint);

			if (mTrackingCenter) {
				int c = mCenterPaint.getColor();
				mCenterPaint.setStyle(Paint.Style.STROKE);

				if (mHighlightCenter) {
					mCenterPaint.setAlpha(0xFF);
				} else {
					mCenterPaint.setAlpha(0x80);
				}
				canvas.drawCircle(0, 0,
						CENTER_RADIUS + mCenterPaint.getStrokeWidth(),
						mCenterPaint);

				mCenterPaint.setStyle(Paint.Style.FILL);
				mCenterPaint.setColor(c);
			}
		}

		
		protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
			setMeasuredDimension(CENTER_X * 2, CENTER_Y * 2);
		}

		private static final int CENTER_X = 100;
		private static final int CENTER_Y = 100;
		private static final int CENTER_RADIUS = 32;

		private int floatToByte(final float x) {
			return java.lang.Math.round(x);
		}

		private int pinToByte(int n) {
			if (n < 0) {
				n = 0;
			} else if (n > 255) {
				n = 255;
			}
			return n;
		}

		private int ave(int s, int d, final float p) {
			return s + java.lang.Math.round(p * (d - s));
		}

		private int interpColor(final int colors[], final float unit) {
			if (unit <= 0) {
				return colors[0];
			}
			if (unit >= 1) {
				return colors[colors.length - 1];
			}

			float p = unit * (colors.length - 1);
			int i = (int) p;
			p -= i;

			// now p is just the fractional part [0...1) and i is the index
			int c0 = colors[i];
			int c1 = colors[i + 1];
			int a = ave(Color.alpha(c0), Color.alpha(c1), p);
			int r = ave(Color.red(c0), Color.red(c1), p);
			int g = ave(Color.green(c0), Color.green(c1), p);
			int b = ave(Color.blue(c0), Color.blue(c1), p);

			return Color.argb(a, r, g, b);
		}

		@SuppressWarnings("unused")
		private int rotateColor(int color, final float rad) {
			float deg = rad * 180 / 3.1415927f;
			int r = Color.red(color);
			int g = Color.green(color);
			int b = Color.blue(color);

			ColorMatrix cm = new ColorMatrix();
			ColorMatrix tmp = new ColorMatrix();

			cm.setRGB2YUV();
			tmp.setRotate(0, deg);
			cm.postConcat(tmp);
			tmp.setYUV2RGB();
			cm.postConcat(tmp);

			final float[] a = cm.getArray();

			int ir = floatToByte(a[0] * r + a[1] * g + a[2] * b);
			int ig = floatToByte(a[5] * r + a[6] * g + a[7] * b);
			int ib = floatToByte(a[10] * r + a[11] * g + a[12] * b);

			return Color.argb(Color.alpha(color), pinToByte(ir), pinToByte(ig),
					pinToByte(ib));
		}

		private static final float PI = 3.1415926f;

		
		public boolean onTouchEvent(MotionEvent event) {
			float x = event.getX() - CENTER_X;
			float y = event.getY() - CENTER_Y;
			boolean inCenter = java.lang.Math.sqrt(x * x + y * y) <= CENTER_RADIUS;

			switch (event.getAction()) {
			case MotionEvent.ACTION_DOWN:
				mTrackingCenter = inCenter;
				if (inCenter) {
					mHighlightCenter = true;
					invalidate();
					break;
				}
			case MotionEvent.ACTION_MOVE:
				if (mTrackingCenter) {
					if (mHighlightCenter != inCenter) {
						mHighlightCenter = inCenter;
						invalidate();
					}
				} else {
					float angle = (float) java.lang.Math.atan2(y, x);
					// need to turn angle [-PI ... PI] into unit [0....1]
					float unit = angle / (2 * PI);
					if (unit < 0) {
						unit += 1;
					}
					
					int newColor = interpColor(mColors, unit);
					
					setCenterColor(newColor);
					lumBar.setColor(newColor);

					invalidate();
				}
				break;
			case MotionEvent.ACTION_UP:
				if (mTrackingCenter) {
					if (inCenter) {
						mListener.colorChanged(mCenterPaint.getColor());
					}
					mTrackingCenter = false; // so we draw w/o halo
					invalidate();
				}
				break;
			}
			return true;
		}

		public void setLum(final float position, int color) {
			lum = position;
			setCenterColor(color);
			invalidate();
		}
	}

	public ColorPickerDialog(Context context, OnColorChangedListener listener,
			int initialColor) {
		super(context);

		mListener = listener;
		mInitialColor = initialColor;
	}

	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		OnColorChangedListener l = new OnColorChangedListener() {
			public void colorChanged(int color) {
				mListener.colorChanged(color);
				dismiss();
			}
		};

		LinearLayout mainLayout = new LinearLayout(getContext());
		mainLayout.setOrientation(LinearLayout.HORIZONTAL);

		final ColorPickerView picker = new ColorPickerView(getContext(), l,
				mInitialColor);

		LinearLayout layout = new LinearLayout(getContext());
		layout.setOrientation(LinearLayout.VERTICAL);
		layout.addView(picker);

		LinearLayout layout2 = new LinearLayout(getContext());

		final Button black = new Button(getContext());
		black.setBackgroundColor(Color.BLACK);
		black.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {
				picker.mCenterPaint.setColor(Color.BLACK);
				picker.invalidate();
			}

		});
		layout2.addView(black);

		final Button darkGray = new Button(getContext());
		darkGray.setBackgroundColor(Color.DKGRAY);
		darkGray.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {
				picker.mCenterPaint.setColor(Color.DKGRAY);
				picker.invalidate();
			}

		});
		layout2.addView(darkGray);

		final Button gray = new Button(getContext());
		gray.setBackgroundColor(Color.GRAY);
		gray.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {
				picker.mCenterPaint.setColor(Color.GRAY);
				picker.invalidate();
			}

		});
		layout2.addView(gray);

		final Button lightGray = new Button(getContext());
		lightGray.setBackgroundColor(Color.LTGRAY);
		lightGray.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {
				picker.mCenterPaint.setColor(Color.LTGRAY);
				picker.invalidate();
			}

		});
		layout2.addView(lightGray);

		final Button white = new Button(getContext());
		white.setBackgroundColor(Color.WHITE);
		white.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {
				picker.mCenterPaint.setColor(Color.WHITE);
				picker.invalidate();
			}

		});
		layout2.addView(white);

		layout.addView(layout2);

		mainLayout.addView(layout);

		lum = new LumBar(getContext(), new OnLumChangeListener() {
			
			public void onLumChanged(View view, float position) {
				picker.setLum(position, lum.color);
			}
		});
		
		lum.color = mInitialColor;
		picker.lumBar = lum;
		lum.setPadding(20, 0, 0, 0);
		mainLayout.addView(lum);

		setTitle(Language.getString("Color"));

		setContentView(mainLayout);
	}
}
