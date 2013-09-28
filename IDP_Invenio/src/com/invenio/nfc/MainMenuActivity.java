package com.invenio.nfc;


import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.support.v4.app.NavUtils;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import com.invenio.nfc.LoanItemLogic;

public class MainMenuActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		try {
			super.onCreate(savedInstanceState);
			setContentView(R.layout.activity_mainmenu);
			//getActionBar().setDisplayHomeAsUpEnabled(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void onClickRegisterAsset(View v){
		try{
			Class ourClass = Class.forName("com.invenio.nfc.RegisterAssetActivity");
			Intent ourIntent = new Intent(MainMenuActivity.this, ourClass);
			startActivity(ourIntent);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public void onClickLoanAsset(View v){
		try{
			LoanItemLogic.clearListItems();
			Class ourClass = Class.forName("com.invenio.nfc.LoanSubMenuActivity");
			Intent ourIntent = new Intent(MainMenuActivity.this, ourClass);
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
        case R.id.menu_main:
            //Toast.makeText(MainMenuActivity.this, "Main Menu", 3000).show();
            try{
    			Class ourClass = Class.forName("com.invenio.nfc.MainMenuActivity");
    			Intent ourIntent = new Intent(MainMenuActivity.this, ourClass);
    			startActivity(ourIntent);
    		}catch(ClassNotFoundException e){
    			e.printStackTrace();
    		}
            return true;
            
        case R.id.menu_about:
            //Toast.makeText(MainMenuActivity.this, "About", 3000).show();
            try{
    			Class ourClass = Class.forName("com.invenio.nfc.AboutActivity");
    			Intent ourIntent = new Intent(MainMenuActivity.this, ourClass);
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
