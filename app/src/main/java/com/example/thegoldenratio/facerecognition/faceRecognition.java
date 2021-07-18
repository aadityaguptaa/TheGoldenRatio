package com.example.thegoldenratio.facerecognition;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import com.example.thegoldenratio.R;
import com.example.thegoldenratio.databinding.ActivityFaceRecognitionBinding;

import java.io.File;
import java.io.IOException;

public class faceRecognition extends AppCompatActivity {


    private static final float STROKE_WIDTH = 6.0f;

    private FaceRecViewModel viewModel;
    private ActivityFaceRecognitionBinding binding;

    public int index = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getPermissions();

        binding = DataBindingUtil.setContentView(this, R.layout.activity_face_recognition);
        viewModel = new ViewModelProvider(this).get(FaceRecViewModel.class);

        binding.faceRecCameraFloatButton.setOnClickListener(v -> launchCameraIntent());
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);

        if (resultCode == RESULT_OK && requestCode == 1) {
            final Bitmap bitmap = BitmapFactory.decodeFile(viewModel.currentPhotoPath);
            if (bitmap != null) {
                binding.imageView.setVisibility(View.VISIBLE);
                viewModel.mSelectedImage = Bitmap.createScaledBitmap(bitmap, binding.imageView.getWidth(), binding.imageView.getHeight(), true);
                binding.imageView.setImageBitmap(viewModel.mSelectedImage);

                viewModel.analyzeImage();

                try {
                    viewModel.analyzeJson(viewModel.resultString);
                    binding.forwardArrow.setOnClickListener(v -> {
                        index++;
                        if(index > 5) index = 1;
                        changeRectangle(index);
                    });
                    binding.backWardArrow.setOnClickListener(v -> {
                        index--;
                        if(index < 1) index = 5;
                        changeRectangle(index);
                    });
                }catch (Exception e){
                    Log.i("result", e.toString());
                }
            }
        }
    }

    public void getPermissions(){
        if(ContextCompat.checkSelfPermission(faceRecognition.this, Manifest.permission.CAMERA)!= PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(faceRecognition.this, new String[]{
                    Manifest.permission.CAMERA
            }, 100);
        }
    }

    public void launchCameraIntent(){
        String fileName = "photo";
        File storageDirectory = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        try{
            File imageFile = File.createTempFile(fileName, ".jpg", storageDirectory);
            viewModel.currentPhotoPath = imageFile.getAbsolutePath();
            Uri imageUri = FileProvider.getUriForFile(faceRecognition.this, "com.example.thegoldenratio.fileprovider", imageFile);
            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
            startActivityForResult(intent, 1);
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public void changeRectangle(int position){
        String text;
        TextView faceRecTextView = findViewById(R.id.faceRecTextView);

        final ImageView myImageView = binding.imageView;
        final Bitmap myBitmap = viewModel.mSelectedImage;
        final Paint myRectPaint;
        myRectPaint = new Paint();
        myRectPaint.setColor(Color.GREEN);
        myRectPaint.setStyle(Paint.Style.STROKE);
        myRectPaint.setStrokeWidth(STROKE_WIDTH);
        final float scale = getResources().getDisplayMetrics().density;

        final Bitmap tempBitmap = Bitmap.createBitmap(myBitmap.getWidth(), myBitmap.getHeight(), Bitmap.Config.RGB_565);
        final Canvas tempCanvas = new Canvas(tempBitmap);

        tempCanvas.drawBitmap(myBitmap, 0, 0, null);

        switch(position) {
            //for both eye and nose golden rectangle
            case(1):
                faceRecTextView.setText(viewModel.typeOfGoldenRatioText[0]);
                tempCanvas.drawRoundRect(new RectF((int) viewModel.faceCoordinates.noseTipX, (int) viewModel.faceCoordinates.eyeRightOuterY, (int) viewModel.faceCoordinates.eyeRightOuterX, (int) viewModel.faceCoordinates.noseTipY), 2, 2, myRectPaint);
                tempCanvas.drawLine((int) viewModel.faceCoordinates.eyeRightInnerX, (int) viewModel.faceCoordinates.eyeRightOuterY, (int) viewModel.faceCoordinates.eyeRightInnerX, (int) viewModel.faceCoordinates.noseTipY, myRectPaint);
                break;
            case(2):
                //for pupils and chin rectangle
                faceRecTextView.setText(viewModel.typeOfGoldenRatioText[1]);
                myRectPaint.setColor(Color.CYAN);
                double distBetweenPupils = viewModel.faceCoordinates.pupilRightX - viewModel.faceCoordinates.pupilLeftX;
                int rectHeight = (int)((distBetweenPupils)*(1.618));
                double smallRectHeight = distBetweenPupils/1.618;
                double smallRectY = viewModel.faceCoordinates.pupilLeftY + (rectHeight - smallRectHeight);
                tempCanvas.drawRoundRect(new RectF((int) viewModel.faceCoordinates.pupilLeftX, (int) viewModel.faceCoordinates.pupilLeftY, (int) viewModel.faceCoordinates.pupilRightX, (int) viewModel.faceCoordinates.pupilLeftY +rectHeight), 2, 2, myRectPaint);
                tempCanvas.drawLine((int) viewModel.faceCoordinates.pupilLeftX, (int)smallRectY, (int) viewModel.faceCoordinates.pupilRightX,  (int)smallRectY, myRectPaint);
                break;

            case(3):
                //for lips rect
                faceRecTextView.setText(viewModel.typeOfGoldenRatioText[2]);
                double lipsRectBreadth = viewModel.faceCoordinates.mouthRightX - viewModel.faceCoordinates.mouthLeftX;
                double lipsRectHeight = lipsRectBreadth/1.618;
                double lipsRectY = viewModel.faceCoordinates.mouthLeftY - lipsRectHeight/2;
                double lipsRectX = viewModel.faceCoordinates.mouthLeftX;
                double lipsRectXdas = viewModel.faceCoordinates.mouthRightX;
                double lipsRectYdas = lipsRectY+lipsRectHeight;
                double smallRectX = lipsRectX + (lipsRectBreadth/2.618);
                myRectPaint.setColor(Color.MAGENTA);
                tempCanvas.drawRoundRect(new RectF((int)lipsRectX, (int)lipsRectY, (int)lipsRectXdas, (int)lipsRectYdas), 2, 2, myRectPaint);
                tempCanvas.drawLine((float)smallRectX, (float)lipsRectY, (float)smallRectX, (float)lipsRectYdas, myRectPaint);
                break;
            case(4):
                //for pupilNoseRect
                faceRecTextView.setText(viewModel.typeOfGoldenRatioText[3]);
                double pnX = viewModel.faceCoordinates.pupilLeftX;
                double pnY = viewModel.faceCoordinates.pupilLeftY;
                double pnRectBreadth = viewModel.faceCoordinates.pupilRightX - viewModel.faceCoordinates.pupilLeftX;
                double pnRectHeight = pnRectBreadth/1.618;
                double pnXdas = viewModel.faceCoordinates.pupilRightX;
                double pnYdas = pnY+pnRectHeight;
                double smallpnRectY = pnRectHeight/2.618;
                myRectPaint.setColor(Color.BLUE);
                tempCanvas.drawRoundRect(new RectF((int)pnX, (int)pnY, (int)pnXdas, (int)pnYdas), 2, 2, myRectPaint);
                tempCanvas.drawLine((float)pnX, (float)(pnYdas-smallpnRectY), (float)pnXdas, (float)(pnYdas-smallpnRectY), myRectPaint);
                break;
            case(5):
                //for center-inner-width
                faceRecTextView.setText(viewModel.typeOfGoldenRatioText[4]);
                double ciwSmallRectX = viewModel.faceCoordinates.eyeLeftOuterX;
                double ciwSmallRectY = viewModel.faceCoordinates.eyeLeftOuterY;
                double ciwSmallRectDista = viewModel.faceCoordinates.noseTipX - viewModel.faceCoordinates.eyeLeftOuterX;
                double ciwSmallRectDistb = ciwSmallRectDista/1.618;
                double ciwRectX = viewModel.faceCoordinates.eyeLeftOuterX - ciwSmallRectDistb;
                double ciwRectY = viewModel.faceCoordinates.eyeLeftOuterY;
                double ciwRectBreadth = viewModel.faceCoordinates.noseTipX - ciwRectX;
                double ciwRectHeight = ciwRectBreadth * 1.618;
                double ciwRectXdas = ciwRectX + ciwRectBreadth;
                double ciwRectYdas = ciwRectY + ciwRectHeight;
                myRectPaint.setColor(Color.RED);
                tempCanvas.drawRoundRect(new RectF((int)ciwRectX, (int)ciwRectY, (int)ciwRectXdas, (int)ciwRectYdas), 2, 2, myRectPaint);
                tempCanvas.drawLine((float)ciwSmallRectX, (float)ciwSmallRectY, (float)ciwSmallRectX, (float)ciwSmallRectY + (float)ciwRectHeight, myRectPaint);
                break;
        }
        myImageView.setImageDrawable(new BitmapDrawable(getResources(), tempBitmap));

    }

}
