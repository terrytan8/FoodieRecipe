package com.example.foodierecipe.model.database

import android.content.Context
import androidx.room.*
import com.example.foodierecipe.model.entities.Recipe


@Database(entities = [Recipe::class],version = 1)
abstract class RecipeRoomDatabase :RoomDatabase(){


    @TypeConverters(Converters::class)
    abstract fun getRecipeDao():RecipeDao
}