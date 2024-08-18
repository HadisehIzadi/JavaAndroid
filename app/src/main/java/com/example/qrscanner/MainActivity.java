package com.example.qrscanner;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.Manifest;
import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    Button scanbutton;
    public static final int CAMERA_REQUEST_PERMISSION = 1;
    public static final int SCANNING_REQUEST_CODE = 2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        scanbutton = findViewById(R.id.btnScann);


        scanbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

    }

    void StartScanningAvtivity()
    {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
            if (checkSelfPermission(Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED){
                //ask for the permision
                requestPermissions(new String[] {Manifest.permission.CAMERA},CAMERA_REQUEST_PERMISSION);
            }else {
                //start scanning
                startActivityForResult(new Intent(MainActivity.this,ScanningActivity.class),SCANNING_REQUEST_CODE);
            }
        }else {
            //start scanning
            startActivityForResult(new Intent(MainActivity.this,ScanningActivity.class),SCANNING_REQUEST_CODE);
        }
    }
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == CAMERA_REQUEST_PERMISSION){
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED){
                startActivityForResult(new Intent(MainActivity.this,ScanningActivity.class),SCANNING_REQUEST_CODE);
            }
        }
    }

    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
            super.onActivityResult(requestCode, resultCode, data);
            if(requestCode == SCANNING_REQUEST_CODE){
                if (resultCode == RESULT_OK){
                    scanbutton.setText(data.getStringExtra("scanning_result"));
                }
            }
        }
}