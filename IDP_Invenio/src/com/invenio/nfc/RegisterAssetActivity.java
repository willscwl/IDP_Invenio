package com.invenio.nfc;

import java.util.Random;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class RegisterAssetActivity extends Activity {
	
	private static String registrationId = "";
	
	public static String getRegistrationId(){
		return registrationId;
	}
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		try {
			super.onCreate(savedInstanceState);
			setContentView(R.layout.activity_registerasset);
			getActionBar().setDisplayHomeAsUpEnabled(true);
			
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
    			Intent ourIntent = new Intent(RegisterAssetActivity.this, ourClass);
    			startActivity(ourIntent);
    		}catch(ClassNotFoundException e){
    			e.printStackTrace();
    		}
            return true;
            
        case R.id.menu_about:
            //Toast.makeText(MainMenuActivity.this, "About", 3000).show();
            try{
    			Class ourClass = Class.forName("com.invenio.nfc.AboutActivity");
    			Intent ourIntent = new Intent(RegisterAssetActivity.this, ourClass);
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
			Class ourClass = Class.forName("com.invenio.nfc.ScanRegisterActivity");
			Intent ourIntent = new Intent(RegisterAssetActivity.this, ourClass);
			startActivity(ourIntent);
			
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}
