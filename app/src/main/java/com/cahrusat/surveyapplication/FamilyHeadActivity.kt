package com.cahrusat.surveyapplication

import android.app.Dialog
import android.os.Bundle
import android.text.TextUtils
import android.view.Gravity
import android.view.WindowManager
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.cahrusat.surveyapplication.database.VillageEntity
import kotlinx.android.synthetic.main.activity_family_head.*
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.add_village_layout.*
import java.text.SimpleDateFormat
import java.util.*


class FamilyHeadActivity : AppCompatActivity() {
    lateinit var viewModel: FamilyHeadActivityVillageViewModel
    lateinit var adapter1:ArrayAdapter<String>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_family_head)
        viewModel=ViewModelProvider(this).get(FamilyHeadActivityVillageViewModel::class.java)

        imgAddVillage.setOnClickListener {
            addVillageDialog()
        }

        var arr:ArrayList<String> = ArrayList()
        viewModel.getRecordObserver().observe(this,object:Observer<List<VillageEntity>>{
            override fun onChanged(t: List<VillageEntity>?) {
                t?.forEach{
                    arr.add(it.villageName)
                }
            }
        })
        fillVillageData(arr)
    }
    fun fillVillageData(arr:java.util.ArrayList<String>)
    {


       adapter1=ArrayAdapter(this,android.R.layout.simple_spinner_dropdown_item,arr)
        edtVillageName.setAdapter(adapter1)

    }
    fun addVillageDialog()
    {
        var dialog=Dialog(this)
        dialog.setCancelable(false)
        dialog.setContentView(R.layout.add_village_layout)
        val lp = WindowManager.LayoutParams()
        lp.copyFrom(dialog.window!!.attributes)
        lp.width = WindowManager.LayoutParams.MATCH_PARENT
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT
        lp.gravity = Gravity.CENTER

        dialog.window!!.attributes = lp
        dialog.btnAddVillageCancel.setOnClickListener {
            dialog.dismiss()
        }
        dialog.btnAddVillage.setOnClickListener {
            var name=dialog.edtAddVillageName.text.toString()
            var desc=dialog.edtAddVillageDesc.text.toString()
            if(TextUtils.isEmpty(name) || TextUtils.isEmpty(desc))
            {
                Toast.makeText(this,"Please Enter Village Data!!",Toast.LENGTH_LONG).show()
            }
            else
            {
                val sdf=SimpleDateFormat("dd MMM,yyyy - HH:mm")
                val currentdate=sdf.format(Date())
                val addvillage=VillageEntity(name,desc,currentdate)
                viewModel.AddVillage(addvillage)
                Toast.makeText(this,"Village Added Successfully!!",Toast.LENGTH_LONG).show()
                dialog.dismiss()
            }
        }
        dialog.show()
        adapter1.clear()
    }
}