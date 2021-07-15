package com.example.tarabuttest.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.example.tarabuttest.network.ApiService
import com.example.tarabuttest.App
import com.example.tarabuttest.db.AppDatabase
import com.example.tarabuttest.model.Match
import com.example.tarabuttest.util.Resource
import kotlinx.coroutines.Dispatchers

class MatchViewModel(
    val apiService: ApiService = App.api!!,
    val appDatabase: AppDatabase = AppDatabase.getInstance()
): ViewModel(){
    fun getMatch1() = liveData(Dispatchers.IO) {
        emit(Resource.loading(data = null))
        try {
            emit(Resource.success(data = apiService.getMatch1()))
        } catch (exception: Exception) {
            Log.e("Error",exception.toString());
            emit(Resource.error(data = null, message = exception.message ?: "Error Occurred!"))
        }
    }

    fun getMatch2() = liveData(Dispatchers.IO) {
        emit(Resource.loading(data = null))
        try {
            emit(Resource.success(data = apiService.getMatch2()))
        } catch (exception: Exception) {
            Log.e("Error",exception.toString());
            emit(Resource.error(data = null, message = exception.message ?: "Error Occurred!"))
        }
    }

    fun insertMatch(i: Match) {
        appDatabase.getMatchDao().insertMatch(i)

    }

    fun getMatchDetails(matchId: Int): Match {
       return appDatabase.getMatchDao().getMatch(matchId)
    }

    fun deleteMatch() {
        appDatabase.getMatchDao().deleteMatch()
    }

}
