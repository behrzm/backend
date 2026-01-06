package com.example.pro.viewmodel

import androidx.compose.runtime.*
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pro.data.model.ChallengeDto
import com.example.pro.data.remote.RetrofitClient
import kotlinx.coroutines.launch

class ChallengeViewModel : ViewModel() {

    var challenges by mutableStateOf<List<ChallengeDto>>(emptyList())
        private set

    fun loadChallenges() {
        viewModelScope.launch {
            try {
                challenges = RetrofitClient.api.getChallenges()
                android.util.Log.d("API", "Loaded: ${challenges.size}")
            } catch (e: Exception) {
                android.util.Log.e("API", "Error", e)
            }
        }
    }

}
