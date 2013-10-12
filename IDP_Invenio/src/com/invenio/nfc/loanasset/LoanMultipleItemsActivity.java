package com.invenio.nfc.loanasset;

import com.invenio.nfc.*;
import com.invenio.nfc.R.id;
import com.invenio.nfc.R.layout;

import android.os.Bundle;
import android.os.Handler;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.invenio.nfc.nfcscan.NdefMessageParser;
import com.invenio.nfc.nfcscan.record.ParsedNdefRecord;
import com.invenio.nfc.nfcscan.record.TextRecord;
import com.invenio.nfc.registerasset.ScanRegisterActivity;
import com.invenio.nfc.registerasset.TagLogic;
import com.invenio.nfc.R;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.IntentFilter;
import android.nfc.NdefMessage;
import android.nfc.NdefRecord;
import android.nfc.NfcAdapter;
import android.os.Bundle;
import android.os.Parcelable;
import android.provider.Settings;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.TextView;

public class LoanMultipleItemsActivity extends Activity {
    private static final DateFormat TIME_FORMAT = SimpleDateFormat.getDateTimeInstance();
	private LinearLayout mTagContent;
    private NfcAdapter mAdapter;
    private PendingIntent mPendingIntent;
    private NdefMessage mNdefPushMessage;
    
	protected void onCreate(Bundle savedInstanceState) {
		try {
			super.onCreate(savedInstanceState);
			setContentView(R.layout.activity_scan);
			mTagContent = (LinearLayout) findViewById(R.id.linearone);
			getActionBar().setDisplayHomeAsUpEnabled(true);
			resolveIntent(getIntent());
	        mAdapter = NfcAdapter.getDefaultAdapter(this);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void resolveIntent(Intent intent) {
        String action = intent.getAction();
        if (NfcAdapter.ACTION_TAG_DISCOVERED.equals(action)
                || NfcAdapter.ACTION_TECH_DISCOVERED.equals(action)
                || NfcAdapter.ACTION_NDEF_DISCOVERED.equals(action)) {
        	
        	Parcelable[] rawMsgs = intent.getParcelableArrayExtra(NfcAdapter.EXTRA_NDEF_MESSAGES);
        	
        	/*
        	//give me message in records
        	List<ParsedNdefRecord> recList = NdefMessageParser.parse((NdefMessage)rawMsgs[0]);
        	for(int i=0;i<recList.size();i++){
        		System.out.println(123);
        		System.out.println(recList.get(i));
        	}
        	*/
        	
        	NdefMessage[] msgs = null;
            if (rawMsgs != null) {
                msgs = new NdefMessage[rawMsgs.length];
                for (int i = 0; i < rawMsgs.length; i++) {
                    msgs[i] = (NdefMessage) rawMsgs[i];
                }
            }else{
            	Toast.makeText(this, "Empty tag", 3000).show();

            	//empty tag.. ask user to overwrite
            }
        	
            buildTagViews(msgs);
        }
    }
    
    @Override
    public void onNewIntent(Intent intent) {
    	setIntent(intent);
        resolveIntent(intent);
    }
    
	@Override
	public void onResume() {
    	super.onResume();
    	PendingIntent intent = PendingIntent.getActivity(this, 0, new Intent(this, 
    	getClass()).addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP), 0);
    	NfcAdapter.getDefaultAdapter(this).enableForegroundDispatch(this,intent,null,null);
	} 
	
	void buildTagViews(NdefMessage[] msgs) {

        LayoutInflater inflater = LayoutInflater.from(this);
        LinearLayout content = mTagContent;
        //TextView t1 = (TextView) findViewById(R.id.textView1);
        Date now = new Date();
        String loaneeName = "";
        
        if (msgs == null || msgs.length == 0) {
        	showLoanDialog("Empty Tag");
        }else{
        	
        	try{
        		List<ParsedNdefRecord> records = NdefMessageParser.parse(msgs[0]);
        		final int size = records.size();
        		for (int i = 0; i < size; i++) {
	   
        			// add items to list
        			TextRecord record = (TextRecord) records.get(i);
        			//showLoanDialog(record.getText().toString());
        			LoanItemLogic l = new LoanItemLogic(this);
        			l.createLoanItemAlertDialog("Confirm Loan", "MainMenuActivity", record.getText().toString());
        			
        			String blah = record.getText().toString(); //info read from the tag
        			TagLogic.retrievedTagInfo = blah;
        		
        			
        		}
        	}catch(Exception e){
        		e.printStackTrace();
        	}
        }
            
	}
	
	
	
	
public void showLoanDialog(String record){
	//------------------- show alert dialog ---------------
	AlertDialog.Builder alertDialog = new AlertDialog.Builder(
			LoanMultipleItemsActivity.this);
	alertDialog.setCancelable(false);
	// Setting Dialog Title
	alertDialog.setTitle("Overwrite Tag?");

	alertDialog.setMessage(record);

	// Setting Positive "Yes" Button
	alertDialog.setPositiveButton("Yes",
			new DialogInterface.OnClickListener() {

				@Override
				public void onClick(DialogInterface dialog,
						int which) {
					
					//---------------- Insert code to send registration details to server ----------------
					
					
					
					
					
					
					
					//----------------------------------------------------------------------------
					
					//---------------------- Code to overwrite tag------------------------------
					Class ourClass;
					try {
						ourClass = Class
								.forName("com.invenio.nfc.registerasset.ScanRegisterWriteActivity");
						Intent ourIntent = new Intent(
								LoanMultipleItemsActivity.this,
								ourClass);
						LoanMultipleItemsActivity.this
								.startActivity(ourIntent);
					} catch (ClassNotFoundException e) {
						// TODO
						// Auto-generated
						// catch block
						e.printStackTrace();
					}
					//--------------------------------------------------------------------------
				
				}
			});

	alertDialog.setNegativeButton("No",
			new DialogInterface.OnClickListener() {

				@Override
				public void onClick(DialogInterface dialog,
						int which) {
					// User pressed cancel
					try {
						Toast.makeText(
								LoanMultipleItemsActivity.this,
								"Registration Cancelled!", 3000)
								.show();
						Class ourClass = Class
								.forName("com.invenio.nfc.MainMenuActivity");
						Intent ourIntent = new Intent(
								LoanMultipleItemsActivity.this,
								ourClass);
						LoanMultipleItemsActivity.this
								.startActivity(ourIntent);

					} catch (ClassNotFoundException e) {
						e.printStackTrace();
					}
				}

			});
	// Showing Alert Message
	alertDialog.show();

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
				Intent ourIntent = new Intent(LoanMultipleItemsActivity.this,
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
				Intent ourIntent = new Intent(LoanMultipleItemsActivity.this,
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
