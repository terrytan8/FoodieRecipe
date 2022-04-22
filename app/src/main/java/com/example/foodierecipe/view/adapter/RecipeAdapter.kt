package com.example.foodierecipe.view.adapter

import android.content.Intent
import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.PopupMenu
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.foodierecipe.R
import com.example.foodierecipe.databinding.ItemRecipeLayoutBinding
import com.example.foodierecipe.model.entities.Recipe
import com.example.foodierecipe.other.Constants
import com.example.foodierecipe.view.activities.AddUpdateRecipeActivity
import com.example.foodierecipe.view.fragment.FavouriteFragment
import com.example.foodierecipe.view.fragment.HomeFragment
import com.example.foodierecipe.viewmodel.HomeViewModel
import java.lang.Exception

class RecipeAdapter(private val fragment: Fragment): RecyclerView.Adapter<RecipeAdapter.ViewHolder>() {

    private var recipes:List<Recipe> = listOf()
    class ViewHolder(view:ItemRecipeLayoutBinding):RecyclerView.ViewHolder(view.root){

        val ivDishImage = view.ivDishImage
        val tvTitle = view.tvRecipeTitle
        //EDIT DELETE
        val ibMore = view.ibMore
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding:ItemRecipeLayoutBinding = ItemRecipeLayoutBinding.inflate(
            LayoutInflater.from(fragment.context),parent,false
        )
        return ViewHolder(binding)
    }

    @RequiresApi(Build.VERSION_CODES.Q)
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val recipe = recipes[position]

        Glide.with(fragment)
            .load(recipe.image)
            .into(holder.ivDishImage)

        holder.tvTitle.text = recipe.title

        holder.itemView.setOnClickListener{
            if(fragment is HomeFragment){
                fragment.dishDetails(recipe)
            }
            if(fragment is FavouriteFragment){
                fragment.dishDetails(recipe)
            }
        }
        holder.ibMore.setOnClickListener{
            val popup = PopupMenu(fragment.context,holder.ibMore)
            popup.menuInflater.inflate(R.menu.menu_adapter,popup.menu)

            popup.setOnMenuItemClickListener {
                if(it.itemId== R.id.action_edit_dish){
                    //FROM POP UP TO ACTIVITY
                    val intent = Intent(fragment.requireActivity(),AddUpdateRecipeActivity::class.java)
                    intent.putExtra(Constants.EXTRA_DISH_DETAILS,recipe)
                    fragment.requireActivity().startActivity(intent)

                }else if(it.itemId == R.id.action_delete_dish){
                    if(fragment is HomeFragment){
                        fragment.deleteDish(recipe)
                    }
                }
                true
            }
            //SHOW POP UP ICON
            try{
                val showIcon = PopupMenu::class.java.getDeclaredField("mPopup")
                showIcon.isAccessible = true
                val menu = showIcon.get(popup)
                menu.javaClass
                    .getDeclaredMethod("setForceShowIcon",Boolean::class.java)
                    .invoke(menu,true)

            }catch (e: Exception){
                e.printStackTrace()
            }
            popup.show()
        }
        if(fragment is HomeFragment){
            holder.ibMore.visibility = View.VISIBLE
        }else if (fragment is FavouriteFragment){
            holder.ibMore.visibility = View.GONE
        }
    }

    override fun getItemCount(): Int {
       return recipes.size
    }

    fun dishesList(list:List<Recipe>){
        recipes = list
        notifyDataSetChanged()
    }
}