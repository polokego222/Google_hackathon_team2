package com.example.plants;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.provider.MediaStore;
import android.os.Bundle;
import android.view.View;
import android.util.*;

import org.jetbrains.annotations.Nullable;

public class MainActivity extends AppCompatActivity {


    private static int CAMERA_PERMISSION_CODE = 100;
    private static int VIDEO_RECORD_CODE = 101;
    private Uri videoPath;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if(isCameraFound()) {
            Log.i("VIDEO_RECORD_TAG","The phone has a camera");
            getCameraPermission();
        } else {

            Log.i("VIDEO_RECORD_TAG","The  phone hasd no camera");

        }
    }


    private void recordVid() {

        Intent intent = new Intent(MediaStore.ACTION_VIDEO_CAPTURE);
        startActivityForResult(intent, VIDEO_RECORD_CODE);

    }


    private Boolean isCameraFound() {
         if (getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA_ANY)) {
             return true;
         } else{

             return false;
         }


    }


    private void getCameraPermission() {

        if (ContextCompat.checkSelfPermission(this,  android.Manifest.permission.CAMERA) == PackageManager.PERMISSION_DENIED ){
            ActivityCompat.requestPermissions(this, new String[]{android.Manifest.permission.CAMERA}, CAMERA_PERMISSION_CODE);
        }
    }

    public void launchApp(View view){
        recordVid();

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data){
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == VIDEO_RECORD_CODE) {
            if (resultCode == RESULT_OK) {
                videoPath = data.getData();
                Log.i("VIDEO_RECORD_TAG", "Video is recorded and available at  path ");

            } else if (resultCode == RESULT_CANCELED) {

                Log.i("VIDEO_RECORD_TAG", "Record video Video has get some error");

            }
        }

    }
}