package sa.com.medisys.rufaida.activity.login;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import sa.com.medisys.rufaida.R;
import sa.com.medisys.rufaida.activity.UserSelectionActivity;
import sa.com.medisys.rufaida.activity.WelcomeActivity;
import sa.com.medisys.rufaida.retorfit.rufaidawebservice.RufaidaWebservice;
import sa.com.medisys.rufaida.retorfit.rufaidawebservice.collection.DoctorLoginCollection;
import sa.com.medisys.rufaida.retorfit.rufaidawebservice.interfaces.DoctorLoginApi;
import sa.com.medisys.rufaida.retorfit.rufaidawebservice.model.DoctorLogin;
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
    DoctorLoginApi doctorLoginApi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        webserviceInitialize();
        initialize();
    }

    private void webserviceInitialize() {
        Log.d(TAG, "webserviceInitialize");
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(RufaidaWebservice.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        doctorLoginApi = retrofit.create(DoctorLoginApi.class);
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

    private void getData(String username, String pwd) {
        Log.d(TAG, username + "\n" +pwd);
        Call<DoctorLoginCollection> getInfo = doctorLoginApi.getDoctorLoginInfo(username, pwd);
        getInfo.enqueue(new Callback<DoctorLoginCollection>() {
            @Override
            public void onResponse(Call<DoctorLoginCollection> call, Response<DoctorLoginCollection> response) {
                List<DoctorLogin> doctorinfo = response.body().data;

                for(int i = 0; i < doctorinfo.size(); i++){
                    Log.d(TAG, "API Doc_code:: " + doctorinfo.get(i).getDoc_code());
                    Log.d(TAG, "API Emp_code:: " + doctorinfo.get(i).getEmp_code());
                    Log.d(TAG, "API Login_name:: " + doctorinfo.get(i).getLogin_name());
                    Log.d(TAG, "API pwd:: " + doctorinfo.get(i).getPwd());
                    Log.d(TAG, "API Role_code:: " + doctorinfo.get(i).getRole_code());
                    Log.d(TAG, "API User_code:: " + doctorinfo.get(i).getUser_code());
                }
            }

            @Override
            public void onFailure(Call<DoctorLoginCollection> call, Throwable t) {
                Log.e(TAG, "ERROR:: " + t.getMessage());
                t.printStackTrace();
            }
        });
    }



    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btnLogin:
                if(RufaidaUtils.isConnected(context)){
                    getData(edtUserId.getText().toString(), edtPass.getText().toString());
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
