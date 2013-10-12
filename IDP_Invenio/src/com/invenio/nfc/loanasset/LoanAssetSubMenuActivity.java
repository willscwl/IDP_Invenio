package com.invenio.nfc.loanasset;

import com.invenio.nfc.R;
import com.invenio.nfc.R.id;
import com.invenio.nfc.R.layout;

import android.os.Bundle;
import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;
import android.support.v4.app.NavUtils;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

public class LoanAssetSubMenuActivity extends ListActivity {
	String[] classes= {"Check Out Items", "Return Items", "View My Loaned Items", "View Asset Loan History"};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		try {
			super.onCreate(savedInstanceState);
			setListAdapter(new ArrayAdapter<String>(LoanAssetSubMenuActivity.this, R.layout.custom_listview, classes));
			getActionBar().setDisplayHomeAsUpEnabled(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
protected void onListItemClick(ListView l, View v, int position, long id) {
		
		//which position was clicked
		String selection = classes[position];
		String clickedOption = "";
		
		if(selection.equals("Check Out Items")){
			clickedOption = "LoanMultipleItemsActivity";
		}else if(selection.equals("Return Items")){
			clickedOption = "ReturnAssetActivity";
		}else if(selection.equals("View My Loaned Items")){
			clickedOption = "ViewLoanItemsActivity";
		}else if(selection.equals("View Asset Loan History")){
			clickedOption = "ScanLoanHistoryActivity";
		}
		
		
		super.onListItemClick(l, v, position, id);
		try{
			Class ourClass = Class.forName("com.invenio.nfc.loanasset." + clickedOption);
			Intent ourIntent = new Intent(LoanAssetSubMenuActivity.this, ourClass);
			startActivity(ourIntent);
		}catch(ClassNotFoundException e){
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
    			Intent ourIntent = new Intent(LoanAssetSubMenuActivity.this, ourClass);
    			startActivity(ourIntent);
    		}catch(ClassNotFoundException e){
    			e.printStackTrace();
    		}
            return true;
            
        case R.id.menu_about:
            //Toast.makeText(MainMenuActivity.this, "About", 3000).show();
            try{
    			Class ourClass = Class.forName("com.invenio.nfc.AboutActivity");
    			Intent ourIntent = new Intent(LoanAssetSubMenuActivity.this, ourClass);
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
