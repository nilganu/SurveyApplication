package com.cahrusat.surveyapplication

import android.app.AlertDialog
import android.app.Dialog
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.view.Gravity
import android.view.WindowManager
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.cahrusat.surveyapplication.database.HeadOftheFamilyEntity
import com.cahrusat.surveyapplication.database.VillageEntity
import kotlinx.android.synthetic.main.activity_family_head.*
import kotlinx.android.synthetic.main.add_village_layout.*
import java.text.SimpleDateFormat
import java.util.*


class FamilyHeadActivity : AppCompatActivity() {
    lateinit var viewModel: FamilyHeadActivityVillageViewModel
    lateinit var viewModelFamilyHead: FamilyHeadActivityViewModel
    lateinit var adapter1:ArrayAdapter<String>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_family_head)
        viewModel=ViewModelProvider(this).get(FamilyHeadActivityVillageViewModel::class.java)
        viewModelFamilyHead=ViewModelProvider(this).get(FamilyHeadActivityViewModel::class.java)

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
        btnNext.setOnClickListener {
            var builder=AlertDialog.Builder(this)
            builder.setMessage("Are you sure want to save details?")
            builder.setPositiveButton("Yes",DialogInterface.OnClickListener { dialogInterface, i ->
                submitForm()

               // viewModelFamilyHead.AddFamilyHead()

            })
            builder.setNegativeButton("No",DialogInterface.OnClickListener { dialogInterface, i ->

            })
            var dialog=builder.create()
            dialog.setCancelable(false)
            dialog.show()
        }
        villageFocusListener()
        distanceFocusListener()
        nameFocusListener()
        aadharFocusListener()
        noofFamilyMembersFocusListener()
        headContactListener()
    }

    private fun submitForm() {
        edtVillageNameContainer.helperText=validVillage()
        edtDistanceContainer.helperText=validDistance()
        edtNameContainer.helperText=validName()
        edtAadharContainer.helperText=validAadhar()
        edtNoOfMembersContainer.helperText=validFamilyMembers()
        edtHeadContactContainer.helperText=validPhone()
        val validVillage=edtVillageNameContainer.helperText==null
        val validDistance=edtDistanceContainer.helperText==null
        val validName=edtNameContainer.helperText==null
        val validAadhar=edtAadharContainer.helperText==null
        val validMember=edtNoOfMembersContainer.helperText==null
        val validContact=edtHeadContactContainer.helperText==null
        if(validVillage && validDistance && validName && validAadhar && validMember && validContact)
        {
            val sdf=SimpleDateFormat("dd MMM,yyyy - HH:mm")
            val currentdate=sdf.format(Date())
            val familyHead =HeadOftheFamilyEntity(edtVillageName.text.toString(),edtDistance.text.toString().toDouble(),edtHeadName.text.toString(),edtAadhar.text.toString(),edtNoOfMembers.text.toString().toInt(),edtHeadContact.text.toString(),currentdate)
            viewModelFamilyHead.AddFamilyHead(familyHead)
            Toast.makeText(this,"Family Head Added Successfully!!",Toast.LENGTH_LONG).show()
            var intent=Intent(this,FamilyMembersActivity::class.java)
            startActivity(intent)
            finish()
        }
        else
        {
            Toast.makeText(this,"Please provide all data accurately!!",Toast.LENGTH_LONG).show()
        }
    }

    private fun headContactListener() {
        edtHeadContact.setOnFocusChangeListener { view, b ->
            if(!b)
            {
                edtHeadContactContainer.helperText=validPhone()
            }
        }
    }

    private fun validPhone(): String? {
        val phoneText = edtHeadContact.text.toString()
        if (phoneText.length != 10)
        {
            return "Must be 10 digits"
        }
        if (!phoneText.matches(".*[0-9].*".toRegex()))
        {
            return "Must be all digits"
        }
        return null
    }

    private fun noofFamilyMembersFocusListener() {
        edtNoOfMembers.setOnFocusChangeListener { view, b ->
            if(!b)
            {
                edtNoOfMembersContainer.helperText=validFamilyMembers()
            }
        }

    }

    private fun validFamilyMembers(): CharSequence? {
        val no=edtNoOfMembers.text.toString()
        if(TextUtils.isEmpty(no))
        {
            return "Must not be empty"
        }
        return null
    }

    private fun aadharFocusListener() {
        edtAadhar.setOnFocusChangeListener { view, b ->
            if(!b)
            {
                edtAadharContainer.helperText=validAadhar()
            }
        }
    }

    private fun validAadhar(): String? {
        val aadharText = edtAadhar.text.toString()
        if (aadharText.length != 12)
        {
            return "Must be 12 characters"
        }
        return null
    }


    private fun nameFocusListener() {
        edtHeadName.setOnFocusChangeListener { view, b ->
            if(!b)
            {
                edtNameContainer.helperText=validName()
            }
        }
    }

    private fun validName(): String? {
        val name=edtHeadName.text.toString()
        if(TextUtils.isEmpty(name))
        {
            return "Must not be empty"
        }
        return null
    }

    private fun distanceFocusListener() {
        edtDistance.setOnFocusChangeListener { view, b ->
            if(!b)
            {
                edtDistanceContainer.helperText=validDistance()
            }
        }
    }

    private fun validDistance(): CharSequence? {
        val distance=edtDistance.text.toString()
        if(TextUtils.isEmpty(distance))
        {
            return "Must not be empty"
        }
        return null
    }

    private fun villageFocusListener() {
        edtVillageName.setOnFocusChangeListener { view, b ->
            if(!b)
            {
                edtVillageNameContainer.helperText=validVillage()
            }
        }
    }

    private fun validVillage(): CharSequence? {
        val village=edtDistance.text.toString()
        if(TextUtils.isEmpty(village))
        {
            return "Must not be empty"
        }
        return null
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