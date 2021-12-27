package com.steema.teechart.android.editors;

import com.steema.teechart.Wall;
import com.steema.teechart.Walls;
import com.steema.teechart.android.editors.LabelSeekBar.OnLabelSeekListener;
import com.steema.teechart.languages.Language;

import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.CompoundButton.OnCheckedChangeListener;

public class WallsEditor extends Dialog {

	private Spinner wall;
	private Walls walls;
	private CheckBox visibleWall;
	private CheckBox autoHide;
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public WallsEditor(final Context context, final Walls walls) {
		super(context);

		this.walls = walls;
		
		setTitle(Language.getString("Walls"));

		LinearLayout layout = new LinearLayout(context);
		layout.setOrientation(LinearLayout.VERTICAL);
		layout.setVerticalScrollBarEnabled(true);
		final CheckBox visible = new CheckBox(context);
		visible.setText(Language.getString("Visible"));
		visible.setChecked(walls.getVisible());

		visible.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			public void onCheckedChanged(CompoundButton arg0, boolean arg1) {
				walls.setVisible(arg1);
			}
		});

		layout.addView(visible);

		final String[] wallsList = { 
				Language.getString("Left"), 
				Language.getString("Bottom"),
				Language.getString("Back"),
				Language.getString("Right")
				};
		
 		wall = new Spinner(context);
		wall.setAdapter(new ArrayAdapter(context,
			             android.R.layout.simple_spinner_item, wallsList));
		wall.setOnItemSelectedListener(new OnItemSelectedListener() {

			public void onItemSelected(AdapterView<?> arg0, View arg1,
					int arg2, long arg3) {
				setWall(currentWall());
			}

			public void onNothingSelected(AdapterView<?> arg0) {
			}
		});
		layout.addView(wall);

		visibleWall = new CheckBox(context);
		visibleWall.setText(Language.getString("Visible"));

		visibleWall.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			public void onCheckedChanged(CompoundButton arg0, boolean arg1) {
				currentWall().setVisible(arg1);
			}
		});

		layout.addView(visibleWall);

		autoHide = new CheckBox(context);
		autoHide.setText(Language.getString("Auto Hide"));

		autoHide.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			public void onCheckedChanged(CompoundButton arg0, boolean arg1) {
				currentWall().setAutoHide(arg1);
			}
		});

		layout.addView(autoHide);

		final LabelSeekBar size = new LabelSeekBar(context, "Size", 0, 100, 
				currentWall().getSize(), new OnLabelSeekListener() {
					
					public void onProgressChanged(LabelSeekBar bar, int progress) {
						currentWall().setSize(progress);
					}
				});
		
		layout.addView(size);
		
		final Button edit = new Button(context);
		edit.setText(Language.getString("Format"));
		edit.setOnClickListener(new View.OnClickListener() {
			
			public void onClick(View v) {
				ShapeEditor edit = new ShapeEditor(context, currentWall(), null);
				edit.show();
			}
		});
		layout.addView(edit);
		
		setWall(walls.getLeft());
		
		setContentView(layout);
		
	}

	protected void setWall(Wall wall) {
		visibleWall.setChecked(wall.getVisible());
		autoHide.setChecked(wall.getAutoHide());
	}

	protected Wall currentWall() {
		switch (wall.getSelectedItemPosition()) {
		case 0: return walls.getLeft();
		case 1: return walls.getBottom();
		case 2: return walls.getBack();
		default : 
			return walls.getRight();
		}
	}

}
