package hu.bme.android.activitydemo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.Date;

public class MainActivity extends AppCompatActivity {

    public static final String TAG_ACTIVITY = "TAG_ACTIVITY";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d(TAG_ACTIVITY, "onCreate called");

        Button btnPress = (Button) findViewById(R.id.btnPress);
        btnPress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this,
                        "Hello az idő: "+new Date(System.currentTimeMillis()),
                        Toast.LENGTH_LONG).show();

                /*Intent intentStart = new Intent();
                intentStart.setClass(MainActivity.this, DetailsActivity.class);
                startActivity(intentStart);*/

                startActivity(new Intent(MainActivity.this, DetailsActivity.class));
                //finish();
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        //Toast.makeText(this, "HAHA sose lépsz ki", Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG_ACTIVITY, "onStart called");
    }

    @Override
    protected void onResume() {
        super.onResume();

        Log.d(TAG_ACTIVITY, "onResume called");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG_ACTIVITY, "onPause called");
    }

    @Override
    protected void onStop() {
        super.onStop();

        Log.d(TAG_ACTIVITY, "onStop called");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        Log.d(TAG_ACTIVITY, "onDestroy called");
    }
}
