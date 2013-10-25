package com.invenio.nfc.login;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URLEncoder;
import java.util.regex.Pattern;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONTokener;

import com.invenio.nfc.*;
import com.invenio.nfc.R.id;
import com.invenio.nfc.R.layout;
import com.invenio.nfc.R.menu;
import com.invenio.nfc.registerasset.HttpRequest;

import android.os.Bundle;
import android.os.Handler;
import android.os.StrictMode;
import android.accounts.Account;
import android.accounts.AccountManager;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.util.Log;
import android.util.Patterns;
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
	final String myTag = "DocsUpload";
	private Context ctx;

	protected void onCreate(Bundle savedInstanceState) {
		try {
			super.onCreate(savedInstanceState);
			setContentView(R.layout.activity_main);
			StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
					.permitAll().build();
			ctx = this;
			StrictMode.setThreadPolicy(policy);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void postData() {

		Pattern emailPattern = Patterns.EMAIL_ADDRESS; // API level 8+
		Account[] accounts = AccountManager.get(ctx).getAccounts();
		for (Account account : accounts) {
			if (emailPattern.matcher(account.name).matches()) {
				String email = account.name;
				String locale = ctx.getResources().getConfiguration().locale
						.getCountry();
				String fullUrl = "https://docs.google.com/forms/d/1pXQSwdXYxXEs--6ucmzyi6AmMFKPkQJYE325tc2A3g4/formResponse";
				HttpRequest mReq = new HttpRequest();

				String data = "entry.1249457384=" + URLEncoder.encode(email)
						+ "&" + "entry.1781752092=" + URLEncoder.encode(locale);
				String response = mReq.sendPost(fullUrl, data);
				Log.i(myTag, response);
			}
		}
	}

	public void onClickLogin(View v) {

		EditText username = (EditText) findViewById(R.id.username);
		TextView password = (EditText) findViewById(R.id.password);

		String uName = username.getText().toString();
		String pWord = password.getText().toString();

		// --------------------- GOOGLE DOC ----------------------
		Thread t = new Thread(new Runnable() {
			@Override
			public void run() {
				postData();

			}
		});
		t.start();
		// --------------------------------------------------------

		// username: admin
		// password: password
		if (uName.equals("")) {
			Toast.makeText(LoginActivity.this, "Please fill in all data!", 3000)
					.show();
		} else if (pWord.equals("")) {
			Toast.makeText(LoginActivity.this, "Please fill in all data!", 3000)
					.show();
		} else {
			InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
			imm.hideSoftInputFromWindow(password.getWindowToken(), 0);
			if (uName.equals("Admin") && pWord.equals("password")) {
				// Create alert dialog
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
				String functionList = "1. Register Asset\n2. Loan Asset\n";
				alertDialog.setMessage(alert1 + "\n" + functionList);

				// Setting Positive "Yes" Button
				alertDialog.setPositiveButton("OK",
						new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog,
									int which) {
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
				 * } catch (ClassNotFoundException e) { // TODO Auto-generated
				 * catch block e.printStackTrace(); }
				 */
			} else {
				Toast.makeText(LoginActivity.this,
						"Invalid username/password!", 3000).show();
			}

		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
