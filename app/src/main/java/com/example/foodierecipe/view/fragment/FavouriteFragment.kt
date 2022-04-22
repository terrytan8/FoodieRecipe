package com.example.foodierecipe.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.foodierecipe.databinding.FragmentFavouriteBinding
import com.example.foodierecipe.model.entities.Recipe
import com.example.foodierecipe.view.activities.MainActivity
import com.example.foodierecipe.view.adapter.RecipeAdapter
import com.example.foodierecipe.viewmodel.RecipeViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class FavouriteFragment : Fragment() {


    private  val viewModel: RecipeViewModel by viewModels()
    private var _binding: FragmentFavouriteBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentFavouriteBinding.inflate(inflater, container, false)
        val root: View = binding.root
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.favouriteRecipe.observe(viewLifecycleOwner) { dishes ->
            dishes.let {

                binding.rvFavoriteDishesList.layoutManager =
                    GridLayoutManager(requireActivity(), 2)

                val adapter = RecipeAdapter(this)
                binding!!.rvFavoriteDishesList.adapter = adapter


                if (it.isNotEmpty()) {
                    binding!!.rvFavoriteDishesList.visibility = View.VISIBLE
                    binding!!.tvNoFavouriteDishesAvailable.visibility = View.GONE
                    adapter.dishesList(it)

                } else {
                    binding!!.rvFavoriteDishesList.visibility = View.GONE
                    binding!!.tvNoFavouriteDishesAvailable.visibility = View.VISIBLE
                }
            }
        }
    }

    fun dishDetails(recipe: Recipe){
        findNavController().navigate(
            FavouriteFragmentDirections.
            actionNavigationFavoriteToRecipeDetailsFragment(recipe))

        if(requireActivity() is MainActivity){
            (activity as MainActivity?)?.hideBottomNavigationView()
        }
    }
    override fun onResume() {
        super.onResume()
        if(requireActivity() is MainActivity){
            (activity as MainActivity?)?.showBottomNavigationView()
        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}