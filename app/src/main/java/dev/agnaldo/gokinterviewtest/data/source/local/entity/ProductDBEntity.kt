package dev.agnaldo.gokinterviewtest.data.source.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index

@Entity(
    tableName = ProductDBEntity.TABLE_NAME,
    indices = [
        Index(value = [ProductDBEntity.NAME], unique = true)
    ]
)
open class ProductDBEntity(
    @ColumnInfo(name = NAME)
    val name: String,
    @ColumnInfo(name = IMAGE_URL)
    val imageURL: String,
    @ColumnInfo(name = DESCRIPTION)
    val description: String,
) : RoomBaseEntity() {

    companion object {
        const val TABLE_NAME = "product"

        const val ID = RoomBaseEntity.ID
        const val NAME = "name"
        const val DESCRIPTION = "description"
        const val IMAGE_URL = "image_url"
    }
}
