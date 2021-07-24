package com.example.thegoldenratio.facerecognition.AzureApiJson

data class FaceInfoAzure(
    val faceId: String,
    val faceLandmarks: FaceLandmarks,
    val faceRectangle: FaceRectangle
)