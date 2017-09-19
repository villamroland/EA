package hu.ait.android.layoutinflatersimpledemo;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Button btnPress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final LinearLayout layoutContent = (LinearLayout) findViewById(
                R.id.layoutContent);
        btnPress = (Button) findViewById(R.id.btnPress);
        btnPress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final View viewDetails =
                        getLayoutInflater().inflate(
                        R.layout.layout_details, null
                );

                Button btnSave = viewDetails.findViewById(
                        R.id.btnSave);
                final EditText etName = viewDetails.findViewById(
                        R.id.etName);

                btnSave.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (TextUtils.isEmpty(etName.getText())) {
                            etName.setError("Ez ne legyen már üres");
                        } else {

                            btnPress.setEnabled(true);
                            layoutContent.removeView(viewDetails);
                        }
                    }
                });

                layoutContent.addView(viewDetails);

                //btnPress.setEnabled(false);

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        
        if (item.getItemId() == R.id.actionAbout) {
            Toast.makeText(this, "About application", Toast.LENGTH_SHORT).show();

            showAlertMessage("This is the alert message");
        } else if (item.getItemId() == R.id.actionHelp) {
            showPopupHelp();
        }
        
        return true;
    }

    private void showPopupHelp() {
        View popupView = getLayoutInflater().inflate(
                R.layout.layout_popup, null);
        final PopupWindow popupWindow = new PopupWindow(popupView,
                LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);

        Button btnDismiss =
                (Button) popupView.findViewById(R.id.btnPopUpOk);
        btnDismiss.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {
                popupWindow.dismiss();
            }
        });

        popupWindow.showAsDropDown(btnPress, 50, -30);

    }

    private void showAlertMessage(final String aMessage) {
        AlertDialog.Builder alertbox =
                new AlertDialog.Builder(this);
        alertbox.setMessage(aMessage);
        alertbox.setNeutralButton("Ok",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface arg0,
                                        int arg1) {
                        // Ok kiválasztva
                    }
                });
        alertbox.show();
    }

}
