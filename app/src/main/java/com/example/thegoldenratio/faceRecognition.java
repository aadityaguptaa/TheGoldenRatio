package com.example.thegoldenratio;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.drawable.BitmapDrawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.util.TypedValue;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.common.io.ByteStreams;
import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Protocol;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;

public class faceRecognition extends AppCompatActivity {

    private static final String subscriptionKey = "1bbcc1273dc742fa9bbc495dd2259cbb";

    private static final String uriBase =
            "https://goldenratioonface.cognitiveservices.azure.com/face/v1.0/detect?returnFaceId=true&returnFaceLandmarks=true";


    private static final String imageWithFaces =
            "{\"url\":\"https://hips.hearstapps.com/hmg-prod.s3.amazonaws.com/images/elm120117wlcoverstory-011copy-1509935070.jpg\"}";

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



        AsyncTask<Void, Void, String> task = new AsyncTask<Void, Void, String>() {
            @Override
            protected String doInBackground(Void... voids) {

                URL url = null;
                try {
                    url = new URL(uriBase);
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                }
                ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
                Bitmap myBitmap = BitmapFactory.decodeResource(getApplicationContext().getResources(), R.drawable.jennif);
                myBitmap.compress(Bitmap.CompressFormat.JPEG, 100, outputStream);
                ByteArrayInputStream inputStream = new ByteArrayInputStream(outputStream.toByteArray());
                byte[] localImageBytes;
                try {
                    localImageBytes = ByteStreams.toByteArray(inputStream);
                    MediaType mediaType = MediaType.parse("application/octet-stream");
                    RequestBody body = RequestBody.create(mediaType,
                            localImageBytes);
                    Request request = new Request.Builder()
                            .url(uriBase).post(body)
                            .addHeader("Ocp-Apim-Subscription-Key", subscriptionKey)
                            .addHeader("Ocp-Apim-Subscription-Region", "centralindia")
                            .addHeader("Content-type", "application/json")
                            .build();
                    client.setProtocols(Arrays.asList(Protocol.HTTP_1_1));


                    Response response = client.newCall(request).execute();
                    String reu = response.body().string();
                    Log.i("result", reu);
                    return reu;
                } catch (IOException e) {
                    e.printStackTrace();
                }

                return "";
            }
        };
        String result ="";
        try {
            result = task.execute().get();
        }catch (Exception e){
            Log.i("result", e.toString());
        }
        try {
            JSONArray output = new JSONArray(result);
            JSONObject one = output.getJSONObject(0);
            JSONObject faceRectangle = one.getJSONObject("faceRectangle");
            double top = faceRectangle.getDouble("top");
            double left = faceRectangle.getDouble("left");
            double width = faceRectangle.getDouble("width");
            double height = faceRectangle.getDouble("height");

            JSONObject faceLandmarks = one.getJSONObject("faceLandmarks");
            JSONObject noseTip = faceLandmarks.getJSONObject("noseTip");
            double noseTipX = noseTip.getDouble("x");
            double noseTipY = noseTip.getDouble("y");
            JSONObject eyeRightInner = faceLandmarks.getJSONObject("eyeRightInner");
            double eyeRightInnerX = eyeRightInner.getDouble("x");
            double eyeRightInnerY = eyeRightInner.getDouble("y");
            JSONObject eyeRightOuter = faceLandmarks.getJSONObject("eyeRightOuter");
            double eyeRightOuterX = eyeRightOuter.getDouble("x");
            double eyeRightOuterY = eyeRightOuter.getDouble("y");

            ImageView myImageView = findViewById(R.id.imageView);
            Bitmap myBitmap = BitmapFactory.decodeResource(getApplicationContext().getResources(), R.drawable.jennif);
            Paint myRectPaint;
            myRectPaint = new Paint();
            myRectPaint.setColor(TEXT_COLOR);
            myRectPaint.setStyle(Paint.Style.STROKE);
            myRectPaint.setStrokeWidth(STROKE_WIDTH);
            int x1 = (int)noseTipX;
            int y1 = (int)eyeRightOuterY;
            int x2 = (int)eyeRightOuterX;
            int y2 = (int)noseTipY;
            Log.i("result", x1 + " " + y1 + " " + x2 + " " + y2);

            Bitmap tempBitmap = Bitmap.createBitmap(myBitmap.getWidth(), myBitmap.getHeight(), Bitmap.Config.RGB_565);
            Log.i("result", myBitmap.getWidth() + " "+ myBitmap.getHeight());
            Canvas tempCanvas = new Canvas(tempBitmap);

            tempCanvas.drawBitmap(myBitmap, 0, 0, null);

            tempCanvas.drawRoundRect(new RectF(x1,y1,x2,y2), 2, 2, myRectPaint);

            myImageView.setImageDrawable(new BitmapDrawable(getResources(), tempBitmap));
        }catch (Exception e){
            Log.i("result", e.toString());
        }








    }
    public static float px2dp(Resources resource, float px)  {
        return (float) TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_PX,
                px,
                resource.getDisplayMetrics()
        );
    }
}
