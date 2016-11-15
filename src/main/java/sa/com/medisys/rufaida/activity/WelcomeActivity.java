package sa.com.medisys.rufaida.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import sa.com.medisys.rufaida.R;
import sa.com.medisys.rufaida.util.RufaidaAlertDialog;
import sa.com.medisys.rufaida.util.RufaidaPreferenceManager;
import sa.com.medisys.rufaida.util.RufaidaUtils;

public class WelcomeActivity extends Activity implements View.OnClickListener{
    final String TAG = "WelcomeActivity";
    Context context;

    String user = "";

    TextView tvWelcomeUserInfo;
    ImageButton welcomeUserImageImgBtn;
    private boolean hardwareBackControll;

    RufaidaPreferenceManager memory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        initialize();
    }

    private void initialize() {
        context = WelcomeActivity.this;

        memory = new RufaidaPreferenceManager(getApplicationContext());
        user = memory.getPref(memory.KEY_USER_TYPE);

        tvWelcomeUserInfo = (TextView) findViewById(R.id.tvWelcomeUserInfo);
        if(user.equals("2")){
            tvWelcomeUserInfo.setText("Patient No");
        }

        welcomeUserImageImgBtn = (ImageButton) findViewById(R.id.welcomeUserImageImgBtn);
        welcomeUserImageImgBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.welcomeUserImageImgBtn:
                Intent rufaidaMenuActivityIntent = new Intent(WelcomeActivity.this, RufaidaMenuActivity.class);
                startActivity(rufaidaMenuActivityIntent);
                finish();
                break;
        }
    }

    @Override
    public void onBackPressed() {
        if (hardwareBackControll) {
            super.onBackPressed();
            return;
        }
        this.hardwareBackControll = true;
        RufaidaAlertDialog.showRequireAlertDialog(context, RufaidaAlertDialog.HARDWARE_BACK_TITLE, RufaidaAlertDialog.HARDWARE_BACK_GO_AHEAD, false);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                hardwareBackControll = false;
            }
        },200);
    }
}
