package sa.com.medisys.rufaida.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.provider.Settings;
import android.util.Log;
import android.view.View;

import sa.com.medisys.rufaida.R;
import sa.com.medisys.rufaida.activity.login.LoginActivity;
import sa.com.medisys.rufaida.util.RufaidaAlertDialog;

public class LogoutActivity extends Activity {
    final String TAG = "LogoutActivity";
    Context context;
    boolean hardwareBackControll;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logout);

        context = LogoutActivity.this;
    }


    public void gotoLogin(View view) {
        Intent loginActivityIntent = new Intent(LogoutActivity.this, LoginActivity.class);
        startActivity(loginActivityIntent);
        finish();
    }
}
