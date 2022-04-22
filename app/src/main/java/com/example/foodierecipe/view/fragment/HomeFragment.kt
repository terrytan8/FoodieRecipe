package com.example.foodierecipe.view.fragment

import android.app.AlertDialog
import android.app.Dialog
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.GridLayout
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.foodierecipe.R
import com.example.foodierecipe.databinding.DialogCustomListBinding
import com.example.foodierecipe.databinding.FragmentHomeBinding
import com.example.foodierecipe.model.entities.Recipe
import com.example.foodierecipe.other.Constants
import com.example.foodierecipe.view.activities.AddUpdateRecipeActivity
import com.example.foodierecipe.view.activities.MainActivity
import com.example.foodierecipe.view.adapter.CustomListAdapter
import com.example.foodierecipe.view.adapter.RecipeAdapter
import com.example.foodierecipe.viewmodel.HomeViewModel
import com.example.foodierecipe.viewmodel.RecipeViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private lateinit var homeViewModel: HomeViewModel
    private var _binding: FragmentHomeBinding? = null
    private lateinit var mCustomListDialog: Dialog
    private lateinit var mRecipeAdapter:RecipeAdapter
    private val binding get() = _binding!!
    private val viewModel : RecipeViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.rvDishesList.layoutManager = GridLayoutManager(requireActivity(),2)

        val recipeAdapter = RecipeAdapter(this)
        mRecipeAdapter = recipeAdapter
        binding.rvDishesList.adapter = recipeAdapter

        viewModel.allDishList.observe(viewLifecycleOwner){
            dishes ->
            dishes.let {
                if(it.isNotEmpty()){
                    binding.rvDishesList.visibility = View.VISIBLE
                    binding.tvNoDishesAddedYet.visibility = View.GONE
                    mRecipeAdapter.dishesList(it)
                }
                else{
                    binding.rvDishesList.visibility= View.GONE
                    binding.tvNoDishesAddedYet.visibility = View.VISIBLE
                }
            }
        }

    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    //When click item
    fun dishDetails(recipe: Recipe){
        findNavController().navigate(HomeFragmentDirections.actionNavigationHomeToRecipeDetailsFragment(recipe))

        if(requireActivity() is MainActivity){
            (activity as MainActivity?)?.hideBottomNavigationView()
        }


    }

    fun deleteDish(recipe: Recipe){
        val builder = AlertDialog.Builder(requireActivity())
        builder.setTitle(resources.getString(R.string.title_delete_recipe))
        builder.setMessage(resources.getString(R.string.msg_delete_recipe_dialog,recipe.title))
        builder.setIcon(R.drawable.ic_delete)
        builder.setPositiveButton(resources.getString(R.string.lbl_yes)){
                dialogInterface,_->
            viewModel.delete(recipe)
            dialogInterface.dismiss()
            Toast.makeText(requireActivity(),"Dish detail delete successfully",
                Toast.LENGTH_SHORT).show()
        }
        builder.setNegativeButton(resources.getString(R.string.lbl_no)){
                dialogInterface,_->
            dialogInterface.dismiss()
        }
        val alertDialog: AlertDialog = builder.create()
        alertDialog.setCancelable(false)
        alertDialog.show()
    }

    private fun filterDishesListDialog(){
        mCustomListDialog= Dialog(requireActivity())
        val binding:DialogCustomListBinding= DialogCustomListBinding.inflate(layoutInflater)

        mCustomListDialog.setContentView(binding.root)
        binding.tvTitle.text = resources.getString(R.string.title_select_item_to_filter)

        val dishTypes = Constants.dishTypes()
        dishTypes.add(0,Constants.ALL_ITEMS)
        binding.rvList.layoutManager = LinearLayoutManager(requireActivity())
        val adapter = CustomListAdapter(requireActivity(),this,dishTypes,Constants.FILTER_SELECTION)
        binding.rvList.adapter = adapter
        mCustomListDialog.show()
    }
    override fun onResume() {
        super.onResume()
        if(requireActivity() is MainActivity){
            (activity as MainActivity?)?.showBottomNavigationView()
        }
    }
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_all_dishes,menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.action_add_recipe->{
                startActivity(Intent(requireActivity(),AddUpdateRecipeActivity::class.java))
                return true
            }
            R.id.action_filter_recipe->{
                filterDishesListDialog()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    fun filterSelection(filterItemSelection:String){
        mCustomListDialog.dismiss()
        Log.i("Tag",filterItemSelection)

        if(filterItemSelection==Constants.ALL_ITEMS){
            viewModel.allDishList.observe(viewLifecycleOwner){
                    dishes->
                dishes.let {
                    if(it.isNotEmpty())
                    {
                       binding.rvDishesList.visibility= View.VISIBLE
                        binding.tvNoDishesAddedYet.visibility = View.GONE
                        mRecipeAdapter.dishesList(it)

                    }else{
                        binding.rvDishesList.visibility= View.GONE
                        binding.tvNoDishesAddedYet.visibility = View.VISIBLE
                    }
                }

            }
        }else{
            viewModel.getFilteredList(filterItemSelection).observe(viewLifecycleOwner){
                    dishes->
                dishes.let {
                    if(it.isNotEmpty()){
                       binding.rvDishesList.visibility =View.VISIBLE
                        binding.tvNoDishesAddedYet.visibility= View.GONE
                        mRecipeAdapter.dishesList(it)
                    }else{
                        binding.rvDishesList.visibility =View.GONE
                        binding.tvNoDishesAddedYet.visibility= View.VISIBLE
                    }
                }
            }
        }
    }
}