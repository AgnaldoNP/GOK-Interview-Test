package dev.agnaldo.gokinterviewtest.data.source.local.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import dev.agnaldo.gokinterviewtest.data.source.local.dao.DaoCash
import dev.agnaldo.gokinterviewtest.data.source.local.dao.DaoProduct
import dev.agnaldo.gokinterviewtest.data.source.local.dao.DaoSpotlight
import dev.agnaldo.gokinterviewtest.data.source.local.entity.CashDBEntity
import dev.agnaldo.gokinterviewtest.data.source.local.entity.ProductDBEntity
import dev.agnaldo.gokinterviewtest.data.source.local.entity.SpotlightDBEntity

@Database(
    entities = [
        SpotlightDBEntity::class,
        ProductDBEntity::class,
        CashDBEntity::class
    ], version = 1
)
@TypeConverters(Converters::class)
abstract class MyDatabase : RoomDatabase() {

    abstract fun daoSpotlight(): DaoSpotlight
    abstract fun daoProduct(): DaoProduct
    abstract fun daoCash(): DaoCash

    companion object {

        private val MIGRATION_1_2 = object : Migration(1, 2) {
            override fun migrate(database: SupportSQLiteDatabase) {
                // TODO implement migration when need
            }
        }


        private var INSTANCE: MyDatabase? = null
        fun destroyInstance() {
            INSTANCE = null
        }

        fun getInstance(context: Context): MyDatabase {
            synchronized(MyDatabase::class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(
                        context.applicationContext, MyDatabase::class.java, "mydatabase.db"
                    ).build()
//                        .addMigrations(
//                            MIGRATION_1_2,
//                        ).build()

                }
                return INSTANCE!!
            }
        }
    }


}
