package com.example.thegoldenratio;

import androidx.appcompat.app.AppCompatActivity;
import androidx.loader.content.Loader;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;

import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Protocol;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;

public class faceRecognition extends AppCompatActivity {

    private static final String subscriptionKey = "1bbcc1273dc742fa9bbc495dd2259cbb";

    private static final String uriBase =
            "https://goldenratioonface.cognitiveservices.azure.com/face/v1.0/detect?returnFaceId=true&returnFaceLandmarks=true";


    private static final String imageWithFaces =
            "{\"url\":\"https://s.yimg.com/ny/api/res/1.2/2cQ_zadvzJfQ4BJxTzptww--~A/YXBwaWQ9aGlnaGxhbmRlcjtzbT0xO3c9NzQ0O2g9OTUw/http://media.zenfs.com/en/homerun/feed_manager_auto_publish_494/cae8ffc764d9c3d6fec857e1344aab8e\"}";

    private static final String faceAttributes =
            "age,gender,headPose,smile,facialHair,glasses,emotion,hair,makeup,occlusion,accessories,blur,exposure,noise";

    private static final String TAG = "TextGraphic";
    private static final int TEXT_COLOR = Color.RED;
    private static final float TEXT_SIZE = 54.0f;
    private static final float STROKE_WIDTH = 4.0f;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_face_recognition);
        final OkHttpClient client = new OkHttpClient();

        AsyncTask<Void, Void, Void> task = new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... voids) {

                URL url = null;
                try {
                    url = new URL(uriBase);
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                }


                MediaType mediaType = MediaType.parse("application/json");
                RequestBody body = RequestBody.create(mediaType,
                        imageWithFaces);
                Request request = new Request.Builder()
                        .url(uriBase).post(body)
                        .addHeader("Ocp-Apim-Subscription-Key", subscriptionKey)
                        .addHeader("Ocp-Apim-Subscription-Region", "centralindia")
                        .addHeader("Content-type", "application/json")
                        .build();
                try {
                    client.setProtocols(Arrays.asList(Protocol.HTTP_1_1));


                    Response response = client.newCall(request).execute();
                    String reu = response.body().string();
                    Log.i("result", reu);
                } catch (Exception e) {
                    Log.i("result", String.valueOf(e));

                }
                return null;
            }
        }.execute();

        ImageView myImageView = findViewById(R.id.imageView);
        Bitmap myBitmap = BitmapFactory.decodeResource(getApplicationContext().getResources(), R.drawable.monalisa);
        Paint myRectPaint;
        myRectPaint = new Paint();
        myRectPaint.setColor(TEXT_COLOR);
        myRectPaint.setStyle(Paint.Style.STROKE);
        myRectPaint.setStrokeWidth(STROKE_WIDTH);
        int x1 = 55;
        int y1 = 55;
        int x2 = 1500;
        int y2 = 900;

//Create a new image bitmap and attach a brand new canvas to it
        Bitmap tempBitmap = Bitmap.createBitmap(myBitmap.getWidth(), myBitmap.getHeight(), Bitmap.Config.RGB_565);
        Canvas tempCanvas = new Canvas(tempBitmap);

//Draw the image bitmap into the cavas
        tempCanvas.drawBitmap(myBitmap, 0, 0, null);

//Draw everything else you want into the canvas, in this example a rectangle with rounded edges
        tempCanvas.drawRoundRect(new RectF(x1,y1,x2,y2), 2, 2, myRectPaint);

//Attach the canvas to the ImageView
        myImageView.setImageDrawable(new BitmapDrawable(getResources(), tempBitmap));





    }
}
