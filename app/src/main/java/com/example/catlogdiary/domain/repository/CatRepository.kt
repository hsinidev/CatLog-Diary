package com.example.catlogdiary.domain.repository

import com.example.catlogdiary.data.database.CatEntity
import com.example.catlogdiary.data.database.WeightLogEntity
import com.example.catlogdiary.data.database.LitterLogEntity
import kotlinx.coroutines.flow.Flow

interface CatRepository {
    fun getCats(): Flow<List<CatEntity>>
    suspend fun addCat(cat: CatEntity)
    fun getWeights(catId: String): Flow<List<WeightLogEntity>>
    suspend fun addWeight(catId: String, weight: Double)
    fun getLitterLogs(catId: String): Flow<List<LitterLogEntity>>
    suspend fun addLitter(catId: String, type: String, blood: Boolean)
}
