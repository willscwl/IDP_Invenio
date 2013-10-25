package com.invenio.nfc.registerasset;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import com.invenio.nfc.R;
import com.invenio.nfc.registerasset.asset.Asset;

import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.DatePicker;
import android.widget.TimePicker;

public class ContactInfoFragment extends Fragment {

	public static EditText lcd;
	private int mYear, mMonth, mDay, mHour, mMinute;
	public int year, month, day, hour, minute;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = getActivity().getLayoutInflater().inflate(
				R.layout.fragment_register_contact_info, null);

		final EditText pic = (EditText) view.findViewById(R.id.pic);
		final EditText ci = (EditText) view.findViewById(R.id.ci);
		final EditText liu = (EditText) view.findViewById(R.id.liu);
		final EditText liut = (EditText) view.findViewById(R.id.liut);
		lcd = (EditText) view.findViewById(R.id.lcd);

		final Calendar c = Calendar.getInstance();
		mYear = c.get(Calendar.YEAR);
		mMonth = c.get(Calendar.MONTH);
		mDay = c.get(Calendar.DAY_OF_MONTH);

		// Person in charge
		pic.addTextChangedListener(new TextWatcher() {
			@Override
			public void onTextChanged(CharSequence s, int start, int before,
					int count) {
			}

			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
				// TODO Auto-generated method stub
			}

			@Override
			public void afterTextChanged(Editable s) {
				// TODO Auto-generated method stub
				Asset.personInCharge = pic.getText().toString();
			}
		});

		// Contact info
		ci.addTextChangedListener(new TextWatcher() {
			@Override
			public void onTextChanged(CharSequence s, int start, int before,
					int count) {
			}

			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
				// TODO Auto-generated method stub
			}

			@Override
			public void afterTextChanged(Editable s) {
				// TODO Auto-generated method stub
				Asset.contactInfo = ci.getText().toString();
			}
		});

		// Last info updated by which user?
		liu.addTextChangedListener(new TextWatcher() {
			@Override
			public void onTextChanged(CharSequence s, int start, int before,
					int count) {
			}

			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
				// TODO Auto-generated method stub
			}

			@Override
			public void afterTextChanged(Editable s) {
				// TODO Auto-generated method stub
				Asset.lastUserUpdate = liu.getText().toString();
			}
		});

		// Last info update timestamp
		SimpleDateFormat s = new SimpleDateFormat("yyyyMMddHHmm");
		liut.setText(s.format(new Date()));
		Asset.lastUpdateTimeStamp = liut.getText().toString();
		
		//Last Calibration Date
		lcd.setClickable(true);
		lcd.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {
				// Show the DatePickerDialog
				DatePickerDialog dpdFromDate = new DatePickerDialog(
						getActivity(), mDateSetListener, mYear, mMonth, mDay);
				dpdFromDate.show();
				dpdFromDate.setButton(DialogInterface.BUTTON_NEGATIVE,
						"Cancel", new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog,
									int which) {
								if (which == DialogInterface.BUTTON_NEGATIVE) {
								}
							}
						});
			}
		});

		return view;
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

			if (String.valueOf(month).length() == 1) {
				String d = String.valueOf(year + "0" + month + "" + day);
				lcd.setText(d);
				Asset.lastCalibrationDate = lcd.getText().toString();
			} else {
				String d = String.valueOf(year + "" + month + "" + day);
				lcd.setText(d);
				Asset.lastCalibrationDate = lcd.getText().toString();
			}

		}
	};
}
