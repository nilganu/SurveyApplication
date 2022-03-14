package com.cahrusat.surveyapplication.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "family_head_detail")
class HeadOftheFamilyEntity(
    @ColumnInfo(name = "village_name")val villageName:String,
    @ColumnInfo(name = "distance_to_near_hosp")val distance_to_near_hosp:Double,
    @ColumnInfo(name = "head_name")val headName:String,
    @ColumnInfo(name = "head_aadhar")val headAadhar:String,
    @ColumnInfo(name = "no_of_family_member")val no_of_family_memeber:Int,
    @ColumnInfo(name = "head_mobile_number")val head_mobile_number:String,
    @ColumnInfo(name = "timestamp")val timeStamp:String,
)
{
    @PrimaryKey(autoGenerate = true)@ColumnInfo(name="id")
    var id:Int=0
}