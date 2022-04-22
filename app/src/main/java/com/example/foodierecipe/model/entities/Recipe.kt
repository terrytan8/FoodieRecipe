package com.example.foodierecipe.model.entities

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity (tableName = "recipes_table")
data class Recipe (
    @ColumnInfo val image:String,
    @ColumnInfo(name ="image_source") val imageSource:String,
    @ColumnInfo val title:String,
    @ColumnInfo val type:String,
    @ColumnInfo val category:String,
    @ColumnInfo val ingredients:String,
    @ColumnInfo(name = "cooking_time") val cookingTime:String,
    @ColumnInfo(name = "instructions") val directionToCook:String,
    @ColumnInfo(name="favourite_recipe") var favouriteRecipe:Boolean = false,
    @PrimaryKey(autoGenerate = true) val id:Int = 0



        ):Parcelable
