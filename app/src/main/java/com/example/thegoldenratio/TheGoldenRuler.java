package com.example.thegoldenratio;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;

import java.io.File;
import java.io.IOException;

public class TheGoldenRuler extends AppCompatActivity {

    public String currentPhotoPath;
    public Bitmap mSelectedImage;

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && requestCode == 1) {
            final Bitmap bitmap = BitmapFactory.decodeFile(currentPhotoPath);
            if (bitmap != null) {
                final ImageView im = findViewById(R.id.rulerCameraView);
                im.setVisibility(View.VISIBLE);
                mSelectedImage = Bitmap.createScaledBitmap(bitmap, im.getWidth(), im.getHeight(), true);
                im.setImageBitmap(mSelectedImage);
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_the_golden_ruler);


        if(ContextCompat.checkSelfPermission(TheGoldenRuler.this, Manifest.permission.CAMERA)!= PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(TheGoldenRuler.this, new String[]{
                    Manifest.permission.CAMERA
            }, 100);
        }
        ImageView cameraClick = findViewById(R.id.rulerFloatButton);
        cameraClick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String fileName = "photo";
                File storageDirectory = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
                try{
                    File imageFile = File.createTempFile(fileName, ".jpg", storageDirectory);
                    currentPhotoPath = imageFile.getAbsolutePath();
                    Uri imageUri = FileProvider.getUriForFile(TheGoldenRuler.this, "com.example.thegoldenratio.fileprovider", imageFile);
                    Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
                    startActivityForResult(intent, 1);
                }catch (IOException e){
                    e.printStackTrace();
                }
            }
        });
    }

}