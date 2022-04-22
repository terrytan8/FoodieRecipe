package com.example.foodierecipe.view.adapter

import android.app.Activity
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.example.foodierecipe.databinding.ItemCustomListBinding
import com.example.foodierecipe.view.activities.AddUpdateRecipeActivity
import com.example.foodierecipe.view.fragment.HomeFragment

class CustomListAdapter (
    private val activity: Activity,

    private val fragment: Fragment?,
    private val listItems:List<String>,
    private val selection:String):
    RecyclerView.Adapter<CustomListAdapter.ViewHolder>(){

    class ViewHolder(view: ItemCustomListBinding): RecyclerView.ViewHolder(view.root){

        val tvText = view.tvText

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding :ItemCustomListBinding = ItemCustomListBinding.inflate(
            LayoutInflater.from(activity),parent,false
        )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = listItems[position]
        holder.tvText.text = item

        //CLICK BUTTON
        holder.itemView.setOnClickListener{
            if(activity is AddUpdateRecipeActivity){
                activity.selectedListItem(item,selection)
            }
            if(fragment is HomeFragment){
                fragment.filterSelection(item)
            }
        }
    }

    override fun getItemCount(): Int {
        return listItems.size
    }
}


