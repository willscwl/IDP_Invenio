package com.invenio.nfc;

import com.invenio.nfc.*;

import android.os.Bundle;
import android.app.Activity;
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

public class AboutActivity extends Activity {

	protected void onCreate(Bundle savedInstanceState) {
		try {
			super.onCreate(savedInstanceState);
			setContentView(R.layout.activity_about);
			TextView wikiLink = (TextView) findViewById(R.id.TextView01);
			wikiLink.setText(Html.fromHtml("<a href=\"https://wiki.smu.edu.sg/is306/2013-14_Term_1_G1_Invenio_A5_Flow_Diagram\">Invenio Wiki Page</a> "));
			wikiLink.setMovementMethod(LinkMovementMethod.getInstance());
			getActionBar().setDisplayHomeAsUpEnabled(true);
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
    			Intent ourIntent = new Intent(AboutActivity.this, ourClass);
    			startActivity(ourIntent);
    		}catch(ClassNotFoundException e){
    			e.printStackTrace();
    		}
            return true;
            
        case R.id.menu_about:
            //Toast.makeText(MainMenuActivity.this, "About", 3000).show();
            try{
    			Class ourClass = Class.forName("com.invenio.nfc.AboutActivity");
    			Intent ourIntent = new Intent(AboutActivity.this, ourClass);
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
