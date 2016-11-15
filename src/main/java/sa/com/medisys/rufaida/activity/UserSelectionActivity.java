package sa.com.medisys.rufaida.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import sa.com.medisys.rufaida.R;
import sa.com.medisys.rufaida.activity.login.LoginActivity;
import sa.com.medisys.rufaida.util.RufaidaPreferenceManager;
import sa.com.medisys.rufaida.util.RufaidaUtils;

public class UserSelectionActivity extends Activity {
    final String TAG = "UserSelectionActivity";
    String user = "";
    RufaidaPreferenceManager memory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_selection);

        memory = new RufaidaPreferenceManager(getApplicationContext());
    }

    public void doctorLogin(View view) {
        memory.putPref(memory.KEY_USER_TYPE, "1");
        Intent loginActivityIntent = new Intent(UserSelectionActivity.this, LoginActivity.class);
        startActivity(loginActivityIntent);
        finish();
    }

    public void patientLogin(View view) {
        memory.putPref(memory.KEY_USER_TYPE, "2");
        Intent loginActivityIntent = new Intent(UserSelectionActivity.this, LoginActivity.class);
        startActivity(loginActivityIntent);
        finish();
    }

    public void pharmacistLogin(View view) {
        memory.putPref(memory.KEY_USER_TYPE, "3");
        Intent loginActivityIntent = new Intent(UserSelectionActivity.this, LoginActivity.class);
        startActivity(loginActivityIntent);
        finish();
    }
}
