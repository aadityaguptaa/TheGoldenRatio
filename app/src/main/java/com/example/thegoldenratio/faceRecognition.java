package com.example.thegoldenratio;

import androidx.appcompat.app.AppCompatActivity;

import android.net.Uri;
import android.os.Bundle;
import android.util.Log;

import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;

import java.net.URI;

public class faceRecognition extends AppCompatActivity {

    private static final String subscriptionKey = "<Subscription Key>";

    private static final String uriBase =
            "https://<My Endpoint String>.com/face/v1.0/detect";

    private static final String imageWithFaces =
            "{\"url\":\"https://upload.wikimedia.org/wikipedia/commons/c/c3/RH_Louise_Lillian_Gish.jpg\"}";

    private static final String faceAttributes =
            "age,gender,headPose,smile,facialHair,glasses,emotion,hair,makeup,occlusion,accessories,blur,exposure,noise";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_face_recognition);
        OkHttpClient client = new OkHttpClient();

        Uri.Builder builder = new Uri.Builder();
        builder.appendEncodedPath(uriBase);
        builder.appendQueryParameter("returnFaceId", "true");
        builder.appendQueryParameter("returnFaceLandmarks", "false");
        builder.appendQueryParameter("returnFaceAttributes", faceAttributes);
        Uri uri = builder.build();
        MediaType mediaType = MediaType.parse("application/json");
        Request request = new Request.Builder()
                .url(String.valueOf(uri))
                .addHeader("Content-Type", "application/json")
                .addHeader("Ocp-Apim-Subscription-Key", subscriptionKey)
                .build();



    }
}
