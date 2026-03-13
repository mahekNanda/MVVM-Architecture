package com.example.itemlist3.model

import android.os.Message
import androidx.lifecycle.LiveData

sealed class UiState {

    object Loading : UiState()
    data class Success(val data: List<Product>): UiState()
    data class Error(val message: String): UiState()

}