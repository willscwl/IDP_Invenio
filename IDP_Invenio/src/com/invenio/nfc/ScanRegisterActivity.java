package com.invenio.nfc;

import java.util.Random;

import android.os.Bundle;
import android.os.Handler;
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
import android.widget.Toast;

import com.invenio.nfc.RegisterAssetActivity;

public class ScanRegisterActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		try {
			super.onCreate(savedInstanceState);
			setContentView(R.layout.activity_scan);
			getActionBar().setDisplayHomeAsUpEnabled(true);

			// auto direct to
			new Handler().postDelayed(new Runnable() {
				@Override
				public void run() {

					AlertDialog.Builder alertDialog = new AlertDialog.Builder(
							ScanRegisterActivity.this);

					// Setting Dialog Title
					alertDialog.setTitle("Confirm Registration");

					// add message
					alertDialog.setMessage("Click 'Yes' to register:\n\nID:"
							+ RegisterAssetActivity.getRegistrationId()
							+ "\nChair");

					// Setting Positive "Yes" Button
					alertDialog.setPositiveButton("Yes",
							new DialogInterface.OnClickListener() {

								@Override
								public void onClick(DialogInterface dialog,
										int which) {
									
									//---------------- Insert code to send registration details to server ----------------
									
									
									
									
									
									
									
									//----------------------------------------------------------------------------
									
									
									AlertDialog.Builder alertDialog2 = new AlertDialog.Builder(
											ScanRegisterActivity.this);

									// Setting Dialog Title
									alertDialog2
											.setTitle("Registration Complete");

									// add message
									alertDialog2
											.setMessage("Asset Registered Successfully!");

									

									alertDialog2
											.setPositiveButton(
													"OK",
													new DialogInterface.OnClickListener() {

														@Override
														public void onClick(
																DialogInterface dialog,
																int which) {
															Class ourClass;
															try {
																ourClass = Class
																		.forName("com.invenio.nfc.MainMenuActivity");
																Intent ourIntent = new Intent(
																		ScanRegisterActivity.this,
																		ourClass);
																ScanRegisterActivity.this
																		.startActivity(ourIntent);
															} catch (ClassNotFoundException e) {
																// TODO
																// Auto-generated
																// catch block
																e.printStackTrace();
															}

														}

													});
									alertDialog2.show();
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
												ScanRegisterActivity.this,
												"Registration Cancelled!", 3000)
												.show();
										Class ourClass = Class
												.forName("com.invenio.nfc.MainMenuActivity");
										Intent ourIntent = new Intent(
												ScanRegisterActivity.this,
												ourClass);
										ScanRegisterActivity.this
												.startActivity(ourIntent);

									} catch (ClassNotFoundException e) {
										e.printStackTrace();
									}
								}

							});
					// Showing Alert Message
					alertDialog.show();
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
				Intent ourIntent = new Intent(ScanRegisterActivity.this,
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
				Intent ourIntent = new Intent(ScanRegisterActivity.this,
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
