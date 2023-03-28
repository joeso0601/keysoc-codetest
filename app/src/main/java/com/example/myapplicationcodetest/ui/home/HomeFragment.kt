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
    var adapter: GalleryAdapter? = null


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
        gallery.layoutManager = GridLayoutManager(context,3)
        viewModel.galleryList.observe(viewLifecycleOwner) {
//            textView.text = it
            adapter = GalleryAdapter(it!!,requireContext())
            gallery.adapter = adapter
        }
        viewModel.getGallery()

        binding.btnShowBookMark.setOnClickListener{
            if(adapter?.toggleBookMark() == true){
                binding.btnShowBookMark.setText("SHOW ALL")
            }else{
                binding.btnShowBookMark.setText("SHOW BOOKMARK")
            }
        }
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}