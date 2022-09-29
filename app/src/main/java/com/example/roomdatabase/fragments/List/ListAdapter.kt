package com.example.roomdatabase.fragments.List

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.roomdatabase.R
import com.example.roomdatabase.model.User
import kotlinx.android.synthetic.main.card.view.*

class ListAdapter: RecyclerView.Adapter<ListAdapter.MyViewHolder>() {

    private  var userList= emptyList<User>()

    //FOR BUTTON CLICK PURPOSE
    private lateinit var mListner: onItemClickListner

    interface onItemClickListner {
        fun onButtonClick(position: Int)

    }


    fun setOnItemClickListner(listner: onItemClickListner) {
        mListner = listner
    }



    class MyViewHolder(itemView:View) :RecyclerView.ViewHolder(itemView) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder((LayoutInflater.from(parent.context).inflate(R.layout.card,parent,false)))
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem=userList[position]
        holder.itemView.tvid.text=currentItem.id.toString()
        holder.itemView.tvfname.text=currentItem.firstName
        holder.itemView.tvlname.text=currentItem.secondName
        holder.itemView.tvage.text=" Age(${currentItem.age})"


        //ADDING  CLICKING ON EACH ITEM
        holder.itemView.rowtLayout.setOnClickListener {

            val action= ListFragmentDirections.actionListfragmentToUpdateFragment(currentItem)
            holder.itemView.findNavController().navigate(action)
        }
    }

    fun setData(user: List<User>){
        this.userList=user
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return  userList.size
    }

}