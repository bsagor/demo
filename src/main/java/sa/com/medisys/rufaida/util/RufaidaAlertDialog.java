package sa.com.medisys.rufaida.util;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.provider.Settings;
import android.text.Editable;
import android.widget.EditText;

import sa.com.medisys.rufaida.R;

public class RufaidaAlertDialog {
	Context mContext;
	EditText editText;
	
	public static final String INTERNET_CONN_FAILURE_TITLE = "No Internet Connection";
	public static final String INTERNET_CONN_FAILURE_MSG = "Please check your internet connection and try again.";
	public static final String REQUIED_FIELD = "Required Field";
	public static final String HARDWARE_BACK_TITLE = "Attention Please";
	public static final String HARDWARE_BACK_MSG = "Please use menu for go back or logout";
	public static final String HARDWARE_BACK_GO_AHEAD = "You cannot go back.\nPlease go ahead for advance menu for navigation or logout.";

	public RufaidaAlertDialog(Context mContext) {
		super();
		this.mContext = mContext;
	}
	
	public RufaidaAlertDialog(Context mContext, EditText editText) {
		super();
		this.mContext = mContext;
		this.editText = editText;
	}
	
	public static void showSuccessAlertDialogs(final Context context, String title, String message, Boolean status, final Activity activity) {
        AlertDialog alertDialog = new AlertDialog.Builder(context).create();
 
        // Setting Dialog Title
        alertDialog.setTitle(title);
 
        // Setting Dialog Message
        alertDialog.setMessage(message);
         
        // Setting alert dialog icon
        alertDialog.setIcon((status) ? R.drawable.rufaida : R.drawable.ic_action_accept);
 
        // Setting OK Button
        alertDialog.setButton("OK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
				Intent intent = new Intent(context, activity.getClass());
				context.startActivity(intent);
                ((Activity) context).finish();
            }
        });

        // Showing Alert Message
        alertDialog.show();
    }
	
	public static void showErrorAlertDialog(final Context context, String title, String message, Boolean status) {
        AlertDialog alertDialog = new AlertDialog.Builder(context).create();
 
        // Setting Dialog Title
        alertDialog.setTitle(title);
 
        // Setting Dialog Message
        alertDialog.setMessage(message);
         
        // Setting alert dialog icon
        alertDialog.setIcon((status) ? R.drawable.rufaida : R.drawable.ic_error_alert);
 
        // Setting OK Button
        alertDialog.setButton("OK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
				context.startActivity(new Intent(Settings.ACTION_SETTINGS));
            }
        }); 
        // Showing Alert Message
        alertDialog.show();
    }
	
	public static void showRequireAlertDialog(Context context, String title, String message, Boolean status) {
        AlertDialog alertDialog = new AlertDialog.Builder(context).create();
 
        // Setting Dialog Title
        alertDialog.setTitle(title);
 
        // Setting Dialog Message
        alertDialog.setMessage(message);
         
        // Setting alert dialog icon
        alertDialog.setIcon((status) ? R.drawable.rufaida : R.drawable.ic_alert);
 
        // Setting OK Button
        alertDialog.setButton("OK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
            }
        }); 
        // Showing Alert Message
        alertDialog.show();
    }
	
	public void showInputAlertDialog(Context context, String title, String message, Boolean status) {
		AlertDialog.Builder alert = new AlertDialog.Builder(context);

		alert.setTitle(title);
		alert.setMessage(message);

		// Set an EditText view to get user input 
		final EditText input = new EditText(context);
		alert.setView(input);

		alert.setPositiveButton("Continue", new DialogInterface.OnClickListener() {
		public void onClick(DialogInterface dialog, int whichButton) {
			Editable value = input.getText();
        	editText.setText(value);
		  // Do something with value!
		  }
		});

		alert.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
		  public void onClick(DialogInterface dialog, int whichButton) {
		    // Canceled.
		  }
		});

		alert.show();
    }
}
