package com.invenio.nfc.registerasset;

import com.invenio.nfc.R;
import com.invenio.nfc.R.id;
import com.invenio.nfc.R.layout;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.Intent;
import android.support.v4.app.NavUtils;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class ConfirmRegisterActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		try {
			super.onCreate(savedInstanceState);
			setContentView(R.layout.activity_confirmregisterasset);
			//getActionBar().setDisplayHomeAsUpEnabled(true);
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
    			Intent ourIntent = new Intent(ConfirmRegisterActivity.this, ourClass);
    			startActivity(ourIntent);
    		}catch(ClassNotFoundException e){
    			e.printStackTrace();
    		}
            return true;
            
        case R.id.menu_about:
            //Toast.makeText(MainMenuActivity.this, "About", 3000).show();
            try{
    			Class ourClass = Class.forName("com.invenio.nfc.AboutActivity");
    			Intent ourIntent = new Intent(ConfirmRegisterActivity.this, ourClass);
    			startActivity(ourIntent);
    		}catch(ClassNotFoundException e){
    			e.printStackTrace();
    		}
            return true;
			
        default:
            return super.onOptionsItemSelected(item);
        }
    }

	public void onClickConfirmOk(View v) {
		//Toast.makeText(ConfirmRegisterActivity.this, "Asset added successfully!", 3000).show();

		try {
			Class ourClass = Class.forName("com.invenio.nfc.RegisterSuccessActivity");
			Intent ourIntent = new Intent(ConfirmRegisterActivity.this,
					ourClass);
			startActivity(ourIntent);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void onClickConfirmCancel(View v) {
		try {
			Class ourClass = Class.forName("com.invenio.nfc.MainMenuActivity");
			Intent ourIntent = new Intent(ConfirmRegisterActivity.this,
					ourClass);
			startActivity(ourIntent);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
