package com.example.foodierecipe.other

object Constants {

    const val DISH_TYPE :String = "DishType"
    const val DISH_CATEGORY:String = "DishCategory"
    const val DISH_COOKING_TIME:String = "DishCookingTime"
    const val DISH_IMAGE_SOURCE_LOCAL:String = "Local"
    const val DISH_IMAGE_SOURCE_ONLINE:String ="Online"
    const val ALL_ITEMS:String = "All"
    const val FILTER_SELECTION:String = "FilterSelection"

    const val EXTRA_DISH_DETAILS:String = "DishDetails"

    const val SHARED_PREFERENCES_NAME = "sharedPref"
    const val KEY_FIRST_TIME_TOGGLE = "KEY_FIRST_TIME_TOGGLE"
    const val DATABASE_NAME = "recipe_database"


    fun dishTypes():ArrayList<String>{
        val list = ArrayList<String>()
        list.add("Breakfast")
        list.add("Lunch")
        list.add("Snacks")
        list.add("Dinner")
        list.add("Salad")
        list.add("Side Dish")
        list.add("Dessert")
        list.add("Other")

        return list
    }

    fun dishCategory():ArrayList<String>{
        val list = ArrayList<String>()

        list.add("Pizza")
        list.add("BBQ")
        list.add("Bakery")
        list.add("Burger")
        list.add("Cafe")
        list.add("Chicken")
        list.add("Dessert")
        list.add("Drinks")
        list.add("Hot Dogs")
        list.add("Juices")
        list.add("Sandwich")
        list.add("Tea & Coffee")
        list.add("Wraps")
        list.add("Other")

        return list
    }

    fun cookingTime():ArrayList<String>{
        val list = ArrayList<String>()
        list.add("10")
        list.add("15")
        list.add("20")
        list.add("30")
        list.add("45")
        list.add("50")
        list.add("60")
        list.add("90")
        list.add("120")
        list.add("150")
        list.add("180")
        return list
    }
}