package dev.agnaldo.gokinterviewtest.data.source.local.dao

import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import dev.agnaldo.gokinterviewtest.data.source.local.entity.RoomBaseEntity

interface DaoBase<T : RoomBaseEntity> {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(vararg obj: T)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(T: List<T>?)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(obj: T)

    @Delete
    suspend fun delete(vararg objs: T)

    @Delete
    suspend fun delete(T: List<T>?)

    @Delete
    suspend fun delete(obj: T)

}
