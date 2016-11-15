package sa.com.medisys.rufaida.activity.appointment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import sa.com.medisys.rufaida.R;
import sa.com.medisys.rufaida.activity.LogoutActivity;
import sa.com.medisys.rufaida.activity.RufaidaMenuActivity;
import sa.com.medisys.rufaida.util.RufaidaUtils;

public class SearchAppointmentActivity extends AppCompatActivity {
    final String TAG = "SearchAppointmentActivity";
    Context context;
    boolean hardwareBackControll;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_appointment);
        setTitle("Show Appointment");

        context = SearchAppointmentActivity.this;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.sync_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()){
            case R.id.logout:
                /*Intent logoutActivityIntent = new Intent(SearchAppointmentActivity.this, LogoutActivity.class);
                logoutActivityIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(logoutActivityIntent);
                finish();*/
                RufaidaUtils.logout(context);
                break;

            case R.id.home:
                /*Intent rufaidaMenuActivityIntent = new Intent(SearchAppointmentActivity.this, RufaidaMenuActivity.class);
                startActivity(rufaidaMenuActivityIntent);
                finish();*/
                RufaidaUtils.home(context);
                break;

            case R.id.sync:
                Toast.makeText(SearchAppointmentActivity.this, "Sync is Running", Toast.LENGTH_SHORT).show();
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
        RufaidaUtils.backToPrevious(context, new AppointmentMenuActivity());
    }
}
