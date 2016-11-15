package sa.com.medisys.rufaida.activity.appointment;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import sa.com.medisys.rufaida.R;
import sa.com.medisys.rufaida.util.RufaidaAlertDialog;
import sa.com.medisys.rufaida.util.RufaidaUtils;

public class NewAppointmentStep2Activity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = "NewAppointmentStep2";
    Button btnSubmit;
    Context context;
    boolean hardwareBackControll;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_appointment_step2);
        setTitle("New Appointment Step - 2");

        initialize();
    }

    private void initialize() {
        context = NewAppointmentStep2Activity.this;
        btnSubmit = (Button) findViewById(R.id.btnSubmit);
        btnSubmit.setOnClickListener(this);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.cencel_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()){
            case R.id.cencelMenu:
                RufaidaUtils.backToPrevious(context, new NewAppointmentStep1Activity());
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btnSubmit:
                if(RufaidaUtils.isConnected(context)){
                    RufaidaAlertDialog.showSuccessAlertDialogs(context, "Congratulation", "Appointment Successfully Created", false, new SearchAppointmentActivity());
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
        RufaidaAlertDialog.showRequireAlertDialog(context, RufaidaAlertDialog.HARDWARE_BACK_TITLE, "Please use x button on upper-right.\nOtherwise submit this.", false);
    }
}
