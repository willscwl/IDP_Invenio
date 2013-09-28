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

public class LoanMultipleItemsActivity extends Activity {

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
							LoanMultipleItemsActivity.this);

					// add items to list
					l.putIn(1,"ID: 12345\nPurple");
					l.putIn(2,"ID: 12345\nRed Chair");
					l.putIn(3,"ID: 12345\nBlue Chair");
					l.putIn(4,"ID: 12345\nPink Chair");
					l.putIn(5,"ID: 12345\nGrey Chair");
					l.putIn(6,"ID: 12345\nGrey Chair");
					l.putIn(7,"ID: 12345\nGrey Chair");
					l.putIn(8,"ID: 12345\nGrey Chair");
					l.putIn(9,"ID: 12345\nGrey Chair");
					
					// create loan items dialog
					l.createLoanItemAlertDialog("Confirm Loan", "MainMenuActivity");
					
					//l.clearListItems();
				}
			}, 1000);

			// ----- Dialog End -----
			// final Intent mainIntent = new Intent(LoanSingleItemActivity.this,
			// ConfirmRegisterActivity.class);
			// LoanSingleItemActivity.this.startActivity(mainIntent);
			// LoanSingleItemActivity.this.finish();
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
