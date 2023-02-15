package vn.thailam.goalachiever.data.goal

import kotlinx.coroutines.flow.Flow
import vn.thailam.goalachiever.data.goal.dao.GoalDao
import vn.thailam.goalachiever.data.goal.entity.Goal

interface IGoalRepository {
    suspend fun insert(goal: Goal)

    suspend fun getById(goalId: Long): Goal

    fun getAllFlow(): Flow<List<Goal>>

    suspend fun delete(goal: Goal)
}

class GoalRepository(
    private val goalDao: GoalDao
): IGoalRepository {
    override suspend fun insert(goal: Goal) {
        return goalDao.insert(goal)
    }

    override suspend fun getById(goalId: Long): Goal {
        return goalDao.getById(goalId)
    }

    override fun getAllFlow(): Flow<List<Goal>> {
        return goalDao.getAllFlow()
    }

    override suspend fun delete(goal: Goal) {
        return goalDao.delete(goal)
    }
}