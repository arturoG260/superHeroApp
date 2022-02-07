package com.example.appsuperhero.views

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.appsuperhero.databinding.ListHerosFragmentBinding
import com.example.appsuperhero.models.HeroViewModel
import com.example.appsuperhero.utils.UTBaseFragment

class ListHeros : UTBaseFragment() {
    private lateinit var binding: ListHerosFragmentBinding
    private lateinit var viewModel: HeroViewModel
    private lateinit var adapterHero: AdapterHeros

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = ListHerosFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
        adapterHero = AdapterHeros(arrayListOf()) {
            val dialog = HeroInfoDialog(it)
            dialog.show(childFragmentManager, dialog.tag)
        }.also { adapterHero = it }
        binding.rvHeros.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = adapterHero
        }
        viewModel.heroeMutableList.observe(viewLifecycleOwner, {
            adapterHero.heroes = it
            adapterHero.notifyDataSetChanged()
        })
    }

    private fun init(){
        viewModel = ViewModelProvider(requireActivity()).get(HeroViewModel::class.java)
        binding.viewmodel = viewModel
        viewModel.getHeros()
    }
}