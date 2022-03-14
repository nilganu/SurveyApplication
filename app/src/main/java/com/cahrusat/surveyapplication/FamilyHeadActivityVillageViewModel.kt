package com.cahrusat.surveyapplication

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import androidx.room.Update
import com.cahrusat.surveyapplication.database.AppDatabase
import com.cahrusat.surveyapplication.database.VillageDao
import com.cahrusat.surveyapplication.database.VillageEntity
import com.cahrusat.surveyapplication.database.VillageRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class FamilyHeadActivityVillageViewModel(application:Application)
    :AndroidViewModel(application)
{
        val allVillages:LiveData<List<VillageEntity>>
        val repository:VillageRepository

        init {
            val villageDao=AppDatabase.getAppDatabaseInstance(application).getVillageDao()
            repository= VillageRepository(villageDao)
            allVillages=repository.allVillages
        }
        fun deleteVillage(villageEntity: VillageEntity)=viewModelScope.launch(Dispatchers.IO)
        {
            repository.delete(villageEntity)
        }
    fun getRecordObserver():LiveData<List<VillageEntity>>
    {
        return  allVillages;
    }
        fun UpdateVillage(villageEntity: VillageEntity)=viewModelScope.launch(Dispatchers.IO)
        {
            repository.update(villageEntity)
        }
        fun AddVillage(villageEntity: VillageEntity)=viewModelScope.launch(Dispatchers.IO)
        {
        repository.insert(villageEntity)
        }



}