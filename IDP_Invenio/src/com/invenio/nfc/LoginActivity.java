package com.invenio.nfc;

import com.invenio.nfc.*;

import android.os.Bundle;
import android.os.Handler;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends Activity {

	protected void onCreate(Bundle savedInstanceState) {
		try {
			super.onCreate(savedInstanceState);
			setContentView(R.layout.activity_main);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void onClickLogin(View v) {

		EditText username = (EditText) findViewById(R.id.username);
		TextView password = (EditText) findViewById(R.id.password);

		// username: admin
		// password: password
		if (username.getText().toString().equals("")) {
			Toast.makeText(LoginActivity.this, "Please fill in all data!", 3000)
					.show();
		} else if (password.getText().toString().equals("")) {
			Toast.makeText(LoginActivity.this, "Please fill in all data!", 3000)
					.show();
		} else if (!username.getText().toString().equals("Admin")) {
			Toast.makeText(LoginActivity.this, "Invalid username!", 3000)
					.show();
		} else if (!password.getText().toString().equals("password")) {
			Toast.makeText(LoginActivity.this, "Invalid password!", 3000)
					.show();
		} else {
			InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
			imm.hideSoftInputFromWindow(password.getWindowToken(), 0);
			
			//Create alert dialog
			AlertDialog.Builder alertDialog = new AlertDialog.Builder(
					LoginActivity.this);

			// Setting Dialog Title
			alertDialog.setTitle("Hi, " + username.getText());
			alertDialog.setCancelable(false);
			
			// pass data from one activity to another
			/*
			 * Intent i = new Intent(getApplicationContext(),
			 * NewActivity.class); i.putExtra("new_variable_name","value");
			 * startActivity(i);
			 */

			// Setting Dialog Message
			String alert1 = "You are authorized for:  \n";
			String functionList = "1. Register Asset\n2. Loan Asset";
			alertDialog.setMessage(alert1 + "\n" + functionList);

			// Setting Positive "Yes" Button
			alertDialog.setPositiveButton("OK",
					new DialogInterface.OnClickListener() {
						public void onClick(DialogInterface dialog, int which) {
							// User pressed OK
							try {
								Class ourClass = Class
										.forName("com.invenio.nfc.MainMenuActivity");

								Intent ourIntent = new Intent(
										LoginActivity.this, ourClass);
								startActivity(ourIntent);

							} catch (ClassNotFoundException e) {
								e.printStackTrace();
							}
						}
					});

			// Showing Alert Message
			alertDialog.show();

			/*
			 * try { Class ourClass = Class
			 * .forName("com.invenio.nfc.LoginSuccessActivity");
			 * 
			 * Intent ourIntent = new Intent(LoginActivity.this, ourClass);
			 * startActivity(ourIntent);
			 * 
			 * 
			 * } catch (ClassNotFoundException e) { // TODO Auto-generated catch
			 * block e.printStackTrace(); }
			 */

		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
