package com.example.roomdatabase.fragment.UpdateFragment

import android.app.AlertDialog
import android.os.Bundle
import android.text.TextUtils
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.roomdatabase.R
import com.example.roomdatabase.model.User
import com.example.roomdatabase.viewmodel.UserViewModel
import kotlinx.android.synthetic.main.fragment_update.*
import kotlinx.android.synthetic.main.fragment_update.view.*

class UpdateFragment : Fragment() {

    private val args by navArgs<UpdateFragmentArgs>()

    private lateinit var mUserViewModel: UserViewModel


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view= inflater.inflate(R.layout.fragment_update, container, false)

        mUserViewModel= ViewModelProvider(this).get(UserViewModel::class.java)

        view.etUpdatefirstname.setText(args.currentUser.firstName)
        view.etUpdatelastname.setText(args.currentUser.secondName)
        view.etUpdateage.setText(args.currentUser.age)

        view.btUpdateadd.setOnClickListener {
            updateItem()
        }


        //ADD MENU
        setHasOptionsMenu(true)

        return view
    }

    private fun updateItem() {
        val firstName=etUpdatefirstname.text.toString()
        val lastName=etUpdatelastname.text.toString()
        val age=etUpdateage.text.toString()
        if (check(firstName,lastName,age)){

            val updateUser=User(id = args.currentUser.id, firstName = firstName, secondName = lastName, age =age)

            mUserViewModel.updateUser(updateUser)

            Toast.makeText(requireContext(),"Successfully updated", Toast.LENGTH_LONG).show()
            findNavController().navigate(R.id.action_updateFragment_to_listfragment)


        }
        else{

            Toast.makeText(requireContext(),"Please fill out all entities", Toast.LENGTH_LONG).show()

        }
    }

    private fun check(firstName:String, secondName:String, age: String):Boolean{
        return !(TextUtils.isEmpty(firstName) && TextUtils.isEmpty(secondName) && TextUtils.isEmpty(age) )
    }

    @Deprecated("Deprecated in Java")
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.delete_item,menu)
    }

    @Deprecated("Deprecated in Java")
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.menu_delete){
            deleteUser()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun deleteUser() {
        val builder=AlertDialog.Builder(requireContext())
        builder.setPositiveButton("Yes"){_,_ ->
            mUserViewModel.delete(args.currentUser)
            Toast.makeText(requireContext(),"Successfully Delete",Toast.LENGTH_LONG).show()
            findNavController().navigate(R.id.action_updateFragment_to_listfragment)

        }
        builder.setNegativeButton("No"){_,_ ->}
        builder.setTitle("Delete ${args.currentUser.firstName}?")
        builder.setMessage("Are you sure you want to delete ${args.currentUser.firstName}?")
        builder.create().show()

    }


}