package com.example.thegoldenratio;

import androidx.appcompat.app.AppCompatActivity;
import androidx.loader.content.Loader;

import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;

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
            "{\"url\":\"https://upload.wikimedia.org/wikipedia/commons/3/37/Dagestani_man_and_woman.jpg\"}";

    private static final String faceAttributes =
            "age,gender,headPose,smile,facialHair,glasses,emotion,hair,makeup,occlusion,accessories,blur,exposure,noise";

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





    }
}
