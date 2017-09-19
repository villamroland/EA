package hu.ait.android.statesavedemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.util.Date;

public class MainActivity extends AppCompatActivity {

    public static final String KEY_DATE = "KEY_DATE";
    private String startDateTime = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView tvDate = (TextView) findViewById(R.id.tvStartDate);

        if (savedInstanceState != null) {
            startDateTime = savedInstanceState.getString(KEY_DATE);
        } else {
            startDateTime = new Date(
                    System.currentTimeMillis()).toString();
        }

        tvDate.setText(startDateTime);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putString(KEY_DATE, startDateTime);


        super.onSaveInstanceState(outState);
    }
}
