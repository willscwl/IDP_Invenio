package com.invenio.nfc.registerasset;

import com.invenio.nfc.R;
import com.invenio.nfc.registerasset.asset.Asset;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
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

public class DetailedInfoFragment extends Fragment {

	// variables to save user selected date and time
	public int year, month, day, hour, minute;
	// declare the variables to Show/Set the date and time when Time and Date
	// Picker Dialog first appears
	public EditText top;
	private int mYear, mMonth, mDay, mHour, mMinute;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = getActivity().getLayoutInflater().inflate(
				R.layout.fragment_register_detailed_info, null);

		final EditText aloc = (EditText) view.findViewById(R.id.aloc);
		top = (EditText) view.findViewById(R.id.tp);
		final EditText re = (EditText) view.findViewById(R.id.re);
		final EditText hd = (EditText) view.findViewById(R.id.hd);
		final EditText rem = (EditText) view.findViewById(R.id.rem);

		final Calendar c = Calendar.getInstance();
		mYear = c.get(Calendar.YEAR);
		mMonth = c.get(Calendar.MONTH);
		mDay = c.get(Calendar.DAY_OF_MONTH);

		// Assigned Location
		aloc.addTextChangedListener(new TextWatcher() {
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
				Asset.location = aloc.getText().toString();
			}
		});

		// Related Equipment
		re.addTextChangedListener(new TextWatcher() {
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
				Asset.relatedEquipment = re.getText().toString();
			}
		});

		// Hardware Description
		hd.addTextChangedListener(new TextWatcher() {
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
				Asset.hardwareDesc = hd.getText().toString();
			}
		});

		// Remarks
		rem.addTextChangedListener(new TextWatcher() {
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
				Asset.remarks = rem.getText().toString();
			}
		});

		top.setClickable(true);
		top.setOnClickListener(new View.OnClickListener() {

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
				String d = String.valueOf(year + "0" + month);
				top.setText(d);
				Asset.timeOfPurchase = top.getText().toString();
			} else {
				String d = String.valueOf(year + "" + month);
				top.setText(d);
				Asset.timeOfPurchase = top.getText().toString();
			}

		}
	};
}
