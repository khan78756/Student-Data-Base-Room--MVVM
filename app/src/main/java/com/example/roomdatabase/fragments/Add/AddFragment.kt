package com.example.roomdatabase.fragments.Add

import android.os.Bundle
import android.text.Editable
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.roomdatabase.R
import com.example.roomdatabase.model.User
import com.example.roomdatabase.viewmodel.UserViewModel
import kotlinx.android.synthetic.main.fragment_addfragment.*
import kotlinx.android.synthetic.main.fragment_addfragment.view.*


class AddFragment   : Fragment() {

    private lateinit var mUserViewModel: UserViewModel


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        // Inflate the layout for this fragment
        val view=inflater.inflate(R.layout.fragment_addfragment, container, false)

        mUserViewModel= ViewModelProvider(this).get(UserViewModel::class.java)

        view.btadd.setOnClickListener {
            insertDataBase()
        }
        return view
    }

    private fun insertDataBase() {
        val firstName=etfirstname.text.toString()
        val lastName=etlastname.text.toString()
        val age=etage.text
        if (check(firstName,lastName,age)){
            val user= User(id = 0, firstName = firstName, secondName = lastName, age = age.toString())
            mUserViewModel.addUser(user)
            Toast.makeText(requireContext(),"Successfully added",Toast.LENGTH_LONG).show()
            findNavController().navigate(R.id.action_addfragment_to_listfragment)
        }
        else
        {
            Toast.makeText(requireContext(),"Fields are empty",Toast.LENGTH_LONG).show()
        }
    }

        private fun check(firstName:String, secondName:String, age: Editable):Boolean{
        return !(TextUtils.isEmpty(firstName) && TextUtils.isEmpty(secondName) && TextUtils.isEmpty(age) )
    }

}