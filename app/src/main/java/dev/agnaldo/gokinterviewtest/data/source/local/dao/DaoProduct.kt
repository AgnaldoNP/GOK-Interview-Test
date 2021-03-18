package dev.agnaldo.gokinterviewtest.data.source.local.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import dev.agnaldo.gokinterviewtest.data.source.local.entity.ProductDBEntity

@Dao
abstract class DaoProduct : DaoBase<ProductDBEntity> {

    @Query("SELECT * FROM '${ProductDBEntity.TABLE_NAME}'")
    abstract fun getAll(): LiveData<List<ProductDBEntity>>

    @Query("DELETE FROM '${ProductDBEntity.TABLE_NAME}'")
    suspend abstract fun deleteAll()

    @Query(
        """
            SELECT * FROM '${ProductDBEntity.TABLE_NAME}'
            WHERE ${ProductDBEntity.ID} = :id
        """
    )
    suspend abstract fun getById(id: Long): ProductDBEntity?

}
