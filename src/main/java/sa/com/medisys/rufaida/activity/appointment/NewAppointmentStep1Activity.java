package sa.com.medisys.rufaida.activity.appointment;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import sa.com.medisys.rufaida.R;
import sa.com.medisys.rufaida.activity.LogoutActivity;
import sa.com.medisys.rufaida.activity.RufaidaMenuActivity;
import sa.com.medisys.rufaida.util.RufaidaUtils;

public class NewAppointmentStep1Activity extends AppCompatActivity implements View.OnClickListener {
    final String TAG = "NewAppointmentStep1Activity";
    Context context;
    boolean hardwareBackControll;

    ImageButton btnNext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_appointment_step1);
        setTitle("New Appointment Step - 1");

        initialize();
    }

    private void initialize() {
        context = NewAppointmentStep1Activity.this;
        btnNext = (ImageButton) findViewById(R.id.btnNext);
        btnNext.setOnClickListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.back_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()){
            case R.id.backMenu:
                RufaidaUtils.backToPrevious(context, new AppointmentMenuActivity());
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btnNext:
                Intent newAppointmentStep2ActivityIntent = new Intent(NewAppointmentStep1Activity.this, NewAppointmentStep2Activity.class);
                newAppointmentStep2ActivityIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(newAppointmentStep2ActivityIntent);
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
        RufaidaUtils.backToPrevious(context, new AppointmentMenuActivity());
    }
}
