package com.asses.adian.helpers

import android.content.Context

import androidx.recyclerview.widget.RecyclerView

import android.view.LayoutInflater

import android.view.View

import android.view.ViewGroup
import android.widget.ImageView

import android.widget.TextView
import android.widget.Toast
import com.asses.adian.R
import com.asses.adian.model.Account
import com.bumptech.glide.Glide
import java.security.AccessController.getContext


class RecyclerAdapter(context: Context, listRecyclerItem: List<Any>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private val context: Context = context
    private val listRecyclerItem: List<Any> = listRecyclerItem

    inner class ItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val name: TextView = itemView.findViewById<View>(R.id.name) as TextView
        val date: TextView = itemView.findViewById<View>(R.id.date) as TextView
        val account: TextView = itemView.findViewById<View>(R.id.account) as TextView
        val image: ImageView = itemView.findViewById<View>(R.id.image) as ImageView
        val amount: TextView = itemView.findViewById<View>(R.id.amount) as TextView
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): RecyclerView.ViewHolder {
        return when (i) {
            TYPE -> {
                val layoutView: View = LayoutInflater.from(viewGroup.context).inflate(
                    R.layout.recylewview_item, viewGroup, false
                )

                layoutView.setOnClickListener {
                    Toast.makeText(
                        viewGroup.context,
                        "Recyleview Item at clicked",
                        Toast.LENGTH_SHORT
                    ).show()
                }
                ItemViewHolder(layoutView)
            }
            else -> {
                val layoutView: View = LayoutInflater.from(viewGroup.context).inflate(
                    R.layout.recylewview_item, viewGroup, false
                )
                layoutView.setOnClickListener {
                    Toast.makeText(
                        viewGroup.context,
                        "Recyleview Item clicked",
                        Toast.LENGTH_SHORT
                    ).show()
                }
                ItemViewHolder(layoutView)
            }

        }
    }

    override fun onBindViewHolder(viewHolder: RecyclerView.ViewHolder, i: Int) {
        val viewType = getItemViewType(i)
        when (viewType) {
            TYPE -> {
                val itemViewHolder = viewHolder as ItemViewHolder
                val account = listRecyclerItem[i] as Account
                itemViewHolder.name.text = account.name
                itemViewHolder.date.text = account.date
                itemViewHolder.account.text = account.account

                itemViewHolder.amount.text = account.amount
                // imagr to be hosted
//                Glide.with(context)
//                    .load(account.image)
//                    .fitCenter()
//                    .into(itemViewHolder.image)
            }
            else -> {
                val itemViewHolder = viewHolder as ItemViewHolder

                val account = listRecyclerItem[i] as Account
                itemViewHolder.name.text = account.name
                itemViewHolder.date.text = account.date
                itemViewHolder.account.text = account.account

                itemViewHolder.amount.text = account.amount
//                Glide.with(context)
//                    .load(account.image)
//                    .fitCenter()
//                    .into(itemViewHolder.image)
            }
        }



    }

    override fun getItemCount(): Int {
        return listRecyclerItem.size
    }

    companion object {
        private const val TYPE = 1
    }

}
