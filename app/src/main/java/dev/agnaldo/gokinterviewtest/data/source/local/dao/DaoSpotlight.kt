package dev.agnaldo.gokinterviewtest.data.source.local.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import dev.agnaldo.gokinterviewtest.data.source.local.entity.SpotlightDBEntity

@Dao
abstract class DaoSpotlight : DaoBase<SpotlightDBEntity> {

    @Query("SELECT * FROM '${SpotlightDBEntity.TABLE_NAME}'")
    abstract fun getAll(): LiveData<List<SpotlightDBEntity>>

    @Query("DELETE FROM '${SpotlightDBEntity.TABLE_NAME}'")
    suspend abstract fun deleteAll()

    @Query(
        """
            SELECT * FROM '${SpotlightDBEntity.TABLE_NAME}'
            WHERE ${SpotlightDBEntity.ID} = :id
        """
    )
    suspend abstract fun getById(id: Long): SpotlightDBEntity?

}
