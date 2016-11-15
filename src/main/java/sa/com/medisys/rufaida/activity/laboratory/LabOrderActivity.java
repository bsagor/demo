package sa.com.medisys.rufaida.activity.laboratory;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import sa.com.medisys.rufaida.R;
import sa.com.medisys.rufaida.activity.RufaidaMenuActivity;
import sa.com.medisys.rufaida.util.RufaidaPreferenceManager;
import sa.com.medisys.rufaida.util.RufaidaUtils;

public class LabOrderActivity extends AppCompatActivity {
    final String TAG = "LabOrderActivity";
    Context context;
    boolean hardwareBackControll;

    String user = "";

    TextView tvSetProfile;
    EditText edtPatientNo;

    RufaidaPreferenceManager memory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lab_order);
        setTitle("Laboratory Order");

        initialize();
    }

    private void initialize() {
        context = LabOrderActivity.this;

        memory = new RufaidaPreferenceManager(getApplicationContext());
        user = memory.getPref(memory.KEY_USER_TYPE);

        tvSetProfile = (TextView) findViewById(R.id.tvSetProfile);
        edtPatientNo = (EditText) findViewById(R.id.edtPatientNo);

        if(user.equals("1")){
            tvSetProfile.setText("Patient Lab Order");
        }

        if(user.equals("2")){
            edtPatientNo.setEnabled(false);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        if(user.equals("1")){
            inflater.inflate(R.menu.all_menu_but_sync, menu);
        } else {
            inflater.inflate(R.menu.all_menu, menu);
        }

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.logout:
                RufaidaUtils.logout(context);
                break;

            case R.id.back:
                RufaidaUtils.backToPrevious(context, new RufaidaMenuActivity());
                break;

            case R.id.home:
                RufaidaUtils.home(context);
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        if (hardwareBackControll) {
            super.onBackPressed();
            return;
        }
        this.hardwareBackControll = true;
        RufaidaUtils.backToPrevious(context, new RufaidaMenuActivity());
    }
}
