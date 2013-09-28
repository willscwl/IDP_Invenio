package com.invenio.nfc;

import com.invenio.nfc.*;

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

public class LoanSingleItemActivity extends Activity {

	protected void onCreate(Bundle savedInstanceState) {
		try {
			super.onCreate(savedInstanceState);
			setContentView(R.layout.activity_scan);
			getActionBar().setDisplayHomeAsUpEnabled(true);

			// auto direct to
			new Handler().postDelayed(new Runnable() {
				@Override
				public void run() {
					// ----- Create dialog to show loaned item -----
					LoanItemLogic l = new LoanItemLogic(
							LoanSingleItemActivity.this);

					// add items to list
					l.putIn(1,"ID: 12345\nMahogany Chair - Small \nRed, sturdy and slightly moist");

					// create loan items dialog
					l.createLoanItemAlertDialog("Confirm Loan", "MainMenuActivity");

				}
			}, 1000);


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
				Intent ourIntent = new Intent(LoanSingleItemActivity.this,
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
				Intent ourIntent = new Intent(LoanSingleItemActivity.this,
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
