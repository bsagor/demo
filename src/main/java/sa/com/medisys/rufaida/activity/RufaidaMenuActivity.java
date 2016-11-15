package sa.com.medisys.rufaida.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import sa.com.medisys.rufaida.R;
import sa.com.medisys.rufaida.activity.laboratory.LabOrderActivity;
import sa.com.medisys.rufaida.activity.radiology.RadiologyOrderActivity;
import sa.com.medisys.rufaida.activity.appointment.AppointmentMenuActivity;
import sa.com.medisys.rufaida.util.RufaidaAlertDialog;
import sa.com.medisys.rufaida.util.RufaidaPreferenceManager;
import sa.com.medisys.rufaida.util.RufaidaUtils;

public class RufaidaMenuActivity extends AppCompatActivity implements View.OnClickListener{
    final String TAG = "RufaidaMenuActivity";
    Context context;

    String user = "";

    TextView tvAppointment;
    TextView tvLaboratory;
    TextView tvRadiology;
    TextView tvPharmacy;
    TextView tvMedication;
    ImageButton appointmentImgBtn;
    ImageButton imgBtnLaboratory;
    ImageButton imgBtnRadiology;
    ImageButton imgBtnPharmacy;
    ImageButton imgBtnMedication;
    boolean hardwareBackControll;

    RufaidaPreferenceManager memory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rufaida_menu);
        setTitle(R.string.app_title);

        initialize();
    }

    private void initialize() {
        context = RufaidaMenuActivity.this;

        memory = new RufaidaPreferenceManager(getApplicationContext());
        user = memory.getPref(memory.KEY_USER_TYPE);

        tvAppointment = (TextView) findViewById(R.id.tvAppointment);
        tvLaboratory = (TextView) findViewById(R.id.tvLaboratory);
        tvRadiology = (TextView) findViewById(R.id.tvRadiology);
        tvPharmacy = (TextView) findViewById(R.id.tvPharmacy);
        tvMedication = (TextView) findViewById(R.id.tvMedication);

        appointmentImgBtn = (ImageButton) findViewById(R.id.imgBtnAppointment);
        appointmentImgBtn.setOnClickListener(this);

        imgBtnLaboratory = (ImageButton) findViewById(R.id.imgBtnLaboratory);
        imgBtnLaboratory.setOnClickListener(this);

        imgBtnRadiology = (ImageButton) findViewById(R.id.imgBtnRadiology);
        imgBtnRadiology.setOnClickListener(this);

        imgBtnPharmacy = (ImageButton) findViewById(R.id.imgBtnPharmacy);
        imgBtnPharmacy.setOnClickListener(this);

        imgBtnMedication = (ImageButton) findViewById(R.id.imgBtnMedication);
        imgBtnMedication.setOnClickListener(this);

        if(user.equals("1")){
            imgBtnPharmacy.setVisibility(View.GONE);
            tvPharmacy.setVisibility(View.GONE);
            imgBtnMedication.setVisibility(View.GONE);
            tvMedication.setVisibility(View.GONE);
        }

        if(user.equals("2")){
            imgBtnPharmacy.setVisibility(View.GONE);
            tvPharmacy.setVisibility(View.GONE);
        }

        if(user.equals("3")){
            appointmentImgBtn.setVisibility(View.GONE);
            tvAppointment.setVisibility(View.GONE);
            imgBtnLaboratory.setVisibility(View.GONE);
            tvLaboratory.setVisibility(View.GONE);
            imgBtnRadiology.setVisibility(View.GONE);
            tvRadiology.setVisibility(View.GONE);
            imgBtnMedication.setVisibility(View.GONE);
            tvMedication.setVisibility(View.GONE);
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.home_menu, menu);
        return true;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.imgBtnAppointment:
                Intent appointmentActivityIntent = new Intent(RufaidaMenuActivity.this, AppointmentMenuActivity.class);
                appointmentActivityIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(appointmentActivityIntent);
                finish();
                break;

            case R.id.imgBtnLaboratory:
                Intent laboratoryActivityIntent = new Intent(RufaidaMenuActivity.this, LabOrderActivity.class);
                laboratoryActivityIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(laboratoryActivityIntent);
                finish();
                break;

            case R.id.imgBtnRadiology:
                Intent radiologyActivityIntent = new Intent(RufaidaMenuActivity.this, RadiologyOrderActivity.class);
                radiologyActivityIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(radiologyActivityIntent);
                finish();
                break;
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.logout:
                RufaidaUtils.logout(context);
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
        RufaidaAlertDialog.showRequireAlertDialog(context, RufaidaAlertDialog.HARDWARE_BACK_TITLE, RufaidaAlertDialog.HARDWARE_BACK_MSG, false);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                hardwareBackControll = false;
            }
        },200);
    }
}
