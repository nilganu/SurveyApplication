package com.cahrusat.surveyapplication.database

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.room.*

@Dao
interface HeadOftheFamilyDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(headOftheFamilyEntity: HeadOftheFamilyEntity)

    @Update
    suspend fun update(headOftheFamilyEntity: HeadOftheFamilyEntity)

    @Delete
    suspend fun delete(headOftheFamilyEntity: HeadOftheFamilyEntity)

    @Query("select * from family_head_detail")
    fun getAllHeadDetail():LiveData<List<HeadOftheFamilyEntity>>



}