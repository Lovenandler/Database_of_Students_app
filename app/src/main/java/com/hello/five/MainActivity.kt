package com.hello.five

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.media.Image
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.view.View
import android.widget.Button
import android.widget.ImageView
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.content.FileProvider
import com.hello.five.databinding.ActivityMainBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import java.io.File
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val studentList = StudentList()
    private val REQUEST_CODE = 2000
    private lateinit var cameraBtn: Button
    private lateinit var photoFile: File
    lateinit var currentPhotoPath: String
    private val PICTURE_FROM_CAMERA: Int = 1
    private lateinit var imageCamera: ImageView
    val scope = CoroutineScope(Dispatchers.Default)
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.addStudentInput.visibility = View.INVISIBLE
        binding.addBtn.visibility = View.INVISIBLE
        cameraBtn = binding.buttonCameraOpen
        imageCamera = binding.cameraPicture
        /*cameraBtn.isEnabled = false
        if (ActivityCompat.checkSelfPermission(this,Manifest.permission.CAMERA)!=PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.CAMERA), 111)
        }else cameraBtn.isEnabled = true
        cameraBtn.setOnClickListener {
            var i = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            startActivityForResult(i, 101)
        }*/

        binding.addStudentBtn.setOnClickListener {
            binding.addStudentInput.visibility = View.VISIBLE
            binding.addBtn.visibility = View.VISIBLE
            binding.addBtn.setOnClickListener {
                studentList.addStudent(getUserString(binding.addStudentInput.text.toString()))
            }

        }
        binding.socialAddBtn.setOnClickListener{
            binding.socialAddedBtn.visibility = View.VISIBLE
            binding.enterNum.visibility = View.VISIBLE
            binding.socialAddedBtn.setOnClickListener{
                studentList.markStudentSoc(binding.enterNum.text.toString().toInt(),true)
            }
        }
        binding.removeStudentBtn.setOnClickListener{
            binding.removeStudentBtn2.visibility = View.VISIBLE
            binding.enterNum.visibility = View.VISIBLE
            binding.removeStudentBtn2.setOnClickListener{
                studentList.removeStudent(binding.enterNum.text.toString().toInt())
            }
        }
        binding.editStudentBtn.setOnClickListener{
            binding.editStudentBtn2.visibility = View.VISIBLE
            binding.enterNum.visibility = View.VISIBLE
            binding.addStudentInput.visibility = View.VISIBLE
            binding.editStudentBtn2.setOnClickListener{
                studentList.editStudent(binding.enterNum.text.toString().toInt(), binding.addStudentInput.text.toString())
            }
        }
        binding.showAllStudentsBtn.setOnClickListener {
            binding.StudentTxt.text = studentList.display(SocialList.ListFilter.All)
            binding.editStudentBtn2.visibility = View.INVISIBLE
            binding.removeStudentBtn2.visibility = View.INVISIBLE
            binding.addBtn.visibility = View.INVISIBLE
            binding.enterNum.visibility = View.INVISIBLE
            binding.socialAddedBtn.visibility = View.INVISIBLE
            binding.addStudentInput.visibility = View.INVISIBLE
        }
        binding.sortStudentsBtn.setOnClickListener{
            binding.StudentTxt.text = studentList.sortStudent()
        }
        binding.searchBtn.setOnClickListener{
            binding.addStudentInput.visibility = View.VISIBLE
            binding.searchBtn2.visibility = View.VISIBLE
            binding.searchBtn2.setOnClickListener{
                binding.StudentTxt.text = studentList.searchStudent(binding.addStudentInput.text.toString()).toString()
            }
        }

    }

   
    private fun getUserString(message: String): String {
        var finished = false
        var name = ""
        while (!finished) {
            if (message.isBlank()) {
                name = binding.StudentTxt.text.toString()
            } else {
                name = message
                finished = true
            }
        }
        return name
    }

}