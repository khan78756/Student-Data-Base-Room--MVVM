package com.example.roomdatabase.fragment.listfragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.roomdatabase.R
import com.example.roomdatabase.viewmodel.UserViewModel
import kotlinx.android.synthetic.main.fragment_listfragment.view.*

class ListFragment : Fragment() {

    private lateinit var mUserViewModel: UserViewModel


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view= inflater.inflate(R.layout.fragment_listfragment, container, false)


        //Setting yp recyclerView
        val adapter= ListAdapter()
        val recyclarView= view.rvShow
        recyclarView.adapter=adapter
        recyclarView.layoutManager= LinearLayoutManager(requireContext())


        //USER VIEW MODEL
        mUserViewModel=ViewModelProvider(this).get(UserViewModel::class.java)
        mUserViewModel.readAllData.observe(viewLifecycleOwner,Observer{
            adapter.setData(it)
        })






        view.fbMove.setOnClickListener {
            findNavController().navigate(R.id.action_listfragment_to_addfragment)
        }

        return view
    }


}