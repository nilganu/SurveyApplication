package com.cahrusat.surveyapplication.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "village_detail")
class VillageEntity(
    @ColumnInfo(name = "village_name")val villageName:String,
    @ColumnInfo(name = "village_desc")val villageDesc:String,
    @ColumnInfo(name = "timestamp")val timeStamp:String,
)
{
    @PrimaryKey(autoGenerate = true)@ColumnInfo(name="id")
    var id:Int=0
}