package com.example.thegoldenratio.facerecognition;

import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.util.Log;

import androidx.lifecycle.ViewModel;

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
import java.util.concurrent.ExecutionException;

public class FaceRecViewModel extends ViewModel {

    public final String subscriptionKey = "1bbcc1273dc742fa9bbc495dd2259cbb";

    public final String uriBase =
            "https://goldenratioonface.cognitiveservices.azure.com/face/v1.0/detect?returnFaceId=true&returnFaceLandmarks=true";
    final FaceCoordinates faceCoordinates = new FaceCoordinates();

    public String currentPhotoPath;

    public String[] typeOfGoldenRatioText = {"Eye corners to nose",
            "Pupils to nose to chin",
            "Cupid's bow tip to width of Lips",
            "Pupils to nose flare to nose bottom",
            "Center to inner outside of eye to width of face"};

    public String resultString;
    public Bitmap mSelectedImage;


    public void analyzeImage(){
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
                Bitmap myBitmap = mSelectedImage;
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

        try {
            resultString =  task.execute().get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void analyzeJson(String result){
        try {
            JSONArray output = new JSONArray(result);
            JSONObject one = output.getJSONObject(0);

            JSONObject faceLandmarks = one.getJSONObject("faceLandmarks");

            JSONObject noseTip = faceLandmarks.getJSONObject("noseTip");
            JSONObject eyeRightInner = faceLandmarks.getJSONObject("eyeRightInner");
            JSONObject eyeRightOuter = faceLandmarks.getJSONObject("eyeRightOuter");
            JSONObject eyeLeftOuter = faceLandmarks.getJSONObject("eyeLeftOuter");
            JSONObject pupilLeft = faceLandmarks.getJSONObject("pupilLeft");
            JSONObject pupilRight = faceLandmarks.getJSONObject("pupilRight");
            JSONObject mouthLeft = faceLandmarks.getJSONObject("mouthLeft");
            JSONObject mouthRight = faceLandmarks.getJSONObject("mouthRight");

            faceCoordinates.noseTipX = noseTip.getDouble("x");
            faceCoordinates.noseTipY = noseTip.getDouble("y");
            faceCoordinates.eyeRightInnerX = eyeRightInner.getDouble("x");
            faceCoordinates.eyeRightOuterX = eyeRightOuter.getDouble("x");
            faceCoordinates.eyeRightOuterY = eyeRightOuter.getDouble("y");
            faceCoordinates.eyeLeftOuterX = eyeLeftOuter.getDouble("x");
            faceCoordinates.eyeLeftOuterY = eyeLeftOuter.getDouble("y");
            faceCoordinates.pupilLeftX = pupilLeft.getDouble("x");
            faceCoordinates.pupilLeftY = pupilLeft.getDouble("y");
            faceCoordinates.pupilRightX = pupilRight.getDouble("x");
            faceCoordinates.mouthLeftX = mouthLeft.getDouble("x");
            faceCoordinates.mouthLeftY = mouthLeft.getDouble("y");
            faceCoordinates.mouthRightX = mouthRight.getDouble("x");
        }catch(Exception e){

        }
    }
}
