package hu.aut.bme.android.sharedprefererencesdemobme;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import java.util.Date;

public class MainActivity extends AppCompatActivity {

    public static final String KEY_USED_DATE = "KEY_USED_DATE";
    private TextView tvData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvData = (TextView) findViewById(R.id.tvData);
    }

    @Override
    protected void onStart() {
        super.onStart();
        showLastStartDate();
    }

    @Override
    protected void onStop() {
        super.onStop();
        saveLastUsedDate();
    }

    private void saveLastUsedDate() {
        SharedPreferences sp =
                PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor = sp.edit();
        editor.putString(KEY_USED_DATE,
                new Date(System.currentTimeMillis()).toString());
        editor.commit();
    }

    private void showLastStartDate() {
        SharedPreferences sp =
                PreferenceManager.getDefaultSharedPreferences(this);
        String date = sp.getString(KEY_USED_DATE, "this is the first time");
        tvData.setText(date);
    }
}
