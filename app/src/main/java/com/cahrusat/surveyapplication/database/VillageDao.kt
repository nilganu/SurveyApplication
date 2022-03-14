package com.cahrusat.surveyapplication.database

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.room.*

@Dao
interface VillageDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(villageEntity: VillageEntity)

    @Update
    suspend fun update(villageEntity: VillageEntity)

    @Delete
    suspend fun delete(villageEntity: VillageEntity)

    @Query("select * from village_detail order by village_name ASC")
    fun getAllVillage():LiveData<List<VillageEntity>>



}