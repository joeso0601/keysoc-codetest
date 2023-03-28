package com.example.myapplicationcodetest.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.support.v4.app.Fragment
import android.arch.lifecycle.ViewModelProvider
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import com.example.myapplicationcodetest.data.model.GalleryModel
import com.example.myapplicationcodetest.data.remote.ITunesService
import com.example.myapplicationcodetest.data.repository.iTunesRepository
import com.example.myapplicationcodetest.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    lateinit var viewModel: HomeViewModel
    private val itunesService = ITunesService.getInstance()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewModel =
            ViewModelProvider(this, HomeViewModelFactory(iTunesRepository(itunesService))).get(
                HomeViewModel::class.java
            )
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val gallery: RecyclerView = binding.galleryList
        gallery.layoutManager = GridLayoutManager(context,2)
        viewModel.galleryList.observe(viewLifecycleOwner) {
//            textView.text = it
            gallery.adapter = GalleryAdapter(it!!,requireContext())
        }
        viewModel.getGallery()
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}