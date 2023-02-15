package vn.thailam.goalachiever.data.goal.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import vn.thailam.goalachiever.data.goal.entity.Goal

@Dao
interface GoalDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(goal: Goal)

    @Query("SELECT * FROM ${Goal.TBL_NAME} WHERE ${Goal.COL_ID}=:goalId")
    suspend fun getById(goalId: Long): Goal

    @Query("SELECT * FROM ${Goal.TBL_NAME}")
    fun getAllFlow(): Flow<List<Goal>>

    @Delete
    suspend fun delete(goal: Goal)
}