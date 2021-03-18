package dev.agnaldo.gokinterviewtest.data.source.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity
open class RoomBaseEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = ID)
    open var id: Long = 0
) : Serializable {

    companion object {
        const val ID = "id"
    }

}
