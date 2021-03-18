package dev.agnaldo.gokinterviewtest.data.source.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity

@Entity(
    tableName = CashDBEntity.TABLE_NAME
)
open class CashDBEntity(
    @ColumnInfo(name = TITLE)
    val title: String,
    @ColumnInfo(name = BANNER_URL)
    val bannerURL: String,
    @ColumnInfo(name = DESCRIPTION)
    val description: String,
) : RoomBaseEntity() {

    companion object {
        const val TABLE_NAME = "cash"

        const val ID = RoomBaseEntity.ID
        const val TITLE = "title"
        const val DESCRIPTION = "description"
        const val BANNER_URL = "banner_url"
    }
}
