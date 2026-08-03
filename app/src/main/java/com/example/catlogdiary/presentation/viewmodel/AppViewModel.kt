package com.example.catlogdiary.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.catlogdiary.data.database.CatEntity
import com.example.catlogdiary.data.database.WeightLogEntity
import com.example.catlogdiary.data.database.LitterLogEntity
import com.example.catlogdiary.domain.repository.CatRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import java.util.UUID
import javax.inject.Inject

@HiltViewModel
class AppViewModel @Inject constructor(
    private val repository: CatRepository
) : ViewModel() {
    val cats: StateFlow<List<CatEntity>> = repository.getCats()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    private val _selectedCatId = MutableStateFlow<String>("")
    val selectedCatId: StateFlow<String> = _selectedCatId.asStateFlow()

    val weights: Flow<List<WeightLogEntity>> = _selectedCatId.flatMapLatest { catId ->
        repository.getWeights(catId)
    }

    val litterLogs: Flow<List<LitterLogEntity>> = _selectedCatId.flatMapLatest { catId ->
        repository.getLitterLogs(catId)
    }

    init {
        // Initialize default cat if database is empty
        viewModelScope.launch {
            repository.getCats().first().let { list ->
                if (list.isEmpty()) {
                    val defaultCat = CatEntity("milo-uuid", "Milo", "Tabby", 3, true)
                    repository.addCat(defaultCat)
                    _selectedCatId.value = defaultCat.id
                    repository.addWeight(defaultCat.id, 4.2)
                    repository.addWeight(defaultCat.id, 4.5)
                    repository.addWeight(defaultCat.id, 4.8)
                } else {
                    _selectedCatId.value = list.first().id
                }
            }
        }
    }

    fun addWeight(weight: Double) {
        viewModelScope.launch {
            repository.addWeight(_selectedCatId.value, weight)
        }
    }

    fun addLitter(type: String, blood: Boolean) {
        viewModelScope.launch {
            repository.addLitter(_selectedCatId.value, type, blood)
        }
    }
}
