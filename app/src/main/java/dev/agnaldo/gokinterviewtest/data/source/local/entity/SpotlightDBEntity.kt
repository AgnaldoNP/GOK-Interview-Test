package dev.agnaldo.gokinterviewtest.data.source.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index

@Entity(
    tableName = SpotlightDBEntity.TABLE_NAME,
    indices = [
        Index(value = [SpotlightDBEntity.NAME], unique = true)
    ]
)
open class SpotlightDBEntity(
    @ColumnInfo(name = NAME)
    val name: String,
    @ColumnInfo(name = BANNER_URL)
    val bannerURL: String,
    @ColumnInfo(name = DESCRIPTION)
    val description: String,
) : RoomBaseEntity() {

    companion object {
        const val TABLE_NAME = "spotlight"

        const val ID = RoomBaseEntity.ID
        const val NAME = "name"
        const val DESCRIPTION = "description"
        const val BANNER_URL = "banner_url"
    }
}
