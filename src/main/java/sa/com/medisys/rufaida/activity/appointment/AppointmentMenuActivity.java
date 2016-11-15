package sa.com.medisys.rufaida.activity.appointment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import sa.com.medisys.rufaida.R;
import sa.com.medisys.rufaida.activity.LogoutActivity;
import sa.com.medisys.rufaida.activity.RufaidaMenuActivity;
import sa.com.medisys.rufaida.util.RufaidaAlertDialog;
import sa.com.medisys.rufaida.util.RufaidaPreferenceManager;
import sa.com.medisys.rufaida.util.RufaidaUtils;

public class AppointmentMenuActivity extends AppCompatActivity {

    final String TAG = "AppointmentMenuActivity";
    Context context;
    boolean hardwareBackControll;

    String user = "";

    ImageButton imgBtnNewAppointment;
    TextView tvNewAppointment;

    RufaidaPreferenceManager memory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_appointment);
        setTitle("Appointment");

        initialize();
    }

    private void initialize() {
        context = AppointmentMenuActivity.this;

        memory = new RufaidaPreferenceManager(getApplicationContext());
        user = memory.getPref(memory.KEY_USER_TYPE);

        imgBtnNewAppointment = (ImageButton) findViewById(R.id.imgBtnNewAppointment);
        tvNewAppointment = (TextView) findViewById(R.id.tvNewAppointment);

        if(user.equals("1")){
            imgBtnNewAppointment.setVisibility(View.GONE);
            tvNewAppointment.setVisibility(View.GONE);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()){
            case R.id.logout:
                RufaidaUtils.logout(context);
                break;

            case R.id.home:
                RufaidaUtils.home(context);
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    public void searchAppointment(View view) {
        Intent searchAppointmentActivityIntent = new Intent(AppointmentMenuActivity.this, SearchAppointmentActivity.class);
        searchAppointmentActivityIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(searchAppointmentActivityIntent);
        finish();
    }

    public void newAppointment(View view) {
        if(RufaidaUtils.isConnected(context)){
            Intent newAppointmentStep1ActivityIntent = new Intent(AppointmentMenuActivity.this, NewAppointmentStep1Activity.class);
            newAppointmentStep1ActivityIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(newAppointmentStep1ActivityIntent);
            finish();
        } else {
            RufaidaAlertDialog.showErrorAlertDialog(context, RufaidaAlertDialog.INTERNET_CONN_FAILURE_TITLE, RufaidaAlertDialog.INTERNET_CONN_FAILURE_MSG, false);
        }
    }

    @Override
    public void onBackPressed() {
        if (hardwareBackControll) {
            super.onBackPressed();
            return;
        }
        this.hardwareBackControll = true;
        RufaidaUtils.home(context);
    }
}
