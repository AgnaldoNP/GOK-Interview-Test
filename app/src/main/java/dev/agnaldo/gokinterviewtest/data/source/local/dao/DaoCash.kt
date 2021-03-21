package dev.agnaldo.gokinterviewtest.data.source.local.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import dev.agnaldo.gokinterviewtest.data.source.local.entity.CashDBEntity

@Dao
abstract class DaoCash : DaoBase<CashDBEntity> {

    @Query("SELECT * FROM '${CashDBEntity.TABLE_NAME}'")
    abstract fun getAll(): List<CashDBEntity>

    @Query("DELETE FROM '${CashDBEntity.TABLE_NAME}'")
    suspend abstract fun deleteAll()

    @Query(
        """
            SELECT * FROM '${CashDBEntity.TABLE_NAME}'
            WHERE ${CashDBEntity.ID} = :id
        """
    )
    abstract fun getById(id: Long): LiveData<CashDBEntity?>

    @Query(
        """
            SELECT COUNT(*) FROM '${CashDBEntity.TABLE_NAME}'
            
        """
    )
    abstract fun countCashData(): Int

    @Query(
        """
            SELECT * FROM '${CashDBEntity.TABLE_NAME}'
            WHERE ${CashDBEntity.ID} = 1
        """
    )
    abstract fun getFirst(): LiveData<CashDBEntity?>

}
