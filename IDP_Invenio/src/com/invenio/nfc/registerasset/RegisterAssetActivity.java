package com.invenio.nfc.registerasset;

import java.io.IOException;

import com.invenio.nfc.R;
import com.invenio.nfc.registerasset.TabsPagerAdapter;
import com.invenio.nfc.registerasset.asset.Asset;

import android.app.ActionBar;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.app.ActionBar.Tab;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.DialogInterface;
import android.content.Intent;
import android.nfc.NfcAdapter;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnFocusChangeListener;
import android.view.WindowManager;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class RegisterAssetActivity extends FragmentActivity implements
		ActionBar.TabListener {

	private ViewPager viewPager;
	private TabsPagerAdapter mAdapter;
	private ActionBar actionBar;
	private EditText lcd;
	private EditText equipmentTagId;
	private EditText manuName;
	private EditText model;
	private EditText manuSerial;
	private EditText internalId;
	private EditText loc;
	private EditText timeOfPur;
	private EditText rEquip;
	private EditText hd;
	private EditText rem;
	private EditText pic;
	private EditText ci;
	private EditText liu;
	private EditText liut;
	private boolean lcdSelected;
	private boolean topSelected;
	
	static final int DATE_DIALOG_ID = 0;
	static final int TIME_DIALOG_ID = 1;
	// variables to save user selected date and time
	public int year, month, day, hour, minute;
	// declare the variables to Show/Set the date and time when Time and Date
	// Picker Dialog first appears
	private int mYear, mMonth, mDay, mHour, mMinute;

	// Tab titles
	private String[] tabs = { "Basic Info (1/3)", "Details (2/3)",
			"Contact (3/3)" };

	private static String registrationId = "";
	private NfcAdapter mAdapter1;

	public static String getRegistrationId() {
		return registrationId;
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		// Assign current Date and Time Values to Variables
		final Calendar c = Calendar.getInstance();
		lcdSelected = false;
		topSelected = false;
		mYear = c.get(Calendar.YEAR);
		mMonth = c.get(Calendar.MONTH);
		mDay = c.get(Calendar.DAY_OF_MONTH);
		mHour = c.get(Calendar.HOUR_OF_DAY);
		mMinute = c.get(Calendar.MINUTE);
		setContentView(R.layout.activity_registermain);
		getActionBar().setDisplayHomeAsUpEnabled(true);

		// Check for NFC settings
		mAdapter1 = NfcAdapter.getDefaultAdapter(this);
		if (mAdapter1 == null) {
			// showMessage("test", "test");
			finish();
			return;
		}

		// Initilization
		viewPager = (ViewPager) findViewById(R.id.pager);
		actionBar = getActionBar();
		mAdapter = new TabsPagerAdapter(getSupportFragmentManager());
		
		viewPager.setAdapter(mAdapter);
		actionBar.setHomeButtonEnabled(false);
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
		
		// Adding Tabs
		for (String tab_name : tabs) {
			actionBar.addTab(actionBar.newTab().setText(tab_name)
					.setTabListener(this));
		}

		/**
		 * on swiping the viewpager make respective tab selected
		 * */
		viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {

			@Override
			public void onPageSelected(int position) {
				// on changing the page
				// make respected tab selected
				actionBar.setSelectedNavigationItem(position);

				try {
					equipmentTagId = (EditText) findViewById(R.id.tid);
					manuName = (EditText) findViewById(R.id.mn);
					model = (EditText) findViewById(R.id.mod);
					manuSerial = (EditText) findViewById(R.id.msn);
					internalId = (EditText) findViewById(R.id.ina);
					loc = (EditText) findViewById(R.id.aloc);
					timeOfPur = (EditText) findViewById(R.id.tp);
					rEquip = (EditText) findViewById(R.id.re);
					hd = (EditText) findViewById(R.id.hd);
					rem = (EditText) findViewById(R.id.rem);
					pic = (EditText) findViewById(R.id.pic);
					ci = (EditText) findViewById(R.id.ci);
					liu = (EditText) findViewById(R.id.liu);
					liut = (EditText) findViewById(R.id.liut);
					lcd = (EditText) findViewById(R.id.lcd);
					
					SimpleDateFormat s = new SimpleDateFormat("yyyyMMddhhmm");
					liut.setText(s.format(new Date()));
					
					lcd.setClickable(true);
					lcd.setOnClickListener(new View.OnClickListener() {
						
						public void onClick(View v) {
							// Show the DatePickerDialog
							topSelected = false;
							lcdSelected = true;
							showDialog(DATE_DIALOG_ID);
						}
					});
					
					timeOfPur.setClickable(true);
					timeOfPur.setOnClickListener(new View.OnClickListener() {
						
						public void onClick(View v) {
							// Show the DatePickerDialog
							
							lcdSelected = false;
							topSelected = true;
							showDialog(DATE_DIALOG_ID);
						}
					});
					
					
					Asset.equipmentTagID = equipmentTagId.getText().toString();
					/*Asset.manufacturerName = manuName.getText().toString();
					Asset.modelNo = model.getText().toString();
					Asset.manufacturerSerialNo = manuSerial.getText()
							.toString();
					Asset.internalID = internalId.getText().toString();
					Asset.location = loc.getText().toString();
					Asset.timeOfPurchase = timeOfPur.getText().toString();
					Asset.relatedEquipment = rEquip.getText().toString();
					Asset.hardwareDesc = hd.getText().toString();
					Asset.remarks = rem.getText().toString();
					Asset.personInCharge = pic.getText().toString();
					Asset.contactInfo = ci.getText().toString();
					Asset.lastUserUpdate = liu.getText().toString();
					Asset.lastUpdateTimeStamp = liut.getText().toString();
					Asset.lastCalibrationDate = lcd.getText().toString();
*/
					// convert to JSON
					// String json = {"equipmentTagId":equipmentTagId,"m":"WP"};

				} catch (Exception e) {
					e.printStackTrace();
				}
			}

			@Override
			public void onPageScrolled(int arg0, float arg1, int arg2) {
			}

			@Override
			public void onPageScrollStateChanged(int arg0) {

			}
		});

	}

	@Override
	public void onTabReselected(Tab tab, FragmentTransaction ft) {
	}

	@Override
	public void onTabSelected(Tab tab, FragmentTransaction ft) {
		// on tab selected
		// show respected fragment view
		viewPager.setCurrentItem(tab.getPosition());
	}

	@Override
	public void onTabUnselected(Tab tab, FragmentTransaction ft) {

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater menuInflater = getMenuInflater();
		menuInflater.inflate(R.layout.menu, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {

		switch (item.getItemId()) {
		case android.R.id.home:
			onBackPressed();
			return true;

		case R.id.menu_main:
			// Toast.makeText(MainMenuActivity.this, "Main Menu", 3000).show();
			try {
				Class ourClass = Class
						.forName("com.invenio.nfc.MainMenuActivity");
				Intent ourIntent = new Intent(this, ourClass);
				startActivity(ourIntent);
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
			return true;

		case R.id.menu_about:
			// Toast.makeText(MainMenuActivity.this, "About", 3000).show();
			try {
				Class ourClass = Class.forName("com.invenio.nfc.AboutActivity");
				Intent ourIntent = new Intent(this, ourClass);
				startActivity(ourIntent);
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
			return true;

		default:
			return super.onOptionsItemSelected(item);
		}
	}

	protected void onResume() {
		super.onResume();
		if (mAdapter1 != null) {
			if (!mAdapter1.isEnabled()) {
				showWirelessSettingsDialog();
			}

		}
	}

	public void onClickRegisterNext(View v) {
		boolean proceed = false;
		try {

			if (!(Asset.equipmentTagID).trim().equals("")) {
				/*
				 * && Asset.manufacturerName != "" } && Asset.modelNo != "" &&
				 * Asset.manufacturerSerialNo != "" && Asset.internalID != "" &&
				 * Asset.location != "" && Asset.timeOfPurchase != "" &&
				 * Asset.relatedEquipment != "" && Asset.hardwareDesc != "" &&
				 * Asset.remarks != "" && Asset.personInCharge != "" &&
				 * Asset.contactInfo != "" && Asset.lastUserUpdate != "" &&
				 * Asset.lastUpdateTimeStamp != "" && Asset.lastCalibrationDate
				 * != "")
				 */

				// Toast.makeText(this, Asset.equipmentTagID, 3000).show();
				// ------------------- Store info into proper format for writing
				// to tag----------------
				TagLogic.clearMap();

				// add to hashmap
				TagLogic.putIn("Equipment Tag ID", Asset.equipmentTagID);
				TagLogic.putIn("Manufacturer Name", Asset.manufacturerName);
				TagLogic.putIn("Model No", Asset.modelNo);
				TagLogic.putIn("Loanee", "");
				// TagLogic.putIn("Hardware Description", Asset.hardwareDesc);
				// TagLogic.putIn("Manufacturer S/N",
				// Asset.manufacturerSerialNo);
				// TagLogic.putIn("Internal Equipment ID", Asset.internalID);
				// TagLogic.putIn("Assigned Location", Asset.location);
				// TagLogic.putIn("Time of Purchase", Asset.timeOfPurchase);
				// TagLogic.putIn("Related Equipment", Asset.relatedEquipment);
				// TagLogic.putIn("Remarks", Asset.remarks);
				// TagLogic.putIn("Person In-Charge", Asset.personInCharge);
				// TagLogic.putIn("Contact Info", Asset.contactInfo);
				// TagLogic.putIn("Last Info Updated By", Asset.lastUserUpdate);
				// TagLogic.putIn("Last Info Updated Date & Time",
				// Asset.lastUpdateTimeStamp);
				// TagLogic.putIn("Last Calibration Date",
				// Asset.lastCalibrationDate);

				TagLogic.formatTagInfo();
				// System.out.println(TagLogic.formattedTagInfo);
				// -----------------------------------------------------------------------------------

				Class ourClass = Class
						.forName("com.invenio.nfc.registerasset.ScanRegisterActivity");
				Intent ourIntent = new Intent(RegisterAssetActivity.this,
						ourClass);
				startActivity(ourIntent);
			} else {
				Toast.makeText(this, "Please fill in all info", 3000).show();
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void showWirelessSettingsDialog() {
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setCancelable(false);
		builder.setMessage("NFC is disabled. Click OK to go to the wireless settings to enable it.");
		builder.setPositiveButton(android.R.string.ok,
				new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialogInterface, int i) {
						Intent intent = new Intent(
								Settings.ACTION_WIRELESS_SETTINGS);
						startActivity(intent);
					}
				});
		builder.setNegativeButton(android.R.string.cancel,
				new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialogInterface, int i) {
						finish();
					}
				});
		builder.create().show();

		// return;
	}
	
	// Register DatePickerDialog listener
		private DatePickerDialog.OnDateSetListener mDateSetListener = new DatePickerDialog.OnDateSetListener() {
			// the callback received when the user "sets" the Date in the
			// DatePickerDialog
			public void onDateSet(DatePicker view, int yearSelected,
					int monthOfYear, int dayOfMonth) {
				year = yearSelected;
				month = monthOfYear + 1;
				day = dayOfMonth;
				
				//if timeOfPur is selected
				if(topSelected){
					// Set the Selected Date in Select date Button
					if(String.valueOf(month).length()==1){
						timeOfPur.setText(year+ "0" + month);
					}else{
						timeOfPur.setText(year+ "" + month);
					}
				}else{
					if(String.valueOf(month).length()==1){
						lcd.setText(year+ "0" + month + "" + day);
					}else{
						lcd.setText(year+ "" + month + "" + day);
					}
				}
				
			}
		};
		
		// Method automatically gets Called when you call showDialog() method
		@Override
		protected Dialog onCreateDialog(int id) {
				// create a new DatePickerDialog with values you want to show
				return new DatePickerDialog(this, mDateSetListener, mYear, mMonth,
						mDay);
				// create a new TimePickerDialog with values you want to show
		}

}
