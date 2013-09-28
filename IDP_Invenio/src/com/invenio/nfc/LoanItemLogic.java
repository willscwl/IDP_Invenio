package com.invenio.nfc;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ListActivity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v4.app.NavUtils;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

public class LoanItemLogic {
	private static HashMap<Integer, String> loanMap = new HashMap<Integer, String>();;
	private static Context currentClassName;
	private static CharSequence[] items = {};
	private static List<String> listItems = new ArrayList<String>();
	private static String loaneeName;

	public LoanItemLogic(Context ccm) {
		this.currentClassName = ccm;
		loanMap.clear();
	}

	// clear listItems
	public static void clearListItems() {
		listItems.clear();
	}

	// add loan item to next empty index in load array
	public static void putIn(int serialNo, String assetDetails) {
		try {
			loanMap.put(serialNo, assetDetails);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void collateLoanItems(String loanItem) {
		listItems.add("\n" + loanItem + "\n");
		items = listItems.toArray(new CharSequence[listItems.size()]);
	}

	// prompt for loanee details in an alertDialog
	public static void askForLoaneeName() {

		AlertDialog.Builder alertDialog = new AlertDialog.Builder(
				currentClassName);

		// Setting Dialog Title
		alertDialog.setTitle("Loanee Name");
		alertDialog.setCancelable(false);

		// add items in listview
		alertDialog.setMessage("Please enter the loanee's name:");
		final EditText input = new EditText(currentClassName);
		alertDialog.setView(input);

		// User clicks NEXT after entering name"
		alertDialog.setPositiveButton("Next",
				new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) {
						loaneeName = input.getText().toString();
						boolean loaneeExistsInDatabase = loaneeExists(loaneeName);
						
						if (loaneeExistsInDatabase) {
							loanSuccessDialog();
						}else{
							Toast.makeText(currentClassName, "User does not exist. Please try again.",3000).show();
							askForLoaneeName();
						}
							
					}
				});

		// User clicks CANCEL at this point, go back to home page
		alertDialog.setNegativeButton("Cancel",
				new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) {

						Toast.makeText(currentClassName, "Loan cancelled!",
								3000).show();
						Class ourClass;
						try {
							ourClass = Class
									.forName("com.invenio.nfc.MainMenuActivity");
							Intent ourIntent = new Intent(currentClassName,
									ourClass);
							currentClassName.startActivity(ourIntent);
						} catch (ClassNotFoundException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}

					}
				});

		alertDialog.show();
	}

	public static void loanSuccessDialog(){
		// create loan success dialog
		AlertDialog.Builder alertDialog2 = new AlertDialog.Builder(
				currentClassName);
		alertDialog2.setCancelable(false);

		// Setting Dialog Title
		alertDialog2.setTitle("Loan Success!");

		// add message
		alertDialog2.setMessage("Loan is made to: " + loaneeName);
		alertDialog2.setPositiveButton("OK",
				new DialogInterface.OnClickListener() {

					@Override
					public void onClick(
							DialogInterface dialog,
							int which) {
						Class ourClass;
						try {
							// clearListItems();
							ourClass = Class
									.forName("com.invenio.nfc.MainMenuActivity");
							Intent ourIntent = new Intent(
									currentClassName,
									ourClass);
							currentClassName
									.startActivity(ourIntent);
						} catch (ClassNotFoundException e) {
							e.printStackTrace();
						}

					}

				});
		alertDialog2.show();
	}
	public static void printLoanMap() {
		Iterator iter = loanMap.keySet().iterator();
		String loanItem = "";

		while (iter.hasNext()) {
			Integer serialNo = (Integer) iter.next();
			String details = (String) loanMap.get(serialNo);
			loanItem = details;
			collateLoanItems(loanItem);
		}

	}

	// ---- To be edited by Ben----
	// check from server if loanee exists
	public static boolean loaneeExists(String loaneeName) {
		return true;
	}

	public static void createLoanItemAlertDialog(String title,
			final String cancelAction) {

		printLoanMap();

		final AlertDialog.Builder alertDialog = new AlertDialog.Builder(
				currentClassName);

		// Setting Dialog Title
		alertDialog.setTitle(title);
		alertDialog.setCancelable(false);

		// add items in listview
		alertDialog.setItems(items, null);

		// Setting Positive "Yes" Button
		alertDialog.setPositiveButton("Next",new DialogInterface.OnClickListener() {

					public void onClick(DialogInterface dialog, int which) {
						// User pressed OK
						askForLoaneeName();
					}

				});

		alertDialog.setNegativeButton("No",
				new DialogInterface.OnClickListener() {

					@Override
					public void onClick(DialogInterface dialog, int which) {
						// User pressed cancel
						try {
							Toast.makeText(currentClassName, "Loan Cancelled!",
									3000).show();
							// clearListItems();
							Class ourClass = Class.forName("com.invenio.nfc."
									+ cancelAction);
							Intent ourIntent = new Intent(currentClassName,
									ourClass);
							currentClassName.startActivity(ourIntent);

						} catch (ClassNotFoundException e) {
							e.printStackTrace();
						}
					}

				});
		// Showing Alert Message
		alertDialog.show();

	}
}
