package com.example.roomcoroutinelivedata.userlist

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.roomcoroutinelivedata.*
import com.example.roomcoroutinelivedata.database.User
import com.example.roomcoroutinelivedata.databinding.FragmentUserListBinding

class UserListFragment :Fragment(){
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        // Get a reference to the binding object and inflate the fragment views.
        val binding: FragmentUserListBinding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_user_list, container, false)

        val application = requireNotNull(this.activity).application

        var adapter = UserListAdapter(UserClickListener { userId -> Toast.makeText(context, "${userId}", Toast.LENGTH_LONG).show()
            findNavController().navigate(
                UserListFragmentDirections.actionUserListFragmentToUserDetailFragment(userId)
            )
            })

        binding.recyclerView.adapter = adapter
        binding.recyclerView.layoutManager = GridLayoutManager(activity, 2, GridLayoutManager.VERTICAL, false)

        // Specify the current activity as the lifecycle owner of the binding.
        // This is necessary so that the binding can observe LiveData updates.
        binding.lifecycleOwner = this

        val userDBViewModel: UserDBViewModel by viewModels(){ UserDBViewModelFactory(application) }

        userDBViewModel.populateData()


        userDBViewModel.fetchUsersFromDB().observe(this, Observer <List<User>>{ users ->
            users?.let {
                Log.d("Activity", "users now ${users.size}")
                //rv.adapter = UserListAdapter(users) }
                //adapter.swapData(users)
                adapter.submitList(users)

            }})

        return binding.root
    }
}