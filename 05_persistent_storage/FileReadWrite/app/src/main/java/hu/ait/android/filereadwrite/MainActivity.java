package hu.ait.android.filereadwrite;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    public static final int REQUEST_CODE_WRITE_PERM = 401;
    private TextView tvData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvData = (TextView) findViewById(R.id.tvData);

        Button btnWriteFile = (Button) findViewById(R.id.btnWriteFile);
        btnWriteFile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                writeFile("Hello: "+new Date(System.currentTimeMillis()).toString());
            }
        });
        Button btnReadFile = (Button) findViewById(R.id.btnReadFile);
        btnReadFile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String data = readFile();
                tvData.setText(data);
            }
        });

        requestNeededPermission();
    }

    public void requestNeededPermission() {
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
                Toast.makeText(MainActivity.this, "I need it for File", Toast.LENGTH_SHORT).show();
            }

            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                    REQUEST_CODE_WRITE_PERM);
        } else {
            Toast.makeText(this, "Already have permission", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {
            case REQUEST_CODE_WRITE_PERM: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    Toast.makeText(MainActivity.this, "WRITE_EXTERNAL_STORAGE perm granted", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MainActivity.this, "WRITE_EXTERNAL_STORAGE perm NOT granted", Toast.LENGTH_SHORT).show();
                }
                return;
            }
        }
    }

    private void writeFile(String data) {
        String file = Environment.getExternalStorageDirectory()+
                "/test.txt";

        Toast.makeText(this,file,Toast.LENGTH_LONG).show();

        FileOutputStream os = null;
        try {

            os = new FileOutputStream(file);
            os.write(data.getBytes());
            os.flush();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (os != null) {
                try {
                    os.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private String readFile() {
        String result = "";

        String file = Environment.getExternalStorageDirectory()+
                "/test.txt";
        FileInputStream is = null;
        try {

            is = new FileInputStream(file);

            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            int ch;
            while ((ch=is.read()) != -1) {
                bos.write(ch);
            }

            result = bos.toString();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (is != null) {
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return result;
    }
}
