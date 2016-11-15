package sa.com.medisys.rufaida.activity.login;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import sa.com.medisys.rufaida.R;
import sa.com.medisys.rufaida.activity.UserSelectionActivity;
import sa.com.medisys.rufaida.activity.WelcomeActivity;
import sa.com.medisys.rufaida.util.RufaidaAlertDialog;
import sa.com.medisys.rufaida.util.RufaidaPreferenceManager;
import sa.com.medisys.rufaida.util.RufaidaUtils;

public class LoginActivity extends Activity implements View.OnClickListener{

    final String TAG = "LoginActivity";
    Context context;
    boolean hardwareBackControll;

    String user = "";

    TextView tvLoginUser;
    TextView tvUserId;
    TextView tvPass;
    EditText edtUserId;
    EditText edtPass;
    Button btnLogin;

    RufaidaPreferenceManager memory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        initialize();
    }

    private void initialize() {
        context = LoginActivity.this;

        memory = new RufaidaPreferenceManager(getApplicationContext());
        user = memory.getPref(memory.KEY_USER_TYPE);

        tvLoginUser = (TextView) findViewById(R.id.tvLoginUser);
        tvUserId = (TextView) findViewById(R.id.tvUserId);
        tvPass = (TextView) findViewById(R.id.tvPass);
        edtUserId = (EditText) findViewById(R.id.edtUserId);
        edtPass = (EditText) findViewById(R.id.edtPass);

        if(user.equals("2")){
            tvLoginUser.setText("PATIENT LOGIN");
            tvUserId.setText("* Patient No");
            edtUserId.setHint("patient no");
            tvPass.setText("* Mobile No");
            edtPass.setHint("mobile no");
        }

        if(user.equals("3")){
            tvLoginUser.setText("PHARMACIST LOGIN");
        }

        btnLogin = (Button) findViewById(R.id.btnLogin);
        btnLogin.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btnLogin:
                if(RufaidaUtils.isConnected(context)){
                    Intent welcomActivityIntent = new Intent(LoginActivity.this, WelcomeActivity.class);
                    startActivity(welcomActivityIntent);
                    finish();
                } else {
                    RufaidaAlertDialog.showErrorAlertDialog(context, RufaidaAlertDialog.INTERNET_CONN_FAILURE_TITLE, RufaidaAlertDialog.INTERNET_CONN_FAILURE_MSG, false);
                }

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
        RufaidaUtils.backToPrevious(context, new UserSelectionActivity());
    }
}
