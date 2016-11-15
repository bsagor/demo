package sa.com.medisys.rufaida.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import sa.com.medisys.rufaida.R;

public class MainActivity extends Activity implements View.OnClickListener{
    ImageView imgIV1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initialize();

    }

    private void initialize() {
        imgIV1 = (ImageView) findViewById(R.id.imgIV1);
        imgIV1.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.imgIV1:
                Intent userSelectionActivityIntent = new Intent(MainActivity.this, UserSelectionActivity.class);
                startActivity(userSelectionActivityIntent);
                finish();
                break;
        }
    }
}
