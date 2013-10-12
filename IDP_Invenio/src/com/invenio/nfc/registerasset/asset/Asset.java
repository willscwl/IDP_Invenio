package com.invenio.nfc.registerasset.asset;

import java.util.HashMap;

public class Asset {
	
	public static String equipmentTagID;
	public static String manufacturerName;
	public static String modelNo;
	public static String hardwareDesc;
	public static String manufacturerSerialNo;
	public static String internalID;
	public static String location;
	public static String timeOfPurchase;
	public static String relatedEquipment;
	public static String remarks;
	public static String personInCharge;
	public static String contactInfo;
	public static String lastUserUpdate;
	public static String lastUpdateTimeStamp;
	public static String lastCalibrationDate;
	
	public Asset(){
	   
	}
	
	public void clearAttributes(){
		Asset.equipmentTagID = "";
		Asset.manufacturerName = "";
		Asset.modelNo = "";
		Asset.hardwareDesc = "";
		Asset.manufacturerSerialNo = "";
		Asset.internalID = "";
		Asset.location = "";
		Asset.timeOfPurchase = "";
		Asset.relatedEquipment = "";
		Asset.remarks = "";
		Asset.personInCharge = "";
		Asset.contactInfo = "";
		Asset.lastUserUpdate = "";
		Asset.lastUpdateTimeStamp = "";
		Asset.lastCalibrationDate = "";
	}
}
