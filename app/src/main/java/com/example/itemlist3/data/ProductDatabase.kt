package com.example.itemlist3.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.itemlist3.dao.ProductDao
import com.example.itemlist3.entity.ProductEntity

@Database(
    entities = [ProductEntity::class],
    version = 1
)
abstract class ProductDatabase : RoomDatabase() {

    abstract fun productDao(): ProductDao
}