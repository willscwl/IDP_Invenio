package com.invenio.nfc.registerasset;

import com.invenio.nfc.R;
import com.invenio.nfc.registerasset.asset.Asset;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.EditText;

public class BasicInfoFragment extends Fragment {

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = getActivity().getLayoutInflater().inflate(
				R.layout.fragment_register_basic_info, null);
		final EditText tid = (EditText) view.findViewById(R.id.tid);
		final EditText mn = (EditText) view.findViewById(R.id.mn);
		final EditText model = (EditText) view.findViewById(R.id.mod);
		final EditText msn = (EditText) view.findViewById(R.id.msn);
		final EditText aid = (EditText) view.findViewById(R.id.ina);
		
		//Equipment Tag ID
		tid.addTextChangedListener(new TextWatcher() {
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
				Asset.equipmentTagID = tid.getText().toString();
			}
		});
		
		//Manufacturer Name
		mn.addTextChangedListener(new TextWatcher() {
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
				Asset.manufacturerName = mn.getText().toString();
			}
		});
		
		//Model
		model.addTextChangedListener(new TextWatcher() {
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
				Asset.modelNo = model.getText().toString();
			}
		});
		
		//Manufacturer Serial No
		msn.addTextChangedListener(new TextWatcher() {
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
				Asset.manufacturerSerialNo = msn.getText().toString();
			}
		});
		
		//Internal Asset ID
		aid.addTextChangedListener(new TextWatcher() {
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
				Asset.internalID = aid.getText().toString();
			}
		});
		return view;
	}
	
	
}
