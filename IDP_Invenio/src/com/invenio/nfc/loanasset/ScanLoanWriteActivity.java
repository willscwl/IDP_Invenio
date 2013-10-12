package com.invenio.nfc.loanasset;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

import android.nfc.FormatException;
import android.nfc.NdefMessage;
import android.nfc.NdefRecord;
import android.nfc.NfcAdapter;
import android.nfc.Tag;
import android.nfc.tech.Ndef;
import android.os.Bundle;
import android.os.Handler;
import android.os.Parcelable;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.invenio.nfc.R;
import com.invenio.nfc.R.id;
import com.invenio.nfc.R.layout;
import com.invenio.nfc.loanasset.LoanItemLogic;
import com.invenio.nfc.nfcscan.NdefMessageParser;
import com.invenio.nfc.nfcscan.record.ParsedNdefRecord;
import com.invenio.nfc.nfcscan.record.TextRecord;
import com.invenio.nfc.registerasset.TagLogic;
import com.invenio.nfc.registerasset.asset.Asset;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

@SuppressLint({ "ParserError", "ParserError" })
public class ScanLoanWriteActivity extends Activity {

	NfcAdapter adapter;
	PendingIntent pendingIntent;
	IntentFilter writeTagFilters[];
	boolean writeMode;
	Tag mytag;
	Context ctx;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_scan);

		ctx=this;
		
		/*
		Button btnWrite = (Button) findViewById(R.id.bWrite);
		final String message = Asset.equipmentTagID;
		
		btnWrite.setOnClickListener(new View.OnClickListener()
		{
			@Override
			public void onClick(View v) {
				try {
					if(mytag==null){
						Toast.makeText(ctx, ctx.getString(R.string.error_detected), Toast.LENGTH_LONG ).show();
					}else{
						write(message,mytag);
						Toast.makeText(ctx, ctx.getString(R.string.ok_writing), Toast.LENGTH_LONG ).show();
					}
				} catch (IOException e) {
					Toast.makeText(ctx, ctx.getString(R.string.error_writing), Toast.LENGTH_LONG ).show();
					e.printStackTrace();
				} catch (FormatException e) {
					Toast.makeText(ctx, ctx.getString(R.string.error_writing) , Toast.LENGTH_LONG ).show();
					e.printStackTrace();
				}
			}
		
		});*/

		adapter = NfcAdapter.getDefaultAdapter(this);
		pendingIntent = PendingIntent.getActivity(this, 0, new Intent(this, getClass()).addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP), 0);
		IntentFilter tagDetected = new IntentFilter(NfcAdapter.ACTION_TAG_DISCOVERED);
		tagDetected.addCategory(Intent.CATEGORY_DEFAULT);
		writeTagFilters = new IntentFilter[] { tagDetected };
		
	}

	private void collateTagInfo(){
		boolean proceed = false;
		byte[] compressed = null;
		try {
			if(mytag==null){
				Toast.makeText(ctx, ctx.getString(R.string.error_detected), Toast.LENGTH_LONG ).show();
			}else{
				//System.out.println(new String(new byte[]{ (byte)0x63 }, "US-ASCII"))
				//String s = new String(compressed);
				
				//------------------------ SPLIT TAG INFO USING \n----------------
				String updatedInfo = "";
				String retrievedInfo = TagLogic.retrievedTagInfo;
				String[]keyValuePairs = retrievedInfo.split("\n");
				for(String kv:keyValuePairs){
					if(kv.contains("Loanee")){
						String temp="";
						String[]keyValueLv2 = kv.split(":");
						temp=keyValueLv2[0]+": "+TagLogic.loaneeName+"";
						updatedInfo+=temp+"\n";
					}else{
						updatedInfo+=kv+"\n";
					}
				}
				
				
				
				
				
				
				//---------------------------------------------------------------
				
				
				
				write(updatedInfo, mytag);
				//System.out.println("Decompressed: " + TagLogic.decompress(compressed));
				Toast.makeText(ctx, ctx.getString(R.string.ok_writing), Toast.LENGTH_LONG ).show();
				
				final Handler handler = new Handler();
				handler.postDelayed(new Runnable() {
				  @Override
				  public void run() {
				    //Do something after 100ms
					  AlertDialog.Builder alertDialog2 = new AlertDialog.Builder(
								ScanLoanWriteActivity.this);

						// Setting Dialog Title
						alertDialog2
								.setTitle("Loan Complete");

						// add message
						alertDialog2
								.setMessage("Loan has been made successfully!");

						

						alertDialog2
								.setPositiveButton(
										"OK",
										new DialogInterface.OnClickListener() {

											@Override
											public void onClick(
													DialogInterface dialog,
													int which) {
												Class ourClass;
												try {
													ourClass = Class
															.forName("com.invenio.nfc.MainMenuActivity");
													Intent ourIntent = new Intent(ScanLoanWriteActivity.this, ourClass);
													ScanLoanWriteActivity.this
															.startActivity(ourIntent);
												} catch (ClassNotFoundException e) {
													// TODO
													// Auto-generated
													// catch block
													e.printStackTrace();
												}

											}

										});
						alertDialog2.show();
				  }
				}, 5000);
				
			}
		} catch (IOException e) {
			Toast.makeText(ctx, ctx.getString(R.string.error_writing), Toast.LENGTH_LONG ).show();
			e.printStackTrace();
		} catch (FormatException e) {
			Toast.makeText(ctx, ctx.getString(R.string.error_writing) , Toast.LENGTH_LONG ).show();
			e.printStackTrace();
		}
	}
	private void write(String text, Tag tag) throws IOException, FormatException {

		NdefRecord[] records = { createRecord(text) };
		NdefMessage  message = new NdefMessage(records);
		// Get an instance of Ndef for the tag.
		Ndef ndef = Ndef.get(tag);
		// Enable I/O
		ndef.connect();
		// Write the message
		ndef.writeNdefMessage(message);
		// Close the connection
		ndef.close();
	}



	private NdefRecord createRecord(String text) throws UnsupportedEncodingException {
		String lang       = "en";
		byte[] textBytes  = text.getBytes();
		byte[] langBytes  = lang.getBytes("US-ASCII");
		int    langLength = langBytes.length;
		int    textLength = textBytes.length;
		byte[] payload    = new byte[1 + langLength + textLength];

		// set status byte (see NDEF spec for actual bits)
		payload[0] = (byte) langLength;

		// copy langbytes and textbytes into payload
		System.arraycopy(langBytes, 0, payload, 1,              langLength);
		System.arraycopy(textBytes, 0, payload, 1 + langLength, textLength);

		NdefRecord recordNFC = new NdefRecord(NdefRecord.TNF_WELL_KNOWN,  NdefRecord.RTD_TEXT,  new byte[0], payload);

		return recordNFC;
	}


	@Override
	protected void onNewIntent(Intent intent){
		if(NfcAdapter.ACTION_TAG_DISCOVERED.equals(intent.getAction())){
			mytag = intent.getParcelableExtra(NfcAdapter.EXTRA_TAG);    
			Toast.makeText(this, "Tag detected. Stay where you are. Writing in progress..." , Toast.LENGTH_LONG ).show();
			
			//write to tag
			collateTagInfo();
			
			
			
		}
	}
	
	@Override
	public void onPause(){
		super.onPause();
		WriteModeOff();
	}

	@Override
	public void onResume(){
		super.onResume();
		WriteModeOn();
	}

	private void WriteModeOn(){
		writeMode = true;
		adapter.enableForegroundDispatch(this, pendingIntent, writeTagFilters, null);
	}

	private void WriteModeOff(){
		writeMode = false;
		adapter.disableForegroundDispatch(this);
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
				Intent ourIntent = new Intent(ScanLoanWriteActivity.this,
						ourClass);
				startActivity(ourIntent);
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
			return true;

		case R.id.menu_about:
			// Toast.makeText(MainMenuActivity.this, "About", 3000).show();
			try {
				Class ourClass = Class.forName("com.invenio.nfc.AboutActivity");
				Intent ourIntent = new Intent(ScanLoanWriteActivity.this,
						ourClass);
				startActivity(ourIntent);
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
			return true;

		default:
			return super.onOptionsItemSelected(item);
		}
	}
	

	
	
}
