package com.example.roomcoroutinelivedata

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
//        val binding: ActivityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)
////        binding.recyclerView.layoutManager =
////            LinearLayoutManager(this@MainActivity, LinearLayoutManager.VERTICAL, false)
//        binding.recyclerView.layoutManager = GridLayoutManager(this@MainActivity, 2, GridLayoutManager.VERTICAL, false)
//
//        var adapter = UserListAdapter()
//        binding.recyclerView.adapter = adapter
//
//        val userDBViewModel: UserDBViewModel by viewModels(){UserDBViewModelFactory(this.application)}
//
//        userDBViewModel.populateData()
//
//        userDBViewModel.fetchUsersFromDB().observe(this, Observer <List<User>>{ users ->
//            users?.let {
//                Log.d("Activity", "users now ${users.size}")
//                //rv.adapter = UserListAdapter(users) }
//                //adapter.swapData(users)
//                adapter.submitList(users)
//
//            }        })
    }

}