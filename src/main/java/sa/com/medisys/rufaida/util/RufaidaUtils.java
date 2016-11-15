package sa.com.medisys.rufaida.util;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.widget.Toast;

import sa.com.medisys.rufaida.activity.LogoutActivity;
import sa.com.medisys.rufaida.activity.RufaidaMenuActivity;

public class RufaidaUtils {
	public final static String APP_NAME = "Rufaida Medical Systems";

	public static boolean loginFormValidation(Context context, String userName,
			String password, String uNameReq, String passReq) {
		if (userName.equals("")) {
			Toast.makeText(context, uNameReq, Toast.LENGTH_SHORT).show();
			return false;
		} else if (password.equals("")) {
			Toast.makeText(context, passReq, Toast.LENGTH_SHORT).show();
			return false;
		} else {
			return true;
		}
	}

	public static final String checkDigit(int number) {
		return number <= 9 ? "0" + number : String.valueOf(number);
	}

	private static Map<String, String> loadFrequency(Object k, Object v) {
		Map<String, String> hMap = new HashMap<String, String>();
		try {
			hMap.put("1", "One Time A Day");
			hMap.put("2", "Twice A Day");
			hMap.put("3", "Three Times A Day");
			hMap.put("4", "Four Times A Day");
			hMap.put("5", "Five Times A Day");
			hMap.put("6", "Once in Two Days");
			hMap.put("7", "Once Weekly");
			hMap.put("13", "Stat");
			hMap.put("14", "Six Times A Day");
			hMap.put("15", "Eight Times A Day");
			hMap.put("16", "Every Hour");
			hMap.put("17", "PRN");
			hMap.put("18", "Twice Weekly");
			hMap.put("19", "Thrice Weekly");
			hMap.put("20", "Four Times Weekly");
			hMap.put("21", "Five Times Weekly");
			hMap.put("22", "Every Two Hour");
			hMap.put("23", "Once in One Month");
			hMap.put("24", "Once in Three Days");
			hMap.put("25", "Every 16 Hour");
			hMap.put("26", "Every 36 Hour");
			hMap.put("27", "Twice A Day for 5 Days");
			hMap.put("28", "Thrice A Day for 5 Days");
			hMap.put("29", "Once A Day for 5 Days");
			hMap.put("30", "2 puff Every 8 Hour");
			hMap.put("31", "2 puff PRN");
			hMap.put("32", "Every Other Day for Month");
		} catch (Exception e) {

		}
		return hMap;
	}

	public static String getName(String id) {
		Map<String, String> hMapList = loadFrequency("", "");
		Iterator iter = hMapList.entrySet().iterator();
		String name = null;
		while (iter.hasNext()) {
			Map.Entry mEntry = (Map.Entry) iter.next();
			if (mEntry.getKey().equals(id)) {
				name = mEntry.getValue().toString();
			}
			// System.out.println(mEntry.getKey() + " : " + mEntry.getValue());
		}
		return name;
	}

	private static Map<String, String> loadDoseDuration(Object k, Object v) {
		Map<String, String> hMap = new HashMap<String, String>();
		try {
			hMap.put("15", "Fifteen Days");
			hMap.put("30", "One Month");
			hMap.put("60", "Two Months");
			hMap.put("90", "Three Months");
			hMap.put("180", "Six Months");
			hMap.put("3", "Three Days");
			hMap.put("4", "Four Days");
			hMap.put("5", "Five Days");
			hMap.put("6", "Six Days");
			hMap.put("7", "One Week");
			hMap.put("1", "One Day");
			hMap.put("2", "Two Days");
			hMap.put("8", "Eight Days");
			hMap.put("9", "Nine Days");
			hMap.put("10", "Ten Days");
			hMap.put("270", "Nine Months");
			hMap.put("21", "Twenty One Days");
			hMap.put("75", "Two and Half Month");
			hMap.put("120", "Four Months");
			hMap.put("150", "Five Months");
			hMap.put("365", "One Year");
			hMap.put("11", "Two Weeks");
		} catch (Exception e) {

		}
		return hMap;
	}

	public static String getDoseDuration(String id) {
		Map<String, String> hMapList = loadDoseDuration("", "");
		Iterator iter = hMapList.entrySet().iterator();
		String name = null;
		while (iter.hasNext()) {
			Map.Entry mEntry = (Map.Entry) iter.next();
			if (mEntry.getKey().equals(id)) {
				name = mEntry.getValue().toString();
			}
			// System.out.println(mEntry.getKey() + " : " + mEntry.getValue());
		}
		return name;

	}

	public static boolean isConnected(Context context) {
		ConnectivityManager connMgr = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
		return (networkInfo != null && networkInfo.isConnected());
	}

	public static Date nextDate(Date date, int days) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.DATE, days); // minus number would decrement the days
		return cal.getTime();
	}

	public static Date prevDate(Date date, int days) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.DATE, days); // minus number would decrement the days
		return cal.getTime();
	}

	public static String twoDigit(int value) {
		if (value < 10) {
			return "0" + value;
		} else {
			// get last two digits
			String str = Integer.toString(value);
			return str.substring(str.length() - 2);
		}
	}

	public static String getCurrentTime() {
		Calendar c = Calendar.getInstance();
		int hour = c.get(Calendar.HOUR_OF_DAY);
		int min = c.get(Calendar.MINUTE);
		return RufaidaUtils.twoDigit(hour) + ":" + RufaidaUtils.twoDigit(min);
	}

	public static void home(Context context){
		Intent rufaidaMenuActivityIntent = new Intent(context, RufaidaMenuActivity.class);
		rufaidaMenuActivityIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		context.startActivity(rufaidaMenuActivityIntent);
		((Activity) context).finish();
	}

	public static void backToPrevious(Context context, Activity activity){
		Intent previousActivityIntent = new Intent(context, activity.getClass());
		previousActivityIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		context.startActivity(previousActivityIntent);
		((Activity) context).finish();
	}

	public static void logout(Context context){
		Intent logoutActivityIntent = new Intent(context, LogoutActivity.class);
		logoutActivityIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		context.startActivity(logoutActivityIntent);
		((Activity) context).finish();
	}
}
