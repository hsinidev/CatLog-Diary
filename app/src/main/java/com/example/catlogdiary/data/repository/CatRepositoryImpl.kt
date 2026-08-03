package com.example.catlogdiary.data.repository

import com.example.catlogdiary.data.database.CatDao
import com.example.catlogdiary.data.database.CatEntity
import com.example.catlogdiary.data.database.WeightLogEntity
import com.example.catlogdiary.data.database.LitterLogEntity
import com.example.catlogdiary.domain.repository.CatRepository
import kotlinx.coroutines.flow.Flow
import java.util.UUID
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CatRepositoryImpl @Inject constructor(
    private val catDao: CatDao
) : CatRepository {
    override fun getCats(): Flow<List<CatEntity>> = catDao.getCats()
    override suspend fun addCat(cat: CatEntity) = catDao.insertCat(cat)
    override fun getWeights(catId: String): Flow<List<WeightLogEntity>> = catDao.getWeights(catId)
    override suspend fun addWeight(catId: String, weight: Double) {
        catDao.insertWeight(WeightLogEntity(UUID.randomUUID().toString(), catId, weight, System.currentTimeMillis()))
    }
    override fun getLitterLogs(catId: String): Flow<List<LitterLogEntity>> = catDao.getLitterLogs(catId)
    override suspend fun addLitter(catId: String, type: String, blood: Boolean) {
        catDao.insertLitter(LitterLogEntity(UUID.randomUUID().toString(), catId, type, blood, System.currentTimeMillis()))
    }
}
