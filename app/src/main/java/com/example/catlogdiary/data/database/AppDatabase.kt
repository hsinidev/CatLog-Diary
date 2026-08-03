package com.example.catlogdiary.data.database

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Entity(tableName = "cats")
data class CatEntity(
    @PrimaryKey val id: String,
    val name: String,
    val breed: String,
    val age: Int,
    val sterilised: Boolean
)

@Entity(tableName = "weight_logs")
data class WeightLogEntity(
    @PrimaryKey val id: String,
    val catId: String,
    val weightKg: Double,
    val timestamp: Long
)

@Entity(tableName = "litter_logs")
data class LitterLogEntity(
    @PrimaryKey val id: String,
    val catId: String,
    val eventType: String, // Urine, Stool
    val bloodPresence: Boolean,
    val timestamp: Long
)

@Dao
interface CatDao {
    @Query("SELECT * FROM cats")
    fun getCats(): Flow<List<CatEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCat(cat: CatEntity)

    @Query("SELECT * FROM weight_logs WHERE catId = :catId ORDER BY timestamp DESC")
    fun getWeights(catId: String): Flow<List<WeightLogEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertWeight(weight: WeightLogEntity)

    @Query("SELECT * FROM litter_logs WHERE catId = :catId ORDER BY timestamp DESC")
    fun getLitterLogs(catId: String): Flow<List<LitterLogEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertLitter(litter: LitterLogEntity)
}

@Database(entities = [CatEntity::class, WeightLogEntity::class, LitterLogEntity::class], version = 2, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun catDao(): CatDao
}
