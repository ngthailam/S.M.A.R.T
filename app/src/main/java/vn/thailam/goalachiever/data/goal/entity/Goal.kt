package vn.thailam.goalachiever.data.goal.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = Goal.TBL_NAME)
data class Goal(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = COL_ID)
    val id: Long = 0,

    @ColumnInfo(name = COL_NAME)
    val name: String = "",
) {
    companion object {
        const val TBL_NAME = "goal"
        const val COL_ID = "id"
        const val COL_NAME = "name"
    }
}