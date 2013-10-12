package com.invenio.nfc.registerasset;

import java.util.Random;

import com.invenio.nfc.R;
import com.invenio.nfc.R.id;
import com.invenio.nfc.R.layout;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class RegisterSuccessActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		try {
			super.onCreate(savedInstanceState);
			setContentView(R.layout.activity_registersuccess);
			//getActionBar().setDisplayHomeAsUpEnabled(true);
			
			//update asset id
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	
	public void onClickRegisterSimilar(View v){
		try{
			Class ourClass = Class.forName("com.invenio.nfc.RegisterAssetActivity");
			Intent ourIntent = new Intent(RegisterSuccessActivity.this, ourClass);
			startActivity(ourIntent);
			
			
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public void onClickRegisterNew(View v){
		try{
			Class ourClass = Class.forName("com.invenio.nfc.RegisterAssetActivity");
			Intent ourIntent = new Intent(RegisterSuccessActivity.this, ourClass);
			startActivity(ourIntent);
			
			
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public void onClickConfirmCancel(View v){
		try{
			Class ourClass = Class.forName("com.invenio.nfc.MainMenuActivity");
			Intent ourIntent = new Intent(RegisterSuccessActivity.this, ourClass);
			startActivity(ourIntent);
			
			
		}catch(Exception e){
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
    			Intent ourIntent = new Intent(RegisterSuccessActivity.this, ourClass);
    			startActivity(ourIntent);
    		}catch(ClassNotFoundException e){
    			e.printStackTrace();
    		}
            return true;
            
        case R.id.menu_about:
            //Toast.makeText(MainMenuActivity.this, "About", 3000).show();
            try{
    			Class ourClass = Class.forName("com.invenio.nfc.AboutActivity");
    			Intent ourIntent = new Intent(RegisterSuccessActivity.this, ourClass);
    			startActivity(ourIntent);
    		}catch(ClassNotFoundException e){
    			e.printStackTrace();
    		}
            return true;
			
        default:
            return super.onOptionsItemSelected(item);
        }
    }
}
