package com.cahrusat.surveyapplication.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "family_members")
class FamilyMemberEntity(
    @ColumnInfo(name = "member_name")val memberName:String,
    @ColumnInfo(name = "member_age")val memberAge:Int,
    @ColumnInfo(name = "member_gender")val memberGender:Char,
    @ColumnInfo(name = "member_income")val memberIncome:Double,
    @ColumnInfo(name = "member_merital_status")val memberMeritalStatus:String,
    @ColumnInfo(name = "member_education")val memberEducation:String,
    @ColumnInfo(name = "member_occupation")val memberOccupation:String,
    @ColumnInfo(name = "member_height")val memberHeight:Int,
    @ColumnInfo(name = "member_weight")val memberWeight:Double,
    @ColumnInfo(name = "member_BMI")val memberBMI:Double,
    @ColumnInfo(name = "member_waist_size")val memberWaistSize:Double,
    @ColumnInfo(name = "id")val id:Int,
    @ColumnInfo(name = "timestamp")val timeStamp:Double,
)
{
    @PrimaryKey(autoGenerate = true)@ColumnInfo(name="m_id")
    var mid:Int=0
}