package com.invenio.nfc.registerasset;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
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


public class TimeLapse {
	
	public static long timeTaken;
	public static long startTime;
	public static long endTime;
	
	public TimeLapse() {
		
	}

	public static void resetTime(){
		timeTaken = 0;
		startTime = 0;
		endTime = 0;
	}
	
	public static void startTimer(){
		resetTime();
		startTime = new Date().getTime();
	}
	
	public static void stopTimer(){
		endTime = new Date().getTime();
		timeTaken = endTime - startTime;
	}
	
}
