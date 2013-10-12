package com.invenio.nfc.registerasset;

import java.util.Random;

import com.invenio.nfc.R;
import com.invenio.nfc.R.id;
import com.invenio.nfc.R.layout;

import android.nfc.NfcAdapter;
import android.os.Bundle;
import android.provider.Settings;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class RegisterBackup extends Activity {
	
	private static String registrationId = "";
	private NfcAdapter mAdapter;
	
	public static String getRegistrationId(){
		return registrationId;
	}
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		try {
			super.onCreate(savedInstanceState);
			setContentView(R.layout.activity_registerasset);
			getActionBar().setDisplayHomeAsUpEnabled(true);
			
			//Check for NFC settings 
			mAdapter = NfcAdapter.getDefaultAdapter(this);
		        if (mAdapter == null) {
		            //showMessage("test", "test");
		            finish();
		            return;
		        }
			
			//update asset id
			Random rand = new Random();
			int randNo = rand.nextInt(1000) + 1;
			TextView aId = (TextView) findViewById(R.id.textView2);
			aId.setText(String.valueOf(randNo));
			registrationId = aId.getText().toString();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater menuInflater = getMenuInflater();
		menuInflater.inflate(R.layout.menu, menu);
		return true;
	}

	@Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
       
        switch (item.getItemId())
        {
        case android.R.id.home:
        	onBackPressed();
    	    return true;
        
        case R.id.menu_main:
            //Toast.makeText(MainMenuActivity.this, "Main Menu", 3000).show();
            try{
    			Class ourClass = Class.forName("com.invenio.nfc.MainMenuActivity");
    			Intent ourIntent = new Intent(RegisterBackup.this, ourClass);
    			startActivity(ourIntent);
    		}catch(ClassNotFoundException e){
    			e.printStackTrace();
    		}
            return true;
            
        case R.id.menu_about:
            //Toast.makeText(MainMenuActivity.this, "About", 3000).show();
            try{
    			Class ourClass = Class.forName("com.invenio.nfc.AboutActivity");
    			Intent ourIntent = new Intent(RegisterBackup.this, ourClass);
    			startActivity(ourIntent);
    		}catch(ClassNotFoundException e){
    			e.printStackTrace();
    		}
            return true;
			
        default:
            return super.onOptionsItemSelected(item);
        }
    }
	
	public void onClickRegisterNext(View v){
		try{
			Class ourClass = Class.forName("com.invenio.nfc.registerasset.ScanRegisterActivity");
			Intent ourIntent = new Intent(RegisterBackup.this, ourClass);
			startActivity(ourIntent);
			
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	protected void onResume() {
        super.onResume();
        if (mAdapter != null) {
            if (!mAdapter.isEnabled()) {
                showWirelessSettingsDialog();
            }

        }
    }
	
	private void showWirelessSettingsDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(false);
        builder.setMessage("NFC is disabled. Click OK to go to the wireless settings to enable it.");
        builder.setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
                Intent intent = new Intent(Settings.ACTION_WIRELESS_SETTINGS);
                startActivity(intent);
            }
        });
        builder.setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
                finish();
            }
        });
        builder.create().show();
        
        //return;
    }
}
