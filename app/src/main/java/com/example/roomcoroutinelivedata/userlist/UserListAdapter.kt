package com.example.roomcoroutinelivedata.userlist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.roomcoroutinelivedata.database.User
import com.example.roomcoroutinelivedata.databinding.ItemLayoutBinding

class UserClickListener(val clickListener: (userId: Int) -> Unit){
    //to define what onClick() does, provide an onClickListener callback argument in the constructor
    // of SleepNightListener and assign it to onClick().
    //Your callback that handles the click should have a useful identifier name.
    // Use clickListener as its name. The clickListener callback only needs the user.id
    // to access data from the database. Your finished UserzclickListener class should look
    // like the code below.
    fun onClick(user : User)  = clickListener(user.id)

}
class UserListAdapter(val clickListener: UserClickListener) : ListAdapter<User, UserListAdapter.ItemViewHolder>(UserListDiffCallback()) {
    class ItemViewHolder(val binding: ItemLayoutBinding): RecyclerView.ViewHolder(binding.root){
        //val textView  = item.findViewById<TextView>(R.id.textView)

//        lateinit var user: User


        fun bind(item: User, clickListener: UserClickListener){
            binding.user = item
            //You have taken a click listener from the adapter constructor, and passed it all the way to the view holder and into the binding object.
            binding.clickListener = clickListener
            //binding.textView.text = user.firstName +" "+ user.lastName
            //This call is an optimization that asks data binding to execute any pending bindings right away. It's always a good idea to call executePendingBindings() when you use binding adapters in a RecyclerView, because it can slightly speed up sizing the views.
            binding.executePendingBindings()

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        return from(parent)
    }

    private fun from(parent: ViewGroup): ItemViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)

//        val layout =
//            layoutInflater.inflate(R.layout.item_layout, parent, false)
//        return ItemViewHolder(layout)
        val binding = ItemLayoutBinding.inflate(layoutInflater,parent, false)
        return ItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val user = getItem(position)
        holder.bind(user, clickListener)
        //holder.textView.text = data[position].name

    }

//    override fun getItemCount(): Int {
//        return data.size
//    }

//    fun swapData(users:List<User>){
//        data.clear()
//        data.addAll(users)
//        notifyDataSetChanged()
//
//    }
}

class UserListDiffCallback : DiffUtil.ItemCallback<User>() {
    override fun areItemsTheSame(oldItem: User, newItem: User): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: User, newItem: User): Boolean {
        return oldItem == newItem
    }

}
