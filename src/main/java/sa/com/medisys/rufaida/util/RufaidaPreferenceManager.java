package sa.com.medisys.rufaida.util;

import android.content.Context;
import android.content.SharedPreferences;

public class RufaidaPreferenceManager {
	
    private static final String TAG = "PreferenceManager"; 
	 // Shared pref mode
    int PRIVATE_MODE = 0;
     
    // Sharedpref file name
    private static final String PREF_NAME = "loginPrefs";
    
 // usertype (make variable public to access from outside)
    public static final String KEY_USER_TYPE = "userType";
    
    // Doctor user default value (make variable public to access from outside)
    public static final String VALUE_DOCTOR_TYPE = "1";
    
 //  patient user default value  (make variable public to access from outside)
    public static final String VALUE_PAT_TYPE = "2"; 
    
 // patno (make variable public to access from outside)
    public static final String KEY_PAT_NO = "patNo";
    
    // patno (make variable public to access from outside)
    public static final String KEY_PAT_NAME = "patName";
    
    public static final String KEY_PAT_AGE = "patAge";
    
    public static final String KEY_PAT_SEX = "patSex";
    
 // doctor username (make variable public to access from outside)
    public static final String KEY_DOCTOR_PASS = "password";

    public static final String KEY_DOCTOR_CODE = "doctorCode";     

    public static final String KEY_EMP_CODE = "empCode";    

    public static final String KEY_LOGIN_NAME = "loginName"; 
 
    public static final String KEY_EMP_NAME = "userName";

	
	 private SharedPreferences prefs;
	 private Context mContext;
	 
	public RufaidaPreferenceManager(Context aContext){
		this. mContext = aContext;
	    prefs = aContext.getSharedPreferences(PREF_NAME,  PRIVATE_MODE);
	  
	}
	
	public  void putPref(String key, String value) {
		
		SharedPreferences.Editor editor = prefs.edit();
		editor.putString(key, value);
		editor.commit();
	}

	public  String getPref(String key) {
		
		return prefs.getString(key, null);
	}
	
	/**
	 *  Method used to delete Preferences */
	public boolean deletePreferences(String key)
	{
		SharedPreferences.Editor editor = prefs.edit();
	    editor.remove(key).commit();
	    return false;
	}

}
