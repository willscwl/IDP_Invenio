package com.invenio.nfc.registerasset;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

import android.os.Bundle;
import android.provider.Settings;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ListActivity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v4.app.NavUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

public class TagLogic {
	static LinkedHashMap<String, String> tagInfoMap = new LinkedHashMap<String, String>();;
	public static String formattedTagInfo;
	public static String loaneeName;
	public static String retrievedTagInfo;
	
	public TagLogic(Context ccm) {

	}

	// remove from tagInfoMap
	public static void remove(String item) {
		for (Entry<String, String> e : tagInfoMap.entrySet()) {
			String key = e.getKey();
			String value = e.getValue();

			if (value.equalsIgnoreCase(item)) {
				tagInfoMap.remove(key);
				System.out.println("REMOVED " + item);
			}
		}

	}

	public static void clearMap() {
		tagInfoMap.clear();
		formattedTagInfo = "";
	}

	public static void formatTagInfo() {
		//Iterate through the hashmap and form a nicely formatted string to write to tag
		Iterator iter = tagInfoMap.keySet().iterator();
		String loanItem = "";

		while (iter.hasNext()) {
			String serialNo = (String) iter.next();
			String details = (String) tagInfoMap.get(serialNo);
			
			formattedTagInfo += serialNo + ": " + details + "\n";
		}
		
	}

	// add to Hashmap
	public static void putIn(String serialNo, String assetDetails) {
		try {
			tagInfoMap.put(serialNo, assetDetails);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	
	public static void printtagInfoMap() {
		Iterator iter = tagInfoMap.keySet().iterator();
		String loanItem = "";

		while (iter.hasNext()) {
			Integer serialNo = (Integer) iter.next();
			String details = (String) tagInfoMap.get(serialNo);
			loanItem = details;

		}
	}
	
	/*
	public static byte[] compress(String string) throws IOException {
	    ByteArrayOutputStream os = new ByteArrayOutputStream(string.length());
	    GZIPOutputStream gos = new GZIPOutputStream(os);
	    gos.write(string.getBytes());
	    gos.close();
	    byte[] compressed = os.toByteArray();
	    os.close();
	    return compressed;
	}

	public static String decompress(byte[] compressed) throws IOException {
	    final int BUFFER_SIZE = 32;
	    ByteArrayInputStream is = new ByteArrayInputStream(compressed);
	    GZIPInputStream gis = new GZIPInputStream(is, BUFFER_SIZE);
	    StringBuilder string = new StringBuilder();
	    byte[] data = new byte[BUFFER_SIZE];
	    int bytesRead;
	    while ((bytesRead = gis.read(data)) != -1) {
	        string.append(new String(data, 0, bytesRead));
	    }
	    gis.close();
	    is.close();
	    return string.toString();
	}*/
	
}
