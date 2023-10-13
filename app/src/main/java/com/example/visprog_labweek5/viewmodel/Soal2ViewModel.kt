package com.example.visprog_labweek5.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.visprog_labweek5.UIState.Subject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class Soal2ViewModel: ViewModel(){
    private val _uiState = MutableStateFlow<List<Subject>>((emptyList()))
    val uiState: StateFlow<List<Subject>> = _uiState.asStateFlow()

    fun addCourse(name: String, score: Double, sks: Int) {
        val newSubject = Subject(name = name, score = score, sks = sks)
        val subjects = _uiState.value.toMutableList()
        subjects.add(newSubject)
        _uiState.value = subjects
    }

    fun removeCourse(subject: Subject){
        val subjects = _uiState.value.toMutableList()
        subjects.remove(subject)
        _uiState.value = subjects
    }

    fun totalSKS(): Int {
        return _uiState.value.sumBy { it.sks }
    }

    fun IPK(): Double {
        return if (_uiState.value.isNotEmpty()) {
            _uiState.value.sumByDouble { it.sks.toDouble() * it.score } / _uiState.value.sumBy { it.sks }
        } else {
            0.0
        }
    }
}