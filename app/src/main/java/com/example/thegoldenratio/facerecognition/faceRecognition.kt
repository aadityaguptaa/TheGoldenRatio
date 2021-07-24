package com.example.thegoldenratio.facerecognition;

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.*
import android.graphics.drawable.BitmapDrawable
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.util.Log
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.content.FileProvider
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.example.thegoldenratio.R
import com.example.thegoldenratio.databinding.ActivityFaceRecognitionBinding
import com.google.common.io.ByteStreams
import com.squareup.okhttp.MediaType
import com.squareup.okhttp.RequestBody
import retrofit2.HttpException
import java.io.ByteArrayInputStream
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.IOException

class faceRecognition: AppCompatActivity() {
    private lateinit var viewModel: FaceRecViewModel
    private lateinit var binding: ActivityFaceRecognitionBinding
    private var index = 1
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        permissions
        binding = DataBindingUtil.setContentView(this, R.layout.activity_face_recognition)
        viewModel = ViewModelProvider(this).get(FaceRecViewModel::class.java)
        binding.faceRecCameraFloatButton.setOnClickListener { launchCameraIntent() }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, intent: Intent?) {
        super.onActivityResult(requestCode, resultCode, intent)
        if (resultCode == RESULT_OK && requestCode == 1) {
            val bitmap = BitmapFactory.decodeFile(viewModel.currentPhotoPath)
            if (bitmap != null) {
                binding.imageView.visibility = View.VISIBLE
                viewModel.mSelectedImage = Bitmap.createScaledBitmap(
                    bitmap,
                    binding.imageView.width,
                    binding.imageView.height,
                    true
                )
                binding.imageView.setImageBitmap(viewModel.mSelectedImage)
                viewModel.analyzeImage()
                /*lifecycleScope.launchWhenCreated {
                    val response = try {
                        val outputStream = ByteArrayOutputStream()
                        val myBitmap: Bitmap = viewModel.mSelectedImage
                        myBitmap.compress(Bitmap.CompressFormat.JPEG, 100, outputStream)
                        val inputStream = ByteArrayInputStream(outputStream.toByteArray())

                        val localImageBytes: ByteArray = ByteStreams.toByteArray(inputStream)
                        val mediaType = MediaType.parse("application/octet-stream")
                        val body = RequestBody.create(
                            mediaType,
                            localImageBytes
                        )
                        RetrofitInstance.api.getFaceInfo(
                        body)
                    }catch (e: IOException) {
                        Log.e("asdf", e.toString())
                        return@launchWhenCreated
                    }catch (e: HttpException) {
                        Log.e("asdf", e.toString())
                        return@launchWhenCreated
                    }*/
                    /*    Log.i("asdf", response.toString())

                }*/
                try {
                    viewModel.analyzeJson(viewModel.resultString)
                    binding.forwardArrow.setOnClickListener {
                        index++
                        if (index > 5) index = 1
                        changeRectangle(index)
                    }
                    binding.backWardArrow.setOnClickListener {
                        index--
                        if (index < 1) index = 5
                        changeRectangle(index)
                    }
                } catch (e: Exception) {
                    Log.i("result", e.toString())
                }
            }
        }
    }

    private val permissions: Unit
        get() {
            if (ContextCompat.checkSelfPermission(
                    this@faceRecognition,
                    Manifest.permission.CAMERA
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                ActivityCompat.requestPermissions(
                    this@faceRecognition, arrayOf(
                        Manifest.permission.CAMERA
                    ), 100
                )
            }
        }

    private fun launchCameraIntent() {
        val fileName = "photo"
        val storageDirectory = getExternalFilesDir(Environment.DIRECTORY_PICTURES)
        try {
            val imageFile = File.createTempFile(fileName, ".jpg", storageDirectory)
            viewModel.currentPhotoPath = imageFile.absolutePath
            val imageUri = FileProvider.getUriForFile(
                this@faceRecognition,
                "com.example.thegoldenratio.fileprovider",
                imageFile
            )
            val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri)
            startActivityForResult(intent, 1)
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }

    private fun changeRectangle(position: Int) {
        var text: String
        val faceRecTextView = findViewById<TextView>(R.id.faceRecTextView)
        val myImageView = binding.imageView
        val myBitmap = viewModel.mSelectedImage
        val myRectPaint: Paint = Paint()
        myRectPaint.color = Color.GREEN
        myRectPaint.style = Paint.Style.STROKE
        myRectPaint.strokeWidth = STROKE_WIDTH
        val scale = resources.displayMetrics.density
        val tempBitmap = Bitmap.createBitmap(myBitmap.width, myBitmap.height, Bitmap.Config.RGB_565)
        val tempCanvas = Canvas(tempBitmap)
        tempCanvas.drawBitmap(myBitmap, 0f, 0f, null)
        when (position) {
            1 -> {
                faceRecTextView.text = viewModel.typeOfGoldenRatioText[0]
                tempCanvas.drawRoundRect(
                    RectF(
                        viewModel.faceCoordinates.noseTipX.toFloat(),
                        viewModel.faceCoordinates.eyeRightOuterY.toFloat(),
                        viewModel.faceCoordinates.eyeRightOuterX.toFloat(),
                        viewModel.faceCoordinates.noseTipY.toFloat()
                    ), 2f, 2f, myRectPaint
                )
                tempCanvas.drawLine(
                    (viewModel.faceCoordinates.eyeRightInnerX).toFloat(),
                    (viewModel.faceCoordinates.eyeRightOuterY).toFloat(),
                    (viewModel.faceCoordinates.eyeRightInnerX).toFloat(),
                    (viewModel.faceCoordinates.noseTipY).toFloat(),
                    myRectPaint
                )
            }
            2 -> {
                //for pupils and chin rectangle
                faceRecTextView.text = viewModel.typeOfGoldenRatioText[1]
                myRectPaint.color = Color.CYAN
                val distBetweenPupils =
                    viewModel.faceCoordinates.pupilRightX - viewModel.faceCoordinates.pupilLeftX
                val rectHeight = (distBetweenPupils * 1.618).toInt()
                val smallRectHeight = distBetweenPupils / 1.618
                val smallRectY =
                    viewModel.faceCoordinates.pupilLeftY + (rectHeight - smallRectHeight)
                tempCanvas.drawRoundRect(
                    RectF(
                        viewModel.faceCoordinates.pupilLeftX.toInt().toFloat(),
                        viewModel.faceCoordinates.pupilLeftY.toInt().toFloat(),
                        viewModel.faceCoordinates.pupilRightX.toInt().toFloat(),
                        (viewModel.faceCoordinates.pupilLeftY.toInt() + rectHeight).toFloat()
                    ), 2f, 2f, myRectPaint
                )
                tempCanvas.drawLine(
                    viewModel.faceCoordinates.pupilLeftX.toFloat(),
                    smallRectY.toFloat(),
                    viewModel.faceCoordinates.pupilRightX.toFloat(),
                    smallRectY.toFloat(),
                    myRectPaint
                )
            }
            3 -> {
                //for lips rect
                faceRecTextView.text = viewModel.typeOfGoldenRatioText[2]
                val lipsRectBreadth =
                    viewModel.faceCoordinates.mouthRightX - viewModel.faceCoordinates.mouthLeftX
                val lipsRectHeight = lipsRectBreadth / 1.618
                val lipsRectY = viewModel.faceCoordinates.mouthLeftY - lipsRectHeight / 2
                val lipsRectX = viewModel.faceCoordinates.mouthLeftX
                val lipsRectXdas = viewModel.faceCoordinates.mouthRightX
                val lipsRectYdas = lipsRectY + lipsRectHeight
                val smallRectX = lipsRectX + lipsRectBreadth / 2.618
                myRectPaint.color = Color.MAGENTA
                tempCanvas.drawRoundRect(
                    RectF(
                        lipsRectX.toFloat(),
                        lipsRectY.toFloat(),
                        lipsRectXdas.toFloat(),
                        lipsRectYdas.toFloat()
                    ), 2f, 2f, myRectPaint
                )
                tempCanvas.drawLine(
                    smallRectX.toFloat(),
                    lipsRectY.toFloat(),
                    smallRectX.toFloat(),
                    lipsRectYdas.toFloat(),
                    myRectPaint
                )
            }
            4 -> {
                //for pupilNoseRect
                faceRecTextView.text = viewModel.typeOfGoldenRatioText[3]
                val pnX = viewModel.faceCoordinates.pupilLeftX
                val pnY = viewModel.faceCoordinates.pupilLeftY
                val pnRectBreadth =
                    viewModel.faceCoordinates.pupilRightX - viewModel.faceCoordinates.pupilLeftX
                val pnRectHeight = pnRectBreadth / 1.618
                val pnXdas = viewModel.faceCoordinates.pupilRightX
                val pnYdas = pnY + pnRectHeight
                val smallpnRectY = pnRectHeight / 2.618
                myRectPaint.color = Color.BLUE
                tempCanvas.drawRoundRect(
                    RectF(
                        pnX.toFloat(),
                        pnY.toFloat(),
                        pnXdas.toFloat(),
                        pnYdas.toFloat()
                    ), 2f, 2f, myRectPaint
                )
                tempCanvas.drawLine(
                    pnX.toFloat(),
                    (pnYdas - smallpnRectY).toFloat(),
                    pnXdas.toFloat(),
                    (pnYdas - smallpnRectY).toFloat(),
                    myRectPaint
                )
            }
            5 -> {
                //for center-inner-width
                faceRecTextView.text = viewModel.typeOfGoldenRatioText[4]
                val ciwSmallRectX = viewModel.faceCoordinates.eyeLeftOuterX
                val ciwSmallRectY = viewModel.faceCoordinates.eyeLeftOuterY
                val ciwSmallRectDista =
                    viewModel.faceCoordinates.noseTipX - viewModel.faceCoordinates.eyeLeftOuterX
                val ciwSmallRectDistb = ciwSmallRectDista / 1.618
                val ciwRectX = viewModel.faceCoordinates.eyeLeftOuterX - ciwSmallRectDistb
                val ciwRectY = viewModel.faceCoordinates.eyeLeftOuterY
                val ciwRectBreadth = viewModel.faceCoordinates.noseTipX - ciwRectX
                val ciwRectHeight = ciwRectBreadth * 1.618
                val ciwRectXdas = ciwRectX + ciwRectBreadth
                val ciwRectYdas = ciwRectY + ciwRectHeight
                myRectPaint.color = Color.RED
                tempCanvas.drawRoundRect(
                    RectF(
                        ciwRectX.toFloat(),
                        ciwRectY.toFloat(),
                        ciwRectXdas.toFloat(),
                        ciwRectYdas.toFloat()
                    ), 2f, 2f, myRectPaint
                )
                tempCanvas.drawLine(
                    ciwSmallRectX.toFloat(),
                    ciwSmallRectY.toFloat(),
                    ciwSmallRectX.toFloat(),
                    ciwSmallRectY.toFloat() + ciwRectHeight.toFloat(),
                    myRectPaint
                )
            }
        }
        myImageView.setImageDrawable(BitmapDrawable(resources, tempBitmap))
    }

    companion object {
        private const val STROKE_WIDTH = 6.0f
    }
}