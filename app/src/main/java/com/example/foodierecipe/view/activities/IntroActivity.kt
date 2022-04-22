package com.example.foodierecipe.view.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.foodierecipe.R
import com.github.appintro.AppIntro
import com.github.appintro.AppIntroFragment
import android.Manifest
import android.content.Intent
import android.content.SharedPreferences
import androidx.navigation.NavOptions
import androidx.navigation.fragment.findNavController
import com.example.foodierecipe.other.Constants.KEY_FIRST_TIME_TOGGLE
import com.github.appintro.AppIntroPageTransformerType
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class IntroActivity : AppIntro() {
    @Inject
    lateinit var sharedPref: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setTransformer(
            AppIntroPageTransformerType.Parallax(
            titleParallaxFactor = 1.0,
            imageParallaxFactor = -1.0,
            descriptionParallaxFactor = 2.0
        ))
        isWizardMode = true
        isColorTransitionsEnabled = true
        addSlide(
            AppIntroFragment.createInstance(
            title = "Welcome to Foodie Recipe",
            description = "Foodie Recipe is a application that you can create your recipe note",
            imageDrawable = R.drawable.cooking,
                backgroundColorRes = R.color.primaryDarkColor,
                titleColorRes = R.color.white,
                descriptionColorRes = R.color.white,

        ))
        addSlide(
            AppIntroFragment.createInstance(
                title = "My Recipe",
                description = "Add, delete, Edit your Recipe",
                imageDrawable = R.drawable.recipe,
                backgroundColorRes = R.color.primaryColor,
                titleColorRes = R.color.white,
                descriptionColorRes = R.color.white,

                ))
        addSlide(
            AppIntroFragment.createInstance(
                title = "Favourite Recipe",
                description = "Setup your favourite Recipe",
                imageDrawable = R.drawable.favourite,
                backgroundColorRes = R.color.primaryDarkColor,
                titleColorRes = R.color.white,
                descriptionColorRes = R.color.white,

                ))
        addSlide(
            AppIntroFragment.createInstance(
                title = "Permission Request",
                description = "In order to access your storage, you must give permissions",
                imageDrawable = R.drawable.key,
                backgroundColorRes = R.color.primaryColor,
                titleColorRes = R.color.white,
                descriptionColorRes = R.color.white,

                ))
        addSlide(
            AppIntroFragment.createInstance(
                title = "Permission Request",
                description = "In order to access your camera, you must give permissions",
                imageDrawable = R.drawable.photo,
                backgroundColorRes = R.color.primaryColor,
                titleColorRes = R.color.white,
                descriptionColorRes = R.color.white,

                ))
        addSlide(
            AppIntroFragment.createInstance(
                title = "Good To Go",
                description = "Feel free to explore the rest of this app!",
                imageDrawable = R.drawable.go,
                backgroundColorRes = R.color.primaryDarkColor,
                titleColorRes = R.color.white,
                descriptionColorRes = R.color.white,

                ))
        askForPermissions(arrayOf(Manifest.permission.CAMERA),4)
        askForPermissions(arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE),5)
    }

    override fun onDonePressed(currentFragment: Fragment?) {
        super.onDonePressed(currentFragment)
        sharedPref.edit()
            .putBoolean(KEY_FIRST_TIME_TOGGLE,false)
            .apply()

        //startActivity(Intent(this, MainActivity::class.java))
        finish()
    }
}