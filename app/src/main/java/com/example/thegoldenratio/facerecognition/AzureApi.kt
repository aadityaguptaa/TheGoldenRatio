package com.example.thegoldenratio.facerecognition

import com.example.thegoldenratio.facerecognition.AzureApiJson.FaceInfoAzure
import com.squareup.okhttp.OkHttpClient
import com.squareup.okhttp.RequestBody
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST


interface AzureApi {



    @POST("/face/v1.0/detect?returnFaceId=true&returnFaceLandmarks=true")
    @Headers(value=["Ocp-Apim-Subscription-Key: 1bbcc1273dc742fa9bbc495dd2259cbb",
        "Ocp-Apim-Subscription-Region: centralindia",
        "Content-type: application/json"])
     suspend fun getFaceInfo(
        @Body bytes: RequestBody
    ): Response<List<FaceInfoAzure>>
}